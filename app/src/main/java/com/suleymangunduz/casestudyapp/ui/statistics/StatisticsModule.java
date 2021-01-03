package com.suleymangunduz.casestudyapp.ui.statistics;

import com.suleymangunduz.casestudyapp.di.ActivityScoped;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class StatisticsModule {

    @ActivityScoped
    @Binds
    abstract StatisticsContract.Presenter presenter(StatisticsPresenter presenter);

}
