package me.lolevsky.nasaplanetary.di;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import me.lolevsky.nasaplanetary.domain.interactor.PlanetaryApodInteraptor;
import me.lolevsky.nasaplanetary.domain.repository.IPlanetaryApod;
import rx.Scheduler;

@Module
public class DomainModule {
    @Provides PlanetaryApodInteraptor providePlanetaryApodInteraptor(IPlanetaryApod planetaryApod,
                                                                     @Named(Rx.MAIN) Scheduler mainScheduler,
                                                                     @Named(Rx.IO) Scheduler ioScheduler) {
        return new PlanetaryApodInteraptor(planetaryApod, mainScheduler, ioScheduler);
    }
}
