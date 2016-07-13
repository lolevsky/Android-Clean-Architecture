package me.lolevsky.nasaplanetary.data.net.result;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import me.lolevsky.nasaplanetary.data.net.result.objects.Photo;

public class MarsPhotosResponse {
    @SerializedName("photos")
    List<Photo> photos;

    public List<Photo> getPhotos() {
        return photos;
    }
}
