package com.example.nvm.lesvena;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

//import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import androidx.fragment.app.Fragment;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import fr.ganfra.materialspinner.MaterialSpinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    private TextView et4_cientifico;
    private TextView et7_descripcion;
    private Spinner spinner_nc;
    //private Spinner spinner_nc;
    private Spinner spinner_est;
    private Button btn_register;
    private Button btn_register2;
    private Button loadButton;
    private DatasetAdmin admBase;
    private static ArrayList<String> comunList = new ArrayList<>();
    private static ArrayList<String> cientificoList = new ArrayList<>();
    private static ArrayList<String> descripcioList = new ArrayList<>();
    private static ArrayList<String> estadoSList = new ArrayList<>();
    private static ArrayList<Clasificacion> clasifList = new ArrayList<>();
    private static ArrayList<Estado> estadoList = new ArrayList<>();
    public static DataSetModel dataSet = new DataSetModel();
    private static Context contexto;

    // private Button loadData;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        // et3_comun = (EditText) findViewById(R.id.et3_comun);
        et4_cientifico = (TextView) view.findViewById(R.id.et4_cientifico);
        et7_descripcion = (TextView) view.findViewById(R.id.et7_descripcion);
        //et8_estado = (EditText) findViewById(R.id.et8_estado);
        spinner_nc = (Spinner) view.findViewById(R.id.spinner_nc);
        spinner_est = (Spinner) view.findViewById(R.id.spinner_est);
        // btn_register = (Button) view.findViewById(R.id.btn_register);
        btn_register = (Button) view.findViewById(R.id.btn_register);
        contexto = getActivity().getBaseContext();

        // loadData = (Button) view.findViewById(R.id.loadData);

        comunList.add("");
        cientificoList.add("");
        descripcioList.add("");
        estadoSList.add("");

/*        Toast.makeText(getActivity().getBaseContext(), "REGFRAG-" + MainActivity.data.getEstadoList().size(),
                Toast.LENGTH_SHORT).show();

/*
        ArrayList<String> clasificList= new ArrayList<String>();
        ArrayList<String> estadoSList= new ArrayList<String>();
        clasificList.add("SUMA");
        clasificList.add("RESTA");
        clasificList.add("MODULO");
        estadoSList.add("BUENO");
        estadoSList.add("MALO");
        estadoSList.add("SALUDABLE");
        */

        //  ArrayList<String> comunList = new ArrayList<>();
        // ArrayList<String> estadoSList = new ArrayList<>();

        try {

            ArrayAdapter<String> adapClasifi = new ArrayAdapter<>(getActivity().getBaseContext(),
                    android.R.layout.simple_spinner_item,comunList);
            ArrayAdapter <String> adapEstado = new ArrayAdapter<>(getActivity().getBaseContext(),
                    android.R.layout.simple_spinner_item,estadoSList);
            adapClasifi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            adapEstado.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_nc.setAdapter(adapClasifi);
            spinner_est.setAdapter(adapEstado);

/*
            btn_register.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v){
                    admBase.registrarClasificacion();//register();
                }
            });*/

            btn_register.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v){
                    register();
                }
            });

          /*  loadData.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v){
                    loadDataList();
                }
            });
            */



            spinner_nc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent,
                                           View view, int position, long id) {

                    //cientificoList.add(clasifiList.get(p).getNombre_cientifico());
                    ;
                    System.out.println("ENTRE" );
                    et4_cientifico.setText("Nombre Científico: " + cientificoList.get(position));
                    et7_descripcion.setText("Descripción: " + descripcioList.get(position));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        } catch(Exception e){
            Toast.makeText(getActivity().getBaseContext(), "ERR On RegisterFragment",
                    Toast.LENGTH_SHORT).show();
        }
        return view;
    }


    public static void loadArrayForSpinner(){
        DatasetAdmin adminBase = new DatasetAdmin(contexto);

        try {
            clasifList = dataSet.getClasificaList();
            estadoList = dataSet.getEstadoList();

          /*  if(clasifiList != null)
            Toast.makeText(contexto, "loAD-" + clasifiList.size(),
                    Toast.LENGTH_SHORT).show();*/

            for(int p=0; p<clasifList.size(); p++){
                Clasificacion nuevoC = new Clasificacion();
                nuevoC = (Clasificacion) clasifList.get(p);
                comunList.add(new String(""+nuevoC.getNombre_comun()));
                cientificoList.add(new String(""+nuevoC.getNombre_cientifico()));
                descripcioList.add(new String(""+nuevoC.getDescripcion()));
            }

            for(int q=0; q<estadoList.size(); q++){
                Estado nuevoE = new Estado();
                nuevoE = (Estado) estadoList.get(q);
                estadoSList.add(new String(""+nuevoE.getEstado()));
            }

        } catch(Exception e){
            Toast.makeText(contexto, "eRR-LOADREGISTER-",
                    Toast.LENGTH_SHORT).show();
        }

    }


    public void register() {

        try {
            // admin.createDataBase();
            //admin.openDataBase();
            DateFormat nuevoDate = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            Date today = Calendar.getInstance().getTime();
            String tmpDateString = nuevoDate.format(today);
            String nuevoDateString = tmpDateString.replace("/","-");

            NumberFormat format = NumberFormat.getInstance(Locale.US);
            Number latitudNmb = format.parse("-2.1477");
            Number longitudNmb = format.parse("-79.9658");
            double latitudPrm = latitudNmb.doubleValue();
            double longitudPrm = longitudNmb.doubleValue();

            Double latitud = new Double(latitudPrm);
            Double longitud = new Double(longitudPrm);

            //String tempFoto = nuevoDateString.replace("/","_");
            // this.data.setNamePicture(nuevoDateString.replace("/","_"));
            //MainActivity.data.setNamePicture(nuevoDateString.replace("/","_"));

            Dataset dataset = new Dataset();

            DatasetAdmin admBase = new DatasetAdmin(contexto);
            int numClasif = spinner_nc.getSelectedItemPosition();
            int numEstado = spinner_est.getSelectedItemPosition();
            Clasificacion clasif = clasifList.get(numClasif);
            Estado estado = estadoList.get(numEstado);
            Usuario usuario = MainActivity.data.getUsuario();
            long clasificado_id = admBase.registerClasificadoTable(clasif, usuario);

            if(MainActivity.data.getUrlPicture() == null)
                throw new NullPointerException();

            dataset.setClasificado_id((int) clasificado_id);
            dataset.setParte_id(estado.getId());
            dataset.setNombreImagen(MainActivity.data.getNamePicture());
            dataset.setUrlImagen(MainActivity.data.getUrlPicture());
            dataset.setPathImagen(MainActivity.data.getPathPicture());
            dataset.setLatitud(latitud);
            dataset.setLongitud(longitud);
            dataset.setFecha(nuevoDateString);

            long dataset_id = admBase.registerDatasetTable(dataset);
            System.out.println("NUM LATITUD" + latitud.toString());
            System.out.println("NUM LONGITUD" + longitud.toString());
            Toast.makeText(contexto, "EXITO-REG-" + dataset_id,
                    Toast.LENGTH_SHORT).show();

        } catch (NullPointerException e) {
            // TODO Auto-generated catch block
            Toast.makeText(contexto, "Capture una Fotografia.",
                    Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(contexto, "ErroR-REG",
                    Toast.LENGTH_SHORT).show();
        }

    }


}
