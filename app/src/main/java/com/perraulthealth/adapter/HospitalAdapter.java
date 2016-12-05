package com.perraulthealth.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.perraulthealth.Datatypes.Hospital;
import com.perraulthealth.R;

import java.util.List;

/**
 * Created by sutu on 12/5/2016.
 */

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.ViewHolder> {

    private List<Hospital> mHospital;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView numberofDoctors;
        private TextView address;
        private TextView timings;
        private TextView specialities;

        public TextView getName() {
            return name;
        }

        public void setName(TextView name) {
            this.name = name;
        }

        public TextView getNumberofDoctors() {
            return numberofDoctors;
        }

        public void setNumberofDoctors(TextView numberofDoctors) {
            this.numberofDoctors = numberofDoctors;
        }

        public TextView getAddress() {
            return address;
        }

        public void setAddress(TextView address) {
            this.address = address;
        }

        public TextView getTimings() {
            return timings;
        }

        public void setTimings(TextView timings) {
            this.timings = timings;
        }

        public TextView getSpecialities() {
            return specialities;
        }

        public void setSpecialities(TextView specialities) {
            this.specialities = specialities;
        }

        public ViewHolder(View v) {
            super(v);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                }
            });
            name = (TextView) v.findViewById(R.id.name);
            numberofDoctors = (TextView) v.findViewById(R.id.numberofDoctors);
            address = (TextView) v.findViewById(R.id.address);
            timings = (TextView) v.findViewById(R.id.timings);
            specialities = (TextView) v.findViewById(R.id.specialities);
        }
    }
    public HospitalAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_hospitals, viewGroup, false);


        HospitalAdapter.ViewHolder vh = new HospitalAdapter.ViewHolder(v);
        return vh;
    }
    @Override
    public void onBindViewHolder(HospitalAdapter.ViewHolder viewHolder, final int position) {
        Hospital hospital = mHospital.get(position);
        TextView name = viewHolder.getName();
        TextView numofDocs = viewHolder.getNumberofDoctors();
        TextView specialities = viewHolder.getSpecialities();
        TextView address = viewHolder.getAddress();
        TextView timings = viewHolder.getTimings();

        if(null!=hospital.getName())
        name.setText(hospital.getName());
        if(null!=hospital.getNumberofDoctors())
            numofDocs.setText(hospital.getNumberofDoctors());
        if(null!=hospital.getSpecialities())
        specialities.setText(hospital.getSpecialities());
        if(null!=hospital.getAddress())
        address.setText(hospital.getAddress());
        if(null!=hospital.getTimings())
        timings.setText(hospital.getTimings());
    }

    public HospitalAdapter(List<Hospital> hospitals) {
        mHospital = hospitals;
    }
    @Override
    public int getItemCount() {
        return mHospital.size();
    }
}

