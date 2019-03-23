package com.example.user.atmbuddy;

public class Exampleitem {
     String name;
     String status;
     double dist;

    public Exampleitem(String name, String status, double dist) {
        this.name = name;
        this.status = status;
        this.dist = dist;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public double getDist() {
        return dist;
    }
}
