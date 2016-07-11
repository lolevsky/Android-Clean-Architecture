package me.lolevsky.nasaplanetary.data.imageloader;

import android.widget.ImageView;

public interface IImageLoader {
    void loadImage(String url, ImageView holder);
}
