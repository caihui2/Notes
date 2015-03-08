package com.notos.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by chyang on 15-2-25.
 */
public class NotesObjInfo implements Serializable{

    private String title;
    private String content;
    private String time;
    private String typeName;



    private int collection;


    public NotesObjInfo(String content, String title, String time, String typeName, int collection) {
        this.content = content;
        this.title = title;
        this.time = time;
        this.typeName = typeName;
        this.collection = collection;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }



    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCollection() {
        return collection;
    }

    public void setCollection(int collection) {
        this.collection = collection;
    }

    @Override
    public String toString() {
        return "NotesObjInfo{" +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                ", collection='" + collection + '\'' +
                '}';
    }

}
