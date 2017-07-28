package com.example.surya.cbsv2;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CBSv2_Login_Activity extends AppCompatActivity {

    private Button btn_forlogin, btn_forlihatkelas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cbsv2_login);

        btn_forlogin = (Button) findViewById(R.id.btn_forlogin);
        btn_forlihatkelas = (Button) findViewById(R.id.btn_forlihatkelas);

    }


    }

