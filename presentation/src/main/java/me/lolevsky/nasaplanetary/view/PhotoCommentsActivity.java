package me.lolevsky.nasaplanetary.view;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.lolevsky.nasaplanetary.MainApplication;
import me.lolevsky.nasaplanetary.R;
import me.lolevsky.nasaplanetary.adapters.PhotoCommentsAdapter;
import me.lolevsky.nasaplanetary.model.objects.MarsPhotoComments;
import me.lolevsky.nasaplanetary.presenter.PhotoCommentPresenter;
import me.lolevsky.nasaplanetary.presenter.Presenter;

public class PhotoCommentsActivity extends BaseActivity<MarsPhotoComments> {
    public static final String EXTRA_PHOTO_ID = "extra_photo_id";

    @Inject MainApplication context;
    @Inject PhotoCommentPresenter photoCommentPresenter;

    @BindView(R.id.root_view) RelativeLayout rootView;
    @BindView(R.id.comments_list) RecyclerView recyclerView;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.message_input) EditText messageInput;
    @BindView(R.id.send_button) ImageButton sendButton;

    PhotoCommentsAdapter photoCommentsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainApplication) getApplication()).getApplicationComponent().inject(this);

        setContentView(R.layout.activity_photo_comments);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        intActionBar();

        photoCommentsAdapter = new PhotoCommentsAdapter(photoCommentPresenter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(photoCommentsAdapter);

        if (savedInstanceState == null) {
            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                photoCommentPresenter.loadData(bundle.getString(EXTRA_PHOTO_ID));
            } else {
                finish();
            }
        }
    }

    private void intActionBar() {
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setTitle(String title) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }

    @Override Presenter getPresenter() {
        return photoCommentPresenter;
    }

    @Override public void onLoading() {

    }

    @Override public void onLoadingMore() {

    }

    @Override public void onComplete(MarsPhotoComments model) {
        photoCommentsAdapter.notifyDataSetChanged();
        if (model.getComments() != null) {
            recyclerView.smoothScrollToPosition(model.getComments().size() + 1);
        }
    }

    @Override public void onError(String error) {
        Snackbar.make(rootView, error, Snackbar.LENGTH_SHORT).show();
    }

    @OnClick(R.id.send_button)
    public void onMessageSend() {
        String message = messageInput.getText().toString();

        if (!message.isEmpty()) {
            sendButton.setEnabled(false);
            messageInput.setEnabled(false);

            photoCommentPresenter.sendComment(message);
        }
    }

    public void sendComplite(Boolean successful){
        sendButton.setEnabled(true);
        messageInput.setEnabled(true);
        if (successful) {
            messageInput.setText("");
        }
    }
}
