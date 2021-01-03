package com.suleymangunduz.casestudyapp.ui.base;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.Toast;


import com.suleymangunduz.casestudyapp.R;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

public abstract class ExtRecyclerViewActivity<T extends BasePresenter, K extends ExtRecyclerAdapter> extends DaggerAppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    @Inject
    protected T presenter;

    protected K adapter;

    @BindView(R.id.recyclerView)
    protected RecyclerView recyclerView;

    @BindView(R.id.swipeRefreshLayout)
    protected SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    protected LinearLayoutManager layoutManager;

    protected abstract String pageTitle();

    protected abstract void bindPresenter();

    protected int layoutId() {
        return -1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId() != -1 ? layoutId() : R.layout.base_recyclerview_with_toolbar);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        setPageTitle(pageTitle());

        layoutManager = new LinearLayoutManager(this);
        layoutManager.setSmoothScrollbarEnabled(false);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        swipeRefreshLayout.setColorSchemeColors(getResources().getIntArray(R.array.swipeRefreshColors));
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setRefreshing(true);

        bindPresenter();

    }

    protected void setPageTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setTitle(title == null ? "" : title);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showMessage(String message) {
        if (TextUtils.isEmpty(message))
            return;

        if (!isFinishing()) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRefresh() {
        presenter.loadData();
    }

    @Override
    protected void onDestroy() {
        presenter.unbind();
        super.onDestroy();
    }

    public void showRefresher() {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(true);
        }
    }

    public void hideRefresher() {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }
}
