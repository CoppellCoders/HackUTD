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
        return new ItemItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_menu, parent, false));
    }


    public class ItemItemViewHolder extends RecyclerViewAdapter.ViewHolder {
        @NonNull
        private final TextView menuName;
        @NonNull
        private final TextView menuDetail;

        @NonNull
        private final TextView menuDesc;




        public ItemItemViewHolder (@NonNull final View itemView) {
            super(itemView);
            menuName = itemView.findViewById(R.id.menu_name);
            menuDetail = itemView.findViewById(R.id.menu_detail);
            menuDesc = itemView.findViewById(R.id.menu_desc);
        }

        @Override
        public void bind(int position) {
            super.bind(position);
            final Menu Item = get(position);

            menuName.setText(Item.name);
            menuDetail.setText(Item.priceCalories);
            menuDesc.setText(Item.description);
        }

    }
}