package com.suleymangunduz.casestudyapp.ui.feedback;

import com.suleymangunduz.casestudyapp.di.ActivityScoped;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class FeedBackModule {

    @ActivityScoped
    @Binds
    abstract FeedBackContract.Presenter presenter(FeedBackPresenter presenter);

}
