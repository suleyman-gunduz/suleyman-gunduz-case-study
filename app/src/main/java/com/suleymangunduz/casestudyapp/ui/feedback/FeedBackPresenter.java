package com.suleymangunduz.casestudyapp.ui.feedback;

import android.content.Context;

import com.suleymangunduz.casestudyapp.database.entity.FeedBack;
import com.suleymangunduz.casestudyapp.di.ActivityScoped;
import com.suleymangunduz.casestudyapp.ui.base.ExtPresenter;
import com.suleymangunduz.casestudyapp.util.DatabaseHelper;
import com.suleymangunduz.casestudyapp.util.constant.Constants;
import com.suleymangunduz.casestudyapp.util.enums.SortFieldEnum;

import javax.inject.Inject;

@ActivityScoped
public class FeedBackPresenter extends ExtPresenter<FeedBackContract.View, FeedBack> implements FeedBackContract.Presenter {

    private Context context;
    private int offset = 0;
    private boolean isFilered = false;
    private String searchString = "";
    SortFieldEnum sortFieldEnum;

    @Inject
    FeedBackPresenter() {
    }

    @Override
    public void bind(FeedBackContract.View view, Context context) {
        this.view = view;
        this.context = context;
        loadData();
    }

    @Override
    public void loadData() {

        if (view != null) {
            view.showRefresher();
        }

        offset = 0;
        isFilered = false;

        getData(sortFieldEnum != null ? sortFieldEnum : SortFieldEnum.RATING);

    }

    private void getData(SortFieldEnum sortFieldEnum) {

        DatabaseHelper.getInstance(context).synchronizeDatabase();

        if (view != null) {

            view.dataLoaded(DatabaseHelper.getInstance(context).getAllFeedBacksPage(sortFieldEnum, offset));
            view.hideRefresher();
        }

    }

    @Override
    public void onSortTypeChange(SortFieldEnum sortFieldEnum) {
        this.sortFieldEnum = sortFieldEnum;
    }


    @Override
    public void loadMoreData() {

        offset = offset + Constants.PAGE_SIZE;

        if (view != null) {
            view.removeFooter();

            if (isFilered) {
                view.moreDataLoaded(DatabaseHelper.getInstance(context).getFilteredPage(searchString, sortFieldEnum != null ? sortFieldEnum : SortFieldEnum.RATING, offset));
            } else {
                view.moreDataLoaded(DatabaseHelper.getInstance(context).getAllFeedBacksPage(sortFieldEnum != null ? sortFieldEnum : SortFieldEnum.RATING, offset));
            }
        }

    }

    @Override
    public void filterList(String searchString) {

        if (view != null) {
            view.showRefresher();
        }

        offset = 0;
        isFilered = true;
        this.searchString = searchString;

        DatabaseHelper.getInstance(context).synchronizeDatabase();

        if (view != null) {

            view.dataLoaded(DatabaseHelper.getInstance(context).getFilteredPage(searchString, sortFieldEnum != null ? sortFieldEnum : SortFieldEnum.RATING, offset));
            view.hideRefresher();
        }

    }
}