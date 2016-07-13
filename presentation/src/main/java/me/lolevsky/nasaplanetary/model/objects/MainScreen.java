package me.lolevsky.nasaplanetary.model.objects;

import org.parceler.Parcel;

@Parcel
public class MainScreen {
    String name;
    int imageId;

    public MainScreen(){

    }

    public MainScreen(String name, int imageId){
        this.name = name;
        this.imageId = imageId;
    }

    public int getImageId() {
        return imageId;
    }

    public String getName() {
        return name;
    }
}
