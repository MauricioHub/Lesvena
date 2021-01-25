package com.example.nvm.lesvena;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
//import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.Base64;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import cz.msebera.android.httpclient.Header;


/**
 * A simple {@link Fragment} subclass.
 */
public class PictureFragment extends Fragment {

    public static ImageView btn_img;
    Button btnfoto;
    Intent takePictureIntent;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int RESULT_OK = -1;
    private String nameFoto = "imagen1.gif";
    private LesvenaUtil utiLes;
    private Context contexto;

    public PictureFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_picture, container, false);
        // btnfoto = (Button) view.findViewById(R.id.btnfoto);
        btn_img = (ImageView) view.findViewById(R.id.btn_img);
        utiLes = new LesvenaUtil(getActivity().getBaseContext());
        contexto = getActivity().getBaseContext();

        return view;
    }
/*
    private void aceptarFoto() {
        takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        /*File foto = new File(getExternalFilesDir(null), "fotop.jpg");
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(foto));
        startActivity(takePictureIntent);
        */

       // if(takePictureIntent.resolveActivity(getPackageManager()) != null){
       /*     startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        //}
    }

    /*
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        Bitmap imageBitmap = null;
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            imgimagen.setImageBitmap(imageBitmap);
        }
     //   savePicture(imageBitmap);
        utiLes.savePicture(imageBitmap);

        utiLes.loadPicture(
                new OnJSONObjectResponse() {
                    String urlPicture = null;
                    Context context = contexto;
                    ArrayList<String> urlPictureList = new ArrayList<>();

                    @Override
                    public void onJSONResponse(boolean success, JSONObject response) {
                        //do something with the JSON
                        if(success){
                            String url = null;
                            String path = null;
                            try {
                                if(MainActivity.data.getUrlPictureList() != null)
                                    urlPictureList = MainActivity.data.getUrlPictureList();
                                url = response.getString("url");
                                path = response.getString("path");
                                urlPictureList.add("" + url + path);
                                MainActivity.data.setUrlPictureList(urlPictureList);
                                //urlPicture = new String("" + url + path);
                                //Camera.urlImagen = url + "JORG";

                               /* Toast.makeText(context, "RESULTO CALLLOADPICTURE!" + MainActivity.data.getUrlPictureList().size(),
                                        Toast.LENGTH_SHORT).show();*/

                        /*    } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                    }
                }
        );
    }

    /*
    public void savePicture(Bitmap btMap){
        Bitmap btm = null;
        DateFormat nuevoDate = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        String tmpDateString = nuevoDate.format(today);
        String nuevoDateString = tmpDateString.replace(" ","_");
        String tempFoto = nuevoDateString.replace("/","_");
       // data.setNamePicture(nuevoDateString.replace("/","_"));

        try {
            //InputStream inputStream = getAssets().open("foto1.gif");
            //btm = BitmapFactory.decodeStream(inputStream);
            btm = btMap;

            ByteArrayOutputStream str = new ByteArrayOutputStream();
            btm.compress(Bitmap.CompressFormat.PNG, 100, str);
            byte[] btArray = str.toByteArray();

            FileOutputStream opStream = getActivity().getBaseContext().openFileOutput(
                    "imagen1.gif", Context.MODE_PRIVATE);
            String st = getActivity().getBaseContext().getFilesDir().getPath();
            opStream.write(btArray);
            opStream.close();

            loadPicture();

            //Toast.makeText(getActivity().getBaseContext(), "EXITO- " + tempFoto,
              //      Toast.LENGTH_SHORT).show();

        } catch(FileNotFoundException fnf) {
            Toast.makeText(getActivity().getBaseContext(), "ERROR FILENOTFOUND EXCEPTION- " + fnf.getMessage(),
                    Toast.LENGTH_SHORT).show();
            System.out.println("FNF" + fnf.getMessage());
        } catch(IOException io) {
            Toast.makeText(getActivity().getBaseContext(), "ERROR IOEXCEPTION- " + io.getMessage(),
                    Toast.LENGTH_SHORT).show();
            System.out.println("IOE" + io.getMessage());
        } catch(Exception e) {
            Toast.makeText(getActivity().getBaseContext(), "EXCEPTION- " + e.getMessage(),
                    Toast.LENGTH_SHORT).show();
            System.out.println("EXC" + e.getMessage());
        }

    }


    /*
    public void loadPicture(){
       // data.setToken(MainActivity.tokenizen);

        AsyncHttpClient client = new AsyncHttpClient();
        String subUrl = "http://13.85.84.67/lesvena/api/uploadImg/";
        String url = subUrl + "token=$2y$04$uPGp1ts9yqUyqwww4KKfmeUClEWPjv4bjrJIayApIpNxyGaLMdljG";
        //String url = "token=$2y$04$uPGp1ts9yqUyqwww4KKfmeUClEWPjv4bjrJIayApIpNxyGaLMdljG";
        //$2y$04$7RhUmxX.GCUiRWXpKBLHGua9BSD45oU1H9UEViyCiV4q9RFOzIpa.
        RequestParams requestParams = new RequestParams();
        String imgBase64 = convertImageToBase64();
        try {
            requestParams.setForceMultipartEntityContentType(true);
            requestParams.put("user","admin");
            requestParams.put("img",imgBase64);
            requestParams.put("token","$2y$04$uPGp1ts9yqUyqwww4KKfmeUClEWPjv4bjrJIayApIpNxyGaLMdljG");
        }catch (Exception e){
            Toast.makeText(getActivity().getBaseContext(),e.getMessage().toString(),
                    Toast.LENGTH_LONG).show();
        }

        RequestHandle post = client.post(subUrl, requestParams, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String url, path = "";
                int num = 0;

              //  Toast.makeText(getActivity().getBaseContext(),"EXI-" + statusCode,
                //        Toast.LENGTH_LONG).show();
                // Camera.numero = 10;
                // Toast.makeText(getApplicationContext(),"EXITO"+ statusCode,
                //       Toast.LENGTH_SHORT).show();
                // System.out.println("DENTRO-"+statusCode);

                if ( responseBody!=null && responseBody.length>0 ){
                    JSONArray jsonArray = null;
                    JSONObject jsonObj = null;
                    //data.setPathPicture(new String(responseBody));
                    try {
                        jsonObj = new JSONObject(new String(responseBody));
                        url = jsonObj.getString("url");
                        path = jsonObj.getString("path");
                        Camera.urlImagen = url + path;

                     //   Toast.makeText(getActivity().getBaseContext(),"EXT-" + url,
                       //         Toast.LENGTH_LONG).show();
                        System.out.println("EXT-" + url);
                        //data.setPathPicture("" + url + path);
                        //num=jsonObj.length();
                        // Toast.makeText(getApplicationContext(),"100-"+url,
                        //      Toast.LENGTH_LONG).show();

                    } catch(Exception ex){
                        Toast.makeText(getActivity().getBaseContext(),ex.getMessage().toString(),
                                Toast.LENGTH_LONG).show();
                    }
                }
/*
                if(statusCode == 200){
                    System.out.println("OK-"+new String(responseBody));
                }
                */
                // Toast.makeText(getApplicationContext(),"EXITO"+ num, Toast.LENGTH_SHORT).show();
/*
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                String body = new String(responseBody);
                Toast.makeText(getActivity().getBaseContext(),"Error"+ statusCode +"-"+body, Toast.LENGTH_SHORT).show();
                System.out.println("OK-"+new String(responseBody));
            }
        });

        // this.data.setPathPicture(urlImagen);
        // Toast.makeText(getApplicationContext(),"OK-"+ urlImagen,
        //  Toast.LENGTH_SHORT).show();
        //  System.out.println("FUERA-"+urlImagen);
        // System.out.println("DENTRO-"+data.getPathPicture());
        // Toast.makeText(getApplicationContext(),"OOKK-"+ data.getPathPicture(),
        //       Toast.LENGTH_SHORT).show();
        //return tokenizen;
        //pruebita();
        // return Camera.numero;

    }
    */


/*
    public String convertImageToBase64(){
        Bitmap btm = null;
        String imagenStr = "";

        try{
            FileInputStream ipStream = new FileInputStream(
                    getActivity().getBaseContext().getFilesDir().getPath()
                            + "/imagen1.gif");
            btm = BitmapFactory.decodeStream(ipStream);

            ByteArrayOutputStream soByteArray = new ByteArrayOutputStream();
            btm.compress(Bitmap.CompressFormat.JPEG, 100, soByteArray);
            byte[] btArray = soByteArray.toByteArray();
            imagenStr = Base64.encodeToString(btArray, Base64.DEFAULT);

            //System.out.println("EXT: " + imagenStr);
            //String st = getApplicationContext().getFilesDir().getPath();

            //imgfotoRes.setImageBitmap(btm);
/*
            Toast.makeText(this, "EXITO- " + st,
                    Toast.LENGTH_SHORT).show();
                    */
/*
        } catch(IOException io) {
            Toast.makeText(getActivity().getBaseContext(), "ERROR IOEXCEPTION- " + io.getMessage(),
                    Toast.LENGTH_SHORT).show();
        } catch(Exception e) {
            Toast.makeText(getActivity().getBaseContext(), "EXCEPTION- " + e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }
        return imagenStr;
    }*/

}
