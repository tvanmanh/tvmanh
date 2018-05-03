package com.example.tranvanmanh.twamp;

import android.content.ClipData;
import android.content.Intent;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.parceler.Parcels;

import java.io.Serializable;
import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    private ItemDetailAdapter adapter;
    private ArrayList<ItemDetail> detailArrayList;
    private ListView lv;
    private Toolbar toolbar;
    Profile profile;
    String dataJson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        findView();
      //  TransferParcel data = (TransferParcel) Parcels.unwrap(getIntent().getParcelableExtra("data"));

      //  Profile profile = data.getProfile();

        detailArrayList = new ArrayList<>();
        adapter = new ItemDetailAdapter(this, R.layout.item_detail, detailArrayList);
        lv.setAdapter(adapter);

        detailArrayList.add(new ItemDetail("Lost Packets", "0"));
        detailArrayList.add(new ItemDetail("Duplicate Packets", "0"));
        detailArrayList.add(new ItemDetail("Inter Delay [us]", "0"));
        detailArrayList.add(new ItemDetail("AvgLatency [us]", "0"));
        detailArrayList.add(new ItemDetail("MaxLatency [us]", "0"));
        detailArrayList.add(new ItemDetail("MinLatency [us]", "0"));
        detailArrayList.add(new ItemDetail("AvgIPDV [us]", "0"));
        detailArrayList.add(new ItemDetail("MaxIPDV [us]", "0"));
        detailArrayList.add(new ItemDetail("MinIPDV [us]", "0"));
        detailArrayList.add(new ItemDetail("Total Packets", "0"));
        detailArrayList.add(new ItemDetail("Destination IP", "0"));
        detailArrayList.add(new ItemDetail("Source IP", "0"));

        adapter.notifyDataSetChanged();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FragmentDetail fragmentDetail = new FragmentDetail();
                Bundle bundle = new Bundle();
                bundle.putInt("position", i);
                fragmentDetail.setArguments(bundle);
                fragmentDetail.show(getFragmentManager(), "dialog");

            }
        });
        Intent intent = getIntent();
        profile = (Profile) intent.getSerializableExtra("data");
       Bundle bundle =  intent.getExtras();
        dataJson = bundle.getString("datajson");
        Log.e("dajson", dataJson);

        if(profile != null) {


            int totalPacket = profile.getTotalPacket();
            int lostPaketP = (profile.getLostpackets()/totalPacket)*100;
            int dupPacketP = (profile.getDuplicatepackets()/totalPacket)*100;
            int oderPacketP = (profile.getOutoforderpacket()/totalPacket)*100;
            detailArrayList.clear();
            detailArrayList.add(new ItemDetail("Lost Packets", lostPaketP + " %"));
            detailArrayList.add(new ItemDetail("Duplicate Packets",dupPacketP + " %" ));
            detailArrayList.add(new ItemDetail("Out Of Order Packets", oderPacketP + " %"));
            detailArrayList.add(new ItemDetail("Inter Delay [us]", profile.getInterdelayus().toString()));
            detailArrayList.add(new ItemDetail("AvgLatency [us]", profile.getLatency().getAvglatencyus().toString()));
            detailArrayList.add(new ItemDetail("MaxLatency [us]", profile.getLatency().getMaxlatencyus().toString()));
            detailArrayList.add(new ItemDetail("MinLatency [us]", profile.getLatency().getMinlatencyus().toString()));
            detailArrayList.add(new ItemDetail("AvgIPDV [us]", profile.getIPDV().getIPDVavg().toString()));
            detailArrayList.add(new ItemDetail("MaxIPDV [us]", profile.getIPDV().getIPDVmax().toString()));
            detailArrayList.add(new ItemDetail("MinIPDV [us]", profile.getIPDV().getIPDVmin().toString()));
            detailArrayList.add(new ItemDetail("Total Packets", profile.getTotalPacket().toString()));
            detailArrayList.add(new ItemDetail("Destination IP", profile.getDestinationIP()));
            detailArrayList.add(new ItemDetail("Source IP", profile.getSourceIP()));

            adapter.notifyDataSetChanged();
        }
    }

    private void findView() {

        lv = (ListView) findViewById(R.id.lvdetails);
        toolbar = (Toolbar) findViewById(R.id.results);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Result");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void onDetails(View view) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("data", dataJson);
        startActivity(intent);
    }

    public void onDrawChart(View view) {
        Intent intent = new Intent(this, ChartActivity.class);
        intent.putExtra("data", (Serializable) profile);
        startActivity(intent);
    }
}
