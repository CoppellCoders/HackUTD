package hackutd.com.dormdash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

public class DeliveryJobs extends AppCompatActivity {
    public RecyclerView recyclerView;
    private JobAdapter adapter;
    List<Restraunts> data;
    private ArrayList<JobItem> jobItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_jobs);
        recyclerView = findViewById(R.id.jobs);
        jobItems = new ArrayList<>();

        jobItems.add(new JobItem("Lemma Coffee Roasters",R.drawable.place_1,"Coffee",5.0,4.50,5));
        InputStream is = getResources().openRawResource(R.raw.data);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try{
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        }catch(Exception e){

        }try {
            JSONArray jsonArray = new JSONArray(writer.toString());
            data = new ArrayList<>();
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject cur = jsonArray.getJSONObject(i);
                data.add(new Restraunts(cur.getString("name"), cur.getString("imageUrl"),
                        cur.getString("rating"), cur.getString("type"),
                        cur.getString("distance")));
            }
        }catch(Exception e){

        }

        System.out.println(jobItems);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getApplication()));
        this.adapter =  new JobAdapter(jobItems,getApplication());
        System.out.println(adapter);
        adapter.setListener(new RecyclerViewAdapter.Listener<JobItem>() {
            @Override
            public void onClick(@NonNull JobItem itemItem) {


            }
        });
        recyclerView.setAdapter(adapter);

    }

}
