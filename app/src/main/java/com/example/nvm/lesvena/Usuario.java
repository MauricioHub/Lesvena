package com.example.nvm.lesvena;

/**
 * Created by Mauricio on 9/11/2017.
 */

public class Usuario {
    private int id;
    private String username;
    private String password;
    private String tokenize;
    private int rol_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getTokenize() {
        return tokenize;
    }

    public void setTokenize(String tokenize) {
        this.tokenize = tokenize;
    }

    public int getRol_id() {
        return rol_id;
    }

    public void setRol_id(int rol_id) {
        this.rol_id = rol_id;
    }
}
