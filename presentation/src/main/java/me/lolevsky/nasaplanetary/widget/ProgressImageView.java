package me.lolevsky.nasaplanetary.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class ProgressImageView extends FrameLayout {

    ImageView imageView;
    ProgressBar progressBar;

    public ProgressImageView(Context context) {
        super(context);
        imageView = new ImageView(context);
        init(context);
    }

    public ProgressImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        imageView = new ImageView(context, attrs);
        init(context);
    }

    public ProgressImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        imageView = new ImageView(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        progressBar = new ProgressBar(context, null, android.R.attr.progressBarStyleSmall);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        progressBar.setLayoutParams(params );

        addView(imageView);
        addView(progressBar);
    }

    public ImageView getImageView(){
        return imageView;
    }

    public ProgressBar getProgressBar(){
        return progressBar;
    }

    public void hideProgressBar() {
        progressBar.setVisibility(GONE);
    }

    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        imageView.jumpDrawablesToCurrentState();
    }

    public void invalidateDrawable(Drawable dr) {
        imageView.invalidateDrawable(dr);
    }

    public boolean hasOverlappingRendering() {
        return imageView.hasOverlappingRendering();
    }

    public boolean getAdjustViewBounds() {
        return imageView.getAdjustViewBounds();
    }

    public void setAdjustViewBounds(boolean adjustViewBounds) {
        imageView.setAdjustViewBounds(adjustViewBounds);
    }

    public int getMaxWidth() {
        return imageView.getMaxWidth();
    }

    public void setMaxWidth(int maxWidth) {
        imageView.setMaxWidth(maxWidth);
    }

    public int getMaxHeight() {
        return imageView.getMaxHeight();
    }

    public void setMaxHeight(int maxHeight) {
        imageView.setMaxHeight(maxHeight);
    }

    public Drawable getDrawable() {
        return imageView.getDrawable();
    }

    public void setImageResource(int resId) {
        imageView.setImageResource(resId);
    }

    public void setImageURI(Uri uri) {
        imageView.setImageURI(uri);
    }

    public void setImageDrawable(Drawable drawable) {
        imageView.setImageDrawable(drawable);
    }

    public void setImageBitmap(Bitmap bm) {
        imageView.setImageBitmap(bm);
        hideProgressBar();
    }

    public void setImageState(int[] state, boolean merge) {
        imageView.setImageState(state, merge);
    }

    public void setImageLevel(int level) {
        imageView.setImageLevel(level);
    }

    public ImageView.ScaleType getScaleType() {
        return imageView.getScaleType();
    }

    public void setScaleType(ImageView.ScaleType scaleType) {
        imageView.setScaleType(scaleType);
    }

    public Matrix getImageMatrix() {
        return imageView.getImageMatrix();
    }

    public void setImageMatrix(Matrix matrix) {
        imageView.setImageMatrix(matrix);
    }

    public boolean getCropToPadding() {
        return imageView.getCropToPadding();
    }

    public void setCropToPadding(boolean cropToPadding) {
        imageView.setCropToPadding(cropToPadding);
    }

    public int[] onCreateDrawableState(int extraSpace) {
        return imageView.onCreateDrawableState(extraSpace);
    }

    public int getImageAlpha() {
        return imageView.getImageAlpha();
    }

    public void setImageAlpha(int alpha) {
        imageView.setImageAlpha(alpha);
    }

    /** @deprecated  */
    @Deprecated
    public void setAlpha(int alpha) {
        imageView.setAlpha(alpha);
    }
}
