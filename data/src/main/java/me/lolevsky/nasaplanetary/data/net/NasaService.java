package me.lolevsky.nasaplanetary.data.net;

import java.util.Map;

import me.lolevsky.nasaplanetary.data.net.result.ApodResponse;
import me.lolevsky.nasaplanetary.data.net.result.MarsPhotosResponse;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

public interface NasaService {
    @GET("/planetary/apod") Observable<ApodResponse> getPlanetaryApod();

    @GET("/mars-photos/api/v1/rovers/curiosity/photos") Observable<MarsPhotosResponse> getMarsPhotos(@QueryMap Map<String, String> options);
}
