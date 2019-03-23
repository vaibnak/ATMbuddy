package com.example.user.atmbuddy;

public class atm {
    String parent;
    int code;
    String address;
    String status;
    double lat;
    double lng;

    public String getParent() {
        return parent;
    }

    public atm() {
    }

    public atm(String parent, int code, String address, String status, double lat, double lng) {
        this.parent = parent;

        this.code = code;
        this.address = address;
        this.status = status;
        this.lat = lat;
        this.lng = lng;
    }

    public int getCode() {
        return code;
    }

    public String getAddress() {
        return address;
    }

    public String getStatus() {
        return status;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }
}
