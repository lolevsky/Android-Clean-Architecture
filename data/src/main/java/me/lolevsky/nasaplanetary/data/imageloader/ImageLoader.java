package me.lolevsky.nasaplanetary.data.imageloader;

import com.bumptech.glide.Glide;
import com.bumptech.glide.MemoryCategory;

import android.app.Application;
import android.widget.ImageView;

public class ImageLoader implements IImageLoader {
    Application context;

    public ImageLoader(Application context) {
        this.context = context;
        Glide.get(context).setMemoryCategory(MemoryCategory.HIGH);
    }

    @Override public void loadImage(String url, ImageView holder) {
        Glide.with(context)
             .load(url)
             .centerCrop()
             .crossFade()
             .into(holder);
    }
}
