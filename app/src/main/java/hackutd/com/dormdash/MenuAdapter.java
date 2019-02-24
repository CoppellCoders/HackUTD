package hackutd.com.dormdash;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;


import java.util.List;

public class MenuAdapter extends RecyclerViewAdapter<Menu, MenuAdapter.ItemItemViewHolder>{


    private Context context;
    List<Menu> menu;

    public MenuAdapter(List<Menu> menu, Context context) {
        super(menu);
        this.menu = menu;
        this.context = context;
    }



    @Override
    public MenuAdapter.ItemItemViewHolder onCreateViewHolder(@NonNull final ViewGroup parent,
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
            final Menu Item = get(position);


            titleTextView.setText(Item.getrName());
            descTextView.setText(Item.getrType());

            ratingTextView.setText(String.valueOf(Item.getRating())+"/5.0");
            distanceTextView.setText(String.valueOf(Item.getDistance() + " miles away"));
        }

    }
}