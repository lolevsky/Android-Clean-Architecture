package me.lolevsky.nasaplanetary.di;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import me.lolevsky.nasaplanetary.domain.interactor.MainScreenInterapter;
import me.lolevsky.nasaplanetary.domain.interactor.MarsPhotosInteraptor;
import me.lolevsky.nasaplanetary.domain.interactor.PlanetaryApodInteraptor;
import me.lolevsky.nasaplanetary.domain.repository.IMarsPhotos;
import me.lolevsky.nasaplanetary.domain.repository.IPlanetaryApod;
import rx.Scheduler;

@Module
public class DomainModule {
    @Provides MainScreenInterapter provideMainScreenInterapter(@Named(Rx.MAIN) Scheduler mainScheduler,
                                                               @Named(Rx.IO) Scheduler ioScheduler) {
        return new MainScreenInterapter(mainScheduler, ioScheduler);
    }

    @Provides MarsPhotosInteraptor provideMarsPhotosInteraptor(IMarsPhotos marsPhotos,
                                                               @Named(Rx.MAIN) Scheduler mainScheduler,
                                                               @Named(Rx.IO) Scheduler ioScheduler) {
        return new MarsPhotosInteraptor(marsPhotos, mainScheduler, ioScheduler);
    }

    @Provides PlanetaryApodInteraptor providePlanetaryApodInteraptor(IPlanetaryApod planetaryApod,
                                                                     @Named(Rx.MAIN) Scheduler mainScheduler,
                                                                     @Named(Rx.IO) Scheduler ioScheduler) {
        return new PlanetaryApodInteraptor(planetaryApod, mainScheduler, ioScheduler);
    }
}
