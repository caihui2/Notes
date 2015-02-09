package com.notos.entity;

import android.graphics.drawable.Drawable;

/**
 * Created by yangcaihui on 15/2/9.
 */
public class MenuSelectEntity {
    private Drawable selectIcn;
    private String selectName;

    public MenuSelectEntity(Drawable selectIcn, String selectName) {
        this.selectIcn = selectIcn;
        this.selectName = selectName;
    }

    public MenuSelectEntity() {}

    public Drawable getSelectIcn() {
        return selectIcn;
    }

    public String getSelectName() {
        return selectName;
    }

    public void setSelectName(String selectName) {
        this.selectName = selectName;
    }

    public void setSelectIcn(Drawable selectIcn) {
        this.selectIcn = selectIcn;
    }
}
