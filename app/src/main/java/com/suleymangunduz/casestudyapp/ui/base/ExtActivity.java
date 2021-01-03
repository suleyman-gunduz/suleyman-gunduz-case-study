package com.suleymangunduz.casestudyapp.ui.base;

import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.Toast;

import com.suleymangunduz.casestudyapp.R;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import dagger.android.support.DaggerAppCompatActivity;

public abstract class ExtActivity<T extends BasePresenter> extends DaggerAppCompatActivity {

    @Inject
    protected T presenter;

    @Nullable
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;


    protected void setPageTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(title);
        }
    }

    protected String pageTitle() {
        return null;
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

    public void errorOccured(String errorMessage) {
        showMessage(errorMessage);
    }

    public void showMessage(String message) {
        if (TextUtils.isEmpty(message))
            return;

        if (!isFinishing()) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        presenter.unbind();
        super.onDestroy();

    }
}
