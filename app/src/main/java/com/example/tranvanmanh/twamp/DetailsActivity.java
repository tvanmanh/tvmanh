package com.example.tranvanmanh.twamp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetailsActivity extends AppCompatActivity {

    TextView tvDetails;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        tvDetails = (TextView) findViewById(R.id.tvdetails);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String dataJson = bundle.getString("data");
        tvDetails.setText(dataJson);
        toolbar = (Toolbar) findViewById(R.id.detail_bar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Details");
      //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
