package com.perraulthealth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.GeoQuery;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.Marker;
import com.perraulthealth.fragment.ListViewFragment;
import com.perraulthealth.fragment.MapsFragment;

import android.widget.ArrayAdapter;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import java.util.Map;

public class ConsumerMapsActivity extends AppCompatActivity implements View.OnClickListener {

    private GoogleMap mMap;
    private SupportMapFragment mapFragment;
    private static final GeoLocation INITIAL_CENTER = new GeoLocation(37.7789, -122.4017);
    private static final int INITIAL_ZOOM_LEVEL = 14;
    private static final String geofire = "geofire";
    private Circle searchCircle;
    private GeoFire geoFire;
    private GeoQuery geoQuery;
    private Map<String, Marker> markers;
    ImageView img_view, img_sidemenu;
    boolean viewboolean = false;
    private MapsFragment mapsFragment = null;
    private ListViewFragment listViewFragment = null;
    private ListView mDrawerList = null;
    private ArrayAdapter<String> mAdapter = null;
    private ActionBarDrawerToggle mDrawerToggle = null;
    private DrawerLayout mDrawerLayout = null;
    private String mActivityTitle = null;
    private Toolbar mToolbar = null;

    android.support.v4.app.FragmentManager fm;
    android.support.v4.app.FragmentTransaction ft;

    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consumerhome);

        findViewById(R.id.body).setOnClickListener(this);
        findViewById(R.id.imageViewList).setOnClickListener(this);
        findViewById(R.id.sidemenu).setOnClickListener(this);
        img_view = (ImageView) findViewById(R.id.imageViewList);
        img_sidemenu = (ImageView) findViewById(R.id.sidemenu);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mapsFragment = new MapsFragment();
        listViewFragment = new ListViewFragment();


        if (viewboolean == false) {
            //img_view.setImageResource(R.drawable.list_view_icon);

            fm = getSupportFragmentManager();
            ft = fm.beginTransaction();

            //ft.remove(listViewFragment);
            ft.add(R.id.containermap, mapsFragment);
            ft.commit();
            viewboolean = true;
        }


    }


    @Override
    public void onStart() {
        super.onStart();
    }
    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.imageViewList) {
            if (false == viewboolean) {
                viewboolean = true;
                img_view.setImageResource(R.drawable.list_view_icon);


                fm = getSupportFragmentManager();
                ft = fm.beginTransaction();

                ft.remove(listViewFragment);
                ft.remove(mapsFragment);
                ft.add(R.id.containermap, mapsFragment);

                ft.commit();
                Toast.makeText(this, "Welcome to Map", Toast.LENGTH_SHORT).show();
            } else if (true == viewboolean) {

                img_view.setImageResource(R.drawable.ic_action_map);
                viewboolean = false;

                fm = getSupportFragmentManager();
                ft = fm.beginTransaction();
                ft.remove(listViewFragment);
                ft.remove(mapsFragment);

                ft.add(R.id.containerlist, listViewFragment);
                ft.commit();
                Toast.makeText(this, "Welcome to list", Toast.LENGTH_SHORT).show();

            }

        } else if (i == R.id.body) {
            Toast.makeText(this, "Welcome body ...tarun", Toast.LENGTH_SHORT).show();
            // finish();
            startActivity(new Intent(getApplicationContext(), Body.class));
        }
        else if (i == R.id.sidemenu) {

            startActivity(new Intent(getApplicationContext(), SidemenuActivity.class));

                Toast.makeText(this, "Side Menu", Toast.LENGTH_SHORT).show();
            }

        }


    private void removeFragments() {
        ft.remove(mapsFragment);
        ft.remove(listViewFragment);
        //ft.remove(sidemenu);
    }



    @Override
    public void onStop() {
        super.onStop();

    }


}
