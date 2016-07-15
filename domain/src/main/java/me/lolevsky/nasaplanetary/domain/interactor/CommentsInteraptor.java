package me.lolevsky.nasaplanetary.domain.interactor;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import dagger.internal.Preconditions;
import me.lolevsky.nasaplanetary.domain.repository.IComments;
import me.lolevsky.nasaplanetary.domain.repository.IPlanetaryApod;
import rx.Observable;
import rx.Scheduler;

public class CommentsInteraptor extends BaseInteractor {

    IComments comments;

    @Inject
    public CommentsInteraptor(@NonNull IComments comments,
                              @NonNull Scheduler mainScheduler,
                              @NonNull Scheduler ioScheduler) {
        super(mainScheduler, ioScheduler);

        this.comments = Preconditions.checkNotNull(comments);
    }

    @Override protected Observable buildUseCaseObservable(String... params) {
        return comments.getComments(params[0]);
    }

    public Observable sendComment(String photoId, String message){
        return comments.sendComment(photoId, message);
    }
}
