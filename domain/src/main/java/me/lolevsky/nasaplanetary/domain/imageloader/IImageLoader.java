package me.lolevsky.nasaplanetary.domain.imageloader;

import android.widget.ImageView;
import android.widget.ProgressBar;

public interface IImageLoader {
    void loadImage(String url, ImageView holder);

    void loadImage(String url, int resourceIdPlaceHolder, ImageView holder, ProgressBar progressBar);

    void loadImage(int resourceId, ImageView holder);
}
