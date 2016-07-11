package me.lolevsky.nasaplanetary.view;

public interface IView<T> {
    void onLoading();

    void onComplete(T model);

    void onError(String error);
}
