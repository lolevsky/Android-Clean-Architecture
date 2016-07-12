package me.lolevsky.nasaplanetary.model.objects;

public class MainScreen {
    String name;
    String imageUrl;

    public MainScreen(String name, String imageUrl){
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getName() {
        return name;
    }
}
