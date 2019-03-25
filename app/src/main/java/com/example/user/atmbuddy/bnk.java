package com.example.user.atmbuddy;

public class bnk {
    String bnkname;
    String bnkcode;
    String address;

    public bnk(String bnkname, String bnkcode, String address) {
        this.bnkname = bnkname;
        this.bnkcode = bnkcode;
        this.address = address;
    }

    public String getBnkname() {
        return bnkname;
    }

    public String getBnkcode() {
        return bnkcode;
    }

    public String getAddress() {
        return address;
    }

}
