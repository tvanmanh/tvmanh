package com.example.tranvanmanh.twamp;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tranvanmanh on 4/4/2018.
 */

public class IPDV implements Serializable {

    private Double IPDVavg;
    private Double IPDVmax;
    private Double IPDVmin;
    private List<Double> Total = null;

    public Double getIPDVavg() {
        return IPDVavg;
    }

    public void setIPDVavg(Double IPDVavg) {
        this.IPDVavg = IPDVavg;
    }

    public Double getIPDVmax() {
        return IPDVmax;
    }

    public void setIPDVmax(Double IPDVmax) {
        this.IPDVmax = IPDVmax;
    }

    public Double getIPDVmin() {
        return IPDVmin;
    }

    public void setIPDVmin(Double IPDVmin) {
        this.IPDVmin = IPDVmin;
    }

    public List<Double> getTotal() {
        return Total;
    }

    public void setTotal(List<Double> Total) {
        this.Total = Total;
    }

}