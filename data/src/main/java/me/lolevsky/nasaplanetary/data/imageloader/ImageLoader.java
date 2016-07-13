package me.lolevsky.nasaplanetary.data.imageloader;

import com.bumptech.glide.Glide;
import com.bumptech.glide.MemoryCategory;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import android.app.Application;
import android.graphics.Bitmap;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import me.lolevsky.nasaplanetary.domain.imageloader.IImageLoader;

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

    @Override public void loadImage(String url, int resourceIdPlaceHolder, ImageView holder, final ProgressBar
                                    progressBar) {
        Glide.with(context)
             .load(url)
             .asBitmap()
             .centerCrop()
             .placeholder(resourceIdPlaceHolder)
             .listener(new RequestListener<String, Bitmap>() {
                 @Override public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                     if(progressBar != null){
                         progressBar.setVisibility(View.GONE);
                     }
                     return false;
                 }

                 @Override public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                     if(progressBar != null){
                         progressBar.setVisibility(View.GONE);
                     }
                     return false;
                 }
             })
             .into(holder);
    }

    @Override public void loadImage(int resourceId, ImageView holder) {
        Glide.with(context)
             .load(resourceId)
             .centerCrop()
             .crossFade()
             .into(holder);
    }
}
