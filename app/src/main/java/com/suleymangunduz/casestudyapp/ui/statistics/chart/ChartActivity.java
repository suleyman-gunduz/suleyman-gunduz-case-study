package com.suleymangunduz.casestudyapp.ui.statistics.chart;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.suleymangunduz.casestudyapp.R;
import com.suleymangunduz.casestudyapp.ui.base.ExtActivity;
import com.suleymangunduz.casestudyapp.util.constant.IntentFilters;
import com.suleymangunduz.casestudyapp.util.enums.ChartTypeEnum;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChartActivity extends ExtActivity<ChartPresenter> implements ChartContract.View {

    @BindView(R.id.pie_chart)
    PieChart pieChart;

    @BindView(R.id.bar_chart)
    HorizontalBarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        ButterKnife.bind(this);
        presenter.bind(this);

        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {

            if (extras.containsKey(IntentFilters.GRAPH_TYPE)) {

                ChartTypeEnum chartTypeEnum = (ChartTypeEnum) extras.getSerializable(IntentFilters.GRAPH_TYPE);

                if (chartTypeEnum != null) {
                    setPageTitle(getString(chartTypeEnum.getNameResourceId()));
                    prepareGraph(chartTypeEnum);
                }

            }

        }
    }

    private void prepareGraph(ChartTypeEnum chartTypeEnum) {

        switch (chartTypeEnum) {
            case PLATFORM_DISTRIBUTION:
                preparePieChart();
                presenter.getPlatformDistributionData(getResources().getString(ChartTypeEnum.PLATFORM_DISTRIBUTION.getNameResourceId()));
                break;
            case BROWSER_DISTRIBUTION:
                preparePieChart();
                presenter.getBrowserDistributionData(getResources().getString(ChartTypeEnum.BROWSER_DISTRIBUTION.getNameResourceId()));
                break;
            case COUNTRY_DISTRIBUTION:
                preparePieChart();
                presenter.getCountryDistributionData(getResources().getString(ChartTypeEnum.COUNTRY_DISTRIBUTION.getNameResourceId()));
                break;
            case AVERAGE_RATINGS_PER_PLATFORM:
                prepareBarChart();
                presenter.getAverageRatingsPerPlatform();
                break;
            case AVERAGE_RATINGS_PER_BROWSER:
                prepareBarChart();
                presenter.getAverageRatingsPerBrowser();
                break;
            case AVERAGE_RATINGS_PER_COUNTRY:
                prepareBarChart();
                presenter.getAverageRatingsPerCountry();
                break;
        }

    }

    private void preparePieChart() {
        pieChart.getDescription().setEnabled(false);
        pieChart.animateY(1000);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setExtraBottomOffset(20f);
        pieChart.setExtraLeftOffset(20f);
        pieChart.setExtraRightOffset(20f);
        pieChart.setVisibility(View.VISIBLE);
    }

    private void prepareBarChart() {
        barChart.getDescription().setEnabled(false);
        barChart.animateY(1000);
        barChart.getAxisLeft().setAxisMaximum(5);
        barChart.getAxisLeft().setAxisMinimum(0);
        barChart.getAxisRight().setAxisMaximum(5);
        barChart.getAxisRight().setAxisMinimum(0);
        barChart.setDrawValueAboveBar(true);
        barChart.setScaleEnabled(false);
        barChart.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPieChartDataLoaded(PieData pieData) {
        pieChart.setData(pieData);
    }

    @Override
    public void onBarChartDataLoaded(BarData barData, List<String> labels) {
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);
        xAxis.setLabelCount(labels.size());
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
        barChart.setData(barData);
    }


}
