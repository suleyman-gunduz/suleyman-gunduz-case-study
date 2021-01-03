package com.suleymangunduz.casestudyapp.ui.statistics.chart;

import android.content.Context;

import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.PieData;
import com.suleymangunduz.casestudyapp.ui.base.BasePresenter;
import com.suleymangunduz.casestudyapp.ui.base.BaseView;

import java.util.List;

public class ChartContract {

    interface View extends BaseView<Object> {
        void onPieChartDataLoaded(PieData pieData);

        void onBarChartDataLoaded(BarData barData, List<String> labels);
    }

    interface Presenter extends BasePresenter<View> {
        void bind(ChartContract.View view, Context context);

        void getPlatformDistributionData(String label);

        void getBrowserDistributionData(String label);

        void getCountryDistributionData(String label);

        void getAverageRatingsPerPlatform();

        void getAverageRatingsPerBrowser();

        void getAverageRatingsPerCountry();
    }

}
