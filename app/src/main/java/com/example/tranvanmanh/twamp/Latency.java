package com.example.tranvanmanh.twamp;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tranvanmanh on 4/4/2018.
 */

public class Latency implements Serializable {

    private Integer Avglatencyus;
    private Integer Maxlatencyus;
    private Integer Minlatencyus;
    private List<Double> Total = null;

    public Integer getAvglatencyus() {
        return Avglatencyus;
    }

    public void setAvglatencyus(Integer Avglatencyus) {
        this.Avglatencyus = Avglatencyus;
    }

    public Integer getMaxlatencyus() {
        return Maxlatencyus;
    }

    public void setMaxlatencyus(Integer Maxlatencyus) {
        this.Maxlatencyus = Maxlatencyus;
    }

    public Integer getMinlatencyus() {
        return Minlatencyus;
    }

    public void setMinlatencyus(Integer Minlatencyus) {
        this.Minlatencyus = Minlatencyus;
    }

    public List<Double> getTotal() {
        return Total;
    }

    public void setTotal(List<Double> Total) {
        this.Total = Total;
    }

}
