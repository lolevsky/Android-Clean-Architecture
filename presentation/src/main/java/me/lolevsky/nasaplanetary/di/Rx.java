package me.lolevsky.nasaplanetary.di;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@Module
public class Rx {
    public static final String MAIN = "main";
    public static final String IO = "io";
    public static final String COMPUTATION = "computation";
    public static final String TRAMPOLINE = "trampoline";

    @Provides @Singleton @Named(Rx.MAIN) Scheduler provideMainScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @Provides @Singleton @Named(Rx.IO) Scheduler provideIoScheduler() {
        return Schedulers.io();
    }

    @Provides @Singleton @Named(Rx.COMPUTATION) Scheduler provideComputationScheduler() {
        return Schedulers.computation();
    }

    @Provides @Singleton @Named(Rx.TRAMPOLINE) Scheduler provideTrampolineScheduler() {
        return Schedulers.trampoline();
    }
}
