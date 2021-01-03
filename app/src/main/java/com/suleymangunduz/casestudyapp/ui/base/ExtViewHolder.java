package com.suleymangunduz.casestudyapp.ui.base;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public abstract class ExtViewHolder<T> extends RecyclerView.ViewHolder {

    public ExtViewHolder(View itemView) {
        super(itemView);
    }

    protected abstract void bind(int position, T object);

}
