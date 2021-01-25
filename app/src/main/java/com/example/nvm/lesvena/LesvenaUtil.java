package com.example.nvm.lesvena;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.Base64;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
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
import java.util.Iterator;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Mauricio on 9/4/2017.
 */

public class LesvenaUtil {

    private Context context;
    private DataSetModel data;
    private DatasetAdmin admBase;
    private String urlTokenize = "http://13.85.84.67/lesvena/api/login/";
    OnJSONObjectResponse callbackClase;
    ArrayList<Clasificacion> clasifList = new ArrayList<>();
    ArrayList<Estado> estadoList = new ArrayList<>();

    public LesvenaUtil(Context context){
        this.context = context;
        admBase = new DatasetAdmin(this.context);
        if(MainActivity.data == null)
            MainActivity.data = new DataSetModel();
    }


    public void savePicture(Bitmap btMap){
        Bitmap btm = null;
        DateFormat nuevoDate = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        String tmpDateString = nuevoDate.format(today);
        String nuevoDateString = tmpDateString.replace(" ","_");
        MainActivity.data.setNamePicture(nuevoDateString.replace("/","_"));

        try {

            btm = btMap;
            ByteArrayOutputStream str = new ByteArrayOutputStream();
            btm.compress(Bitmap.CompressFormat.PNG, 100, str);
            byte[] btArray = str.toByteArray();

            FileOutputStream opStream = this.context.openFileOutput(
                    MainActivity.data.getNamePicture() + ".gif", Context.MODE_PRIVATE);
            String st = this.context.getFilesDir().getPath();
            /*Toast.makeText(this.context, "EITO- " + st ,
                    Toast.LENGTH_SHORT).show();*/

            opStream.write(btArray);
            opStream.close();


        } catch(FileNotFoundException fnf) {
            Toast.makeText(this.context, "ERROR FILENOTFOUND- " + fnf.getMessage(),
                    Toast.LENGTH_SHORT).show();
            System.out.println("FNF" + fnf.getMessage());
        } catch(IOException io) {
            Toast.makeText(this.context, "ERROR IOEXCEPTION- " + io.getMessage(),
                    Toast.LENGTH_SHORT).show();
            System.out.println("IOE" + io.getMessage());
        } catch(Exception e) {
          /*  Toast.makeText(this.context, "EXCEPTION- " + e.getMessage(),
                    Toast.LENGTH_SHORT).show();*/
            System.out.println("EXC" + e.getMessage());
        }

    }


    public JSONObject loadPicture(OnJSONObjectResponse callback) {

        ArrayList<String> imgBase64List = new ArrayList<>();
        ArrayList<Usuario> usuarioList = admBase.readUsuarioTable();
        AsyncHttpClient client = new AsyncHttpClient();
        String subUrl = "http://13.85.84.67/lesvena/api/uploadImg/";
        String url = subUrl + "$2y$04$uPGp1ts9yqUyqwww4KKfmeUClEWPjv4bjrJIayApIpNxyGaLMdljG";
        String nameImg = MainActivity.data.getNamePicture();
        RequestParams requestParams = new RequestParams();
        String imgBase64 = convertImageToBase64(nameImg);
        callbackClase = callback;
        JSONObject nuevoObject = new JSONObject();

        Usuario nuevoUsuario = usuarioList.get(0);
       // nuevoUsuario = MainActivity.data.getUsuario();

        try {/*
            if(MainActivity.data.getImgBase64List() != null)
                imgBase64List = MainActivity.data.getImgBase64List();
            imgBase64List.add(imgBase64);
            MainActivity.data.setImgBase64List(imgBase64List);*/

            requestParams.setForceMultipartEntityContentType(true);
            requestParams.put("user",nuevoUsuario.getUsername());
            requestParams.put("img",imgBase64);
            requestParams.put("token", nuevoUsuario.getTokenize());
        }catch (Exception e){
            Toast.makeText(this.context,e.getMessage().toString(),
                    Toast.LENGTH_LONG).show();
        }

        RequestHandle post = client.post(subUrl, requestParams, new AsyncHttpResponseHandler() {
            Context contextoTmp= context;

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String url, path = "";
                int num = 0;

                if ( responseBody!=null && responseBody.length>0 ){
                    JSONArray jsonArray = null;
                    JSONObject jsonObj = null;
                    try {
                        jsonObj = new JSONObject(new String(responseBody));
                        callbackClase.onJSONResponse(true,jsonObj);

                    } catch(Exception ex){
                        Toast.makeText(contextoTmp,ex.getMessage().toString(),
                                Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                String body = new String(responseBody);
                Toast.makeText(contextoTmp,"Error"+ statusCode +"-"+body, Toast.LENGTH_SHORT).show();
                System.out.println("OK-"+new String(responseBody));
            }
        });
        return nuevoObject;
    }


    public ArrayList<Estado> loadListEstado(JSONObject objJson){
        ArrayList<Estado> estadoList = new ArrayList<>();
        Estado nuevoEstado = new Estado();
        JSONObject jsonObj = null;

        try {
            jsonObj = objJson;

            Iterator<?> indices = jsonObj.keys();
            while(indices.hasNext() ){
                String keyIndice = (String)indices.next();
                System.out.println("tabLAS-" + keyIndice);
                nuevoEstado = new Estado();

                Iterator<?> arreglos = jsonObj.getJSONObject(keyIndice).keys();
                while(arreglos.hasNext()){

                    String keyArreglo = (String)arreglos.next();
                    String valueArreglo = jsonObj.getJSONObject(keyIndice).getString(keyArreglo);
                    System.out.println("indice423-" + keyArreglo + ": " + valueArreglo);

                    switch(keyArreglo){
                        case "id" :
                            nuevoEstado.setId(Integer.parseInt(valueArreglo));
                            break;
                        case "parte" :
                            nuevoEstado.setEstado(valueArreglo);
                            break;
                        case "creation_time" :
                            break;
                        case "modification_time" :
                            break;
                        default:
                            break;
                    }

                }
                estadoList.add(nuevoEstado);
            }
        } catch(Exception ex){
            Toast.makeText(this.context, "ERR-LOadEstadoList" + ex.getMessage().toString(),
                    Toast.LENGTH_LONG).show();
            System.out.println("ERR-LOadEstadoList" + ex.getMessage().toString());
        }
        return estadoList;
    }

    public ArrayList<Clasificacion> loadListClasificacion(JSONObject objJson){
        ArrayList<Clasificacion> clasifList = new ArrayList<>();
        Clasificacion nuevoClasif = new Clasificacion();
        JSONObject jsonObj = null;

        try {
            jsonObj = objJson;

            Iterator<?> indices = jsonObj.keys();
            while(indices.hasNext() ){
                String keyIndice = (String)indices.next();
                System.out.println("tabLAS-" + keyIndice);
                nuevoClasif = new Clasificacion();

                Iterator<?> arreglos = jsonObj.getJSONObject(keyIndice).keys();
                while(arreglos.hasNext()){

                    String keyArreglo = (String)arreglos.next();
                    String valueArreglo = jsonObj.getJSONObject(keyIndice).getString(keyArreglo);
                    System.out.println("indice423-" + keyArreglo + ": " + valueArreglo);

                    switch(keyArreglo){
                        case "id" :
                            nuevoClasif.setId(Integer.parseInt(valueArreglo));
                            break;
                        case "nombre_comun" :
                            nuevoClasif.setNombre_comun(valueArreglo);
                            break;
                        case "nombre_cientifico" :
                            nuevoClasif.setNombre_cientifico(valueArreglo);
                            break;
                        case "descripcion" :
                            nuevoClasif.setDescripcion(valueArreglo);
                            break;
                        case "creation_time" :
                            break;
                        case "modification_time" :
                            break;
                        default:
                            break;
                    }

                }
                clasifList.add(nuevoClasif);
            }
        } catch(Exception ex){
            Toast.makeText(this.context, "ERR-LOadClasifList" + ex.getMessage().toString(),
                    Toast.LENGTH_LONG).show();
            System.out.println("ERR-LOadClasifList" + ex.getMessage().toString());
        }
        return clasifList;
    }


    public String convertListToJSON(ArrayList<Sincronizacion> syncList){
        ArrayList<String> syncListStr = new ArrayList<>();
        String nuevoStrTotal = "";
        int count = 0;

        for (int p=0; p<syncList.size(); p++){
            Sincronizacion nuevo = syncList.get(p);
            String nuevoStr = new String("{\"tabla\":\"" + nuevo.getTabla() +
                    "\",\"fecha\":\"" + nuevo.getFecha() + "\"}");
            syncListStr.add(nuevoStr);
        }

        for (int q=0; q<syncListStr.size(); q++){
            int flag = syncListStr.size();

            String coma;
            if((flag-1) > count)
                coma = ",";
            else
                coma = "";

            nuevoStrTotal = nuevoStrTotal + new String(
                syncListStr.get(q)
                        + coma
            );
            count ++;
        }
        return new String(
                "[" + nuevoStrTotal
                + "]"
        );
    }



    public String convertImageToBase64(String nameImg){
        Bitmap btm = null;
        String imagenStr = "";

        try{
            FileInputStream ipStream = new FileInputStream(
                    this.context.getFilesDir().getPath()
                            + "/" + nameImg + ".gif");
            btm = BitmapFactory.decodeStream(ipStream);

            ByteArrayOutputStream soByteArray = new ByteArrayOutputStream();
            btm.compress(Bitmap.CompressFormat.JPEG, 100, soByteArray);
            byte[] btArray = soByteArray.toByteArray();
            imagenStr = Base64.encodeToString(btArray, Base64.DEFAULT);


        } catch(IOException io) {
            Toast.makeText(this.context, "ERROR IOEXCEPTION- " + io.getMessage(),
                    Toast.LENGTH_SHORT).show();
        } catch(Exception e) {
            Toast.makeText(this.context, "EXCEPTION- " + e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }
        return imagenStr;
    }


    public JSONObject saveResponseToken(OnJSONObjectResponse
                                                callback){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams requestParams = new RequestParams();
        JSONObject nuevoObject = new JSONObject();
        callbackClase = callback;

        try {
        requestParams.
             setForceMultipartEntityContentType(true);
        requestParams.put("user", MainActivity.data.getUsername());
        requestParams.put("pwd", MainActivity.data.getPassword());
        }catch (Exception e){
            Toast.makeText(this.context,e.getMessage().toString(),
                    Toast.LENGTH_LONG).show();
        }

        client.post(urlTokenize, requestParams,
                new AsyncHttpResponseHandler() {

            Context contexto = context;
            @Override
            public void onSuccess(int statusCode, Header[] headers,
                                  byte[] responseBody) {

                if ( responseBody!=null && responseBody.length>0 ){
                    JSONObject jsonObj = null;
                    try {
                        jsonObj = new JSONObject(new String(responseBody));
                        callbackClase.onJSONResponse(true, jsonObj);

                    } catch(Exception ex){
                        Toast.makeText(contexto,
                                ex.getMessage().toString(),
                                Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers,
                        byte[] responseBody, Throwable error) {

                try {
                    callbackClase.onJSONResponse(false, new JSONObject());

                } catch(Exception e){
                    Toast.makeText(contexto,"Error"+ statusCode,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        return nuevoObject;
    }


    public JSONObject saveResponseSincronize(OnJSONObjectResponse callback){
            AsyncHttpClient client = new AsyncHttpClient();
            String subUrl = "http://13.85.84.67/lesvena/api/sincronizar/";
            String url = subUrl + "token=$2y$04$uPGp1ts9yqUyqwww4KKfmeUClEWPjv4bjrJIayApIpNxyGaLMdljG";
            callbackClase = callback;
            JSONObject nuevoObject = new JSONObject();
            String valuePostSincronize = new String(MainActivity.data.getValuePostSincronize());
            Usuario nuevoUsuario = MainActivity.data.getUsuario();

            RequestParams requestParams = new RequestParams();
            try {
                requestParams.setForceMultipartEntityContentType(true);
                requestParams.put("user",nuevoUsuario.getUsername());
                requestParams.put("values",valuePostSincronize);
                requestParams.put("token",nuevoUsuario.getTokenize());
                System.out.println("ENTRE-" +MainActivity.data.getValuePostSincronize());
            }catch (Exception e){
                Toast.makeText(this.context,e.getMessage().toString(),
                        Toast.LENGTH_LONG).show();
            }

            RequestHandle post = client.post(subUrl, requestParams, new AsyncHttpResponseHandler() {
                Context contextoTmp= context;
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    int num = 0;

                    if(responseBody == null || responseBody.length == 0) {
                        throw new NullPointerException();
                    }


                    if ( statusCode == 200 ){
                        JSONObject jsonObj = null;

                        try {
                            jsonObj = new JSONObject(new String(responseBody));
                            callbackClase.onJSONResponse(true,jsonObj);

                        } catch(Exception e){
                            callbackClase.onJSONResponse(true,new JSONObject());
                        }
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                    callbackClase.onJSONResponse(false, new JSONObject());
                    Toast.makeText(contextoTmp,"FAILURE-SYNC" + statusCode, Toast.LENGTH_SHORT).show();
                }
            });
            return nuevoObject;
    }


    public ArrayList<Sincronizacion> loadSincronizeBegin() {
        ArrayList<Sincronizacion> syncList = new ArrayList<>();
        try {
            Sincronizacion nuevoSync1 = new Sincronizacion();
            nuevoSync1.setId(1);
            nuevoSync1.setTabla("clasificacion");
            nuevoSync1.setFecha(MainActivity.dateString);
            Sincronizacion nuevoSync2 = new Sincronizacion();
            nuevoSync2.setId(2);
            nuevoSync2.setTabla("estado");
            nuevoSync2.setFecha(MainActivity.dateString);
            syncList.add(nuevoSync1);
            syncList.add(nuevoSync2);
        } catch(Exception e){
            e.printStackTrace();
        }
        return syncList;
    }



    public ArrayList<Estado> loadListEstadoTmp(JSONObject objJson){
        ArrayList<Estado> estadoList = new ArrayList<>();
        Estado nuevoEstado = new Estado();
        JSONObject jsonObj = null;

        try {
            jsonObj = objJson;

            Iterator<?> indices = jsonObj.keys();
            while(indices.hasNext() ){
                String keyIndice = (String)indices.next();
                System.out.println("tabLAS-" + keyIndice);
                nuevoEstado = new Estado();

                JSONArray nuevoArray = jsonObj.getJSONArray(keyIndice);
                for (int i = 0; i < nuevoArray.length(); i++) {
                    JSONObject jsonobject = nuevoArray.getJSONObject(i);
                    String id = "", parte = "";

                    switch(i){
                        case 0:
                            id = jsonobject.getString("id");
                            nuevoEstado.setId(Integer.parseInt(id));
                            break;
                        case 1:
                            parte = jsonobject.getString("parte");
                            nuevoEstado.setEstado(parte);
                            break;
                        default:
                            break;
                    }
                   // String parte = jsonobject.getString("parte");
                   // System.out.println("ID-" +id + " PARTE-" + parte);
                }
                estadoList.add(nuevoEstado);
            }
        } catch(Exception ex){
            Toast.makeText(this.context, "ERR-LOadEstadoList" + ex.getMessage().toString(),
                    Toast.LENGTH_LONG).show();
            System.out.println("ERR-LOadEstadoList" + ex.getMessage().toString());
        }
        return estadoList;
    }


    public ArrayList<Clasificacion> loadListClasificacionTmp(JSONObject objJson){
        ArrayList<Clasificacion> clasifList = new ArrayList<>();
        Clasificacion nuevoClasif = new Clasificacion();
        JSONObject jsonObj = null;

        try {
            jsonObj = objJson;

            Iterator<?> indices = jsonObj.keys();
            while(indices.hasNext() ){
                String keyIndice = (String)indices.next();
                System.out.println("tabLAS-" + keyIndice);
                nuevoClasif = new Clasificacion();

                JSONArray nuevoArray = jsonObj.getJSONArray(keyIndice);
                for (int i = 0; i < nuevoArray.length(); i++) {
                    JSONObject jsonobject = nuevoArray.getJSONObject(i);
                    String id = "", comun = "", cientif = "", descrip = "";

                    switch(i){
                        case 0:
                            id = jsonobject.getString("id");
                            nuevoClasif.setId(Integer.parseInt(id));
                            break;
                        case 1:
                            comun = jsonobject.getString("nombre_comun");
                            nuevoClasif.setNombre_comun(comun);
                            break;
                        case 2:
                            cientif = jsonobject.getString("nombre_cientifico");
                            nuevoClasif.setNombre_cientifico(cientif);
                            break;
                        case 3:
                            descrip = jsonobject.getString("descripcion");
                            nuevoClasif.setDescripcion(descrip);
                            break;
                        default:
                            break;
                    }
                }
                clasifList.add(nuevoClasif);
            }
        } catch(Exception ex){
            Toast.makeText(this.context, "ERR-LOadClasifList" + ex.getMessage().toString(),
                    Toast.LENGTH_LONG).show();
            System.out.println("ERR-LOadClasifList" + ex.getMessage().toString());
        }
        return clasifList;
    }

    public Bitmap getPictureDataset(String namePicture){
        Bitmap bitmap = null;
        try{
            FileInputStream fileInputStream =
                    new FileInputStream(context.getFilesDir().getPath()
                            + "/" + namePicture + ".gif");
            bitmap = BitmapFactory.decodeStream(fileInputStream);
        }catch (IOException io){
            io.printStackTrace();
        }
        return  bitmap;
    }

}
