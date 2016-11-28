package com.perraulthealth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.perraulthealth.fragment.DoctorsList;
import com.perraulthealth.fragment.ListViewFragment;

/**
 * Created by sutu on 11/28/2016.
 */

public class ConsumerListsActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar mToolbar = null;
    private ListViewFragment listViewFragment = null;

    android.support.v4.app.FragmentManager fm;
    android.support.v4.app.FragmentTransaction ft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);

        findViewById(R.id.body).setOnClickListener(this);
        findViewById(R.id.imageViewMap).setOnClickListener(this);
        findViewById(R.id.sidemenu).setOnClickListener(this);
        findViewById(R.id.imageViewDoctor).setOnClickListener(this);
        //img_view = (ImageView) findViewById(R.id.imageViewList);
        //img_sidemenu = (ImageView) findViewById(R.id.sidemenu);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        listViewFragment = new ListViewFragment();
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();

        //ft.remove(listViewFragment);
        ft.replace(R.id.containerlist, listViewFragment);
        ft.commit();
    }
    @Override
    public void onStart() {
        super.onStart();
    }
    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.imageViewList) {
            Toast.makeText(this, "Welcome to Map", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), ConsumerMapsActivity.class));
        }
        if (i == R.id.imageViewDoctor) {

            DoctorsList doctorsList = new DoctorsList();
            fm = getSupportFragmentManager();
            ft = fm.beginTransaction();
            ft.replace(R.id.containerlist, doctorsList);
            ft.commit();

            Toast.makeText(this, "Doctor", Toast.LENGTH_SHORT).show();
        }
    }
}
