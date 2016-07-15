package me.lolevsky.nasaplanetary.data.imageloader;

import com.bumptech.glide.Glide;
import com.bumptech.glide.MemoryCategory;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import android.app.Application;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import me.lolevsky.nasaplanetary.domain.imageloader.IImageLoader;

public class ImageLoader implements IImageLoader {
    Application context;

    public ImageLoader(Application context) {
        this.context = context;
        Glide.get(context).setMemoryCategory(MemoryCategory.LOW);
    }

    @Override public void loadImage(String url, ImageView holder) {
        clear(holder);

        Glide.with(context)
             .load(url)
             .centerCrop()
             .crossFade()
             .into(holder);
    }

    @Override public void loadImage(String url, int resourceIdPlaceHolder, ImageView holder, final ProgressBar
                                    progressBar) {
        clear(holder);

        Glide.with(context)
             .load(url)
             .centerCrop()
             .placeholder(resourceIdPlaceHolder)
             .listener(new RequestListener<String, GlideDrawable>() {
                 @Override public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                     if(progressBar != null){
                         progressBar.setVisibility(View.GONE);
                     }
                     return false;
                 }

                 @Override public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                     if(progressBar != null){
                         progressBar.setVisibility(View.GONE);
                     }
                     return false;
                 }
             })
             .into(holder);
    }

    @Override public void loadImage(int resourceId, ImageView holder) {
        clear(holder);

        Glide.with(context)
             .load(resourceId)
             .centerCrop()
             .crossFade()
             .into(holder);
    }

    private void clear(ImageView holder){
        Glide.clear(holder);
        // remove the placeholder (optional); read comments below
        holder.setImageDrawable(null);
    }
}
