package com.example.nvm.lesvena;


import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    private static TextView et1_comun;
    private static TextView et2_cientifico;
    private static TextView et3_descripcion;
    private ImageView imgDetail;


    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        et1_comun = (TextView) view.findViewById(R.id.et1_comun);
        et2_cientifico = (TextView) view.findViewById(R.id.et2_cientifico);
        et3_descripcion = (TextView) view.findViewById(R.id.et3_descripcion);
        imgDetail = (ImageView) view.findViewById(R.id.imgdetail);

        return view;
    }

    public static void loadDetailView(String name){
        et1_comun.setText      ("Nombre Común : " + name);
        et2_cientifico.setText ("Nm Científico:" + name);
        et3_descripcion.setText("Descripción  :" + name);
    }

}
