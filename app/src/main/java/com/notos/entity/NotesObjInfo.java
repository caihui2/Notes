package com.notos.entity;

/**
 * Created by chyang on 15-2-25.
 */
public class NotesObjInfo {

    private String title;
    private String content;
    private String time;
    private int collection;


    public NotesObjInfo() {
    }

    public NotesObjInfo(String title, String content, String time, int collection) {
        this.title = title;
        this.content = content;
        this.time = time;
        this.collection = collection;
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
