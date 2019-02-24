package hackutd.com.dormdash;


import android.content.Context;
        import android.support.annotation.NonNull;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;



        import java.util.List;

public class FreeFoodAdapter extends RecyclerViewAdapter<FreeFoods, FreeFoodAdapter.ItemItemViewHolder>{


    private Context context;
    List<FreeFoods> menu;

    public FreeFoodAdapter(List<FreeFoods> menu, Context context) {
        super(menu);
        this.menu = menu;
        this.context = context;
    }



    @Override
    public FreeFoodAdapter.ItemItemViewHolder onCreateViewHolder(@NonNull final ViewGroup parent,
                                                             final int viewType) {
        return new ItemItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_freefood, parent, false));
    }


    public class ItemItemViewHolder extends RecyclerViewAdapter.ViewHolder {
        TextView title,time,desc, loc;




        public ItemItemViewHolder (@NonNull final View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.free_name);
            time = itemView.findViewById(R.id.freefood_time);
            desc = itemView.findViewById(R.id.freefood_desc);
            loc = itemView.findViewById(R.id.freefood_location);
        }

        @Override
        public void bind(int position) {
            super.bind(position);
            final FreeFoods freeFoods = get(position);
            String dw = "N/A";
         title.setText(freeFoods.getTitle());
            System.out.println(System.currentTimeMillis());
            int mins = 60*1000;
            int hours =60*60*1000;
            double days = 60*60*24*1000;
            double week = 60*60*24*7*1000;
            double months = 60*60*24*30*1000l;
            long t = (System.currentTimeMillis()-Long.parseLong(freeFoods.getTime()));
            System.out.println(t);
            int min = (int)Math.floor(t/mins);
            System.out.println(min + " nibba");
            int hour = (int)Math.floor(t/hours);
            int day = (int)Math.floor(t/days);
            int weekk = (int)Math.floor(t/week);
            int monthss = (int)Math.floor(t/months);
            System.out.println(hour + " " +day + " " + weekk + " " + monthss);
            int count =0;
            if(min<60){
                if(min==1){
                    dw =("A minute ago");
                }else if(min==0){
                    dw =("Less than a minute ago");
                }
                else{
                    dw =(min + " minutes ago");
                }




            }else
            if(hour<24){
                if(hour==1){
                    dw = ("An hour ago");
                }else {

                    dw =(hour + " hours ago");
                }
            }
            else  if(day<7){
                if(day==1){
                    dw =("A day ago");
                }else {

                    dw =(day + " days ago");
                }
            }   else  if(weekk<=4){
                if(weekk==1){
                    dw =("A week ago");
                }else {

                    dw =(weekk + " weeks ago");
                }
            } else{
                if(monthss==1){
                    dw =("A month ago");
                }else {

                    dw =(monthss + " months ago");
                }
            }
            time.setText(dw);

           desc.setText(freeFoods.getDesc());
        loc.setText("Location: " + freeFoods.getLocation());
        }

    }
}