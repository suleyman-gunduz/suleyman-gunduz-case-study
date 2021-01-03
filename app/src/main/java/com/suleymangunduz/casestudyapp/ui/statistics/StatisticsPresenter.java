package com.suleymangunduz.casestudyapp.ui.statistics;

import android.content.Context;

import com.suleymangunduz.casestudyapp.di.ActivityScoped;
import com.suleymangunduz.casestudyapp.model.statistics.ApprovedAndRefusedStatistic;
import com.suleymangunduz.casestudyapp.model.statistics.CountSumAveragePojo;
import com.suleymangunduz.casestudyapp.model.statistics.StatisticsInfo;
import com.suleymangunduz.casestudyapp.ui.base.ExtPresenter;
import com.suleymangunduz.casestudyapp.util.DatabaseHelper;

import javax.inject.Inject;

@ActivityScoped
public class StatisticsPresenter extends ExtPresenter<StatisticsContract.View, StatisticsInfo> implements StatisticsContract.Presenter {
    private Context context;

    @Inject
    StatisticsPresenter() {

    }

    @Override
    public void bind(StatisticsContract.View view, Context context) {
        this.view = view;
        this.context = context;
        loadData();
    }

    @Override
    public void getStatisticsInfo() {
        StatisticsInfo statisticsInfo = new StatisticsInfo();
        setRatingCountAndAverage(statisticsInfo);
        setApprovedAndRefusedPlatform(statisticsInfo);
        setApprovedAndRefusedBrowser(statisticsInfo);

        if (view != null) {

            view.onStatisticsInfoLoaded(statisticsInfo);
        }
    }

    private void setRatingCountAndAverage(StatisticsInfo statisticsInfo) {
        CountSumAveragePojo countSumAverage = DatabaseHelper.getInstance(context).getCountSumAverage();
        statisticsInfo.setRatingCount(countSumAverage.getFeedbackCount());
        statisticsInfo.setAverageRating((double) countSumAverage.getAverage());
    }

    private void setApprovedAndRefusedPlatform(StatisticsInfo statisticsInfo) {
        ApprovedAndRefusedStatistic approvedAndRefusedStatistic = DatabaseHelper.getInstance(context).getApprovedAndRefusedPlatform();
        statisticsInfo.setApprovedPlatform(approvedAndRefusedStatistic.getApprovedName());
        statisticsInfo.setRefusedPlatform(approvedAndRefusedStatistic.getRefusedName());
    }

    private void setApprovedAndRefusedBrowser(StatisticsInfo statisticsInfo) {
        ApprovedAndRefusedStatistic approvedAndRefusedStatistic = DatabaseHelper.getInstance(context).getApprovedAndRefusedBrowser();
        statisticsInfo.setApprovedBrowser(approvedAndRefusedStatistic.getApprovedName());
        statisticsInfo.setRefusedBrowser(approvedAndRefusedStatistic.getRefusedName());
    }

}