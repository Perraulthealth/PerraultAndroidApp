package com.perraulthealth.Datatypes;

/**
 * Created by sutu on 11/9/2016.
 */

public class Hospital {

    private String name;
    private String numberofDoctors;
    private String address;
    private String timings;
    private String specialities;

    public String getSpecialities() {
        return specialities;
    }

    public void setSpecialities(String specialities) {
        this.specialities = specialities;
    }

    public Hospital() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumberofDoctors() {
        return numberofDoctors;
    }

    public void setNumberofDoctors(String numberofDoctors) {
        this.numberofDoctors = numberofDoctors;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTimings() {
        return timings;
    }

    public void setTimings(String timings) {
        this.timings = timings;
    }
}
