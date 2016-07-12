package me.lolevsky.nasaplanetary.domain.entety;

public class MainScreenEntity {
    String name;
    String imageUrl;

    public MainScreenEntity(String name, String imageUrl){
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
