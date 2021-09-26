package com.example.bankapp;

public class Data {

    private static String puid;



    public Data() {

    }

    public Data(String puid) {
        this.puid = puid;
    }
    public static String getPuid() {
        return puid;
    }
}