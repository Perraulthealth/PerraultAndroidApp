package com.Doctor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.perraulthealth.R;

public class DoctorHomeActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home);
        findViewById(R.id.newprescription).setOnClickListener(this);

            }

    @Override
    public void onClick(View v) {

        

    }
}
