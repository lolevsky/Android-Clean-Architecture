package me.lolevsky.nasaplanetary.domain.interactor;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.internal.Preconditions;
import me.lolevsky.nasaplanetary.domain.entety.MainScreenEntity;
import me.lolevsky.nasaplanetary.domain.repository.IMarsPhotos;
import rx.Observable;
import rx.Scheduler;

public class MainScreenInterapter extends BaseInteractor {

    @Inject
    public MainScreenInterapter(@NonNull Scheduler mainScheduler,
                                @NonNull Scheduler ioScheduler) {
        super(mainScheduler, ioScheduler);
    }

    @Override protected Observable buildUseCaseObservable(String... params) {
        List<MainScreenEntity> entities = new ArrayList<>();

        entities.add(new MainScreenEntity("test1", null));
        entities.add(new MainScreenEntity("test2", null));

        return Observable.just(entities);
    }
}
