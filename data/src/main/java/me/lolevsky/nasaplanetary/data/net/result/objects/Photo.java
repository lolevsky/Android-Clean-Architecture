package me.lolevsky.nasaplanetary.data.net.result.objects;

import com.google.gson.annotations.SerializedName;

public class Photo {
    @SerializedName("id")
    int id;
    @SerializedName("img_src")
    String imgSrc;
    @SerializedName("earth_date")
    String earthDate;
    @SerializedName("camera")
    Camera camera;
    @SerializedName("rover")
    Rover rover;

    public Rover getRover() {
        return rover;
    }

    public int getId() {
        return id;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public String getEarthDate() {
        return earthDate;
    }

    public Camera getCamera() {
        return camera;
    }
}
