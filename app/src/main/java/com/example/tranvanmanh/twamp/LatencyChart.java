package com.example.tranvanmanh.twamp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

public class LatencyChart extends AppCompatActivity {
    private LineChart mchart;

    private Profile profile;
    private List<Double> iPdv;
    private List<Double> lLatency;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latency_chart);
        Intent intent = getIntent();
        profile = (Profile) intent.getSerializableExtra("data");

        toolbar = (Toolbar) findViewById(R.id.latency_bar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Latency Chart");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(profile != null){
            iPdv = profile.getIPDV().getTotal();
            lLatency = profile.getLatency().getTotal();
        }

        mchart = (LineChart) findViewById(R.id.latencyChart);
        mchart.setScaleYEnabled(true);
        mchart.setDragEnabled(true);

        ArrayList<Entry> Y1values = new ArrayList<>();

        for(int i = 0 ; i < lLatency.size(); i++){

            Y1values.add(new Entry(i, lLatency.get(i).floatValue()));
        }
        LineDataSet set2 = new LineDataSet(Y1values, "LATENCY [packet/us]");
        set2.setColor(Color.BLUE);
        set2.setLineWidth(2f);
        set2.setValueTextColor(Color.GRAY);
        set2.setValueTextSize(10f);

        set2.setFillAlpha(110);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

//        dataSets.add(set1);
        dataSets.add(set2);

        LineData data = new LineData(dataSets);
        data.setValueTextColor(Color.CYAN);

        mchart.setData(data);



    }
}
