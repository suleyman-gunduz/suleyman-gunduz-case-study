package com.suleymangunduz.casestudyapp.ui.statistics;

import android.content.Context;

import com.suleymangunduz.casestudyapp.model.statistics.StatisticsInfo;
import com.suleymangunduz.casestudyapp.ui.base.BasePresenter;
import com.suleymangunduz.casestudyapp.ui.base.BaseView;

public class StatisticsContract {

    interface View extends BaseView<StatisticsInfo> {
        void onStatisticsInfoLoaded(StatisticsInfo statisticsInfo);
    }

    interface Presenter extends BasePresenter<View> {
        void bind(StatisticsContract.View view, Context context);

        void getStatisticsInfo();
    }

}
