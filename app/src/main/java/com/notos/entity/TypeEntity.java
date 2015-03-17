package com.notos.entity;

import java.io.Serializable;

/**
 * Created by yangcaihui on 15/3/15.
 */
public class TypeEntity implements Serializable {
    private int _id ;
    private String name;

    public TypeEntity(int _id, String name) {
        this._id = _id;
        this.name = name;
    }

    public TypeEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    @Override
    public String toString() {
        return "TypeEntity{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                '}';
    }
}
