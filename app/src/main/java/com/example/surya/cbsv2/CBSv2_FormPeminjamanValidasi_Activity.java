package com.example.surya.cbsv2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Dialog;
import android.app.ProgressDialog;
public class CBSv2_FormPeminjamanValidasi_Activity extends AppCompatActivity {

    //Deklarasi variable
    String dataIntent, tanggalpinjam, jampinjam, tanggalkembali, jamkembali, gedung;
    private TextView tv_tanggalpinjam, tv_jampinjam, tv_tanggalkembali, tv_jamkembali;
    private Button btn_batal, btn_pinjam;
    private ImageButton ib_back;

    PinjamKelasDB myDB;
    ProgressDialog progressDialog;
    BackgroundThread backgroundThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cbsv2_formpeminjamanvalidasi);
        myDB = new PinjamKelasDB(this);

        progressDialog= ProgressDialog.show(CBSv2_FormPeminjamanValidasi_Activity.this,"Progress Dialog","wait");
        backgroundThread=new BackgroundThread();


        backgroundThread.setRunning(true);
        backgroundThread.start();

        tv_tanggalpinjam = (TextView)findViewById(R.id.tv_tanggalpinjam);
        tv_jampinjam = (TextView)findViewById(R.id.tv_jampinjam);
        tv_tanggalkembali = (TextView)findViewById(R.id.tv_tanggalkembali);
        tv_jamkembali = (TextView)findViewById(R.id.tv_jamkembali);
        btn_pinjam = (Button)findViewById(R.id.btn_pinjam);
        btn_batal = (Button)findViewById(R.id.btn_batal);
        ib_back = (ImageButton) findViewById(R.id.ib_back);

        //mengambil nilai intent
        Intent intent = getIntent();
        String[] data = intent.getStringArrayExtra("data");
        dataIntent = data[0];
        tanggalpinjam = data[1];
        jampinjam = data[2];
        tanggalkembali = data[3];
        jamkembali = data[4];
//        gedung = data[4];

        tv_tanggalpinjam.setText(tanggalpinjam);
        tv_jampinjam.setText(jampinjam);
        tv_tanggalkembali.setText(tanggalkembali);
        tv_jamkembali.setText(jamkembali);


        //1. Ketika tombol Login di klik
        btn_pinjam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Data akan disimpan di database
                boolean isInserted = myDB.insertDataKelas(dataIntent, tanggalpinjam, jampinjam, tanggalkembali, jamkembali);
                if (isInserted = true) {
                    Toast.makeText(CBSv2_FormPeminjamanValidasi_Activity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CBSv2_FormPeminjamanValidasi_Activity.this, CBSv2_LihatKelas_Activity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(CBSv2_FormPeminjamanValidasi_Activity.this, "Data not Insetred", Toast.LENGTH_LONG).show();
                    Toast.makeText(CBSv2_FormPeminjamanValidasi_Activity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CBSv2_FormPeminjamanValidasi_Activity.this, CBSv2_FormPeminjaman_Activity.class);
                    startActivity(intent);
                }
            }
        });

        //2. Ketika tombol Lihat Kelas di klik
        btn_batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CBSv2_FormPeminjamanValidasi_Activity.this, CBSv2_FormPeminjaman_Activity.class);
                startActivity(intent);

            }
        });

//        //3. Ketika Image Button di klik
//        ib_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(CBSv2_FormPeminjamanValidasi_Activity.this, CBSv2_FormPeminjaman_Activity.class);
//                startActivity(intent);
//
//            }
//        });
    }
    public class BackgroundThread extends Thread{
        volatile boolean running = false;
        int cnt;

        void setRunning(boolean b) {
            running = b;
            cnt = 10;
        }

        @Override
        public void run() {

            while(running){
                try {
                    sleep(100);
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

            Toast.makeText(CBSv2_FormPeminjamanValidasi_Activity.this,
                    "Selesai Loading", Toast.LENGTH_LONG).show();
        }

    };
}
