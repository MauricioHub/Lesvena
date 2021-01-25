package com.example.nvm.lesvena;

import java.io.Serializable;

/**
 * Created by Mauricio on 8/20/2017.
 */

@SuppressWarnings("serial")
public class Estado implements Serializable {

    private int id;
    private String estado;

    public Estado(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
