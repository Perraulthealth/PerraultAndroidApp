package com.perraulthealth.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.perraulthealth.Datatypes.Hospital;
import com.perraulthealth.R;
import com.perraulthealth.adapter.HospitalAdapter;

import java.util.ArrayList;
import java.util.List;

;

/**
 * Created by sutu on 12/5/2016.
 */

public class HospitalsList extends Fragment {

    private static final String TAG = "HospitalsListFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 2;
    private static final int DATASET_COUNT = 60;
   // protected LayoutManagerType mCurrentLayoutManagerType;
    protected RecyclerView mRecyclerView;
    protected HospitalAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected List<Hospital> mHospital;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDataset();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.hospitalslist, container, false);
        rootView.setTag(TAG);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerViewHospitals);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new HospitalAdapter(mHospital);
        mRecyclerView.setAdapter(mAdapter);
        return rootView;
    }


    private void initDataset() {



        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        DatabaseReference mRef = ref.child("Data").child("Hospitals");
        //Hospital setHospital = new Hospital();
        //setHospital.setName("AIIMS");
        //setHospital.setAddress("ABCD");
        //setHospital.setNumberofDoctors("20 Doctors");
        //setHospital.setSpecialities("ALL");
        //setHospital.setTimings("1234");
        Log.d(TAG, "init");
         //mRef.push().setValue(setHospital);
        mHospital = new ArrayList<>();

        mRef.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Hospital hospital = dataSnapshot.getValue(Hospital.class);
                mHospital.add(hospital);
                mAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Hospital hospital = dataSnapshot.getValue(Hospital.class);

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }
            @Override
            public void onCancelled(DatabaseError firebaseError) {
                Log.e("Hospital", "The read failed: " );
            }


        });


    }
}
