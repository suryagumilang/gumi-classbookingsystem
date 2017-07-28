package com.example.surya.cbsv2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/*
This class is temporary not used.
- Surya -
 */


public class CBSv2_Login2_Activity extends AppCompatActivity {


    private EditText et_username;
    private EditText et_password;
    String username, password;
    private Button btn_login_fragment, btn_batal_fragment;

    ProgressDialog progressDialog;
    BackgroundThread backgroundThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cbsv2_login2);

        //finalButtonb= (Button)getActivity().findViewById(R.id.btnFragment);
        btn_login_fragment = (Button)findViewById(R.id.btn_login_fragment);
        btn_batal_fragment = (Button)findViewById(R.id.btn_batal_fragment);

        et_username = (EditText)findViewById(R.id.et_username);
        et_password = (EditText)findViewById(R.id.et_password);

        progressDialog=ProgressDialog.show(CBSv2_Login2_Activity.this,"Progress Dialog","wait");
        backgroundThread=new BackgroundThread();
        backgroundThread.start();

        backgroundThread=new BackgroundThread();
        backgroundThread.setRunning(true);
        backgroundThread.start();
        //1. Ketika tombol Login di klik
        btn_login_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*
                Menyimpan inputan user dari
                et_username => (String) username
                et_password => (String) password
                 */
                username = et_username.getText().toString();
                password = et_password.getText().toString();

                CBSv2_LoginController loginController = new CBSv2_LoginController(username, password);
                boolean status = loginController.doLogin();
                //String loginStatus = "uwes";
                Intent intent = new Intent(CBSv2_Login2_Activity.this, TestCaseActivity.class);
                intent.putExtra("login",status);
                startActivity(intent);
//                if(status == true){
//                    String login = "login";
//
//                    Intent intent = new Intent(getActivity(), CBSv2_LihatKelas_Activity.class);
//                    intent.putExtra("login",login);
//                    startActivity(intent);
//                }else{
//                    et_username.setText("Login gagal !");
//                    et_password.setText("Login gagal !");
//                }

            }
        });

        //2. Ketika tombol Batal di klik
        btn_batal_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(CBSv2_Login2_Activity.this, CBSv2_Login_Activity.class);
                startActivity(intent);

            }
        });

    }



    public class BackgroundThread extends Thread{
        volatile boolean running = false;
        int cnt;

        void setRunning(boolean b) {
            running = b;
            cnt = 1000;
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

            Toast.makeText(CBSv2_Login2_Activity.this,
                    "dismissed", Toast.LENGTH_LONG).show();
        }

    };
}

