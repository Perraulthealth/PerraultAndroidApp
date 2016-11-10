package com.perraulthealth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.perraulthealth.fragment.Sidemenu;

import java.util.Map;

import static com.perraulthealth.R.id.imageViewList;


public class ConsumerMapsActivity extends AppCompatActivity implements View.OnClickListener {

    private GoogleMap  mMap;
    private SupportMapFragment mapFragment;
    private static final GeoLocation INITIAL_CENTER = new GeoLocation(37.7789, -122.4017);
    private static final int INITIAL_ZOOM_LEVEL = 14;
    private static final String geofire = "geofire";
    private Circle searchCircle;
    private GeoFire geoFire;
    private GeoQuery geoQuery;
    private Map<String,Marker> markers;
    ImageView img,img2;
    ListView listView;
     boolean viewboolean = false;
     boolean sidemenuboolean = false;

    android.support.v4.app.FragmentManager fm;
    android.support.v4.app.FragmentTransaction ft;






    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_home);
        img = (ImageView) findViewById(R.id.imageViewList);
        img2 = (ImageView) findViewById(R.id.sidemenu);
        //listView = (ListView) findViewById(R.id.listview);

        findViewById(R.id.body).setOnClickListener(this);
        findViewById(imageViewList).setOnClickListener(this);
        findViewById(R.id.sidemenu).setOnClickListener(this);

        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.container, new MapsFragment());
        ft.commit();



        //setContentView(R.layout.fragment_map);
        //
       // findViewById(R.id.container).setOnClickListener(this);

         //Obtain the SupportMapFragment and get notified when the map is ready to be used.
        // mapFragment = (SupportMapFragment) getSupportFragmentManager()
          //      .findFragmentById(R.id.container);
        //mapFragment.getMapAsync(this);

        // Pushing MapView Fragment


    }
    @Override
    public void onStart()
    {
        super.onStart();
        //setContentView(R.layout.fragment_map);
    }
    @Override
    public void onClick(View v)
    {
        int i = v.getId();
        if (i == imageViewList){
            if(false == viewboolean)
            {
                img.setImageResource(R.drawable.ic_action_map);
                viewboolean = true;

               fm = getSupportFragmentManager();
                ft = fm.beginTransaction();
                ft.replace(R.id.container, new ListViewFragment());
                ft.commit();
                Toast.makeText(this, "Welcome to List", Toast.LENGTH_SHORT).show();
                return;


            }
            else
            {
                img.setImageResource(R.drawable.list_view_icon);
                viewboolean = false;

               fm = getSupportFragmentManager();
                ft = fm.beginTransaction();
                ft.replace(R.id.container, new MapsFragment());
                ft.commit();
                Toast.makeText(this, "Welcome to map", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        else if (i == R.id.body) {
            Toast.makeText(this, "Welcome body ...tarun", Toast.LENGTH_SHORT).show();
           // finish();
            startActivity(new Intent(getApplicationContext(), Body.class));
        }
        else if (i == R.id.sidemenu){

            if(false==sidemenuboolean)
            {
                img2.setImageResource(R.drawable.ic_action_siddemenuv);
                sidemenuboolean = true;
               fm = getSupportFragmentManager();
                ft = fm.beginTransaction();
                ft.replace(R.id.sidemenu, new Sidemenu());
                ft.commit();
                Toast.makeText(this, "Open sidemenu", Toast.LENGTH_SHORT).show();
            }





        }

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    /*
    @Override
    public void onMapReady(GoogleMap googleMap) {
        System.out.println("googleMap" + googleMap);
        mMap = googleMap;
        //mMap=com.google.android.gms.maps.GoogleMap@42b94dc8
        if(mMap==null)
            return;


                 mMap.getUiSettings().setAllGesturesEnabled(true);



            LatLng latLngCenter = new LatLng(INITIAL_CENTER.latitude, INITIAL_CENTER.longitude);
            searchCircle = mMap.addCircle(new CircleOptions().center(latLngCenter).radius(100));
            searchCircle.setFillColor(Color.argb(66, 255, 0, 255));
            searchCircle.setStrokeColor(Color.argb(66, 0, 0, 0));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngCenter, INITIAL_ZOOM_LEVEL));
            //mMap.addMarker(new MarkerOptions().position(latLngCenter).title("Marker in Sydney"));


            // setup GeoFire
            DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
            Log.d("GeoFire", "onMapReady:" + mRootRef);


            geoFire = new GeoFire(mRootRef.child(geofire).getRef());
            Log.d("GeoFire", "onMapReady:mRootRef.child(geofire).getRef()" + geoFire);
            geoFire.setLocation("firebase-hq", new GeoLocation(37.7853889, -122.4056973));
            geoFire.setLocation("Marker in Sydney", new GeoLocation(-34, 151));

        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        }

        // Add a marker in Sydney and move the camera

       */
}


