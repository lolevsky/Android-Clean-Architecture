package me.lolevsky.nasaplanetary.model;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

import me.lolevsky.nasaplanetary.model.objects.MarsPhoto;

@Parcel
public class MarsPhotosModel {
    List<MarsPhoto> marsPhotos = new ArrayList<>();
    int pageNumber = 0;

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public List<MarsPhoto> getMarsPhotos() {
        return marsPhotos;
    }

    public void setMarsPhotos(List<MarsPhoto> marsPhotos) {
        this.marsPhotos = marsPhotos;
    }
}
