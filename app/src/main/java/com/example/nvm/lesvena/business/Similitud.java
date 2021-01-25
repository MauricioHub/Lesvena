package com.example.nvm.lesvena.business;

import android.widget.Button;

/**
 * Created by mariamargoth on 11/9/2017.
 */

public class Similitud {
    public Similitud(String name, String percentage, int position) {
        this.name = name;
        this.percentage = percentage;
       // this.btnDetail = null;
        this.positionSimil = position;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    private String name;

    public String getPercentage() {
        return percentage;
    }

    private String percentage;

  //  private Button btnDetail;

    private int positionSimil;

   /* public Button getBtnDetail() {
        return btnDetail;
    }

    public void setBtnDetail(Button btnDetail) {
        this.btnDetail = btnDetail;
    }*/

    public int getPositionSimil() {
        return positionSimil;
    }

    public void setPositionSimil(int positionSimil) {
        this.positionSimil = positionSimil;
    }
}
