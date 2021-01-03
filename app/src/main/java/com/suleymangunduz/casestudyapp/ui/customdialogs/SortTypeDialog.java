package com.suleymangunduz.casestudyapp.ui.customdialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.suleymangunduz.casestudyapp.R;
import com.suleymangunduz.casestudyapp.ui.base.OnItemClickListener;
import com.suleymangunduz.casestudyapp.ui.customdialogs.adapters.SortTypeAdapter;
import com.suleymangunduz.casestudyapp.util.enums.SortFieldEnum;

import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SortTypeDialog extends Dialog implements OnItemClickListener<SortFieldEnum> {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    Context context;

    SortTypeAdapter adapter;

    private final SortTypeDialog.OnDialogClickListener listener;

    public SortTypeDialog(@NonNull Context context, SortTypeDialog.OnDialogClickListener listener) {
        super(context);
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.sort_field_dialog);

        if (this.getWindow() != null) {
            this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        ButterKnife.bind(this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        SortFieldEnum[] sortFieldEnums = SortFieldEnum.values();

        loadData(Arrays.asList(sortFieldEnums));
    }

    private void loadData(List<SortFieldEnum> SortFieldEnums) {

        if (adapter == null) {
            adapter = new SortTypeAdapter(context, SortFieldEnums, this::onItemClick);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.setList(SortFieldEnums);
        }
    }

    @Override
    public void onItemClick(View view, SortFieldEnum item) {
        this.dismiss();
        listener.onSortTypeSelected(item);
    }

    public interface OnDialogClickListener {
        void onSortTypeSelected(SortFieldEnum sortFieldEnum);
    }
}
