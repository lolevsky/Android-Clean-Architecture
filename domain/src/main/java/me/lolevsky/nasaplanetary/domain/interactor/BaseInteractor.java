package me.lolevsky.nasaplanetary.domain.interactor;


import android.support.annotation.NonNull;

import dagger.internal.Preconditions;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

public abstract class BaseInteractor {
    private Scheduler mainScheduler;
    private Scheduler ioScheduler;

    private Subscription subscription = Subscriptions.empty();

    protected BaseInteractor(@NonNull Scheduler mainScheduler,
                             @NonNull Scheduler ioScheduler) {
        this.mainScheduler = Preconditions.checkNotNull(mainScheduler);
        this.ioScheduler = Preconditions.checkNotNull(ioScheduler);
    }

    protected abstract Observable buildUseCaseObservable(String... params);

    @SuppressWarnings("unchecked")
    public void execute(Subscriber UseCaseSubscriber, String... params) {
        this.subscription = this.buildUseCaseObservable(params)
                                .subscribeOn(ioScheduler)
                                .observeOn(mainScheduler)
                                .subscribe(UseCaseSubscriber);
    }

    public void unsubscribe() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
