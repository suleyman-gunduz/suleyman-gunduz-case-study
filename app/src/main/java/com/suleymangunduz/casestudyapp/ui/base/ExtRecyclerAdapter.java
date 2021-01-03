package com.suleymangunduz.casestudyapp.ui.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.suleymangunduz.casestudyapp.R;
import com.suleymangunduz.casestudyapp.util.constant.Constants;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;


public abstract class ExtRecyclerAdapter<T, K extends ExtViewHolder> extends RecyclerView.Adapter<ExtViewHolder> {

    private final int LOAD_MORE = 0;
    private final int ITEM = 1;
    protected Context context;
    protected List<T> list = new ArrayList<>();

    @Nullable
    protected OnItemClickListener<T> onItemClickListener;

    @Nullable
    private OnLoadMoreListener onLoadMoreListener;

    private boolean styled = true;
    private int visibleThreshold = 10;
    protected int lastVisibleItem, totalItemCount;
    private boolean loading;


    public ExtRecyclerAdapter(Context context, List<T> list) {
        this.context = context;
        if (list != null)
            this.list = list;
    }

    public ExtRecyclerAdapter(Context context, List<T> list, boolean styled) {
        this.context = context;
        this.styled = styled;
        if (list != null)
            this.list = list;
    }

    public ExtRecyclerAdapter(Context context, List<T> list, @Nullable OnItemClickListener<T> onItemClickListener) {
        this.context = context;
        if (list != null)
            this.list = list;
        this.onItemClickListener = onItemClickListener;
    }

    public ExtRecyclerAdapter(Context context, List<T> list, OnItemClickListener<T> onItemClickListener, boolean styled) {
        this(context, list, onItemClickListener);
        this.styled = styled;
    }


    public void addScrollListener(RecyclerView recyclerView, @Nullable OnLoadMoreListener mOnLoadMoreListener) {
        this.onLoadMoreListener = mOnLoadMoreListener;
        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                    totalItemCount = linearLayoutManager.getItemCount();
                    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                    if (!loading && totalItemCount >= Constants.PAGE_SIZE && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                        if (onLoadMoreListener != null) {
                            recyclerView.post(() -> {
                                addLoadingFooterItem();
                                onLoadMoreListener.onLoadMore();
                            });
                        }
                        loading = true;
                    }
                }
            });
        }
    }

    protected abstract K getItemViewHolder(ViewGroup viewGroup);

    private ExtRecyclerAdapter.LoadMoreViewHolder getLoadMoreViewHolder(ViewGroup viewGroup) {
        View view = LayoutInflater.from(context).inflate(R.layout.base_indicator_layout, viewGroup, false);
        return new ExtRecyclerAdapter.LoadMoreViewHolder(view);
    }

    @NonNull
    @Override
    public ExtViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM) {
            return getItemViewHolder(parent);
        } else if (viewType == LOAD_MORE) {
            return getLoadMoreViewHolder(parent);
        }
        return null;
    }


    @Override
    public int getItemViewType(int position) {

        if (getList().get(position) == null) {
            return LOAD_MORE;
        } else {
            return ITEM;
        }

    }


    @Override
    public void onBindViewHolder(ExtViewHolder holder, int position) {
        if (holder instanceof ExtRecyclerAdapter.LoadMoreViewHolder) {
            ExtRecyclerAdapter.LoadMoreViewHolder loadingViewHolder = (ExtRecyclerAdapter.LoadMoreViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        } else {
            holder.bind(position, getItem(position));
            if (styled) {
                if (position % 2 == 1) {
                    holder.itemView.setBackgroundResource(R.color.pijama);
                } else {
                    holder.itemView.setBackgroundResource(R.color.white);
                }
            }
        }
    }


    @Override
    public int getItemCount() {
        return getList() == null ? 0 : getList().size();
    }


    private T getItem(int position) {
        return getList().get(position);
    }


    public void setLoaded() {
        loading = false;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> pList) {
        if (pList == null) {
            this.list = new ArrayList<>();
        } else {
            this.list = pList;
        }
        totalItemCount = this.list.size();
        notifyDataSetChanged();
    }


    public void removeItem(int position) {
        getList().remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getList().size());
        notifyDataSetChanged();
    }

    private void removeAll() {
        for (int i = 0; i < getList().size(); i++) {
            removeItem(i);
        }
    }

    public void addAll(List<T> lst) {
        if (lst == null) {
            return;
        }

        removeLoadMoreFooterItem();

        this.getList().addAll(lst);
        notifyDataSetChanged();
    }

    public void add(T item) {
        this.getList().add(item);
        notifyDataSetChanged();
    }

    private void addLoadingFooterItem() {
        this.getList().add(null); // handler
        notifyItemInserted(getList().size() - 1);
        notifyDataSetChanged();
    }

    public void removeLoadMoreFooterItem() {
        if (this.getList().size() > 0 && this.getList().get(this.getList().size() - 1) == null) {
            getList().remove(this.getList().size() - 1);
            notifyItemRemoved(this.getList().size());
            notifyDataSetChanged();
        }
    }


    class LoadMoreViewHolder extends ExtViewHolder<T> {

        @BindView(R.id.progressBar)
        ProgressBar progressBar;

        LoadMoreViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void bind(int position, T object) {
        }
    }
}
