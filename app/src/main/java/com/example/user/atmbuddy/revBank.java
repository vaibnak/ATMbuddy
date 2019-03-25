package com.example.user.atmbuddy;

public class revBank {
    String code;
    String parent;
    String review;

    public revBank() {
    }

    public revBank(String code, String parent, String review) {
        this.code = code;
        this.parent = parent;
        this.review = review;
    }

    public String getCode() {
        return code;
    }

    public revBank(String review) {
        this.review = review;
    }

    public String getParent() {
        return parent;
    }
}
