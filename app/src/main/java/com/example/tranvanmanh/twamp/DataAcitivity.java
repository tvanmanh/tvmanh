package com.example.tranvanmanh.twamp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.hookedonplay.decoviewlib.DecoView;
import com.hookedonplay.decoviewlib.charts.SeriesItem;
import com.hookedonplay.decoviewlib.events.DecoEvent;

public class DataAcitivity extends AppCompatActivity {

    private DecoView decoViewLa, decoViewI, decoViewLoss;
    private Profile profile;
    private TextView tvPecentLa, tvLevelLa, tvPecentI, tvLevelI, tvPecentLoss, tvLevelLoss;

    final static double MAX_LATENCY = 250000; //microsecond specify by Qwest
    final static double MAX_IPDV = 1000; //microsecond specify by Internap
    final static double MAX_PACKET_LOSS = 0; // percent specify by Internap

    double percentLatency, percentIpdv, percentLoss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_acitivity);
        Intent intent = getIntent();
        profile = (Profile) intent.getSerializableExtra("data");

        tvPecentLa = (TextView) findViewById(R.id.data_pecentL);
        tvPecentI = (TextView) findViewById(R.id.data_pecentipdv);
        tvPecentLoss = (TextView) findViewById(R.id.data_pecentloss);
        tvLevelLa = (TextView) findViewById(R.id.data_levelL);
        tvLevelI = (TextView) findViewById(R.id.data_levelipdv);
        tvLevelLoss = (TextView) findViewById(R.id.data_levelloss);



        if(profile != null){
            percentLatency = profile.getLatency().getAvglatencyus();
            percentIpdv = profile.getIPDV().getIPDVavg();
            percentLoss = (profile.getLostpackets()*100)/(profile.getTotalPacket());
        }

        //________________draw latency chart
        decoViewLa = (DecoView) findViewById(R.id.data_latency);

        SeriesItem seriesItem = new SeriesItem.Builder(Color.parseColor("#FFE2E2E2"))
                .setRange(0, 100, 100)
                .build();
        int backIndex = decoViewLa.addSeries(seriesItem);
        SeriesItem seriesItem1 = new SeriesItem.Builder(Color.YELLOW)
                .setRange(0, 100, 0)
                .build();
        int backIndex1 = decoViewLa.addSeries(seriesItem1);


        if(percentLatency >= MAX_LATENCY){
            tvPecentLa.setText("");
            tvLevelLa.setText("unAceptable Level");
        }
        else{
            percentLatency = percentLatency/MAX_LATENCY;
            percentLatency = 100 - (percentLatency*100);
            tvPecentLa.setText(String.valueOf((int)percentLatency) + "%");
            decoViewLa.addEvent(new DecoEvent.Builder((int) percentLatency)
                    .setIndex(backIndex1)
                    .build());
            if(percentLatency>65){
                tvLevelLa.setText("Good");
            }else if(percentLatency<65 && percentLatency>50)
            {
                tvLevelLa.setText("Average");
            }else{
                tvLevelLa.setText("Low");
            }
        }

        //____________________draw ipdv chart

        decoViewI = (DecoView) findViewById(R.id.data_ipdv);

        SeriesItem seriesItemI = new SeriesItem.Builder(Color.parseColor("#FFE2E2E2"))
                .setRange(0, 100, 100)
                .build();
        int backIndexI = decoViewI.addSeries(seriesItemI);
        SeriesItem seriesItemI1 = new SeriesItem.Builder(Color.RED)
                .setRange(0, 100, 0)
                .build();
        int backIndexI1 = decoViewI.addSeries(seriesItemI1);
        if(percentIpdv >= MAX_IPDV){
            tvPecentI.setText("");
            tvLevelI.setText("unAceptable Level");
        }
        else {
            percentIpdv = percentIpdv/MAX_IPDV;
            percentIpdv = 100 - (percentIpdv * 100);
            tvPecentI.setText(String.valueOf((int) percentIpdv) + "%");
            decoViewI.addEvent(new DecoEvent.Builder((int) percentIpdv)
                    .setIndex(backIndex1)
                    .build());
            if (percentIpdv > 65) {
                tvLevelI.setText("Good");
            } else if (percentIpdv < 65 && percentIpdv > 50) {
                tvLevelI.setText("Average");
            } else {
                tvLevelI.setText("Low");
            }
        }

        //____________________draw loss packet chart
        decoViewLoss = (DecoView) findViewById(R.id.data_losspacket);

        SeriesItem seriesItemL = new SeriesItem.Builder(Color.parseColor("#FFE2E2E2"))
                .setRange(0, 100, 100)
                .build();
        int backIndexL = decoViewLoss.addSeries(seriesItemL);
        SeriesItem seriesItemL1 = new SeriesItem.Builder(Color.BLUE)
                .setRange(0, 100, 0)
                .build();
        int backIndexL1 = decoViewLoss.addSeries(seriesItemL1);
        if(percentLoss > MAX_PACKET_LOSS){
            tvPecentI.setText("");
            tvLevelI.setText("unAceptable Level");
        }
        else {
            tvPecentLoss.setText(String.valueOf(100) + "%");
            decoViewLoss.addEvent(new DecoEvent.Builder(100)
                    .setIndex(backIndex1)
                    .build());
                tvLevelLoss.setText("Good");
            }

    }
}
