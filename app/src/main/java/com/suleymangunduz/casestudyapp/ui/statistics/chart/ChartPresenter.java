package com.suleymangunduz.casestudyapp.ui.statistics.chart;

import android.content.Context;

import com.suleymangunduz.casestudyapp.di.ActivityScoped;
import com.suleymangunduz.casestudyapp.model.chart.BarChartDto;
import com.suleymangunduz.casestudyapp.ui.base.ExtPresenter;
import com.suleymangunduz.casestudyapp.util.DatabaseHelper;

import javax.inject.Inject;

@ActivityScoped
public class ChartPresenter extends ExtPresenter<ChartContract.View, Object> implements ChartContract.Presenter {
    private Context context;

    @Inject
    ChartPresenter() {

    }

    @Override
    public void bind(ChartContract.View view, Context context) {
        this.view = view;
        this.context = context;
        loadData();
    }

    @Override
    public void getPlatformDistributionData(String label) {
        if (view != null) {
            view.onPieChartDataLoaded(DatabaseHelper.getInstance(context).getPlatformDistributionData(label));
        }
    }

    @Override
    public void getBrowserDistributionData(String label) {
        if (view != null) {
            view.onPieChartDataLoaded(DatabaseHelper.getInstance(context).getBrowserDistributionData(label));
        }
    }

    @Override
    public void getCountryDistributionData(String label) {
        if (view != null) {
            view.onPieChartDataLoaded(DatabaseHelper.getInstance(context).getCountryDistributionData(label));
        }
    }

    @Override
    public void getAverageRatingsPerPlatform() {
        if (view != null) {
            BarChartDto barChartDto = DatabaseHelper.getInstance(context).getAverageRatingsPerPlatform();
            view.onBarChartDataLoaded(barChartDto.getBarData(), barChartDto.getLabels());
        }
    }

    @Override
    public void getAverageRatingsPerBrowser() {
        if (view != null) {
            BarChartDto barChartDto = DatabaseHelper.getInstance(context).getAverageRatingsPerBrowser();
            view.onBarChartDataLoaded(barChartDto.getBarData(), barChartDto.getLabels());
        }
    }

    @Override
    public void getAverageRatingsPerCountry() {
        if (view != null) {
            BarChartDto barChartDto = DatabaseHelper.getInstance(context).getAverageRatingsPerCountry();
            view.onBarChartDataLoaded(barChartDto.getBarData(), barChartDto.getLabels());
        }
    }

}