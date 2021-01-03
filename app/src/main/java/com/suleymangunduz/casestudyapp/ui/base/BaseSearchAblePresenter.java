package com.suleymangunduz.casestudyapp.ui.base;

public interface BaseSearchAblePresenter<T> extends BasePresenter<T> {
    void filterList(String searchString);
}
