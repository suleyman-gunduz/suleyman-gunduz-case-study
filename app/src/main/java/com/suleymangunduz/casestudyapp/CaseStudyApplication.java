package com.suleymangunduz.casestudyapp;

import android.content.Context;
import android.os.StrictMode;

import com.suleymangunduz.casestudyapp.di.component.AppComponent;
import com.suleymangunduz.casestudyapp.di.component.DaggerAppComponent;
import com.suleymangunduz.casestudyapp.di.module.SharedPreferencesModule;

import androidx.multidex.MultiDex;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class CaseStudyApplication extends DaggerApplication {

    private static CaseStudyApplication instance;

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        instance = this;
        context = getBaseContext();

    }

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {

        AppComponent appComponent = DaggerAppComponent.builder()
                .application(this)
                .sharedPreferencesModule(new SharedPreferencesModule())
                .build();
        appComponent.inject(this);
        return appComponent;
    }

    public static CaseStudyApplication getInstance() {
        return instance;
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        CaseStudyApplication.context = context;
    }
}
