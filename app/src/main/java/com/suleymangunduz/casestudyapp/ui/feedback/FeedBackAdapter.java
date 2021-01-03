package com.suleymangunduz.casestudyapp.ui.feedback;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.suleymangunduz.casestudyapp.R;
import com.suleymangunduz.casestudyapp.database.entity.FeedBack;
import com.suleymangunduz.casestudyapp.ui.base.ExtRecyclerAdapter;
import com.suleymangunduz.casestudyapp.ui.base.ExtViewHolder;
import com.suleymangunduz.casestudyapp.ui.base.OnItemClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedBackAdapter extends ExtRecyclerAdapter<FeedBack, FeedBackAdapter.FeedBackViewHolder> {

    public FeedBackAdapter(Context context, List<FeedBack> list, OnItemClickListener<FeedBack> onItemClickListener) {
        super(context, list, onItemClickListener, false);
    }

    @Override
    protected FeedBackAdapter.FeedBackViewHolder getItemViewHolder(ViewGroup viewGroup) {
        View view = LayoutInflater.from(context).inflate(R.layout.feedback_item, viewGroup, false);
        return new FeedBackAdapter.FeedBackViewHolder(view);
    }

    class FeedBackViewHolder extends ExtViewHolder<FeedBack> {

        @BindView(R.id.text_label)
        TextView textViewLabel;

        @BindView(R.id.text_platform)
        TextView textViewPlatform;

        @BindView(R.id.text_browser_with_version)
        TextView textViewBrowserWithVersion;

        @BindView(R.id.text_country)
        TextView textViewCountry;

        @BindView(R.id.layout_star)
        LinearLayout starLayout;

        @BindView(R.id.image_star1)
        ImageView imageViewStar1;

        @BindView(R.id.image_star2)
        ImageView imageViewStar2;

        @BindView(R.id.image_star3)
        ImageView imageViewStar3;

        @BindView(R.id.image_star4)
        ImageView imageViewStar4;

        @BindView(R.id.image_star5)
        ImageView imageViewStar5;


        FeedBackViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void bind(int position, FeedBack object) {
            if (object == null) {
                return;
            }

            textViewLabel.setText(capitalize(object.labels));
            textViewBrowserWithVersion.setText(getBrowserWithVersion(object.browserName, object.browserVersion));
            textViewPlatform.setText(object.platform);
            textViewCountry.setText(object.computedLocation);

            if (object.rating != null && object.rating > 0) {

                setStarView(object.rating);

            } else {

                starLayout.setVisibility(View.GONE);

            }

            textViewCountry.setOnClickListener(v -> {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(v, object);
                }
            });

        }

        private String capitalize(String str) {
            if (str == null || str.isEmpty()) {
                return null;
            }
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        }

        private String getBrowserWithVersion(String browserName, Double version) {

            StringBuilder stringBuilder = new StringBuilder();
            if (browserName != null) {
                stringBuilder.append(browserName);
                stringBuilder.append(" ");
            }
            if (version != null) {
                stringBuilder.append(version);
            }

            return stringBuilder.toString();
        }

        private void setStarView(Integer star) {

            if (star == 1) {

                loadStarImage(imageViewStar1, R.drawable.star);
                loadStarImage(imageViewStar2, R.drawable.star_empty);
                loadStarImage(imageViewStar3, R.drawable.star_empty);
                loadStarImage(imageViewStar4, R.drawable.star_empty);
                loadStarImage(imageViewStar5, R.drawable.star_empty);

            } else if (star == 2) {

                loadStarImage(imageViewStar1, R.drawable.star);
                loadStarImage(imageViewStar2, R.drawable.star);
                loadStarImage(imageViewStar3, R.drawable.star_empty);
                loadStarImage(imageViewStar4, R.drawable.star_empty);
                loadStarImage(imageViewStar5, R.drawable.star_empty);

            } else if (star == 3) {

                loadStarImage(imageViewStar1, R.drawable.star);
                loadStarImage(imageViewStar2, R.drawable.star);
                loadStarImage(imageViewStar3, R.drawable.star);
                loadStarImage(imageViewStar4, R.drawable.star_empty);
                loadStarImage(imageViewStar5, R.drawable.star_empty);

            } else if (star == 4) {

                loadStarImage(imageViewStar1, R.drawable.star);
                loadStarImage(imageViewStar2, R.drawable.star);
                loadStarImage(imageViewStar3, R.drawable.star);
                loadStarImage(imageViewStar4, R.drawable.star);
                loadStarImage(imageViewStar5, R.drawable.star_empty);

            } else {

                loadStarImage(imageViewStar1, R.drawable.star);
                loadStarImage(imageViewStar2, R.drawable.star);
                loadStarImage(imageViewStar3, R.drawable.star);
                loadStarImage(imageViewStar4, R.drawable.star);
                loadStarImage(imageViewStar5, R.drawable.star);

            }

        }

        private void loadStarImage(ImageView imageView, int resourceId) {

            Picasso.get()
                    .load(resourceId)
                    .placeholder(resourceId)
                    .into(imageView);

        }

    }

}
