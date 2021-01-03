package com.suleymangunduz.casestudyapp.di.component;

import android.app.Application;

import com.suleymangunduz.casestudyapp.CaseStudyApplication;
import com.suleymangunduz.casestudyapp.di.module.ActivityBindingModule;
import com.suleymangunduz.casestudyapp.di.module.AppModule;
import com.suleymangunduz.casestudyapp.di.module.SharedPreferencesModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;


@Singleton
@Component(modules = {
        ActivityBindingModule.class,
        AppModule.class,
        SharedPreferencesModule.class,
        AndroidSupportInjectionModule.class
})
public interface AppComponent extends AndroidInjector<DaggerApplication> {

    void inject(CaseStudyApplication caseStudyApplication);

    @Override
    void inject(DaggerApplication instance);

    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        @BindsInstance
        AppComponent.Builder sharedPreferencesModule(SharedPreferencesModule sharedPreferencesModule);

        AppComponent build();
    }
}
