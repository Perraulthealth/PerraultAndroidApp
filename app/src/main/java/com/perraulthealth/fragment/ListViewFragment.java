package com.perraulthealth.fragment;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.perraulthealth.Datatypes.Doctor;
import com.perraulthealth.Datatypes.Hospital;
import com.perraulthealth.Lab;
import com.perraulthealth.Pharmacy;
import com.perraulthealth.R;
import com.perraulthealth.model.ListViewItem;

import java.util.ArrayList;


public class ListViewFragment extends ListFragment {

    private ListView  listView;
    //private ViewGroup listContacts;
    String[] numbers_text = new String[] { "one", "two", "three", "four",
            "five", "six", "seven", "eight", "nine", "ten", "eleven",
            "twelve", "thirteen", "fourteen", "fifteen" };
    String[] numbers_digits = new String[] { "1", "2", "3", "4", "5", "6", "7",
            "8", "9", "10", "11", "12", "13", "14", "15" };
    private Doctor objDoctor=null;
    private Pharmacy objPharmacy = null;
    private Lab objLab = null;
    private Hospital objHospital = null;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        System.out.println("container" + container);
        if(container==null)
            return null;
        View v = inflater.inflate(R.layout.fragment_listview, container, false);

/*
        ListAdapter customAdapter = new CustomAdapter(getContext() );
        listView = (ListView) v.findViewById(R.id.listView);
        listView.setAdapter(customAdapter);
        */


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                inflater.getContext(), android.R.layout.simple_list_item_1,
                numbers_text);
        setListAdapter(adapter);

        return super.onCreateView(inflater, container, savedInstanceState);


        //numbers_digits = {objDoctor,objHospital,objLab,objPharmacy};

        /*
        ArrayList<Doctor> doctorArrayList = new ArrayList<Doctor>();

        DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        objDoctor = new Doctor();
        objDoctor.setName("A K Sharma");
        objDoctor.setDegree("MBBS");
        objDoctor.setRegnum("AQTPM23ertu6789u");

        mRootRef.child("Doctor").setValue(objDoctor);



        doctorArrayList.add(objDoctor);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                inflater.getContext(), android.R.layout.simple_list_item_1,
                numbers_text);
        setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
        //return v;

*/
    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // TODO implement some logic
    }


    private ArrayList<ListViewItem> GetlistContact(){
        ArrayList<ListViewItem> contactlist = new ArrayList<ListViewItem>();

        ListViewItem contact = new ListViewItem();

        contact.setImgId(1);
        contact.setTitle("Item1");
        contactlist.add(contact);

        contact = new ListViewItem();
        contact.setImgId(2);
        contact.setTitle("Item2");
        contactlist.add(contact);

        contact = new ListViewItem();
        contact.setImgId(3);
        contact.setTitle("Item3");
        contactlist.add(contact);

        DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        objDoctor = new Doctor();
        objDoctor.setName("A K Sharma");
      // objDoctor.setDegree("MBBS");
        //objDoctor.setRegnum("AQTPM23ertu6789u");
        mRootRef.child("Doctor").child(uid).setValue(objDoctor);

         objPharmacy = new Pharmacy();
        objPharmacy.setName("Apollo pharmacy");
        objPharmacy.setRegnum("AQWERTYU");
        objPharmacy.setRating("4.3");
        mRootRef.child("Pharmacy").child(uid).setValue(objPharmacy);


         objLab = new Lab();
        objLab.setName("Focus Imagine");
        mRootRef.child("Lab").child(uid).setValue(objLab);

         objHospital = new Hospital();
        objHospital.setName("MAX hospital");
       // objHospital.setRating("5.1");
        //objHospital.setNumofbeds(600);
        objHospital.setAddress("Saket New Delhi, India");
        mRootRef.child("Hospital").child(uid).setValue(objLab);

        return contactlist;
    }

}
