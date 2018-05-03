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

public class IPDVChart extends AppCompatActivity {
    private LineChart mchart;

    private Profile profile;
    private List<Double> iPdv;
    private List<Double> lLatency;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipdvchart);

        Intent intent = getIntent();
        profile = (Profile) intent.getSerializableExtra("data");

        toolbar = (Toolbar) findViewById(R.id.ipdv_bar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("IPDV Chart");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(profile != null){
            iPdv = profile.getIPDV().getTotal();
            lLatency = profile.getLatency().getTotal();
        }

        mchart = (LineChart) findViewById(R.id.ipdvChart);
        mchart.setScaleYEnabled(true);
        mchart.setDragEnabled(true);

        ArrayList<Entry> Yvalues = new ArrayList<>();

        for(int i = 0; i < iPdv.size(); i++){
            Yvalues.add(new Entry(i, iPdv.get(i).floatValue()));
        }
        LineDataSet set1 = new LineDataSet(Yvalues, "IPDV [packet/us]");
        set1.setColor(Color.BLUE);
        set1.setLineWidth(2f);
        set1.setValueTextColor(Color.GRAY);
        set1.setValueTextSize(10f);

        set1.setFillAlpha(110);
        ArrayList<Entry> Y1values = new ArrayList<>();

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

        dataSets.add(set1);
        LineData data = new LineData(dataSets);
        data.setValueTextColor(Color.CYAN);

        mchart.setData(data);



    }
}
