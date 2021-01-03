package com.suleymangunduz.casestudyapp.ui.base;

public interface BasePresenter<T> {

    void bind(T view);

    default void loadData() {
    }

    default void loadMoreData() {
    }

    void unbind();

}
