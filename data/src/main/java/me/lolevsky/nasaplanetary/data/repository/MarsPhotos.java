package me.lolevsky.nasaplanetary.data.repository;

import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import dagger.internal.Preconditions;
import me.lolevsky.nasaplanetary.data.net.NasaService;
import me.lolevsky.nasaplanetary.domain.repository.IMarsPhotos;
import me.lolevsky.nasaplanetary.domain.repository.IPlanetaryApod;
import rx.Observable;

public class MarsPhotos implements IMarsPhotos {
    private final String PARAM_SOL = "sol";
    private final String PARAM_PAGE = "page";

    NasaService api;

    public MarsPhotos(@NonNull NasaService api) {
        this.api = Preconditions.checkNotNull(api);
    }

    @Override public Observable getMarsPhotos(String page) {
        Map<String, String> options = new HashMap<>();
        options.put(PARAM_PAGE, page);
        options.put(PARAM_SOL, "1000");

        return api.getMarsPhotos(options);
    }
}
