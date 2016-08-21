package me.lolevsky.nasaplanetary.di;

import javax.inject.Singleton;

import dagger.Component;
import me.lolevsky.nasaplanetary.MainApplication;
import me.lolevsky.nasaplanetary.view.screens.main.MainActivity;
import me.lolevsky.nasaplanetary.view.screens.marsphoto.MarsPhotosActivity;
import me.lolevsky.nasaplanetary.view.screens.photocomment.PhotoCommentsActivity;
import me.lolevsky.nasaplanetary.view.screens.planetoryapod.PlanetaryApodActivity;

@Singleton
@Component(
        modules = {
                NetworkModule.class,
                DataModule.class,
                DomainModule.class,
                Rx.class
        }
)

public interface ApplicationComponent {
    void inject(MainApplication mainApplication);

    void inject(MainActivity mainActivity);

    void inject(PlanetaryApodActivity planetaryApodActivity);

    void inject(MarsPhotosActivity marsPhotosActivity);

    void inject(PhotoCommentsActivity photoCommentsActivity);
}
