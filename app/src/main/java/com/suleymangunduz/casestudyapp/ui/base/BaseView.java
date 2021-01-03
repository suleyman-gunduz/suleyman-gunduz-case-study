package com.suleymangunduz.casestudyapp.ui.base;

public interface BaseView<T> {

    void errorOccured(String errorMessage);


    default void dataLoaded(T result) {
    }

    default void moreDataLoaded(T result) {
    }

    default void showRefresher() {
    }

    default void hideRefresher() {
    }

    default void removeFooter() {
    }

}
