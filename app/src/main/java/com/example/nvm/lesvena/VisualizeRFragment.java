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

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class VisualizeRFragment extends Fragment {

    private ImageView imgimagen_r;
    public Intent takePictureIntent;

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int RESULT_OK = -1;
    private LesvenaUtil utiLes;
    private Context contexto;


    public VisualizeRFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_visualize_r, container, false);
        imgimagen_r = (ImageView) view.findViewById(R.id.imgfoto_r);
        contexto = getActivity().getBaseContext();

        PictureRFragment.btn_img_r.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){
                aceptarFoto();
            }

        });

        return view;
    }


    private void aceptarFoto() {
        takePictureIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePictureIntent,
                REQUEST_IMAGE_CAPTURE);
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
            imgimagen_r.setImageBitmap(imageBitmap);
        }
        /*utiLes.savePicture(imageBitmap);
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
                            try {
                                if(MainActivity.data.getUrlRPictureList() != null)
                                    urlRPictureList = MainActivity.data.getUrlRPictureList();
                                url = response.getString("url");
                                path = response.getString("path");
                                urlRPictureList.add("" + url + path);
                                MainActivity.data.setUrlRPictureList(urlRPictureList);
                                //urlPicture = new String("" + url + path);
                                //Camera.urlImagen = url + "JORG";

                               /* Toast.makeText(context, "RESULTO LOADRPICTURE-" + MainActivity.data.getUrlRPictureList().size(),
                                        Toast.LENGTH_SHORT).show();*/
/*
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                    }
                }
        );*/

    }

}
