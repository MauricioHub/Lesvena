package com.example.nvm.lesvena;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Mauricio on 8/19/2017.
 */

 @SuppressWarnings("serial")
 public class DataSetModel implements Serializable {

    private String url;
    private String token;
    private String pathPicture;
    private String urlPicture;
    private String imgBase64;
    private String username;
    private String password;
    private String namePicture;
    private String valuePostSincronize;
    private int pruebita;
    private Usuario usuario;

    private LatLng gye;
    private Dataset dataset;
    private ArrayList<String> urlPictureList;
    private ArrayList<String> urlRPictureList;
    private ArrayList<String> imgBase64List;
    private ArrayList<Clasificacion> clasificaList;
    private ArrayList<Estado> estadoList;
    private ArrayList<Sincronizacion> syncList;

    private double latitud;
    private double longitud;
    private JSONObject objJSON;


    public DataSetModel(){}


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPathPicture() {
        return pathPicture;
    }

    public void setPathPicture(String pathPicture) {
        this.pathPicture = pathPicture;
    }

    public String getUrlPicture() { return urlPicture; }

    public void setUrlPicture(String urlPicture) { this.urlPicture = urlPicture; }

    public String getImgBase64() {
        return imgBase64;
    }

    public void setImgBase64(String imgBase64) {
        this.imgBase64 = imgBase64;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getValuePostSincronize() {
        return valuePostSincronize;
    }

    public void setValuePostSincronize(String valuePostSincronize) {
        this.valuePostSincronize = valuePostSincronize;
    }

    public LatLng getGye() { return gye; }

    public void setGye(LatLng gye) { this.gye = gye; }

    public Dataset getDataset() {
        return dataset;
    }

    public void setDataset(Dataset dataset) {
        this.dataset = dataset;
    }

    public String getNamePicture() {
        return namePicture;
    }

    public void setNamePicture(String namePicture) {
        this.namePicture = namePicture;
    }

    public int getPruebita() {
        return pruebita;
    }

    public void setPruebita(int pruebita) {
        this.pruebita = pruebita;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }


    public ArrayList<String> getUrlPictureList() {
        return urlPictureList;
    }

    public void setUrlPictureList(ArrayList<String> urlPictureList) {
        this.urlPictureList = urlPictureList;
    }

    public ArrayList<String> getUrlRPictureList() {
        return urlRPictureList;
    }

    public void setUrlRPictureList(ArrayList<String> urlRPictureList) {
        this.urlRPictureList = urlRPictureList;
    }

    public ArrayList<String> getImgBase64List() {
        return imgBase64List;
    }

    public void setImgBase64List(ArrayList<String> imgBase64List) {
        this.imgBase64List = imgBase64List;
    }

    public ArrayList<Clasificacion> getClasificaList() {
        return clasificaList;
    }

    public void setClasificaList(ArrayList<Clasificacion> clasificaList) {
        this.clasificaList = clasificaList;
    }

    public ArrayList<Estado> getEstadoList() {
        return estadoList;
    }

    public void setEstadoList(ArrayList<Estado> estadoList) {
        this.estadoList = estadoList;
    }

    public ArrayList<Sincronizacion> getSyncList() {
        return syncList;
    }

    public void setSyncList(ArrayList<Sincronizacion> syncList) {
        this.syncList = syncList;
    }

    public JSONObject getObjJSON() {
        return objJSON;
    }

    public void setObjJSON(JSONObject objJSON) {
        this.objJSON = objJSON;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
