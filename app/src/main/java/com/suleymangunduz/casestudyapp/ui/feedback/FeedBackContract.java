package com.suleymangunduz.casestudyapp.ui.feedback;

import android.content.Context;

import com.suleymangunduz.casestudyapp.database.entity.FeedBack;
import com.suleymangunduz.casestudyapp.ui.base.BaseSearchAblePresenter;
import com.suleymangunduz.casestudyapp.ui.base.BaseView;
import com.suleymangunduz.casestudyapp.util.enums.SortFieldEnum;

import java.util.List;

public class FeedBackContract {

    interface View extends BaseView<List<FeedBack>> {
    }

    interface Presenter extends BaseSearchAblePresenter<View> {
        void bind(View view, Context context);

        void onSortTypeChange(SortFieldEnum sortFieldEnum);
    }

}
