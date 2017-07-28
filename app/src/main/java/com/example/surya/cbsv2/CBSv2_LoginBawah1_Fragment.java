package com.example.surya.cbsv2;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
//import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.InterruptedIOException;


/**
 * A simple {@link Fragment} subclass.
 */
public class CBSv2_LoginBawah1_Fragment extends Fragment {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private Button btn_forlogin, btn_forlihatkelas;
    private ProgressBar pb_login;
    private TextView tv_progress;
    private Thread bgthread;


    public CBSv2_LoginBawah1_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cbsv2_loginbawah1, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedState) {
        super.onActivityCreated(savedState);

//        final Button btn_forlogin = (Button) getActivity().findViewById(R.id.btn_forlogin);
//        final Button btn_forlihatkelas = (Button) getActivity().findViewById(R.id.btn_forlihatkelas);
        btn_forlogin = (Button) getActivity().findViewById(R.id.btn_forlogin);
        btn_forlihatkelas = (Button) getActivity().findViewById(R.id.btn_forlihatkelas);
        pb_login = (ProgressBar) getActivity().findViewById(R.id.pb_login);
        tv_progress = (TextView) getActivity().findViewById(R.id.tv_progress);


        fragmentManager = getActivity().getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();


        //1. Ketika tombol Login di klik
        btn_forlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                final ProgressDialog progressDialog = new ProgressDialog(getActivity());
                //perform upload action
                final Thread thread = new Thread()
                {
                    @Override
                    public void run() {
                        getActivity().runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                progressDialog.setTitle("Loading");
                                progressDialog.setMessage("Please Wait...");
                                progressDialog.isIndeterminate();
                                progressDialog.show();
                            }
                        });
            //        Thread.sleep(100);
                      //  callActivity();
//                        //nama method kalau udah selesai
//
                        getActivity().runOnUiThread(new Runnable() {
                            //
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                            }
                        });

                    }


                };

                thread.start();



                pb_login.setVisibility(view.GONE);
                tv_progress.setVisibility(view.GONE);

                CBSv2_LoginBawah2_Fragment loginFormFragment = new CBSv2_LoginBawah2_Fragment();
                fragmentTransaction.replace(R.id.fragment_cbsv2_loginbawah1, loginFormFragment);
                //fragmentTransaction.addToBackStack("login");
                fragmentTransaction.addToBackStack("null");
                fragmentTransaction.commit();
                btn_forlogin.setVisibility(view.GONE);
                btn_forlihatkelas.setVisibility(view.GONE);

//                btn_forlogin.setEnabled(false);
//                btn_forlihatkelas.setEnabled(false);
            }
        });

        //2. Ketika tombol Lihat Kelas di klik
        btn_forlihatkelas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), CBSv2_LihatKelas_Activity.class);
                startActivity(intent);

            }
        });
    }
    }

