package com.example.user.atmbuddy;

public class bnk {
    String bnkname;
    int bnkcode;
    String address;
    double lat;
    double lng;

    public bnk(String bnkname, int bnkcode, String address, double lat, double lng) {
        this.bnkname = bnkname;
        this.bnkcode = bnkcode;
        this.address = address;
        this.lat = lat;
        this.lng = lng;
    }

    public String getBnkname() {
        return bnkname;
    }

    public int getBnkcode() {
        return bnkcode;
    }

    public String getAddress() {
        return address;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }
}
