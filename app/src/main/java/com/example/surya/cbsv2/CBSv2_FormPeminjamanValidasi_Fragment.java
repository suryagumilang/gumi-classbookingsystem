package com.example.surya.cbsv2;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
//import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class CBSv2_FormPeminjamanValidasi_Fragment extends Fragment {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    public CBSv2_FormPeminjamanValidasi_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cbsv2_formpeminjamanvalidasi, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedState) {
        super.onActivityCreated(savedState);

//        final Button btn_forlogin = (Button) getActivity().findViewById(R.id.btn_forlogin);
//        final Button btn_forlihatkelas = (Button) getActivity().findViewById(R.id.btn_forlihatkelas);
        final ImageButton ib_back = (ImageButton)getActivity().findViewById(R.id.ib_back);


        fragmentManager = getActivity().getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();


        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), CBSv2_FormPeminjaman_Activity.class);
                startActivity(intent);

            }
        });
    }

}
