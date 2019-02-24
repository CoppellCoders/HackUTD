package hackutd.com.dormdash;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Random;

import static android.view.View.TEXT_ALIGNMENT_CENTER;


public class DeliveryJobs extends AppCompatActivity {
    public RecyclerView recyclerView;
    private JobAdapter adapter;
    LinearLayout x;
    TextView money;
    Dialog myDia;
    ImageButton imagebutton, imagebuttonn;
    private ArrayList<JobItem> jobItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_jobs);
        recyclerView = findViewById(R.id.jobs);
        jobItems = new ArrayList<>();
        x = findViewById(R.id.deliveyjob);
        money = findViewById(R.id.moniesss);
        final SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.refesh_delivery);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        jobItems.clear();
                        jobItems.add(new JobItem("Einstein Bros Bagels","https://content-service.sodexomyway.com/media/EBB_bagels-coffee-1920x976_tcm54-6902.jpg?url=https://gorilladining.sodexomyway.com/","American",4.1,10.67,2.9,"Parking Structure 3"));
                        jobItems.add(new JobItem("Papa Johns","https://www.papajohns.com/static-assets/a/images/web/product/pizzas/sma_GardenFresh-compressed.jpg","American",4.8,6.58,1.2,"Dining Hall West"));
                        jobItems.add(new JobItem("Smashd","https://d9hyo6bif16lx.cloudfront.net/live/img/production/detail/menu/lunch-dinner_burgers_all-american-burger.jpg","American",4.5,4.26,3.8,"Student Union"));
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplication()));
                        adapter =  new JobAdapter(jobItems,getApplication());
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        adapter.setListener(new RecyclerViewAdapter.Listener<JobItem>() {
                            @Override
                            public void onClick(@NonNull final JobItem itemItem) {
                                myDia.setContentView(R.layout.jobitempopup);
                                imagebutton = (ImageButton) myDia.findViewById(R.id.map_aaaaa);
                                imagebuttonn = (ImageButton) myDia.findViewById(R.id.map_aaaa);
                                TextView locName = myDia.findViewById(R.id.location_name);
                                locName.setText(itemItem.getLoc());
                           imagebutton.setOnClickListener(new View.OnClickListener() {
                               @Override
                               public void onClick(View view) {
                                   imagebutton.setVisibility(View.GONE);
                                   imagebuttonn.setVisibility(View.VISIBLE);
                               }
                           });

                                imagebuttonn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        imagebutton.setVisibility(View.VISIBLE);
                                        imagebuttonn.setVisibility(View.GONE);
                                    }
                                });
                                Button close = myDia.findViewById(R.id.close_btnjob);
                                Button confirm = myDia.findViewById(R.id.confirm_delvery);

                                confirm.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        int random = (int )(Math.random() * 999999 + 000000);
                                        myDia.dismiss();
                                        jobItems.remove(itemItem);
                                        adapter =  new JobAdapter(jobItems,getApplication());
                                        double current = Double.parseDouble((money.getText().toString().replace("$","")));
                                        double newBal = current+itemItem.getMoneys();

                                        money.setText("$"+String.format("%.2f", newBal));
                                        recyclerView.setAdapter(adapter);
                                        adapter.notifyDataSetChanged();





                                        showSnackbar(x,"Pickup confirmed Please go to the location. [Order ID: " + random+"]");
                                    }
                                });

                                close.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        myDia.dismiss();
                                    }
                                });
                                myDia.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                myDia.show();


                            }
                        });

                    }
                },1000);
            }
        });
        jobItems.add(new JobItem("Papa Johns","https://www.papajohns.com/static-assets/a/images/web/product/pizzas/sma_GardenFresh-compressed.jpg","American",4.8,6.58,1.2,"Dining Hall West"));
        jobItems.add(new JobItem("Smashd","https://d9hyo6bif16lx.cloudfront.net/live/img/production/detail/menu/lunch-dinner_burgers_all-american-burger.jpg","American",4.5,4.26,3.8,"Student Union"));

        System.out.println(jobItems);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getApplication()));
        this.adapter =  new JobAdapter(jobItems,getApplication());
        myDia = new Dialog(DeliveryJobs.this);

        adapter.setListener(new RecyclerViewAdapter.Listener<JobItem>() {
            @Override
            public void onClick(@NonNull final JobItem itemItem) {
                myDia.setContentView(R.layout.jobitempopup);
                imagebutton = (ImageButton) myDia.findViewById(R.id.map_aaaaa);
                imagebuttonn = (ImageButton) myDia.findViewById(R.id.map_aaaa);
                imagebutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        imagebutton.setVisibility(View.GONE);
                        imagebuttonn.setVisibility(View.VISIBLE);
                    }
                });

                imagebuttonn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        imagebutton.setVisibility(View.VISIBLE);
                        imagebuttonn.setVisibility(View.GONE);
                    }
                });
                Button close = myDia.findViewById(R.id.close_btnjob);
                Button confirm = myDia.findViewById(R.id.confirm_delvery);
                TextView locName = myDia.findViewById(R.id.location_name);
                locName.setText(itemItem.getLoc());
                confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int random = (int )(Math.random() * 999999 + 000000);
                        myDia.dismiss();
                        double current = Double.parseDouble((money.getText().toString().replace("$","")));
                        double newBal = current+itemItem.getMoneys();
                        money.setText("$"+String.format("%.2f", newBal));
                        jobItems.remove(itemItem);
                        adapter =  new JobAdapter(jobItems,getApplication());
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();





                        showSnackbar(x,"Pickup confirmed Please go to the location. [Order ID: " + random+"]");
                    }
                });

                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        myDia.dismiss();
                    }
                });
                myDia.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                myDia.show();


            }
        });
        recyclerView.setAdapter(adapter);

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
        snackbar.show();
        // Set an action on it, and a handler


    }



}