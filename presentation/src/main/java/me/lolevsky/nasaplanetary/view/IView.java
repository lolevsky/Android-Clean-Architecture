package me.lolevsky.nasaplanetary.view;

import android.os.Bundle;

public interface IView<T> {
    void onLoading();

    void onLoadingMore();

    void onComplete(T model);

    void onError(String error);

    void onStartActivity(Class<?> cls, Bundle bundle);
}
