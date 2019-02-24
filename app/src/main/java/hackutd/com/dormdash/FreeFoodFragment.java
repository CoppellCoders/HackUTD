package hackutd.com.dormdash;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;

import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;

public class FreeFoodFragment extends Fragment {
    private DatabaseReference mFirebaseRef;
    private ArrayList<FreeFoods> freeFoodAdapters;
    private RecyclerView mRecyclerView;
    private FreeFoodAdapter mAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment4
        View view = inflater.inflate(R.layout.activity_freefood, container, false);
          mRecyclerView = (RecyclerView) view.findViewById(R.id.freefood);
        freeFoodAdapters = new ArrayList<>();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new FreeFoodAdapter(freeFoodAdapters, getContext());

        mRecyclerView.setAdapter(mAdapter);
        Button addfreepost = view.findViewById(R.id.addpostbtn);

        addfreepost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), FreeFoodAdd.class));

            }
        });


        addfreepost.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(getContext(), "Add a post!", Toast.LENGTH_SHORT).show();

                return false;
            }
        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        mFirebaseRef = database.getReference("freefood");
       // mFirebaseRef.push().setValue(new FreeFoods("Free Chick Fil A Sandwich", "Chick Fil A at UTD","1550989508651","If you guys go to the local place there is free food"));

        final SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.refesh_free);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        freeFoodAdapters.clear();
                        mFirebaseRef.addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                if (dataSnapshot != null && dataSnapshot.getValue() != null) {
                                    try {

                                        FreeFoods model = dataSnapshot.getValue(FreeFoods.class);


                                        freeFoodAdapters.add(model);
                                        ArrayList<FreeFoods> inOrder = new ArrayList();
                                        for (int i=freeFoodAdapters.size()-1; i > -1 ; i--) {
                                            try {
                                                inOrder.add(freeFoodAdapters.get(i));
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }

                                        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                                        mAdapter = new FreeFoodAdapter(inOrder, getContext());

                                        mRecyclerView.setAdapter(mAdapter);

                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            }

                            @Override
                            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                            }

                            @Override
                            public void onChildRemoved(DataSnapshot dataSnapshot) {

                            }

                            @Override
                            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }


                        });



                    }
                },1000);
            }
        });
        mFirebaseRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot != null && dataSnapshot.getValue() != null) {
                    try {

                        FreeFoods model = dataSnapshot.getValue(FreeFoods.class);


                        freeFoodAdapters.add(model);
                        ArrayList<FreeFoods> inOrder = new ArrayList();
                        for (int i=freeFoodAdapters.size()-1; i > -1 ; i--) {
                            try {
                                inOrder.add(freeFoodAdapters.get(i));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                        mAdapter = new FreeFoodAdapter(inOrder, getContext());

                        mRecyclerView.setAdapter(mAdapter);

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });



        return view;

    }

}
