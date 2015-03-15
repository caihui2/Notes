package com.notos.entity;

/**
 * Created by yangcaihui on 15/3/15.
 */
public class NoteEntity {

    private int _id ;
    private String title;
    private String content;
    private String time;
    private int collection;
    private String typeName;
    private int typeID;
    private int mediaId;

    public NoteEntity(int _id,String title, String content, String time,
                      int collection, String typeName, int typeID, int mediaId) {
        this._id = _id;
        this.title = title;
        this.content = content;
        this.time = time;
        this.collection = collection;
        this.typeName = typeName;
        this.typeID = typeID;
        this.mediaId = mediaId;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getCollection() {
        return collection;
    }

    public void setCollection(int collection) {
        this.collection = collection;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public int getMediaId() {
        return mediaId;
    }

    public void setMediaId(int mediaId) {
        this.mediaId = mediaId;
    }

    @Override
    public String toString() {
        return "NoteEntity{" +
                "_id=" + _id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                ", collection=" + collection +
                ", typeName='" + typeName + '\'' +
                ", typeID=" + typeID +
                ", mediaId=" + mediaId +
                '}';
    }
}
