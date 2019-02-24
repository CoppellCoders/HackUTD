package hackutd.com.dormdash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CheckoutActivity extends Activity{
    RecyclerView checkoutRv;
    MenuAdapter ma;
    TextView foodPrice;
    TextView deliveryPrice;
    TextView tax;
    TextView total;
    TextView total_price;
    TextView total_items;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        foodPrice = findViewById(R.id.food_price);
        deliveryPrice = findViewById(R.id.delivery_fee);
        tax = findViewById(R.id.tax);
        total = findViewById(R.id.total);
        total_price = findViewById(R.id.total_price);
        total_items = findViewById(R.id.checkout_items);
        int items = 0;
        Intent intent = getIntent();
        double amount = intent.getDoubleExtra("amount", 0);
        Bundle args = intent.getBundleExtra("BUNDLE");
        List<Menu> menu = (ArrayList<Menu>)args.getSerializable("data");
        List<Menu> finalMenu = new ArrayList<>();
        for(int i = 0; i < menu.size(); i++){
            if(menu.get(i).count!=0){
                items += menu.get(i).count;
                finalMenu.add(menu.get(i));
            }
        }
        System.out.println(menu);
        checkoutRv = findViewById(R.id.checkoutrv);
        checkoutRv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        ma = new MenuAdapter(finalMenu, getApplicationContext());
        checkoutRv.setAdapter(ma);
        double t = amount + amount*.05 + amount * 0.0825;
        foodPrice.setText("$"+amount);
        deliveryPrice.setText(String.format("$%.2f", amount*.05));
        tax.setText(String.format("$%.2f", amount*0.825));
        total.setText(String.format("$%.2f", t));
        total_price.setText(String.format("$%.2f", t));
        total_items.setText(items+"");
    }
}
