package com.example.tranvanmanh.twamp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import java.io.Serializable;
import java.sql.BatchUpdateException;

public class ChartActivity extends AppCompatActivity {

    private Profile profile;
    private Button btnLatencyChart;
    private Button btnIpdvChart;
    private Button btnVoipChart;
    private Button btnVideoChart;
    private Button btnDataChart;
    private android.support.v7.widget.Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        Intent intent = getIntent();
        profile = (Profile) intent.getSerializableExtra("data");

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.chart_bar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Line Charts");
    //    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnLatencyChart = (Button) findViewById(R.id.btn_latencychart);
        btnLatencyChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(profile.getLatency().getMaxlatencyus()==0){
                    Toast.makeText(ChartActivity.this, "latency is zero, can't draw chart", Toast.LENGTH_SHORT).show();
                }else {
                Intent intent = new Intent(ChartActivity.this, LatencyChart.class);
                intent.putExtra("data", (Serializable) profile);
                startActivity(intent);}
            }
        });


        btnIpdvChart = (Button) findViewById(R.id.btn_ipdvchart);
        btnIpdvChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChartActivity.this, IPDVChart.class);
                intent.putExtra("data", (Serializable) profile);
                startActivity(intent);
            }
        });


        btnVoipChart = (Button) findViewById(R.id.btn_voipchart);
        btnVoipChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChartActivity.this, VoipChart.class);
                intent.putExtra("data", (Serializable) profile);
                startActivity(intent);
            }
        });

        btnVideoChart = (Button) findViewById(R.id.btn_videochart);
        btnVideoChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChartActivity.this, VideoActivity.class);
                intent.putExtra("data", (Serializable) profile);
                startActivity(intent);
            }
        });

        btnDataChart = (Button) findViewById(R.id.btn_datachart);
        btnDataChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChartActivity.this, DataAcitivity.class);
                intent.putExtra("data", (Serializable) profile);
                startActivity(intent);
            }
        });
    }
}
