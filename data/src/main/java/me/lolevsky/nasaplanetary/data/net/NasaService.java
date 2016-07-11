package me.lolevsky.nasaplanetary.data.net;

import me.lolevsky.nasaplanetary.data.net.result.ApodResponse;
import retrofit2.http.GET;
import rx.Observable;

public interface NasaService {

    @GET("/planetary/apod") Observable<ApodResponse> getPlanetaryApod();

}
