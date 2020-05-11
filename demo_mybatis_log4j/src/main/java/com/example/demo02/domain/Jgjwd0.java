package com.example.demo02.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Jgjwd0 {

    private BigDecimal lng;

    private BigDecimal lat;

    private Integer count;

    public Jgjwd0(BigDecimal lng, BigDecimal lat, Integer count) {
        this.lng = lng;
        this.lat = lat;
        this.count = count;
    }
}
