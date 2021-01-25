package com.example.nvm.lesvena;

/**
 * Created by NVM on 11/7/2017.
 */

public class Clasificado {

    private int id;
    private int clasificacionId;
    private int usuarioId;
    private String descripcion;

    public Clasificado() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClasificacionId() {
        return clasificacionId;
    }

    public void setClasificacionId(int clasificacionId) {
        this.clasificacionId = clasificacionId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
