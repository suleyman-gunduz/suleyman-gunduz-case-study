package com.suleymangunduz.casestudyapp.ui.customdialogs.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.suleymangunduz.casestudyapp.R;
import com.suleymangunduz.casestudyapp.ui.base.ExtRecyclerAdapter;
import com.suleymangunduz.casestudyapp.ui.base.ExtViewHolder;
import com.suleymangunduz.casestudyapp.ui.base.OnItemClickListener;
import com.suleymangunduz.casestudyapp.util.enums.SortFieldEnum;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SortTypeAdapter extends ExtRecyclerAdapter<SortFieldEnum, SortTypeAdapter.SortTypeViewHolder> {

    public SortTypeAdapter(Context context, List<SortFieldEnum> list, OnItemClickListener<SortFieldEnum> onItemClickListener) {
        super(context, list, onItemClickListener, false);
    }

    @Override
    protected SortTypeViewHolder getItemViewHolder(ViewGroup viewGroup) {
        View view = LayoutInflater.from(context).inflate(R.layout.sort_field_item, viewGroup, false);
        return new SortTypeAdapter.SortTypeViewHolder(view);
    }

    class SortTypeViewHolder extends ExtViewHolder<SortFieldEnum> {

        @BindView(R.id.text_sort_field)
        TextView textViewSortField;

        @BindView(R.id.rl_row)
        RelativeLayout rl_row;


        SortTypeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void bind(int position, SortFieldEnum object) {
            if (object == null) {
                return;
            }

            textViewSortField.setText(context.getString(object.getNameResourceId()));

            itemView.setOnClickListener(v -> {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(v, object);
                }
            });

        }

    }


}
