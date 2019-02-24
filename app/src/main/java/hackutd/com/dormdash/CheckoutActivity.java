package hackutd.com.dormdash;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.TEXT_ALIGNMENT_CENTER;

public class CheckoutActivity extends Activity{
    RecyclerView checkoutRv;
    MenuAdapter ma;
    TextView foodPrice;
    TextView deliveryPrice;
    TextView tax;
    TextView total;
    TextView total_price;
    TextView total_items;
    TextView name;
    TextView address;
    LinearLayout x;
    TextView eta;
    FrameLayout checkout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        foodPrice = findViewById(R.id.food_price);
        deliveryPrice = findViewById(R.id.delivery_fee);
        tax = findViewById(R.id.tax);
        total = findViewById(R.id.total);
        total_price = findViewById(R.id.total_price);
        checkout = findViewById(R.id.checkout_btnn);
        total_items = findViewById(R.id.checkout_items);
        name = findViewById(R.id.checkout_restraunt_name);
        address = findViewById(R.id.checkout_address);
        x = findViewById(R.id.checkout_lay);
        eta = findViewById(R.id.eta);
        name.setText(getIntent().getStringExtra("rName"));
        address.setText(getIntent().getStringExtra("address"));
        final int time = (int)((Math.random())*30+1);
        eta.setText("Estimated Time: "+time+ " mins ");
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
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSnackbar(x,"Order successful. Arriving in " + time + " mins");
            }
        });
        System.out.println(menu);
        checkoutRv = findViewById(R.id.checkoutrv);
        checkoutRv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        ma = new MenuAdapter(finalMenu, getApplicationContext());
        checkoutRv.setAdapter(ma);
        double t = amount + amount*.05 + amount * 0.0825;
        foodPrice.setText(String.format("$%.2f", amount));
        deliveryPrice.setText(String.format("$%.2f", amount*.05));
        tax.setText(String.format("$%.2f", amount*0.825));
        total.setText(String.format("$%.2f", t));
        total_price.setText(String.format("$%.2f", t));
        total_items.setText(items+"");

    }

    public void showSnackbar(View view, String message) {



        // Create snackbar
        final Snackbar snackbar = Snackbar.make(view, message, 3000 );
        View sbView = snackbar.getView();

        sbView.setBackgroundColor(Color.GREEN);
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        textView.setTextSize(18f);
        textView.setTextAlignment(TEXT_ALIGNMENT_CENTER);

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        snackbar.show();
        // Set an action on it, and a handler
        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
            @Override
            public void run(){

                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        }, 2000);


    }
}
