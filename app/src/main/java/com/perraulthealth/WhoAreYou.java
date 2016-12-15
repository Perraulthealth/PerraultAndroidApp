package com.perraulthealth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.Doctor.DoctorHomeActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WhoAreYou extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_who_are_you);
        findViewById(R.id.Consumer).setOnClickListener(this);
        findViewById(R.id.Doctor).setOnClickListener(this);
        findViewById(R.id.LabTechnician).setOnClickListener(this);
        findViewById(R.id.Nurse).setOnClickListener(this);
        findViewById(R.id.Pharmacist).setOnClickListener(this);
        findViewById(R.id.HospitalAdministrator).setOnClickListener(this);
        findViewById(R.id.MedicalAssistant).setOnClickListener(this);


        DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


        String uid = user.getUid();
        DatabaseReference ref = mRootRef.child("Users").child(uid).child("Usertype");


    }
    @Override
    public void onClick(View v) {
        int i = v.getId();

        if (i == R.id.Consumer)
        {
            saveData("Patient");
            finish();
            startActivity(new Intent(getApplicationContext(), ConsumerMapsActivity.class));
        }
        if (i == R.id.Doctor)
        {
            saveData("Doctor");
            finish();
            startActivity(new Intent(getApplicationContext(), DoctorHomeActivity.class));
        }
        if (i == R.id.Pharmacist)
        {
            saveData("Pharmacist");
        }
        if (i == R.id.LabTechnician)
        {
            saveData("Lab Technician");
        }
        if (i == R.id.Nurse)
        {
            saveData("Nurse");
        }
        if (i == R.id.HospitalAdministrator)
        {
            saveData("Hospital Administrator");
        }
        if (i == R.id.MedicalAssistant)
        {
            saveData("Medical Assistant");
        }
    }
    private void saveData(String value) {

        DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        mRootRef.child("Users").child(uid).child("Usertype").setValue(value);


    }
}
