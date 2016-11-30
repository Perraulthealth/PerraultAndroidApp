package com.perraulthealth.Datatypes;

/**
 * Created by sutu on 11/9/2016.
 */

public class Doctor {

    public Doctor() {
    }

    /*
        private static Doctor objDoctor;
        public static Doctor getInstane(){

            if(null==objDoctor){
                objDoctor = new Doctor();
            }
            return objDoctor;
        }
    */
    private String name;
    private String qualifications;
    private String ClinicName;
    private String experience;
    private String fee;
    private String specialities;
    private String timings;
    private String area;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    public String getClinicName() {
        return ClinicName;
    }

    public void setClinicName(String clinicName) {
        this.ClinicName = clinicName;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getSpecialities() {
        return specialities;
    }

    public void setSpecialities(String specialities) {
        this.specialities = specialities;
    }

    public String getTimings() {
        return timings;
    }

    public void setTimings(String timings) {
        this.timings = timings;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
