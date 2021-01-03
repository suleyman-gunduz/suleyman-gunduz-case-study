package com.suleymangunduz.casestudyapp.database.dao;

import com.suleymangunduz.casestudyapp.database.entity.FeedBack;
import com.suleymangunduz.casestudyapp.model.chart.BarChartPojo;
import com.suleymangunduz.casestudyapp.model.chart.PieChartPojo;
import com.suleymangunduz.casestudyapp.model.statistics.CountSumAveragePojo;
import com.suleymangunduz.casestudyapp.model.statistics.NameAveragePojo;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface FeedbackDao {

    @Query("SELECT * FROM feedback ORDER BY CASE WHEN :sortFieldId = 1 THEN rating END DESC," +
            "CASE WHEN :sortFieldId = 2 THEN browser_version END DESC," +
            "CASE WHEN :sortFieldId = 3 THEN LOWER(platform) END ASC LIMIT :pageSize OFFSET :offset")
    List<FeedBack> getAllPage(int sortFieldId, Integer pageSize, Integer offset);

    @Query("SELECT * FROM feedback WHERE labels LIKE '%' || :searchString  || '%' ORDER BY CASE WHEN :sortFieldId = 1 THEN rating END DESC," +
            "CASE WHEN :sortFieldId = 2 THEN browser_version END DESC," +
            "CASE WHEN :sortFieldId = 3 THEN LOWER(platform) END ASC LIMIT :pageSize OFFSET :offset")
    List<FeedBack> getFilteredPage(String searchString, int sortFieldId, Integer pageSize, Integer offset);

    @Query("SELECT COUNT(feedBackId) as feedbackCount, SUM(rating) as total, AVG(rating) as average FROM feedback")
    CountSumAveragePojo getCountSumAverage();

    @Query("SELECT platform as name, AVG(rating) as average FROM feedback WHERE platform LIKE :platformName")
    NameAveragePojo getPlatformNameAndAverageByPlatformName(String platformName);

    @Query("SELECT browser_name as name, AVG(rating) as average FROM feedback WHERE browser_name LIKE :browserName")
    NameAveragePojo getBrowserNameAndAverageByBrowserName(String browserName);

    @Query("SELECT COUNT(feedBackId) as value, platform as label FROM feedback WHERE platform LIKE :platformName")
    PieChartPojo getPlatformDistributionChartData(String platformName);

    @Query("SELECT COUNT(feedBackId) as value, browser_name as label FROM feedback WHERE browser_name LIKE :browserName")
    PieChartPojo getBrowserDistributionChartData(String browserName);

    @Query("SELECT COUNT(feedBackId) as value, computed_location as label FROM feedback WHERE computed_location LIKE :countryName")
    PieChartPojo getCountryDistributionChartData(String countryName);

    @Query("SELECT AVG(rating) as value FROM feedback WHERE platform LIKE :platformName")
    BarChartPojo getAverageRatingsPerPlatform(String platformName);

    @Query("SELECT AVG(rating) as value FROM feedback WHERE browser_name LIKE :browserName")
    BarChartPojo getAverageRatingsPerBrowser(String browserName);

    @Query("SELECT AVG(rating) as value FROM feedback WHERE computed_location LIKE :countryName")
    BarChartPojo getAverageRatingsPerCountry(String countryName);

    @Query("SELECT DISTINCT platform from feedback")
    List<String> getPlatforms();

    @Query("SELECT DISTINCT browser_name from feedback")
    List<String> getBrowsers();

    @Query("SELECT DISTINCT computed_location from feedback")
    List<String> getCountries();

    @Query("SELECT COUNT(feedBackId) FROM feedback")
    int getCount();

    @Insert
    void insert(List<FeedBack> feedBacks);
}
