package com.example.tranvanmanh.twamp;

/**
 * Created by tranvanmanh on 4/18/2018.
 */

public class ItemDetail {

    private String nameMetric;
    private String result;

    public ItemDetail(String nameMetric, String result) {
        this.nameMetric = nameMetric;
        this.result = result;
    }

    public String getNameMetric() {
        return nameMetric;
    }

    public String getResult() {
        return result;
    }

    public void setNameMetric(String nameMetric) {
        this.nameMetric = nameMetric;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
