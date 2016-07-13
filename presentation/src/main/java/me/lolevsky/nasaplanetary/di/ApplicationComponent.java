package me.lolevsky.nasaplanetary.di;

import javax.inject.Singleton;

import dagger.Component;
import me.lolevsky.nasaplanetary.MainApplication;
import me.lolevsky.nasaplanetary.view.MainActivity;
import me.lolevsky.nasaplanetary.view.MarsPhotosActivity;
import me.lolevsky.nasaplanetary.view.PlanetaryApodActivity;

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
}
