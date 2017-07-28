package com.example.surya.cbsv2;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class CBSv2_FormPeminjaman_Activity extends AppCompatActivity {

    int hour, minute, mYear,mMonth, mDay;
    static final int TIME_DIALOG_ID1 = 0;
    static final int DATE_DIALOG_ID1 = 1;
    static final int TIME_DIALOG_ID2 = 2;
    static final int DATE_DIALOG_ID2 = 3;
    private String[] arrMonth = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
    private EditText et_tanggalpinjam, et_jampinjam, et_tanggalkembali, et_jamkembali;
    private Button btn_lanjutkan;
    private ImageButton ib_back;

    String tanggalpinjam, jampinjam, tanggalkembali, jamkembali;

    ProgressDialog progressDialog;
    BackgroundThread backgroundThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cbsv2_formpeminjaman);

        progressDialog= ProgressDialog.show(CBSv2_FormPeminjaman_Activity.this,"Progress Dialog","wait");
        backgroundThread=new BackgroundThread();


        backgroundThread.setRunning(true);
        backgroundThread.start();

        Intent intent = getIntent();
        final String dataIntent = intent.getStringExtra("addKelas");

        et_tanggalpinjam = (EditText)findViewById(R.id.et_tanggalpinjam);
        et_jampinjam = (EditText)findViewById(R.id.et_jampinjam);
        et_tanggalkembali = (EditText)findViewById(R.id.et_tanggalkembali);
        et_jamkembali = (EditText)findViewById(R.id.et_jamkembali);
        ib_back = (ImageButton)findViewById(R.id.ib_back);
        btn_lanjutkan = (Button)findViewById(R.id.btn_lanjutkan);

        // get the current date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        et_tanggalpinjam.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                // TODO Auto-generated method stub
                showDialog(DATE_DIALOG_ID1);
                return true;
            }
        });

        et_jampinjam.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                // TODO Auto-generated method stub
                showDialog(TIME_DIALOG_ID1);
                return true;
            }
        });

        et_tanggalkembali.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                // TODO Auto-generated method stub
                showDialog(DATE_DIALOG_ID2);
                return true;
            }
        });

        et_jamkembali.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                // TODO Auto-generated method stub
                showDialog(TIME_DIALOG_ID2);
                return true;
            }
        });

        btn_lanjutkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tanggalpinjam = et_tanggalpinjam.getText().toString();
                jampinjam = et_jampinjam.getText().toString();

                tanggalkembali = et_tanggalkembali.getText().toString();
                jamkembali = et_jamkembali.getText().toString();

                //String data[]= {tanggalpinjam, jampinjam, tanggalkembali, jamkembali, ruang};
                String data[]= {dataIntent, tanggalpinjam, jampinjam, tanggalkembali, jamkembali};

                Intent intent = new Intent(CBSv2_FormPeminjaman_Activity.this, CBSv2_FormPeminjamanValidasi_Activity.class);
                intent.putExtra("data", data);
                startActivity(intent);

            }
        });

        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Ketika image button di klik
                Intent intent = new Intent(CBSv2_FormPeminjaman_Activity.this, CBSv2_LihatDataKelas_Activity.class);
                startActivity(intent);

            }
        });
    }


    @Override
    protected Dialog onCreateDialog(int id)
    {
        switch (id) {
            case TIME_DIALOG_ID1:
                return new TimePickerDialog(
                        this, mTimeSetListener1, hour, minute, true);
            case DATE_DIALOG_ID1:
                return new DatePickerDialog(
                        this, mDateSetListener1, mYear, mMonth, mDay);
            case TIME_DIALOG_ID2:
                return new TimePickerDialog(
                        this, mTimeSetListener2, hour, minute, true);
            case DATE_DIALOG_ID2:
                return new DatePickerDialog(
                        this, mDateSetListener2, mYear, mMonth, mDay);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener1 =
            new DatePickerDialog.OnDateSetListener()
            {

                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    String sdate = arrMonth[mMonth] + " " + LPad(mDay + "", "0", 2) + ", " + mYear;
                    et_tanggalpinjam.setText(sdate);
                }
            };

    private TimePickerDialog.OnTimeSetListener mTimeSetListener1 =
            new TimePickerDialog.OnTimeSetListener()
            {
                public void onTimeSet(TimePicker view, int hourOfDay, int minuteOfHour)
                {
                    hour = hourOfDay;
                    minute = minuteOfHour;
                    String stime = LPad(""+hour, "0", 2) + ":"+ LPad(""+minute, "0", 2);//hour yang kedua harusnya diganti minutes
                    et_jampinjam.setText(stime);
                }
            };

    private DatePickerDialog.OnDateSetListener mDateSetListener2 =
            new DatePickerDialog.OnDateSetListener()
            {

                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    String sdate = arrMonth[mMonth] + " " + LPad(mDay + "", "0", 2) + ", " + mYear;
                    et_tanggalkembali.setText(sdate);
                }
            };

    private TimePickerDialog.OnTimeSetListener mTimeSetListener2 =
            new TimePickerDialog.OnTimeSetListener()
            {
                public void onTimeSet(TimePicker view, int hourOfDay, int minuteOfHour)
                {
                    hour = hourOfDay;
                    minute = minuteOfHour;
                    String stime = LPad(""+hour, "0", 2) + ":"+ LPad(""+minute, "0", 2);//hour yang kedua harusnya diganti minutes
                    et_jamkembali.setText(stime);
                }
            };

    private static String LPad(String schar, String spad, int len) {
        String sret = schar;
        for (int i = sret.length(); i < len; i++) {
            sret = spad + sret;
        }
        return new String(sret);
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

            Toast.makeText(CBSv2_FormPeminjaman_Activity.this,
                    "Selesai Loading", Toast.LENGTH_LONG).show();
        }

    };
}
