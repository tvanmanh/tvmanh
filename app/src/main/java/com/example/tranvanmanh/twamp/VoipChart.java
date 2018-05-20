package com.example.tranvanmanh.twamp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.hookedonplay.decoviewlib.DecoView;
import com.hookedonplay.decoviewlib.charts.SeriesItem;
import com.hookedonplay.decoviewlib.events.DecoEvent;

import org.w3c.dom.Text;

public class VoipChart extends AppCompatActivity {

    private DecoView decoViewLa, decoViewI, decoViewLoss;
    private Profile profile;
    private TextView tvPecentLa, tvLevelLa, tvPecentI, tvLevelI, tvPecentLoss, tvLevelLoss;

    final static double MAX_LATENCY = 50000; //microsecond specify by Qwest
    final static double MAX_IPDV = 500; //microsecond specify by Internap
    final static double MAX_PACKET_LOSS = 0.3; // percent specify by Internap

    double percentLatency, percentIpdv, percentLoss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voip_chart);
        Intent intent = getIntent();
        profile = (Profile) intent.getSerializableExtra("data");

        tvPecentLa = (TextView) findViewById(R.id.voip_pecentL);
        tvPecentI = (TextView) findViewById(R.id.voip_pecentipdv);
        tvPecentLoss = (TextView) findViewById(R.id.voip_pecentloss);
        tvLevelLa = (TextView) findViewById(R.id.voip_levelL);
        tvLevelI = (TextView) findViewById(R.id.voip_levelipdv);
        tvLevelLoss = (TextView) findViewById(R.id.voip_levelloss);



        if(profile != null){
            percentLatency = profile.getLatency().getAvglatencyus();
            percentIpdv = profile.getIPDV().getIPDVavg();
            percentLoss = (profile.getLostpackets()*100)/(profile.getTotalPacket());
        }

        //________________draw latency chart
        decoViewLa = (DecoView) findViewById(R.id.voip_latency);

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
            tvLevelLa.setText("unAcceptable Level");
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

        decoViewI = (DecoView) findViewById(R.id.voip_ipdv);

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
        decoViewLoss = (DecoView) findViewById(R.id.voip_losspacket);

        SeriesItem seriesItemL = new SeriesItem.Builder(Color.parseColor("#FFE2E2E2"))
                .setRange(0, 100, 100)
                .build();
        int backIndexL = decoViewLoss.addSeries(seriesItemL);
        SeriesItem seriesItemL1 = new SeriesItem.Builder(Color.BLUE)
                .setRange(0, 100, 0)
                .build();
        int backIndexL1 = decoViewLoss.addSeries(seriesItemL1);
        if(percentLoss >= MAX_PACKET_LOSS){
            tvPecentI.setText("");
            tvLevelI.setText("unAceptable Level");
        }
        else {
            percentLoss = percentLoss/MAX_PACKET_LOSS;
            percentLoss = 100 - (percentLoss * 100);
            tvPecentLoss.setText(String.valueOf((int) percentLoss) + "%");
            decoViewLoss.addEvent(new DecoEvent.Builder((int) percentLoss)
                    .setIndex(backIndex1)
                    .build());
            if (percentLoss > 65) {
                tvLevelLoss.setText("Good");
            } else if (percentLoss < 65 && percentLoss > 50) {
                tvLevelLoss.setText("Average");
            } else {
                tvLevelLoss.setText("Low");
            }
        }

    }
}
