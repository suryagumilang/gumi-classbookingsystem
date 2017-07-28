package com.example.surya.cbsv2;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.R.attr.data;

public class CBSv2_LihatKelas_Activity extends AppCompatActivity {

    PinjamKelasDB myDB;
    ArrayList<String> theList;
    Cursor data;

    ProgressDialog progressDialog;
    BackgroundThread backgroundThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cbsv2_lihatkelas);

        progressDialog= ProgressDialog.show(CBSv2_LihatKelas_Activity.this,"Progress Dialog","wait");
        backgroundThread=new BackgroundThread();


        backgroundThread.setRunning(true);
        backgroundThread.start();

        myDB = new PinjamKelasDB(this);

        final ListView listKelas = (ListView) findViewById(R.id.listKelas);
        theList = new ArrayList<String>();
        data = myDB.getListKelas();

        while (data.moveToNext()) {
            theList.add(data.getString(0));
            //ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList);
            ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, theList);
            listKelas.setAdapter(listAdapter);

            listKelas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {

                    Intent intent = getIntent();
                    boolean loginStatus = intent.getBooleanExtra("login", true);

                    String selectedKelas = theList.get(position);
                    //Toast.makeText(getApplicationContext(), "Kelas dipilih : " + selectedKelas, Toast.LENGTH_LONG).show();
                    ;
                    String dataIntent = selectedKelas;
//                    Intent intentSelectedKelas = new Intent(getApplicationContext(), CBSv2_LihatDataKelas_Activity.class);
//                    intentSelectedKelas.putExtra("dataIntent", dataIntent);
//                    startActivity(intentSelectedKelas);

                    if(loginStatus == true){
                        Intent intentSelectedKelas = new Intent(getApplicationContext(), CBSv2_LihatDataKelas_Activity.class);
                        intentSelectedKelas.putExtra("dataIntent", dataIntent);
                        startActivity(intentSelectedKelas);
                    }else {
                        Intent intentSelectedKelas = new Intent(getApplicationContext(), CBSv2_LihatDataKelasNonLogin_Activity.class);
                        intentSelectedKelas.putExtra("dataIntent", dataIntent);
                        startActivity(intentSelectedKelas);
                    }
                }
            });
        }

    }
    public class BackgroundThread extends Thread{
        volatile boolean running = false;
        int cnt;

        void setRunning(boolean b) {
            running = b;
            cnt = 9;
        }

        @Override
        public void run() {

            while(running){
                try {
                    sleep(1000);
                    if(cnt-- == 0){
                        running = false;
                    }
                } catch (InterruptedException e) {

                    e.printStackTrace();
                }
            }
            handler.sendMessage(handler.obtainMessage());
        }
    }

    Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {

            progressDialog.dismiss();

            boolean retry = true;
            while(retry){
                try {
                    backgroundThread.join();
                    retry = false;
                } catch (InterruptedException e) {

                    e.printStackTrace();
                }
            }

            Toast.makeText(CBSv2_LihatKelas_Activity.this,
                    "Selesai Loading", Toast.LENGTH_LONG).show();
        }

    };
}
