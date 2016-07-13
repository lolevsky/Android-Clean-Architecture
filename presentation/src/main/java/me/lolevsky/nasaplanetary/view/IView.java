package me.lolevsky.nasaplanetary.view;

public interface IView<T> {
    void onLoading();

    void onLoadingMore();

    void onComplete(T model);

    void onError(String error);
}
