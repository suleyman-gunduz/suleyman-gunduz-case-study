package com.suleymangunduz.casestudyapp.ui.statistics.chart;

import com.suleymangunduz.casestudyapp.di.ActivityScoped;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ChartModule {

    @ActivityScoped
    @Binds
    abstract ChartContract.Presenter presenter(ChartPresenter presenter);

}
