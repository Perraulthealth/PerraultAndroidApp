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


    private String[] mDataSet;
    private List<Doctor> mDoctors;
    private String name;
    private String regnum;
    private String degree;
    //TextView doctor_name;
    //TextView doctor_regnum;
    //TextView doctor_degree;
    //ImageView doctor_photo;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private TextView textViewName;
        public ViewHolder(View v) {
            super(v);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                }
            });
            textViewName = (TextView) v.findViewById(R.id.name);
        }
        public TextView getTextViewName() {
            return textViewName;
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
        TextView name = viewHolder.getTextViewName();
        name.setText(doctor.getName());
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