package com.suleymangunduz.casestudyapp.ui.feedback;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import com.suleymangunduz.casestudyapp.R;
import com.suleymangunduz.casestudyapp.database.entity.FeedBack;
import com.suleymangunduz.casestudyapp.ui.base.ExtSearchableRecyclerViewActivity;
import com.suleymangunduz.casestudyapp.ui.base.OnItemClickListener;
import com.suleymangunduz.casestudyapp.ui.base.OnLoadMoreListener;
import com.suleymangunduz.casestudyapp.ui.customdialogs.SortTypeDialog;
import com.suleymangunduz.casestudyapp.ui.statistics.StatisticsActivity;
import com.suleymangunduz.casestudyapp.util.enums.SortFieldEnum;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import butterknife.BindView;
import butterknife.OnClick;

public class FeedBackActivity extends ExtSearchableRecyclerViewActivity<FeedBackPresenter, FeedBackAdapter> implements FeedBackContract.View, OnItemClickListener<FeedBack>, OnLoadMoreListener {

    @BindView(R.id.text_sort)
    TextView textViewSort;

    @Override
    protected int layoutId() {
        return R.layout.activity_feedback;
    }

    @Override
    protected String queryHint() {
        return getResources().getString(R.string.search_label);
    }

    @Override
    protected boolean displayHomeAsUpEnabled() {
        return false;
    }

    @Override
    protected void bindPresenter() {
        presenter.bind(this, this);
    }

    @Override
    public void dataLoaded(List<FeedBack> result) {

        if (adapter == null) {
            adapter = new FeedBackAdapter(this, result, this);
            adapter.addScrollListener(recyclerView, this);
            DividerItemDecoration itemDecorator = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
            itemDecorator.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(this, R.drawable.recycler_divider)));
            recyclerView.addItemDecoration(itemDecorator);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.setList(result);
        }

    }

    @Override
    public void onLoadMore() {
        presenter.loadMoreData();
    }

    @Override
    public void moreDataLoaded(List<FeedBack> result) {
        adapter.addAll(result);
    }

    @Override
    public void removeFooter() {
        adapter.removeLoadMoreFooterItem();
        adapter.setLoaded();
    }

    @Override
    public void errorOccured(String errorMessage) {
        showMessage(errorMessage);
    }

    @Override
    public void onItemClick(View view, FeedBack item) {

        if (item != null && item.latitude != null && item.longitude != null) {

            try {
                String uri = String.format(Locale.getDefault(), "geo:%s,%s?q=%s,%s(%s)", item.latitude, item.longitude
                        , item.latitude, item.longitude, item.rating);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @OnClick(R.id.text_sort)
    public void showSortTypeDialog() {

        SortTypeDialog sortTypeDialog = new SortTypeDialog(this, new SortTypeDialog.OnDialogClickListener() {
            @Override
            public void onSortTypeSelected(SortFieldEnum sortFieldEnum) {
                textViewSort.setText(getResources().getString(sortFieldEnum.getNameResourceId()));
                presenter.onSortTypeChange(sortFieldEnum);
                onRefresh();
            }
        });
        sortTypeDialog.setCancelable(true);
        sortTypeDialog.show();

    }

    @OnClick(R.id.text_statistics)
    public void openStatistics() {
        Intent intent = new Intent(this, StatisticsActivity.class);
        startActivity(intent);
    }
}
