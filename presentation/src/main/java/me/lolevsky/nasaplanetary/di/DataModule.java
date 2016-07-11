package me.lolevsky.nasaplanetary.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.lolevsky.nasaplanetary.MainApplication;
import me.lolevsky.nasaplanetary.data.imageloader.IImageLoader;
import me.lolevsky.nasaplanetary.data.imageloader.ImageLoader;
import me.lolevsky.nasaplanetary.data.repository.PlanetaryApod;
import me.lolevsky.nasaplanetary.data.net.NasaService;
import me.lolevsky.nasaplanetary.data.tracking.ITracking;
import me.lolevsky.nasaplanetary.data.tracking.Tracking;
import me.lolevsky.nasaplanetary.domain.repository.IPlanetaryApod;

@Module
public class DataModule {

    MainApplication application;

    public DataModule(MainApplication mainApplication) {
        this.application = mainApplication;
    }

    @Singleton
    @Provides ITracking provideTracking() {
        return new Tracking();
    }

    @Provides MainApplication provideMainApplication() {
        return application;
    }

    @Provides IPlanetaryApod providePlanetaryApod(NasaService nasaService) {
        return new PlanetaryApod(nasaService);
    }

    @Singleton
    @Provides IImageLoader provideImageLoader(MainApplication mainApplication) {
        return new ImageLoader(mainApplication);
    }
}
