package com.suleymangunduz.casestudyapp.ui.base;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.SearchView;
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

public abstract class ExtSearchableRecyclerViewActivity<T extends BaseSearchAblePresenter, K extends ExtRecyclerAdapter> extends DaggerAppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, SearchView.OnQueryTextListener {

    @Inject
    protected T presenter;

    protected K adapter;

    protected LinearLayoutManager layoutManager;

    @BindView(R.id.searchView)
    protected SearchView searchView;

    @BindView(R.id.recyclerView)
    protected RecyclerView recyclerView;

    @BindView(R.id.swipeRefreshLayout)
    protected SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    protected abstract String queryHint();

    protected abstract boolean displayHomeAsUpEnabled();

    protected abstract void bindPresenter();

    protected int layoutId() {
        return -1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId() != -1 ? layoutId() : R.layout.base_searchable_recyclerview);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(displayHomeAsUpEnabled());
        }

        layoutManager = new LinearLayoutManager(this);
        layoutManager.setSmoothScrollbarEnabled(false);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        swipeRefreshLayout.setColorSchemeColors(getResources().getIntArray(R.array.swipeRefreshColors));
        swipeRefreshLayout.setOnRefreshListener(this);

        searchView.onActionViewExpanded();
        searchView.setQueryHint(queryHint());

        searchView.setIconified(false);
        searchView.setOnQueryTextListener(this);
        searchView.clearFocus();

        swipeRefreshLayout.setRefreshing(true);

        bindPresenter();
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String searchString) {
        if (TextUtils.isEmpty(searchString.trim())) {
            presenter.loadData();
            return false;
        }
        presenter.filterList(searchString.trim());
        return false;
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
        if (TextUtils.isEmpty(message)) {
            return;
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        if (!searchView.getQuery().toString().isEmpty()) {
            resetSearchView();
        } else {
            presenter.loadData();
        }
    }

    private void resetSearchView() {
        searchView.setQuery("", false);
    }

    public void hideRefresher() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    protected void onDestroy() {
        presenter.unbind();
        super.onDestroy();

    }
}
