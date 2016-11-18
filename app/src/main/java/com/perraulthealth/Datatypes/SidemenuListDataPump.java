package com.perraulthealth.Datatypes;

/**
 * Created by nandinimaheshwari on 11/18/16.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SidemenuListDataPump {

    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> signout = new ArrayList<String>();

        List<String> legalandabout = new ArrayList<String>();

        List<String> rateourapp = new ArrayList<String>();

        List<String> customerservice = new ArrayList<String>();

        List<String> promotionsandoffers = new ArrayList<String>();

        List<String> ratings = new ArrayList<String>();

        List<String> myactivity = new ArrayList<String>();

        List<String> mysettings = new ArrayList<String>();
        mysettings.add("Change account settings");
        mysettings.add("Manage SMS alerts");
        mysettings.add("Manage addresses");
        mysettings.add("Manage payment");
        mysettings.add("Manage insurance");
        mysettings.add("Privacy");
        mysettings.add("App preferences");
        mysettings.add("Feed preferences");
        mysettings.add("Language settings");

        List<String> mydocuments = new ArrayList<String>();

        List<String> myprofile = new ArrayList<String>();

        expandableListDetail.put("Signout", signout);
        expandableListDetail.put("Legal and About", legalandabout);
        expandableListDetail.put("Rate out app", rateourapp);
        expandableListDetail.put("Customer Service", customerservice);
        expandableListDetail.put("Promotions and offers", promotionsandoffers);
        expandableListDetail.put("Ratings", ratings);
        expandableListDetail.put("My activity", myactivity);
        expandableListDetail.put("My settings", mysettings);
        expandableListDetail.put("My documents", mydocuments);
        expandableListDetail.put("My profile", myprofile);

        return expandableListDetail;
    }

}
