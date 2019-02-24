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

public class DeliveryJobs extends AppCompatActivity {
    public RecyclerView recyclerView;
    private JobAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_jobs);

        recyclerView = findViewById(R.id.jobs);
        this.adapter =  new AmmoAdapter(outfits,getActivity());
        adapter.setListener(new RecyclerViewAdapter.Listener<AmmoItem>() {
            @Override
            public void onClick(@NonNull AmmoItem itemItem) {

                if (itemItem.getTitle().equals("Light Bullets")) {
                    startActivity(new Intent(getActivity(), LightBullets.class));
                } else if (itemItem.getTitle().equals("Energy Bullets")) {

                    startActivity(new Intent(getActivity(), EnergyBullets.class));
                } else if (itemItem.getTitle().equals("Heavy Bullets")) {
                    startActivity(new Intent(getActivity(), HeavyBullets.class));

                }
                else if(itemItem.getTitle().equals("Shotgun Shells")){
                    startActivity(new Intent(getActivity(), ShotgunBullets.class));

                }else if(itemItem.getTitle().equals("Unique Rounds")){

                    startActivity(new Intent(getActivity(), Unique.class));
                }
            }
        });
        this.recyclerView.setAdapter(adapter);

    }

}
