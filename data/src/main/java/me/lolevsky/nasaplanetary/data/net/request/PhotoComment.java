package me.lolevsky.nasaplanetary.data.net.request;

import org.parceler.Parcel;

import java.util.Date;

@Parcel
public class PhotoComment {
    Date date;
    String comment;

    public PhotoComment(){

    }

    public PhotoComment(Date date, String comment){
        this.date = date;
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
