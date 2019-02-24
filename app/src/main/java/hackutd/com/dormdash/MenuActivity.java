package hackutd.com.dormdash;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MenuActivity extends Activity {
    RecyclerView rv;
    MenuAdapter ma;
    ImageView imgImageView;
    TextView titleTextView;
    TextView descTextView;
    TextView ratingTextView;
    TextView distanceTextView;
    TextView menuTitle;
    double amount = 0.0;
    Button checkout;
    HashMap<String, Integer> order;
    List<Menu> menu;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        imgImageView = findViewById(R.id.menuImage);
        titleTextView = findViewById(R.id.menuItemName);
        descTextView = findViewById(R.id.menuItemDesc);
        ratingTextView = findViewById(R.id.menuItemRating);
        distanceTextView = findViewById(R.id.menuItemDistance);
        menuTitle = findViewById(R.id.menuTitle);
        checkout = findViewById(R.id.checkout);
        order = new HashMap<>();
        menu = new ArrayList<>();
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CheckoutActivity.class);
                intent.putExtra("amount", amount);
                Bundle args = new Bundle();
                args.putSerializable("data", (Serializable)menu);
                intent.putExtra("BUNDLE", args);
                startActivity(intent);
            }
        });
        try {
            Restraunts rs = (Restraunts) getIntent().getSerializableExtra("info");
            Picasso.with(getApplicationContext()).load(((R.drawable.place_1))).into(imgImageView);

            titleTextView.setText(rs.getrName());
            descTextView.setText(rs.getrType());

            ratingTextView.setText(String.valueOf(rs.getRating())+"/5.0");
            distanceTextView.setText(String.valueOf(rs.getDistance() + " miles away"));
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
                public void onClick(@NonNull final Menu menu) {

                    final View alertDialogView = LayoutInflater.from(MenuActivity.this).inflate
                            (R.layout.additem, null);
                    Button addItem = alertDialogView.findViewById(R.id.addItem);
                    final NumberPicker numItems = alertDialogView.findViewById(R.id.numberPicker);
                    numItems.setMinValue(0);
                    numItems.setMaxValue(15);
                    final AlertDialog dialog;

                    dialog = new AlertDialog.Builder(MenuActivity.this)
                            .setView(alertDialogView)
                            .setCancelable(true)
                            .create();
                    dialog.show();
                    addItem.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                            if(order.containsKey(menu.name)){
                                amount += numItems.getValue() * Double.parseDouble(menu.priceCalories.split("\\|")[0].substring(1));
                                amount -= order.get(menu.name) * Double.parseDouble(menu.priceCalories.split("\\|")[0].substring(1));
                                order.remove(menu.name);

                                order.put(menu.name, numItems.getValue());
                            }else {
                                amount += numItems.getValue() * Double.parseDouble(menu.priceCalories.split("\\|")[0].substring(1));
                                order.put(menu.name, numItems.getValue());
                            }
                            menu.setCount(numItems.getValue());
                            menuTitle.setText("Menu - $" + String.format("%.2f", amount));
                            ma.notifyDataSetChanged();

                        }
                    });


                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }



    }
}
