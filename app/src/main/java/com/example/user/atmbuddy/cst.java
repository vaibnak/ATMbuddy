package com.example.user.atmbuddy;

public class cst {
    String name;
    int phoneno;
    String address;
    double lat;
    double lng;

    public cst(String name, int phoneno, String address, double lat, double lng) {
        this.name = name;
        this.phoneno = phoneno;
        this.address = address;
        this.lat = lat;
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public int getPhoneno() {
        return phoneno;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public String getAddress() {
        return address;
    }
}
