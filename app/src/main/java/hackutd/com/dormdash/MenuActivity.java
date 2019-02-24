package hackutd.com.dormdash;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends Activity {
    RecyclerView rv;
    MenuAdapter ma;
    ImageView imgImageView;
    TextView titleTextView;
    TextView descTextView;
    TextView ratingTextView;
    TextView distanceTextView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        imgImageView = findViewById(R.id.menuImage);
        titleTextView = findViewById(R.id.menuItemName);
        descTextView = findViewById(R.id.menuItemDesc);
        ratingTextView = findViewById(R.id.menuItemRating);
        distanceTextView = findViewById(R.id.menuItemDistance);

        try {
            Restraunts rs = (Restraunts) getIntent().getSerializableExtra("info");
            Picasso.with(getApplicationContext()).load(((R.drawable.place_1))).into(imgImageView);

            titleTextView.setText(rs.getrName());
            descTextView.setText(rs.getrType());

            ratingTextView.setText(String.valueOf(rs.getRating())+"/5.0");
            distanceTextView.setText(String.valueOf(rs.getDistance() + " miles away"));
            List<Menu> menu = new ArrayList<>();
            JSONArray m = new JSONArray(rs.menu);
            System.out.println(m);
            for (int i = 0; i < m.length(); i++) {
                JSONObject cur = m.getJSONObject(i);
                System.out.println(cur);
                menu.add(
                        new Menu(cur.getString("itemName"), "$"+cur.getString("price")+" | "+cur.getString("calories")+" cals", cur.getString("description"))
                );
            }
            System.out.println(menu);
            rv = findViewById(R.id.menurv);
            rv.setLayoutManager(new LinearLayoutManager(this));
            ma = new MenuAdapter(menu, this);
            rv.setAdapter(ma);
            ma.setListener(new RecyclerViewAdapter.Listener<Menu>() {
                @Override
                public void onClick(@NonNull Menu menu) {

                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }



    }
}
