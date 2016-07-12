package me.lolevsky.nasaplanetary.domain.interactor;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import dagger.internal.Preconditions;
import me.lolevsky.nasaplanetary.domain.repository.IPlanetaryApod;
import rx.Observable;
import rx.Scheduler;

public class PlanetaryApodInteraptor extends BaseInteractor {

    IPlanetaryApod planetaryApod;

    @Inject
    public PlanetaryApodInteraptor(@NonNull IPlanetaryApod planetaryApod,
                                   @NonNull Scheduler mainScheduler,
                                   @NonNull Scheduler ioScheduler) {
        super(mainScheduler, ioScheduler);

        this.planetaryApod = Preconditions.checkNotNull(planetaryApod);
    }

    @Override protected Observable buildUseCaseObservable(String... params) {
        return planetaryApod.getPlanetaryApod();
    }
}
