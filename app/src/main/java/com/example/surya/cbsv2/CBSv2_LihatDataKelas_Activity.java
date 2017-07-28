package com.example.surya.cbsv2;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class CBSv2_LihatDataKelas_Activity extends AppCompatActivity {
    PinjamKelasDB myDB;
    ArrayList<String> theList;
    Button btnAddKelas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cbsv2_lihatdatakelas);


        btnAddKelas = (Button) findViewById(R.id.button_add);
        Intent intent = getIntent();

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
            buffer.append("Ruang " + data.getString(1) + " dipinjam pada : \n");
            buffer.append(data.getString(2) + " " + data.getString(3) + " hingga \n");
            buffer.append(data.getString(4) + " " + data.getString(5) + "\n\n");
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Data Peminjaman");
        builder.setMessage(buffer.toString());
        builder.show();

        btnAddKelas.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String selectedKelas = dataIntent;
//                        Intent intenAddKelas = new Intent(getApplicationContext(), Activity_InsertDataKelas.class);
//                        intenAddKelas.putExtra("selectedKelas", dataIntent);
//                        startActivity(intenAddKelas);
                        Intent intenAddKelas = new Intent(getApplicationContext(), CBSv2_FormPeminjaman_Activity.class);
                        intenAddKelas.putExtra("addKelas", dataIntent);
                        startActivity(intenAddKelas);
                    }
                }
        );


    }
}
