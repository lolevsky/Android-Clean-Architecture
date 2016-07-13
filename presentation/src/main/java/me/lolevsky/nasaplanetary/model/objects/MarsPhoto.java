package me.lolevsky.nasaplanetary.model.objects;

import org.parceler.Parcel;

@Parcel
public class MarsPhoto {
    int id;
    String imgSrc;
    String earthDate;
    String cameraName;
    int roverId;
    String cameraFullName;
    String roverName;
    String roverLandingDate;

    public String getRoverLandingDate() {
        return roverLandingDate;
    }

    public void setRoverLandingDate(String roverLandingDate) {
        this.roverLandingDate = roverLandingDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getEarthDate() {
        return earthDate;
    }

    public void setEarthDate(String earthDate) {
        this.earthDate = earthDate;
    }

    public String getCameraName() {
        return cameraName;
    }

    public void setCameraName(String cameraName) {
        this.cameraName = cameraName;
    }

    public int getRoverId() {
        return roverId;
    }

    public void setRoverId(int roverId) {
        this.roverId = roverId;
    }

    public String getCameraFullName() {
        return cameraFullName;
    }

    public void setCameraFullName(String cameraFullName) {
        this.cameraFullName = cameraFullName;
    }

    public String getRoverName() {
        return roverName;
    }

    public void setRoverName(String roverName) {
        this.roverName = roverName;
    }
}
