package com.suleymangunduz.casestudyapp.di.module;

import com.suleymangunduz.casestudyapp.di.ActivityScoped;
import com.suleymangunduz.casestudyapp.ui.feedback.FeedBackActivity;
import com.suleymangunduz.casestudyapp.ui.feedback.FeedBackModule;
import com.suleymangunduz.casestudyapp.ui.statistics.StatisticsActivity;
import com.suleymangunduz.casestudyapp.ui.statistics.StatisticsModule;
import com.suleymangunduz.casestudyapp.ui.statistics.chart.ChartActivity;
import com.suleymangunduz.casestudyapp.ui.statistics.chart.ChartModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = {FeedBackModule.class})
    abstract FeedBackActivity feedBackActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = {StatisticsModule.class})
    abstract StatisticsActivity statisticsActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = {ChartModule.class})
    abstract ChartActivity chartActivity();

}