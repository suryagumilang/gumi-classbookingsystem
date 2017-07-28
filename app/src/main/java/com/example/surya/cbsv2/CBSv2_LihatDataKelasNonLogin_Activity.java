package com.example.surya.cbsv2;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CBSv2_LihatDataKelasNonLogin_Activity extends AppCompatActivity {

    PinjamKelasDB myDB;
    ArrayList<String> theList;
    Button btnAddKelas;
    ProgressDialog progressDialog;
    BackgroundThread backgroundThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cbsv2_lihatdatakelasnonlogin);


//        btnAddKelas = (Button) findViewById(R.id.button_add);
        Intent intent = getIntent();
        progressDialog= ProgressDialog.show(CBSv2_LihatDataKelasNonLogin_Activity.this,"Progress Dialog","wait");
        backgroundThread=new BackgroundThread();


        backgroundThread.setRunning(true);
        backgroundThread.start();
        final String dataIntent = intent.getStringExtra("dataIntent");

        myDB = new PinjamKelasDB(this);

        final ListView dataKelas = (ListView) findViewById(R.id.dataKelas);
        theList = new ArrayList<>();
        final Cursor data = myDB.getDataKelas(dataIntent);

        StringBuffer buffer = new StringBuffer();
        if (data.getCount() == 0) {
            buffer.append("Tidak ada peminjaman");
        }

        while (data.moveToNext()) {
            buffer.append(data.getString(0) + ". ");
            buffer.append("Ruang " + data.getString(1) + " dipinjam pada : \n");
            buffer.append(data.getString(2) + " " + data.getString(3) + " hingga \n");
            buffer.append(data.getString(4) + " " + data.getString(5) + "\n\n");
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Data Peminjaman");
        builder.setMessage(buffer.toString());
        builder.show();



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

            Toast.makeText(CBSv2_LihatDataKelasNonLogin_Activity.this,
                    "Selesai Loading", Toast.LENGTH_LONG).show();
        }

    };
}
