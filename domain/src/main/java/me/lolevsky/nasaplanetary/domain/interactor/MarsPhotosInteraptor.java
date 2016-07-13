package me.lolevsky.nasaplanetary.domain.interactor;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import dagger.internal.Preconditions;
import me.lolevsky.nasaplanetary.domain.repository.IMarsPhotos;
import rx.Observable;
import rx.Scheduler;

public class MarsPhotosInteraptor extends BaseInteractor {

    IMarsPhotos marsPhotos;

    @Inject
    public MarsPhotosInteraptor(@NonNull IMarsPhotos marsPhotos,
                                @NonNull Scheduler mainScheduler,
                                @NonNull Scheduler ioScheduler) {
        super(mainScheduler, ioScheduler);

        this.marsPhotos = Preconditions.checkNotNull(marsPhotos);
    }

    @Override protected Observable buildUseCaseObservable(String... params) {
        Preconditions.checkNotNull(params);
        return marsPhotos.getMarsPhotos(params[0]);
    }
}
