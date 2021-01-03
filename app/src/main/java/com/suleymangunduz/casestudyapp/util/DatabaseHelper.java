package com.suleymangunduz.casestudyapp.util;

import android.content.Context;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieEntry;
import com.suleymangunduz.casestudyapp.R;
import com.suleymangunduz.casestudyapp.database.DatabaseManager;
import com.suleymangunduz.casestudyapp.database.dao.FeedbackDao;
import com.suleymangunduz.casestudyapp.database.entity.FeedBack;
import com.suleymangunduz.casestudyapp.model.chart.BarChartDto;
import com.suleymangunduz.casestudyapp.model.chart.BarChartPojo;
import com.suleymangunduz.casestudyapp.model.chart.PieChartPojo;
import com.suleymangunduz.casestudyapp.model.response.FeedBackDto;
import com.suleymangunduz.casestudyapp.model.response.ListResponse;
import com.suleymangunduz.casestudyapp.model.statistics.ApprovedAndRefusedStatistic;
import com.suleymangunduz.casestudyapp.model.statistics.CountSumAveragePojo;
import com.suleymangunduz.casestudyapp.model.statistics.NameAveragePojo;
import com.suleymangunduz.casestudyapp.util.constant.Constants;
import com.suleymangunduz.casestudyapp.util.enums.SortFieldEnum;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DatabaseHelper {

    private Context context;
    private DatabaseManager databaseManager;
    private FeedbackDao feedbackDao;

    private static DatabaseHelper instance = null;

    private DatabaseHelper(Context context) {
        this.databaseManager = DatabaseManager.getDatabaseManager(context);
        this.feedbackDao = databaseManager.feedbackDao();
        this.context = context;
    }

    public static DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context);
        }
        return instance;
    }

    public List<FeedBack> getAllFeedBacksPage(SortFieldEnum sortFieldEnum, int offset) {

        return feedbackDao.getAllPage(sortFieldEnum.getId(), Constants.PAGE_SIZE, offset);

    }

    public List<FeedBack> getFilteredPage(String searchString, SortFieldEnum sortFieldEnum, int offset) {

        return feedbackDao.getFilteredPage(searchString, sortFieldEnum.getId(), Constants.PAGE_SIZE, offset);

    }

    public void synchronizeDatabase() {

        if (feedbackDao.getCount() == 0) {

            String jsonString = JsonFileUtils.readRawResource(context, R.raw.apidemo);
            ListResponse<FeedBackDto> listResponse = JacksonUtils.readValue(jsonString, new TypeReference<ListResponse<FeedBackDto>>() {
            });

            if (listResponse != null && listResponse.getItems() != null) {
                saveFeedBacks(convert(listResponse.getItems()));
            }

        }

    }

    public void saveFeedBacks(List<FeedBack> feedBacks) {
        feedbackDao.insert(feedBacks);
    }

    private List<FeedBack> convert(List<FeedBackDto> listResponse) {
        List<FeedBack> feedBackList = new ArrayList<>();

        for (FeedBackDto feedBackDto : listResponse) {

            FeedBack feedBack = new FeedBack();

            if (feedBackDto.getBrowserDto() != null) {
                feedBack.browserName = feedBackDto.getBrowserDto().getBrowserName() != null ? feedBackDto.getBrowserDto().getBrowserName().trim() : null;
                feedBack.browserVersion = feedBackDto.getBrowserDto().getBrowserVersion();
                feedBack.platform = feedBackDto.getBrowserDto().getPlatform() != null ? feedBackDto.getBrowserDto().getPlatform().trim() : null;
            }

            if (feedBackDto.getGeoLocationDto() != null) {
                feedBack.country = feedBackDto.getGeoLocationDto().getCountry() != null ? feedBackDto.getGeoLocationDto().getCountry().trim() : null;
                feedBack.city = feedBackDto.getGeoLocationDto().getCity() != null ? feedBackDto.getGeoLocationDto().getCity().trim() : null;
                feedBack.latitude = feedBackDto.getGeoLocationDto().getLatitude();
                feedBack.longitude = feedBackDto.getGeoLocationDto().getLongitude();
            }

            feedBack.computedLocation = feedBackDto.getComputedLocation() != null ? feedBackDto.getComputedLocation().trim() : null;
            feedBack.labels = android.text.TextUtils.join(", ", feedBackDto.getLabels()).trim();
            feedBack.rating = feedBackDto.getRating();

            feedBackList.add(feedBack);

        }

        return feedBackList;

    }

    public CountSumAveragePojo getCountSumAverage() {
        return feedbackDao.getCountSumAverage();
    }

    public ApprovedAndRefusedStatistic getApprovedAndRefusedPlatform() {
        List<String> platforms = feedbackDao.getPlatforms();

        return getApprovedAndRefusedItems(platforms, true);
    }

    public ApprovedAndRefusedStatistic getApprovedAndRefusedBrowser() {
        List<String> browsers = feedbackDao.getBrowsers();

        return getApprovedAndRefusedItems(browsers, false);
    }

    private ApprovedAndRefusedStatistic getApprovedAndRefusedItems(List<String> names, boolean isPlatform) {

        List<NameAveragePojo> approveds = new ArrayList<>();
        List<NameAveragePojo> refuseds = new ArrayList<>();

        for (String name : names) {

            NameAveragePojo nameAverage;
            if (isPlatform) {
                nameAverage = feedbackDao.getPlatformNameAndAverageByPlatformName(name);
            } else {
                nameAverage = feedbackDao.getBrowserNameAndAverageByBrowserName(name);
            }

            if (approveds.isEmpty()) {
                approveds.add(nameAverage);
            } else if (nameAverage.getAverage() > approveds.get(0).getAverage()) {
                approveds.removeAll(approveds);
                approveds.add(nameAverage);
            } else if (nameAverage.getAverage() == approveds.get(0).getAverage()) {
                approveds.add(nameAverage);
            }

            if (refuseds.isEmpty()) {
                refuseds.add(nameAverage);
            } else if (nameAverage.getAverage() < refuseds.get(0).getAverage()) {
                refuseds.removeAll(refuseds);
                refuseds.add(nameAverage);
            } else if (nameAverage.getAverage() == refuseds.get(0).getAverage()) {
                refuseds.add(nameAverage);
            }

        }

        ApprovedAndRefusedStatistic approvedAndRefusedStatistic = new ApprovedAndRefusedStatistic();

        if (!approveds.isEmpty()) {
            approvedAndRefusedStatistic.setApprovedName(getNameAndRating(approveds));
        }
        if (!refuseds.isEmpty()) {
            approvedAndRefusedStatistic.setRefusedName(getNameAndRating(refuseds));
        }

        return approvedAndRefusedStatistic;
    }

    private String getNameAndRating(List<NameAveragePojo> nameAverages) {
        StringBuilder stringBuilder = new StringBuilder();
        Iterator<NameAveragePojo> iterator = nameAverages.iterator();

        while (iterator.hasNext()) {

            NameAveragePojo nameAverage = iterator.next();

            if (nameAverage.getName() != null) {
                stringBuilder.append(nameAverage.getName());
                stringBuilder.append(" - ");
            }
            stringBuilder.append(context.getResources().getString(R.string.rating));
            stringBuilder.append(": ");
            stringBuilder.append(String.format("%.2f", nameAverage.average));

            if (iterator.hasNext()) {
                stringBuilder.append(System.getProperty("line.separator"));
            }
        }

        return stringBuilder.toString();
    }

    public PieData getPlatformDistributionData(String label) {
        List<String> platforms = feedbackDao.getPlatforms();
        List<PieEntry> pieEntries = new ArrayList<>();

        for (String platform : platforms) {
            PieChartPojo pieChartPojo = feedbackDao.getPlatformDistributionChartData(platform);
            pieEntries.add(new PieEntry(pieChartPojo.getValue(), pieChartPojo.getLabel()));
        }

        return ChartUtils.generatePieData(pieEntries, label);
    }

    public PieData getBrowserDistributionData(String label) {
        List<String> browsers = feedbackDao.getBrowsers();
        List<PieEntry> pieEntries = new ArrayList<>();

        for (String browser : browsers) {
            PieChartPojo pieChartPojo = feedbackDao.getBrowserDistributionChartData(browser);
            pieEntries.add(new PieEntry(pieChartPojo.getValue(), pieChartPojo.getLabel()));
        }

        return ChartUtils.generatePieData(pieEntries, label);
    }

    public PieData getCountryDistributionData(String label) {
        List<String> countries = feedbackDao.getCountries();
        List<PieEntry> pieEntries = new ArrayList<>();

        for (String country : countries) {
            PieChartPojo pieChartPojo = feedbackDao.getCountryDistributionChartData(country);
            pieEntries.add(new PieEntry(pieChartPojo.getValue(), pieChartPojo.getLabel()));
        }

        return ChartUtils.generatePieData(pieEntries, label);
    }

    public BarChartDto getAverageRatingsPerPlatform() {
        List<String> platforms = feedbackDao.getPlatforms();
        List<BarEntry> barEntries = new ArrayList<>();

        for (int i = 0; i < platforms.size(); i++) {

            String platform = platforms.get(i);
            BarChartPojo barChartPojo = feedbackDao.getAverageRatingsPerPlatform(platform);
            barEntries.add(new BarEntry(i, barChartPojo.getValue()));
        }

        return new BarChartDto(ChartUtils.generateBarData(barEntries, context.getString(R.string.platforms)), platforms);
    }

    public BarChartDto getAverageRatingsPerBrowser() {
        List<String> browsers = feedbackDao.getBrowsers();
        List<BarEntry> barEntries = new ArrayList<>();

        for (int i = 0; i < browsers.size(); i++) {

            String browser = browsers.get(i);
            BarChartPojo barChartPojo = feedbackDao.getAverageRatingsPerBrowser(browser);
            barEntries.add(new BarEntry(i, barChartPojo.getValue()));
        }

        return new BarChartDto(ChartUtils.generateBarData(barEntries, context.getString(R.string.browsers)), browsers);
    }

    public BarChartDto getAverageRatingsPerCountry() {
        List<String> countries = feedbackDao.getCountries();
        List<BarEntry> barEntries = new ArrayList<>();

        for (int i = 0; i < countries.size(); i++) {

            String country = countries.get(i);
            BarChartPojo barChartPojo = feedbackDao.getAverageRatingsPerCountry(country);
            barEntries.add(new BarEntry(i, barChartPojo.getValue()));
        }

        return new BarChartDto(ChartUtils.generateBarData(barEntries, context.getString(R.string.countries)), countries);
    }
}
