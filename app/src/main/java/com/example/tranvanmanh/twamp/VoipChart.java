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

    final static int MAX_LATENCY = 50000; //microsecond specify by Qwest
    final static int MAX_IPDV = 500; //microsecond specify by Internap
    final static double MAX_PACKET_LOSS = 0.3; // percent specify by Internap

    double percentLatency, percentIpdv, percentLoss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voip_chart);
        Intent intent = getIntent();
        profile = (Profile) intent.getSerializableExtra("data");
        Log.e("profileVoip", profile.toString());

        tvPecentLa = (TextView) findViewById(R.id.voip_pecentL);
        tvPecentI = (TextView) findViewById(R.id.voip_pecentipdv);
        tvPecentLoss = (TextView) findViewById(R.id.voip_pecentloss);
        tvLevelLa = (TextView) findViewById(R.id.voip_levelL);
        tvLevelI = (TextView) findViewById(R.id.voip_levelipdv);
        tvLevelLoss = (TextView) findViewById(R.id.voip_levelloss);



        if(profile != null){
            percentLatency = profile.getLatency().getAvglatencyus()/MAX_LATENCY;
            Log.e("percent latency", String.valueOf(percentLatency));
            Log.e("percent latency", String.valueOf(profile.getLatency().getAvglatencyus()));
            percentIpdv = profile.getIPDV().getIPDVavg()/MAX_IPDV;
            percentLoss = profile.getLostpackets()/MAX_PACKET_LOSS;
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
            tvLevelLa.setText("unAceptable Level");
        }
        else{
            percentLatency = 100 - (percentLatency*100);
            tvPecentLa.setText(String.valueOf( percentLatency));
            decoViewLa.addEvent(new DecoEvent.Builder((float) percentLatency)
                    .setIndex(backIndex1)
                    .build());
            if(percentLatency>75){
                tvLevelLa.setText("Good");
            }else if(percentLatency<75 && percentLatency>50)
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
        decoViewI.addEvent(new DecoEvent.Builder(50)
                .setIndex(backIndexI1)
                .build());

        //____________________draw loss packet chart
        decoViewLoss = (DecoView) findViewById(R.id.voip_losspacket);

        SeriesItem seriesItemL = new SeriesItem.Builder(Color.parseColor("#FFE2E2E2"))
                .setRange(0, 100, 100)
                .build();
        int backIndexL = decoViewLoss.addSeries(seriesItemL);
        SeriesItem seriesItemL1 = new SeriesItem.Builder(Color.CYAN)
                .setRange(0, 100, 0)
                .build();
        int backIndexL1 = decoViewLoss.addSeries(seriesItemL1);
        decoViewLoss.addEvent(new DecoEvent.Builder(70)
                .setIndex(backIndexL1)
                .build());

    }
}
