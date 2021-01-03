package com.suleymangunduz.casestudyapp.ui.base;


import androidx.annotation.Nullable;

public class ExtPresenter<T, K> {

    @Nullable
    protected T view;

    @Nullable
    protected K object;

    public void bind(T view) {
        this.view = view;

    }

    public void loadMoreData() {

    }

    public void unbind() {
        view = null;
        object = null;
    }
}
