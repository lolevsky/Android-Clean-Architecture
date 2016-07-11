package me.lolevsky.nasaplanetary.domain.imageloader;

import android.widget.ImageView;

public interface IImageLoader {
    void loadImage(String url, ImageView holder);
}
