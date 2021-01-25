package com.example.nvm.lesvena;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Mauricio on 9/4/2017.
 */

public class DatasetAdmin {

    private Context context;
   // private DataSetModel data;
  //  public static DataSetModel dataSet = new DataSetModel();

    public DatasetAdmin(Context context) {
    //    this.data = MainActivity.data;
        this.context = context;
    }

    public void registrarClasificacion() {
/*        int valor = spinner_nc.getSelectedItemPosition();
        ArrayList<Clasificacion> nuevoClasifica;
        nuevoClasifica = data.getClasificaList();
        Clasificacion clasifica = nuevoClasifica.get(0);
        */

        //  int numClasif = spinner_nc.getSelectedItemPosition();
        //  int numEstado = spinner_est.getSelectedItemPosition();
        Clasificacion clasifi;
        Estado estado;
        // clasifi = data.getClasificaList().get(numClasif);
        // estado = data.getEstadoList().get(numEstado);

        /*Toast.makeText(this, "REG-" + numClasif,
                Toast.LENGTH_SHORT).show();
        System.out.println("REG-" + numEstado);*/


        AdmSQLiteOpenHelper admin = new AdmSQLiteOpenHelper(this.context);

        try {
            admin.createDataBase();
            //admin.openDataBase();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //      "lesvena3", null, 1)
        //AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
        //      "lesvena3", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
      /*  String cod = et3_comun.getText().toString();
        String descri = et4_cientifico.getText().toString();
        String pre = et7_descripcion.getText().toString();
        String est = et8_estado.getText().toString();*/

        // Estado estado = new Estado();
        //Clasificacion clasifica = new Clasificacion();
/*        estado.setEstado(est);
        clasifica.setNombre_comun(cod);
        clasifica.setNombre_cientifico(descri);
        clasifica.setDescripcion(pre);
        data.setEstado(estado);
        data.setClasifica(clasifica);
        */

        ContentValues registro = new ContentValues();
        //registro.put("id", 2);
        registro.put("nombre_comun", "FLOR");
        registro.put("nombre_cientifico", "silvestre");
        registro.put("descripcion", "AMARI");
        bd.insert("clasificacion", null, registro);
        bd.close();
       /* et3_comun.setText("");
        et4_cientifico.setText("");
        et7_descripcion.setText("");
        et8_estado.setText("");*/
        Toast.makeText(this.context, "Se cargaron los datos de Clasificacion",
                Toast.LENGTH_SHORT).show();
/*
        Intent k = new Intent(this, Summary.class);
        startActivity(k);
        */
    }

    public void registerSincronizacionTable() {

        String tabla1 = "clasificacion";
        String tabla2 = "estado";
        DateFormat nuevoDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        String tmpDateString = nuevoDate.format(today);
        String nuevoDateString = tmpDateString.replace("/","-");
        AdmSQLiteOpenHelper admin;
        ContentValues registro;

        try {
            admin = new AdmSQLiteOpenHelper(this.context);
            admin.createDataBase();

            SQLiteDatabase bd = admin.getWritableDatabase();
            registro = new ContentValues();
            registro.put("tabla", tabla1);
            registro.put("fecha", nuevoDateString);
            bd.insert("sincronizacion", null, registro);

            registro = new ContentValues();
            registro.put("tabla", tabla2);
            registro.put("fecha", nuevoDateString);
            bd.insert("sincronizacion", null, registro);
            bd.close();

            Toast.makeText(this.context, "REGISTRO datos de Sincronizacion",
                    Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(this.context, "ERR-SYNC datos de Sincronizacion",
                    Toast.LENGTH_SHORT).show();
            System.out.println("ERR-REGSYNC" + e.getMessage());
        }

    }


    public void updateSincronizacionTable(int codigo) {
        AdmSQLiteOpenHelper admin;
        SQLiteDatabase bd;
        String tabla = "";
        DateFormat nuevoDate = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        String tmpDateString = nuevoDate.format(today);
        String nuevoDateString = tmpDateString.replace("/","-");

        try{
            admin = new AdmSQLiteOpenHelper(this.context);
            admin.createDataBase();
            bd = admin.getWritableDatabase();
            ContentValues registro;
            int cant = 0;

            switch(codigo){
                case 1:
                    registro = new ContentValues();
                    registro.put("tabla", "clasificacion");
                    registro.put("fecha", nuevoDateString);
                    cant = bd.update("sincronizacion", registro, "id=" + codigo, null);
                    break;
                case 2:
                    registro = new ContentValues();
                    registro.put("tabla", "estado");
                    registro.put("fecha", nuevoDateString);
                    cant = bd.update("sincronizacion", registro, "id=" + codigo, null);
                    break;
                case 3:
                    registro = new ContentValues();
                    registro.put("tabla", "clasificacion");
                    registro.put("fecha", nuevoDateString);
                    cant = bd.update("sincronizacion", registro, "id=1", null);

                    registro = new ContentValues();
                    registro.put("tabla", "estado");
                    registro.put("fecha", nuevoDateString);
                    cant = bd.update("sincronizacion", registro, "id=2", null);
                    break;
                default:
                    break;

            }
            bd.close();
            if (cant == 1)
                Toast.makeText(this.context, "se modifico SYNC", Toast.LENGTH_SHORT)
                        .show();
            else
                Toast.makeText(this.context, "no modifico SYNC",
                        Toast.LENGTH_SHORT).show();

        } catch(Exception e){
            Toast.makeText(this.context, "Err-MOD Sincronizacion",
                    Toast.LENGTH_SHORT).show();
        }

    }

    public long registerClasificadoTable(Clasificacion clasifi, Usuario usuario) {

        /*String tabla1 = "clasificacion";
        String tabla2 = "estado";
        String fecha1 = "2017-01-01 15:39:27";
        String fecha2 = "2017-01-01 15:40:23";*/

        AdmSQLiteOpenHelper admin;
        ContentValues registro;
        long clasificado_id = 0;

        try {
            admin = new AdmSQLiteOpenHelper(this.context);
            admin.createDataBase();

            SQLiteDatabase bd = admin.getWritableDatabase();
            registro = new ContentValues();
            registro.put("clasificacion_id", clasifi.getId());
            registro.put("usuario_id", usuario.getId());
            clasificado_id = bd.insert("clasificado", null, registro);
/*
            Toast.makeText(this.context, "Se cargaron los datos de Sincronizacion",
                    Toast.LENGTH_SHORT).show();
*/
            //admin.openDataBase();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(this.context, "ERR-REGSYNC datos de Sincronizacion",
                    Toast.LENGTH_SHORT).show();
            System.out.println("ERR-REGSYNC" + e.getMessage());
        }
        return clasificado_id;

    }

    public void registerUsuario(Usuario nuevoUser) {

       // Usuario nuevoUser;
        AdmSQLiteOpenHelper admin;

        try {
            admin = new AdmSQLiteOpenHelper(this.context);
            admin.createDataBase();

            SQLiteDatabase bd = admin.getWritableDatabase();
            ContentValues registro = new ContentValues();
            //registro.put("id", 2);
            registro.put("username", nuevoUser.getUsername());
            registro.put("password", nuevoUser.getPassword());
            registro.put("tokenize", nuevoUser.getTokenize());
            registro.put("rol_id", 1);
            bd.insert("usuario", null, registro);
            bd.close();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Toast.makeText(this.context, "Se registro Usuario",
                Toast.LENGTH_SHORT).show();
    }


    public long registerDatasetTable(Dataset nuevoData) {

        AdmSQLiteOpenHelper admin;
        ContentValues registro;
        long dataset_id = 0;

        try {
            admin = new AdmSQLiteOpenHelper(this.context);
            admin.createDataBase();

            SQLiteDatabase bd = admin.getWritableDatabase();
            registro = new ContentValues();
            registro.put("clasificado_id", nuevoData.getClasificado_id());
            registro.put("parte_id", nuevoData.getParte_id());
            registro.put("nombre_imagen", nuevoData.getNombreImagen());
            registro.put("url_imagen", nuevoData.getUrlImagen());
            registro.put("path_imagen", nuevoData.getPathImagen());
            registro.put("longitud", nuevoData.getLongitud().toString());
            registro.put("latitud", nuevoData.getLatitud().toString());
            registro.put("fecha", nuevoData.getFecha());
            dataset_id = bd.insert("dataset", null, registro);
/*
            Toast.makeText(this.context, "Se cargaron los datos de Sincronizacion",
                    Toast.LENGTH_SHORT).show();
*/
            //admin.openDataBase();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(this.context, "ERR-REGDTSET datos de Dataset",
                    Toast.LENGTH_SHORT).show();
            System.out.println("ERR-REGDTSET" + e.getMessage());
        }
        return dataset_id;

    }



    public void registerFromListToClasificacion(ArrayList<Clasificacion> clasifList) {
        AdmSQLiteOpenHelper admin;
        ArrayList<Clasificacion> myClasifList= new ArrayList<>();

        try {
            admin = new AdmSQLiteOpenHelper(this.context);
            admin.createDataBase();
            //admin.openDataBase();
            SQLiteDatabase bd = admin.getWritableDatabase();
            if(clasifList != null)
                myClasifList = clasifList;

            for (int p=0; p<myClasifList.size(); p++){
                Clasificacion nuevo;
                nuevo = (Clasificacion) myClasifList.get(p);

                ContentValues registro = new ContentValues();
                registro.put("nombre_comun", nuevo.getNombre_comun());
                registro.put("nombre_cientifico", nuevo.getNombre_cientifico());
                registro.put("descripcion", nuevo.getDescripcion());
                bd.insert("clasificacion", null, registro);
            }
            bd.close();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Toast.makeText(this.context, "Se cargaron datos de Clasificacion",
                Toast.LENGTH_SHORT).show();
    }


    public void registerFromListToEstado(ArrayList<Estado> estadoList) {
        AdmSQLiteOpenHelper admin;
        ArrayList<Estado> myEstadoList= new ArrayList<>();

        try {
            admin = new AdmSQLiteOpenHelper(this.context);
            admin.createDataBase();
            //admin.openDataBase();
            SQLiteDatabase bd = admin.getWritableDatabase();
            if(estadoList != null)
                myEstadoList = estadoList;

            for (int p=0; p<myEstadoList.size(); p++){
                Estado nuevo;
                nuevo = (Estado) myEstadoList.get(p);

                ContentValues registro = new ContentValues();
                registro.put("nombre_parte", nuevo.getEstado());
                bd.insert("parte", null, registro);
            }
            bd.close();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Toast.makeText(this.context, "Se cargaron datos de Estado",
                Toast.LENGTH_SHORT).show();
    }

/*
    public boolean readSincronizeTableOld(){
        ArrayList<Sincronizacion> syncList= new ArrayList<Sincronizacion>();
        //Sincronizacion nuevoSync = new Sincronizacion();
        try {
            AdmSQLiteOpenHelper admin = new AdmSQLiteOpenHelper(this.context);
            SQLiteDatabase bd = admin.getWritableDatabase();
            // String cod = et3_comun.getText().toString();
            Cursor fila = bd.rawQuery(
                    "select id,tabla,fecha from sincronizacion" , null);

            if (fila.moveToFirst()) {
                do {
                    Sincronizacion nuevoSync = new Sincronizacion();
                    nuevoSync.setId(fila.getInt(0));
                    nuevoSync.setTabla(fila.getString(1));
                    nuevoSync.setFecha(fila.getString(2));
                    syncList.add(nuevoSync);

                } while(fila.moveToNext());
            } else
                Toast.makeText(this.context, "No existen registros en tabla SYNC",
                        Toast.LENGTH_SHORT).show();
            this.data.setSyncList(syncList);
            MainActivity.data = this.data;
            bd.close();

        } catch(Exception e){
            Toast.makeText(this.context, "Error en readSincronize",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }*/

    public ArrayList<Sincronizacion> readSincronizeTable(){
        ArrayList<Sincronizacion> syncList= new ArrayList<>();
        //Sincronizacion nuevoSync = new Sincronizacion();
        try {
            AdmSQLiteOpenHelper admin = new AdmSQLiteOpenHelper(this.context);
            SQLiteDatabase bd = admin.getWritableDatabase();
            // String cod = et3_comun.getText().toString();
            Cursor fila = bd.rawQuery(
                    "select id,tabla,fecha from sincronizacion" , null);

            if (fila.moveToFirst()) {
                do {
                    Sincronizacion nuevoSync = new Sincronizacion();
                    nuevoSync.setId(fila.getInt(0));
                    nuevoSync.setTabla(fila.getString(1));
                    nuevoSync.setFecha(fila.getString(2));
                    syncList.add(nuevoSync);

                } while(fila.moveToNext());
            } else
                Toast.makeText(this.context, "No existen registros en tabla SYNC",
                        Toast.LENGTH_SHORT).show();
          //  this.data.setSyncList(syncList);
           // MainActivity.data = this.data;
            bd.close();

        } catch(Exception e){
            Toast.makeText(this.context, "Error en readSincronize",
                    Toast.LENGTH_SHORT).show();
            return null;
        }
        return syncList;
    }


    public ArrayList<Clasificacion> readClasificacionTable(){
        ArrayList<Clasificacion> clasifiList= new ArrayList<>();
        //Sincronizacion nuevoSync = new Sincronizacion();
        try {
            AdmSQLiteOpenHelper admin = new AdmSQLiteOpenHelper(this.context);
            SQLiteDatabase bd = admin.getWritableDatabase();
            // String cod = et3_comun.getText().toString();
            Cursor fila = bd.rawQuery(
                    "select id,nombre_comun,nombre_cientifico,descripcion from clasificacion" , null);

            if (fila.moveToFirst()) {
                do {
                    Clasificacion nuevaClasifi = new Clasificacion();
                    nuevaClasifi.setId(fila.getInt(0));
                    nuevaClasifi.setNombre_comun(fila.getString(1));
                    nuevaClasifi.setNombre_cientifico(fila.getString(2));
                    nuevaClasifi.setDescripcion(fila.getString(3));
                    clasifiList.add(nuevaClasifi);

                } while(fila.moveToNext());
            } else
                Toast.makeText(this.context, "No existen registros en tabla CLASIFI",
                        Toast.LENGTH_SHORT).show();
            //this.data.setSyncList(syncList);
            //MainActivity.data = this.data;
            bd.close();

        } catch(Exception e){
            Toast.makeText(this.context, "Error en readSincronize",
                    Toast.LENGTH_SHORT).show();
        }
        return clasifiList;
    }

    public ArrayList<Estado> readEstadoTable(){
        ArrayList<Estado> estadoList= new ArrayList<>();
        //Sincronizacion nuevoSync = new Sincronizacion();
        try {
            AdmSQLiteOpenHelper admin = new AdmSQLiteOpenHelper(this.context);
            SQLiteDatabase bd = admin.getWritableDatabase();
            // String cod = et3_comun.getText().toString();
            Cursor fila = bd.rawQuery(
                    "select id,nombre_parte from parte" , null);

            if (fila.moveToFirst()) {
                do {
                    Estado nuevoEstado = new Estado();
                    nuevoEstado.setId(fila.getInt(0));
                    nuevoEstado.setEstado(fila.getString(1));
                    estadoList.add(nuevoEstado);

                } while(fila.moveToNext());
            } else
                Toast.makeText(this.context, "No existen registros en tabla ESTADO",
                        Toast.LENGTH_SHORT).show();
            //this.data.setSyncList(syncList);
            //MainActivity.data = this.data;
            bd.close();

        } catch(Exception e){
            Toast.makeText(this.context, "Error en readSincronize",
                    Toast.LENGTH_SHORT).show();
        }
        return estadoList;
    }


    public Clasificacion readUnitClasificacionTable(int identificador){
        Clasificacion nuevaClasifi = null;
        //Sincronizacion nuevoSync = new Sincronizacion();
        try {
            AdmSQLiteOpenHelper admin = new AdmSQLiteOpenHelper(this.context);
            SQLiteDatabase bd = admin.getWritableDatabase();
            // String cod = et3_comun.getText().toString();
            Cursor fila = bd.rawQuery(
                    "select id,nombre_comun,nombre_cientifico,descripcion from clasificacion" +
                            " where id=" + identificador, null);

            if (fila.moveToFirst()) {
                do {
                    nuevaClasifi = new Clasificacion();
                    nuevaClasifi.setId(fila.getInt(0));
                    nuevaClasifi.setNombre_comun(fila.getString(1));
                    nuevaClasifi.setNombre_cientifico(fila.getString(2));
                    nuevaClasifi.setDescripcion(fila.getString(3));
                    //clasifiList.add(nuevaClasifi);

                } while(fila.moveToNext());
            } else
                Toast.makeText(this.context, "No existen registros en tabla CLASIFI",
                        Toast.LENGTH_SHORT).show();
            //this.data.setSyncList(syncList);
            //MainActivity.data = this.data;
            bd.close();

        } catch(Exception e){
            Toast.makeText(this.context, "Error en readSincronize",
                    Toast.LENGTH_SHORT).show();
        }
        return nuevaClasifi;
    }


    public Estado readUnitEstadoTable(int identificador){
        Estado nuevoEstado= null;
        try {
            AdmSQLiteOpenHelper admin = new AdmSQLiteOpenHelper(this.context);
            SQLiteDatabase bd = admin.getWritableDatabase();
            // String cod = et3_comun.getText().toString();
            Cursor fila = bd.rawQuery(
                    "select id,nombre_parte from parte" +
                            " where id=" + identificador , null);

            if (fila.moveToFirst()) {
                do {
                    nuevoEstado = new Estado();
                    nuevoEstado.setId(fila.getInt(0));
                    nuevoEstado.setEstado(fila.getString(1));

                } while(fila.moveToNext());
            } else
                Toast.makeText(this.context, "No existen registros en tabla ESTADO",
                        Toast.LENGTH_SHORT).show();
            bd.close();

        } catch(Exception e){
            Toast.makeText(this.context, "Error en readSincronize",
                    Toast.LENGTH_SHORT).show();
        }
        return nuevoEstado;
    }


    public Clasificado readUnitClasificadoTable(int identificador){
        Clasificado nuevoClasificado= null;
        try {
            AdmSQLiteOpenHelper admin = new AdmSQLiteOpenHelper(this.context);
            SQLiteDatabase bd = admin.getWritableDatabase();

            Cursor fila = bd.rawQuery(
                    "select id,clasificacion_id,usuario_id,descripcion from clasificado" +
                            " where id=" + identificador , null);

            if (fila.moveToFirst()) {
                do {
                    nuevoClasificado = new Clasificado();
                    nuevoClasificado.setId(fila.getInt(0));
                    nuevoClasificado.setClasificacionId(fila.getInt(1));
                    nuevoClasificado.setUsuarioId(fila.getInt(2));
                    nuevoClasificado.setDescripcion(fila.getString(3));

                } while(fila.moveToNext());
            } else
                Toast.makeText(this.context, "No existen registros en tabla CLASIFICADO",
                        Toast.LENGTH_SHORT).show();
            bd.close();

        } catch(Exception e){
            Toast.makeText(this.context, "Error en readClasificado",
                    Toast.LENGTH_SHORT).show();
        }
        return nuevoClasificado;
    }


    public ArrayList<Usuario> readUsuarioTable(){
        ArrayList<Usuario> userList= new ArrayList<>();
        //Sincronizacion nuevoSync = new Sincronizacion();
        try {
            AdmSQLiteOpenHelper admin = new AdmSQLiteOpenHelper(this.context);
            SQLiteDatabase bd = admin.getWritableDatabase();
            // String cod = et3_comun.getText().toString();
            Cursor fila = bd.rawQuery(
                    "select id,username,password,tokenize,rol_id from usuario" , null);

            if (fila.moveToFirst()) {
                do {
                    Usuario nuevoUser = new Usuario();
                    nuevoUser.setId(fila.getInt(0));
                    nuevoUser.setUsername(fila.getString(1));
                    nuevoUser.setPassword(fila.getString(2));
                    nuevoUser.setTokenize(fila.getString(3));
                    nuevoUser.setRol_id(fila.getInt(4));
                    //nuevoUser.setRol_id(fila.getInt(3));
                    userList.add(nuevoUser);

                } while(fila.moveToNext());
            } else {
                Toast.makeText(this.context, "No existen registros en tabla USUARIO",
                        Toast.LENGTH_SHORT).show();
                return null;
            }

           /* this.data.setSyncList(syncList);
            MainActivity.data = this.data;*/
            bd.close();

        } catch(Exception e){
            Toast.makeText(this.context, "Error en readSincronize",
                    Toast.LENGTH_SHORT).show();
            System.out.println("READSYNC-" + e.getMessage());
        }
        return userList;
    }


    public ArrayList<Dataset> readDatasetTable(){
        ArrayList<Dataset> datasetList= new ArrayList<>();
        //Sincronizacion nuevoSync = new Sincronizacion();
        try {
            AdmSQLiteOpenHelper admin = new AdmSQLiteOpenHelper(this.context);
            SQLiteDatabase bd = admin.getWritableDatabase();
            // String cod = et3_comun.getText().toString();
            Cursor fila = bd.rawQuery(
                    "select id,clasificado_id,parte_id,nombre_imagen," +
                    "latitud,longitud,fecha from dataset" , null);

            if (fila.moveToFirst()) {
                do {
                    double latitudPrm = Double.parseDouble(fila.getString(4));
                    double longitudPrm = Double.parseDouble(fila.getString(5));
                    Double latitud = new Double(latitudPrm);
                    Double longitud = new Double(longitudPrm);
                    Dataset nuevoDataset = new Dataset();
                    nuevoDataset.setId(fila.getInt(0));
                    nuevoDataset.setClasificado_id(fila.getInt(1));
                    nuevoDataset.setParte_id(fila.getInt(2));
                    nuevoDataset.setNombreImagen(fila.getString(3));
                    nuevoDataset.setLatitud(latitud);
                    nuevoDataset.setLongitud(longitud);
                    nuevoDataset.setFecha(fila.getString(6));
                    datasetList.add(nuevoDataset);

                } while(fila.moveToNext());
            } else
                Toast.makeText(this.context, "No existen registros en tabla DATASET",
                        Toast.LENGTH_SHORT).show();
            //this.data.setSyncList(syncList);
            //MainActivity.data = this.data;
            bd.close();

        } catch(Exception e){
            Toast.makeText(this.context, "Error en readDataset",
                    Toast.LENGTH_SHORT).show();
        }
        return datasetList;
    }

}
