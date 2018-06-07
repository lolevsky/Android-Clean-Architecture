package me.lolevsky.nasaplanetary.di;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import me.lolevsky.nasaplanetary.domain.interactor.CommentsInteraptor;
import me.lolevsky.nasaplanetary.domain.interactor.MainScreenInteractor;
import me.lolevsky.nasaplanetary.domain.interactor.MarsPhotosInteraptor;
import me.lolevsky.nasaplanetary.domain.interactor.PlanetaryApodInteraptor;
import me.lolevsky.nasaplanetary.domain.repository.IComments;
import me.lolevsky.nasaplanetary.domain.repository.IMarsPhotos;
import me.lolevsky.nasaplanetary.domain.repository.IPlanetaryApod;
import rx.Scheduler;

@Module
public class DomainModule {
    @Provides
    MainScreenInteractor provideMainScreenInteractor(@Named(Rx.MAIN) Scheduler mainScheduler,
                                                     @Named(Rx.IO) Scheduler ioScheduler) {
        return new MainScreenInteractor(mainScheduler, ioScheduler);
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

    @Provides CommentsInteraptor provideCommentsInteraptor(IComments comments,
                                                           @Named(Rx.MAIN) Scheduler mainScheduler,
                                                           @Named(Rx.IO) Scheduler ioScheduler) {
        return new CommentsInteraptor(comments, mainScheduler, ioScheduler);
    }
}
