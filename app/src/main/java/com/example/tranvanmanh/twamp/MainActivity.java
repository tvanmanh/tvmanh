package com.example.tranvanmanh.twamp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.parceler.Parcels;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

     private Toolbar toolbar;
     private EditText edtTCPPort, edtDestination, edtPortSend, edtPortRecieve, edtTestSessions, edtTestMessage, edtUdpPort;
     private CheckBox cbLightMode;
     private boolean check = false;
     private String tcpPORT, updPORT, destination, portSend, portRecieve, testSession, testMessage, lightMode;
     private SeekBar sbrSession, sbrMessage;
     String url = "http://"+destination+":8080/s192.168.142.129/m"+testMessage+"/n"+testSession+"/P" + portSend+"/p" + portRecieve+"/l"+lightMode+"/u"+updPORT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();

        sbrSession.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                edtTestSessions.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sbrMessage.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                edtTestMessage.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private  void findView(){
        toolbar = (Toolbar) findViewById(R.id.main_bar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("TWAMP");
        edtTCPPort = (EditText) findViewById(R.id.edtTcpPort);
        edtDestination = (EditText) findViewById(R.id.edtDes);
        edtPortSend = (EditText) findViewById(R.id.edtPortSend);
        edtPortRecieve = (EditText) findViewById(R.id.edtPortReceive);
        edtTestSessions = (EditText) findViewById(R.id.edtTestSessions);
        edtTestMessage = (EditText) findViewById(R.id.edtTestMessage);
        edtUdpPort = (EditText) findViewById(R.id.edtUdpPort);
        cbLightMode = (CheckBox) findViewById(R.id.checkBox);
        sbrMessage = (SeekBar) findViewById(R.id.sbrMessage);
        sbrSession = (SeekBar) findViewById(R.id.sbrsession);
    }
    private boolean getView()
    {
        if(!edtDestination.getText().toString().equals("")){
            destination = edtDestination.getText().toString();
            check = true;
            if(edtTCPPort.getText().toString().equals(""))
            {
                tcpPORT = "862";
                edtTCPPort.setText("862");
            }
            else {
                tcpPORT = edtTCPPort.getText().toString().trim();
            }
            if(edtPortSend.getText().toString().equals("")){
                portSend = "30000";
                edtPortSend.setText("30000");
            }
            else {
                portSend = edtPortSend.getText().toString().trim();
            }
            if(edtPortRecieve.getText().toString().equals("")){
                portRecieve = "20000";
                edtPortRecieve.setText("20000");
            }else
            {
                portRecieve = edtPortRecieve.getText().toString().trim();
            }
            if(edtTestSessions.getText().toString().equals("")){
                testSession = "1";
                edtTestSessions.setText("1");
            }else{
                testSession = edtTestSessions.getText().toString().trim();
            }
            if(edtTestMessage.getText().toString().equals("")){
                testMessage = "1";
                edtTestMessage.setText("1");
            }else {
                testMessage = edtTestMessage.getText().toString().trim();
            }
            if(cbLightMode.isChecked()){
                lightMode = "1";
                if(edtUdpPort.getText().equals("")){
                    updPORT = "3000";
                    edtUdpPort.setText("3000");
                }
            }
            else {
                lightMode = "0";
                updPORT = "";
            }
        }else {
            check=false;
            Toast.makeText(this, "Miss Destination!", Toast.LENGTH_SHORT).show();
        }
        return check;
    }

    class GetJsonFromWebSerivce extends AsyncTask<String, String, String>{

        OkHttpClient client = new OkHttpClient();
        @Override
        protected String doInBackground(String... strings) {
            Request request = new  Request.Builder().url(strings[0]).build();
            try {
                Response response = client.newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            String dataJson = s;
           // Log.e("datajson", dataJson);
            if(s != null){
                Gson gson = new Gson();
                Type listType = new TypeToken<Profile>(){}.getType();
                Profile profile = gson.fromJson(dataJson, listType);
                Log.e("haha", profile.getSendtime().toString());
               // TransferParcel data = new TransferParcel(profile);
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
               // intent.putExtra("data", Parcels.wrap(data));
                intent.putExtra("data", (Serializable)profile);
                intent.putExtra("datajson", dataJson);
                startActivity(intent);
            }
            else {
                Toast.makeText(MainActivity.this, "failed link!", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void onStart(View view) {
        if(getView())
        {
//              Intent intent = new Intent(MainActivity.this, ResultActivity.class);
//              startActivity(intent);
            url = "http://"+destination+":8080/s127.0.0.1/m"+testMessage+"/n"+testSession+"/P" + portSend+"/p" + portRecieve+"/l"+lightMode+"/u"+updPORT;
            Log.e("abc", url);
            new GetJsonFromWebSerivce().execute(url);
        }
    }
}
