package me.lolevsky.nasaplanetary.data.repository;

import android.support.annotation.NonNull;

import dagger.internal.Preconditions;
import me.lolevsky.nasaplanetary.data.net.NasaService;
import me.lolevsky.nasaplanetary.domain.repository.IPlanetaryApod;
import rx.Observable;

public class PlanetaryApod implements IPlanetaryApod {

    NasaService api;

    public PlanetaryApod(@NonNull NasaService api) {
        this.api = Preconditions.checkNotNull(api);
    }

    @Override public Observable getPlanetaryApod() {
        return api.getPlanetaryApod();
    }
}
