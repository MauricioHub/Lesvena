package com.example.nvm.lesvena;

import java.io.Serializable;

/**
 * Created by Mauricio on 8/20/2017.
 */

@SuppressWarnings("serial")
public class Clasificacion implements Serializable {

    private int id;
    private String nombre_comun;
    private String nombre_cientifico;
    private String descripcion;

    public Clasificacion(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre_comun() {
        return nombre_comun;
    }

    public void setNombre_comun(String nombre_comun) {
        this.nombre_comun = nombre_comun;
    }

    public String getNombre_cientifico() {
        return nombre_cientifico;
    }

    public void setNombre_cientifico(String nombre_cientifico) {
        this.nombre_cientifico = nombre_cientifico;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
