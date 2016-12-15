package com.perraulthealth.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.perraulthealth.Datatypes.Doctor;
import com.perraulthealth.R;

import java.util.List;

public  class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private List<Doctor> mDoctors;



    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private TextView name;
        private TextView qualifications;
        private TextView specialities;
        private TextView experience;
        private TextView clinicName;
        private TextView timings;
        private TextView area;
        private TextView fee;

        public ViewHolder(View v) {
            super(v);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                }
            });
            name = (TextView) v.findViewById(R.id.name);
            qualifications = (TextView) v.findViewById(R.id.qualifications);
            specialities = (TextView) v.findViewById(R.id.specialities);
            experience = (TextView) v.findViewById(R.id.experience);
            clinicName = (TextView) v.findViewById(R.id.clinicName);
            timings = (TextView) v.findViewById(R.id.timings);
            area = (TextView) v.findViewById(R.id.area);
            fee = (TextView) v.findViewById(R.id.fee);
        }
        public TextView getName() {
            return name;
        }
        public TextView getQualifications() {
            return qualifications;
        }
        public TextView getSpecialities() {
            return specialities;
        }
        public TextView getExperience() {
            return experience;
        }
        public TextView getClinicName() {
            return clinicName;
        }
        public TextView getTimings() {
            return timings;
        }
        public TextView getArea() {
            return area;
        }
        public TextView getFee() {
            return fee;
        }

    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_doctor, viewGroup, false);


        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
       // Log.d(TAG, "Element " + position + " set.");

        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        Doctor doctor = mDoctors.get(position);
        TextView name = viewHolder.getName();
        TextView qualifications = viewHolder.getQualifications();
        TextView specialities = viewHolder.getSpecialities();
        TextView experience = viewHolder.getExperience();
        TextView clinicName = viewHolder.getClinicName();
        TextView timings = viewHolder.getTimings();
        TextView area = viewHolder.getArea();
        TextView fee = viewHolder.getFee();

        name.setText(doctor.getName());
        qualifications.setText(doctor.getQualifications());
        specialities.setText(doctor.getSpecialities());
        experience.setText(doctor.getExperience());
        clinicName.setText(doctor.getClinicName());
        timings.setText(doctor.getTimings());
        area.setText(doctor.getArea());
        fee.setText(doctor.getFee());


        //viewHolder.getTextViewName().setText(mDataSet[position]);
    }


    public CustomAdapter(List<Doctor> doctors) {
        mDoctors = doctors;
    }
    @Override
    public int getItemCount() {
        return mDoctors.size();
    }
}

/*
@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater mInflator = LayoutInflater.from(getContext());

        View customView = mInflator.inflate(R.layout.item_doctor, parent, false);
         doctor_name = (TextView) customView.findViewById(R.id.name);
         doctor_regnum = (TextView) customView.findViewById(R.id.regnum);
         doctor_degree = (TextView) customView.findViewById(R.id.degree);
         doctor_photo = (ImageView) customView.findViewById(R.id.doctorphoto);


        String singleItem = getItem(position);
        //Doctor mDoctor = new Doctor();

        DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        mRootRef.child("Doctor").child(uid)
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                mDoctor = dataSnapshot.getValue(Doctor.class);
                doctor_name.setText(mDoctor.getName());
                doctor_photo.setImageResource(doctor_photo);
                doctor_regnum.setText(mDoctor.getRegnum());
                doctor_degree.setText(mDoctor.getDegree());
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                //Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });
        return customView;

    }
 */