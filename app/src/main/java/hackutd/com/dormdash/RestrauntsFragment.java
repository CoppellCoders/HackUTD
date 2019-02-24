package hackutd.com.dormdash;

import android.os.Bundle;
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

        }try {
            JSONArray jsonArray = new JSONArray(writer.toString());
            data = new ArrayList<>();
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject cur = jsonArray.getJSONObject(i);
                data.add(new Restraunts(cur.getString("name"), cur.getString("imageUrl"),
                        cur.getString("rating"), cur.getString("cuisine"),
                        cur.getString("distance")));
            }
            rv.setAdapter(new RestrauntAdapter(data, getContext()));
        }catch(Exception e){

        }

        return view;
    }
}
