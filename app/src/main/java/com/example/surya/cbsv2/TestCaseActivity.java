package com.example.surya.cbsv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

/*
This class is temporary not used.
This is just a class for testing intent.
- Surya -
 */

public class TestCaseActivity extends AppCompatActivity {

    private TextView tv_test1, tv_test2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_case);

        tv_test1 = (TextView)findViewById(R.id.tv_test1);
        tv_test2 = (TextView)findViewById(R.id.tv_test2);


        Intent intent = getIntent();
        //boolean loginStatus = intent.getBooleanExtra("login", true);
        String [] data = intent.getStringArrayExtra("data");

        String username = data[0];
        String password = data[1];


        tv_test1.setText(username);
        tv_test2.setText(password);

    }
}
