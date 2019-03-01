package com.example.user.atmbuddy;

public class bnk {
    String bnkname;
    int bnkcode;
    String address;

    public bnk(String bnkname, int bnkcode, String address) {
        this.bnkname = bnkname;
        this.bnkcode = bnkcode;
        this.address = address;
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
}
