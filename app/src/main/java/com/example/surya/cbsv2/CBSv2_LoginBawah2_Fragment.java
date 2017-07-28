package com.example.surya.cbsv2;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
//import android.support.v4.app.Fragment;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class CBSv2_LoginBawah2_Fragment extends Fragment {

    //Deklarasi variable
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private EditText et_username;
    private EditText et_password;
    String username, password;
    private Button btn_login_fragment, btn_batal_fragment;

    ProgressDialog progressDialog;
    CBSv2_FormPeminjaman_Activity.BackgroundThread backgroundThread;


    String [][] user = {
            {"admin","admin"},
            {"dimas","dimas"},
            {"anggit","anggit"},
            {"mimin","mimin"},
            {"lilis","lilis"}
    };

    public CBSv2_LoginBawah2_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cbsv2_loginbawah2, container, false);
    }


    @Override
    public void onActivityCreated(Bundle savedState) {
        super.onActivityCreated(savedState);


        //finalButtonb= (Button)getActivity().findViewById(R.id.btnFragment);
        btn_login_fragment = (Button)getActivity().findViewById(R.id.btn_login_fragment);
        btn_batal_fragment = (Button)getActivity().findViewById(R.id.btn_batal_fragment);

        et_username = (EditText)getActivity().findViewById(R.id.et_username);
        et_password = (EditText)getActivity().findViewById(R.id.et_password);

        fragmentManager = getActivity().getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

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

//                String []data = {username, password};
//                //Intent intent = new Intent(getActivity().getApplicationContext(), CBSv2_LihatKelas_Activity.class);
//                Intent intent = new Intent(getActivity().getApplicationContext(), TestCaseActivity.class);
//                    intent.putExtra("data", data);
//                    startActivity(intent);

                if(et_username.getText().toString().equals("admin") && et_password.getText().toString().equals("admin")){
//                    String login = "login";
//
//                    Intent intent = new Intent(getActivity(), CBSv2_LihatKelas_Activity.class);
//                    intent.putExtra("login",login);
//                    startActivity(intent);

                    String []data = {username, password};
                    //Intent intent = new Intent(getActivity().getApplicationContext(), CBSv2_LihatKelas_Activity.class);
                    Intent intent = new Intent(getActivity().getApplicationContext(), CBSv2_LihatKelas_Activity.class);
                    intent.putExtra("data", data);
                    startActivity(intent);
                }else{
                    et_username.setText("Login gagal !");
                    et_password.setText("Login gagal !");
                }

            }
        });

        //2. Ketika tombol Batal di klik
        btn_batal_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), CBSv2_Login_Activity.class);
                startActivity(intent);

            }
        });

    }



}
