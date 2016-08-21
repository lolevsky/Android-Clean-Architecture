package me.lolevsky.nasaplanetary.view.presenter;

import android.support.annotation.NonNull;

import me.lolevsky.nasaplanetary.domain.tracking.ITracking;

public interface Presenter<T, M> {
    void resume();
    void pause();
    void destroy();
    void loadData(String... params);
    void setView(@NonNull T view);
    M getModel();
    void setModel(M model);
    ITracking getTracking();

}
