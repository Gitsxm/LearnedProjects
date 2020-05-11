package com.example.demo02.domain;

public class Jgjwd {
    private String akb020;

    private String lng;

    private String lat;

    private Integer count;

    public String getAkb020() {
        return akb020;
    }

    public void setAkb020(String akb020) {
        this.akb020 = akb020 == null ? null : akb020.trim();
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng == null ? null : lng.trim();
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat == null ? null : lat.trim();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}