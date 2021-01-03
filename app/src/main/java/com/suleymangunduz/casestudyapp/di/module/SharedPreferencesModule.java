package com.suleymangunduz.casestudyapp.di.module;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SharedPreferencesModule {

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(Context context) {
        return context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    SharedPreferences.Editor provideSharedPreferencesEditor(Context context) {
        SharedPreferences settings = provideSharedPreferences(context);
        return settings.edit();
    }
}