package com.AKapps.unitconvertor.model;

import android.graphics.drawable.Drawable;

public class converter_type {
    int imgView;
    String Name;

    public converter_type(int imgView, String name) {
        this.imgView = imgView;
        this.Name = name;
    }

    public int getImgView() {
        return imgView;
    }

    public void setImgView(int imgView) {
        this.imgView = imgView;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
