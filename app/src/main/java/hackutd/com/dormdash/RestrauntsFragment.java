package hackutd.com.dormdash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class RestrauntsFragment extends Fragment {
    RecyclerView rv;
    List<Restraunts> data;
    RestrauntAdapter ra;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.restraunts_fragment, container, false);
        rv = view.findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
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
            e.printStackTrace();
        }try {
            JSONArray jsonArray = new JSONArray(writer.toString());
            data = new ArrayList<>();
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject cur = jsonArray.getJSONObject(i);
                data.add(new Restraunts(cur.getString("name"), cur.getString("imageUrl"),
                        cur.getString("rating"), cur.getString("cusine"),
                        cur.getString("distance"), cur.getJSONArray("menu")));
            }
            ra = new RestrauntAdapter(data, getContext());
            rv.setAdapter(ra);
        }catch(Exception e){
            e.printStackTrace();
        }
        ra.setListener(new RecyclerViewAdapter.Listener<Restraunts>() {
            @Override
            public void onClick(@NonNull Restraunts restraunts) {
                Intent intent = new Intent(getContext(), MenuActivity.class);
                intent.putExtra("info", restraunts);
                startActivity(intent);
            }

        });


        return view;
    }
}
