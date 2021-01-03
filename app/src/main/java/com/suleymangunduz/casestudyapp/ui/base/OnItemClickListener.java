package com.suleymangunduz.casestudyapp.ui.base;

import android.view.View;

public interface OnItemClickListener<T> {
    void onItemClick(View view, T item);
}