package hackutd.com.dormdash;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;


import java.util.List;

public class RestrauntAdapter extends RecyclerViewAdapter<Restraunts, RestrauntAdapter.ItemItemViewHolder>{


    private Context context;
    List<Restraunts> restraunts;

    public RestrauntAdapter(List<Restraunts> outfit, Context context) {
        super(outfit);
        this.restraunts = outfit;
        this.context = context;
    }



    @Override
    public RestrauntAdapter.ItemItemViewHolder onCreateViewHolder(@NonNull final ViewGroup parent,
                                                            final int viewType) {
        return new ItemItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_jobs, parent, false));
    }


    public class ItemItemViewHolder extends RecyclerViewAdapter.ViewHolder {
        @NonNull
        private final ImageView imgImageView;
        @NonNull
        private final TextView titleTextView;

        @NonNull
        private final TextView descTextView;

        @NonNull
        private final TextView ratingTextView;

        @NonNull
        private final TextView distanceTextView;


        public ItemItemViewHolder (@NonNull final View itemView) {
            super(itemView);
            imgImageView = (ImageView) itemView.findViewById(R.id.itemPic);
            titleTextView = (TextView) itemView.findViewById(R.id.itemName);
            descTextView = (TextView) itemView.findViewById(R.id.item_desc);
            ratingTextView = (TextView) itemView.findViewById(R.id.item_rating);
            distanceTextView = (TextView) itemView.findViewById(R.id.item_distance);
        }

        @Override
        public void bind(int position) {
            super.bind(position);


            final Restraunts Item = get(position);
            Picasso.with(context).load(Item.imageUrl).fit().centerCrop().into(imgImageView);

            titleTextView.setText(Item.getrName());
            descTextView.setText(Item.getrType());

            ratingTextView.setText(String.valueOf(Item.getRating())+"/5.0");
            distanceTextView.setText(String.valueOf(Item.getDistance() + " miles away"));
        }

    }
}