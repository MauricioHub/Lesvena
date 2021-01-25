package com.example.nvm.lesvena;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
//import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class VisualizeFragment extends Fragment {

    private ImageView imgimagen;
    public Intent takePictureIntent;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int RESULT_OK = -1;
    private LesvenaUtil utiLes;
    private Context contexto;

    public VisualizeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_visualize, container, false);
        imgimagen = (ImageView) view.findViewById(R.id.imgfoto);
        contexto = getActivity().getBaseContext();
        utiLes = new LesvenaUtil(contexto);

        PictureFragment.btn_img.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){
                aceptarFoto();
            }

        });

        return view;
    }

    private void aceptarFoto() {
        takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
    }


    /*public void bridgeFoto(){
        takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);

    }*/

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        Bitmap imageBitmap = null;
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            imgimagen.setImageBitmap(imageBitmap);
        }
        utiLes.savePicture(imageBitmap);

/*        Toast.makeText(contexto, "REslOAD-" ,
                Toast.LENGTH_SHORT).show();*/
        utiLes.loadPicture(
                new OnJSONObjectResponse() {
                    String urlPicture = null;
                    Context context = contexto;
                    ArrayList<String> urlRPictureList = new ArrayList<>();

                    @Override
                    public void onJSONResponse(boolean success, JSONObject response) {
                        //do something with the JSON
                        if(success){
                            String url = null;
                            String path = null;
                            try {/*
                                if(MainActivity.data.getUrlRPictureList() != null)
                                    urlRPictureList = MainActivity.data.getUrlRPictureList();*/
                                url = response.getString("url");
                                path = response.getString("path");
                                //urlRPictureList.add("" + url + path);

                                MainActivity.data.setUrlPicture("" + url);
                                MainActivity.data.setPathPicture(""  + path);
                                //urlPicture = new String("" + url + path);
                                //Camera.urlImagen = url + "JORG";


                                Toast.makeText(context, "REslOAD-" + MainActivity.data.getUrlPicture(),
                                        Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                    }
                }
        );


    }

}
