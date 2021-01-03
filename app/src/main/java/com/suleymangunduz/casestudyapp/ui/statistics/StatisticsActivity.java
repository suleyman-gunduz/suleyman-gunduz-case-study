package com.suleymangunduz.casestudyapp.ui.statistics;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.suleymangunduz.casestudyapp.R;
import com.suleymangunduz.casestudyapp.model.statistics.StatisticsInfo;
import com.suleymangunduz.casestudyapp.ui.base.ExtActivity;
import com.suleymangunduz.casestudyapp.ui.statistics.chart.ChartActivity;
import com.suleymangunduz.casestudyapp.util.constant.IntentFilters;
import com.suleymangunduz.casestudyapp.util.enums.ChartTypeEnum;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StatisticsActivity extends ExtActivity<StatisticsPresenter> implements StatisticsContract.View {

    @BindView(R.id.text_rating_count)
    TextView textViewRatingCount;

    @BindView(R.id.text_average_rating)
    TextView textViewAverageRating;

    @BindView(R.id.text_most_approved_platform)
    TextView textViewMostApprovedPlatform;

    @BindView(R.id.text_most_refused_platform)
    TextView textViewMostRefusedPlatform;

    @BindView(R.id.text_most_approved_browser)
    TextView textViewMostApprovedBrowser;

    @BindView(R.id.text_most_refused_browser)
    TextView textViewMostRefusedBrowser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        ButterKnife.bind(this);
        presenter.bind(this);

        setSupportActionBar(toolbar);
        setPageTitle(getString(R.string.statistics));

        presenter.getStatisticsInfo();
    }

    @Override
    public void onStatisticsInfoLoaded(StatisticsInfo statisticsInfo) {
        textViewRatingCount.setText(String.valueOf(statisticsInfo.getRatingCount()));
        textViewAverageRating.setText(statisticsInfo.getAverageRating() != null ? String.format("%.2f", statisticsInfo.getAverageRating()) : null);
        textViewMostApprovedPlatform.setText(statisticsInfo.getApprovedPlatform());
        textViewMostRefusedPlatform.setText(statisticsInfo.getRefusedPlatform());
        textViewMostApprovedBrowser.setText(statisticsInfo.getApprovedBrowser());
        textViewMostRefusedBrowser.setText(statisticsInfo.getRefusedBrowser());
    }

    @OnClick(R.id.layout_graph_platform_distribution)
    public void openPlatformDistributionChart() {
        openChartActivity(ChartTypeEnum.PLATFORM_DISTRIBUTION);
    }

    @OnClick(R.id.layout_graph_browser_distribution)
    public void openBrowserDistributionChart() {
        openChartActivity(ChartTypeEnum.BROWSER_DISTRIBUTION);
    }

    @OnClick(R.id.layout_graph_country_distribution)
    public void openCountryDistributionChart() {
        openChartActivity(ChartTypeEnum.COUNTRY_DISTRIBUTION);
    }

    @OnClick(R.id.layout_graph_average_rating_per_platform)
    public void openAverageRatingPerPlatformChart() {
        openChartActivity(ChartTypeEnum.AVERAGE_RATINGS_PER_PLATFORM);
    }

    @OnClick(R.id.layout_graph_average_rating_per_browser)
    public void openAverageRatingPerBrowserChart() {
        openChartActivity(ChartTypeEnum.AVERAGE_RATINGS_PER_BROWSER);
    }

    @OnClick(R.id.layout_graph_average_rating_per_country)
    public void openAverageRatingPerCountryChart() {
        openChartActivity(ChartTypeEnum.AVERAGE_RATINGS_PER_COUNTRY);
    }

    private void openChartActivity(ChartTypeEnum chartTypeEnum) {
        Intent intent = new Intent(this, ChartActivity.class);
        intent.putExtra(IntentFilters.GRAPH_TYPE, chartTypeEnum);
        startActivity(intent);
    }
}
