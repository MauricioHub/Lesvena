package com.example.nvm.lesvena;

import android.content.Context;
import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private EditText et1_user, et2_pass;
    Button btnfoto;
  //  static final int REQUEST_IMAGE_CAPTURE = 1;
    public static DataSetModel data = new DataSetModel();
    public static String tokenizen;
    public static boolean banderita = true;
    public static String dateString = "";
    public DatasetAdmin admBase;
    public LesvenaUtil utiLes;
    public Context contexto;
    public static Context contexty;
    private View mProgressView;

    OnJSONObjectResponse callbackClase;
    private String urlTokenize = "";
    AsyncHttpClient client = new AsyncHttpClient();
    RequestParams requestParams = new RequestParams();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressView = findViewById(R.id.login_progress);
        ArrayList<Usuario> userList = new ArrayList<>();
        contexto = this;
        contexty = getBaseContext();
        mProgressView.setVisibility(View.GONE);

       try{
           createLesvenaDataBase();
           admBase = new DatasetAdmin(this);
           utiLes = new LesvenaUtil(this);
           userList = admBase.readUsuarioTable();
           if(userList == null){
               banderita = false;
               Intent i = new Intent(this, LoginActivity.class);
               startActivity(i);
           } else {
               String todayString = "2017-01-01 15:39:26";
               MainActivity.data.setUsuario(userList.get(0));
               sincronizeDataset(todayString);
           }

       } catch(Exception e){
           e.printStackTrace();

       }
    }


    public void createLesvenaDataBase() {
        AdmSQLiteOpenHelper admin;

        try {
            admin = new AdmSQLiteOpenHelper(this);
            admin.createDataBase();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(this, "ERR-createBAse",
                    Toast.LENGTH_SHORT).show();
            System.out.println("ERR-createBAse"
                    + e.getMessage());
        }
    }


    public void sincronizeDataset(String todayCalendar){
        ArrayList<Sincronizacion> syncList = new ArrayList<>();
        String nuevoStr = "";
        MainActivity.dateString = todayCalendar;

        try {
            syncList = admBase.readSincronizeTable();
            if(syncList != null)
                nuevoStr = utiLes.convertListToJSON(syncList);
            MainActivity.data.setValuePostSincronize(nuevoStr);

            utiLes.saveResponseSincronize(
                    new OnJSONObjectResponse() {
                        ArrayList<Clasificacion> clasifList = new ArrayList<>();
                        ArrayList<Estado> estadoList = new ArrayList<>();
                        Context myContexto = contexto;
                        JSONObject obJSONEstado, obJSONClasificacion;
                        @Override
                        public void onJSONResponse(boolean success, JSONObject response) {
                            String estado, clasificacion = "";
                            try {

                                if(response.length() == 0) {
                                    Intent i = new Intent(myContexto, MenuProjectActivity.class);
                                    startActivity(i);
                                    RegisterFragment.dataSet.setClasificaList(admBase.readClasificacionTable());
                                    RegisterFragment.dataSet.setEstadoList(admBase.readEstadoTable());
                                    RegisterFragment.loadArrayForSpinner();
                                }

                                if(success){
                                    estado = response.getString("estado");
                                    clasificacion = response.getString("clasificacion");
                                    obJSONEstado = new JSONObject(new String(estado));
                                    obJSONClasificacion = new JSONObject(new String(clasificacion));
                                    estadoList = utiLes.loadListEstado(obJSONEstado);
                                    clasifList = utiLes.loadListClasificacion(obJSONClasificacion);

                                    if(estadoList != null){
                                        if(estadoList.size() > 0)
                                            admBase.registerFromListToEstado(estadoList);
                                    }

                                    if(clasifList != null){
                                        if(clasifList.size() > 0)
                                            admBase.registerFromListToClasificacion(clasifList);
                                    }
                                    Intent i = new Intent(myContexto, MenuProjectActivity.class);
                                    startActivity(i);
                                    RegisterFragment.dataSet.setClasificaList(admBase.readClasificacionTable());
                                    RegisterFragment.dataSet.setEstadoList(admBase.readEstadoTable());
                                    RegisterFragment.loadArrayForSpinner();
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
            );

        } catch(NullPointerException e){
            Toast.makeText(contexto, "NULLPOINTER-"+ e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        } catch(Exception e){
            Toast.makeText(contexto, "syncDATASET-"+ e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }
    }


}
