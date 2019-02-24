package hackutd.com.dormdash;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;



import java.util.List;

public class JobAdapter extends RecyclerViewAdapter<JobItem, JobAdapter.ItemItemViewHolder>{


    private Context context;
    List<JobItem> outfit;

    public JobAdapter(List<JobItem> outfit, Context context) {
        super(outfit);
        this.outfit = outfit;
        this.context = context;
    }



    @Override
    public JobAdapter.ItemItemViewHolder onCreateViewHolder(@NonNull final ViewGroup parent,
                                                             final int viewType) {
        return new ItemItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_cosmet, parent, false));
    }


    public class ItemItemViewHolder extends RecyclerViewAdapter.ViewHolder {
        @NonNull
        private final ImageView imgImageView;
        @NonNull
        private final TextView titleTextView;


        public ItemItemViewHolder (@NonNull final View itemView) {
            super(itemView);
            imgImageView = (ImageView) itemView.findViewById(R.id.itemPic);
            titleTextView = (TextView) itemView.findViewById(R.id.itemName);

        }

        @Override
        public void bind(int position) {
            super.bind(position);
            final AmmoItem newsItem = get(position);
            Picasso.with(context).load(newsItem.getImgSrc()).into(imgImageView);

            titleTextView.setText(newsItem.getTitle());

        }

    }
}
