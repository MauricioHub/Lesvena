package com.example.nvm.lesvena;

import android.graphics.Bitmap;
import android.widget.ImageView;

import java.io.Serializable;

/**
 * Created by Mauricio on 8/20/2017.
 */

@SuppressWarnings("serial")
public class Dataset implements Serializable {

    private int id;
    private int parte_id;
    private int clasificado_id;
    private String nombreImagen;
    private String urlImagen;
    private String pathImagen;
    private String fecha;
    private Double latitud;
    private Double longitud;
    private Clasificacion clasificacion;
    private Estado estado;
    private ImageView cardImagen;
    private Bitmap cardBitmap;

    public Dataset(){}


    public Dataset(String nombreImagen, String fecha, int id) {
        this.id = id;
        this.nombreImagen = nombreImagen;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParte_id() {
        return parte_id;
    }

    public void setParte_id(int parte_id) {
        this.parte_id = parte_id;
    }

    public int getClasificado_id() {
        return clasificado_id;
    }

    public void setClasificado_id(int clasificado_id) {
        this.clasificado_id = clasificado_id;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getNombreImagen() {
        return nombreImagen;
    }

    public void setNombreImagen(String nombreImagen) {
        this.nombreImagen = nombreImagen;
    }

    public String getPathImagen() {
        return pathImagen;
    }

    public void setPathImagen(String pathImagen) {
        this.pathImagen = pathImagen;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }


    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public Clasificacion getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(Clasificacion clasificacion) {
        this.clasificacion = clasificacion;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public ImageView getCardImagen() {
        return cardImagen;
    }

    public void setCardImagen(ImageView cardImagen) {
        this.cardImagen = cardImagen;
    }

    public Bitmap getCardBitmap() {
        return cardBitmap;
    }

    public void setCardBitmap(Bitmap cardBitmap) {
        this.cardBitmap = cardBitmap;
    }
}
