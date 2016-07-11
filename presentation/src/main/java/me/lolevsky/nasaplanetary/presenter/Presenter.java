package me.lolevsky.nasaplanetary.presenter;

import android.support.annotation.NonNull;

public interface Presenter<T, M> {
    void resume();
    void pause();
    void destroy();
    void loadData();
    void setView(@NonNull T view);
    M getModel();
    void setModel(M model);

}
