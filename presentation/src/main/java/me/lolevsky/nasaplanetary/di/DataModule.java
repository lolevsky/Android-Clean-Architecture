package me.lolevsky.nasaplanetary.di;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.lolevsky.nasaplanetary.MainApplication;
import me.lolevsky.nasaplanetary.data.repository.Comments;
import me.lolevsky.nasaplanetary.data.repository.MarsPhotos;
import me.lolevsky.nasaplanetary.domain.imageloader.IImageLoader;
import me.lolevsky.nasaplanetary.data.imageloader.ImageLoader;
import me.lolevsky.nasaplanetary.data.repository.PlanetaryApod;
import me.lolevsky.nasaplanetary.data.net.NasaService;
import me.lolevsky.nasaplanetary.domain.repository.IComments;
import me.lolevsky.nasaplanetary.domain.repository.IMarsPhotos;
import me.lolevsky.nasaplanetary.domain.tracking.ITracking;
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

    @Provides IMarsPhotos provideMarsPhotos(NasaService nasaService) {
        return new MarsPhotos(nasaService);
    }

    @Provides IComments provideComments() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        return new Comments(databaseReference);
    }

    @Singleton
    @Provides IImageLoader provideImageLoader(MainApplication mainApplication) {
        return new ImageLoader(mainApplication);
    }
}
