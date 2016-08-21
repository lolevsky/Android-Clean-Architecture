package me.lolevsky.nasaplanetary.view.screens.marsphoto;

import android.os.Bundle;

import javax.inject.Inject;

import dagger.internal.Preconditions;
import me.lolevsky.nasaplanetary.data.net.result.MarsPhotosResponse;
import me.lolevsky.nasaplanetary.domain.interactor.CommentsInteraptor;
import me.lolevsky.nasaplanetary.domain.interactor.MarsPhotosInteraptor;
import me.lolevsky.nasaplanetary.domain.tracking.ITracking;
import me.lolevsky.nasaplanetary.mapper.MarsPhotosModelDataMapper;
import me.lolevsky.nasaplanetary.model.MarsPhotosModel;
import me.lolevsky.nasaplanetary.view.presenter.BasePresenter;
import me.lolevsky.nasaplanetary.view.IView;
import me.lolevsky.nasaplanetary.view.screens.photocomment.PhotoCommentsActivity;
import rx.Subscriber;

public class MarsPhotosPresenter extends BasePresenter<IView, MarsPhotosModel, MarsPhotosResponse> {
    CommentsInteraptor comments;

    @Inject
    public MarsPhotosPresenter(MarsPhotosInteraptor marsPhotosInteraptor,
                               MarsPhotosModelDataMapper marsPhotosModelDataMapper,
                               CommentsInteraptor comments,
                               ITracking tracking) {
        super(Preconditions.checkNotNull(marsPhotosInteraptor), Preconditions.checkNotNull(marsPhotosModelDataMapper)
                , Preconditions.checkNotNull(tracking));
        this.comments = Preconditions.checkNotNull(comments);

        tracking.LogEventScreen("MarsPhotosScreen");
    }

    @Override public void pagingAddNewData(MarsPhotosModel newModel) {
        model.getMarsPhotos().addAll(newModel.getMarsPhotos());
    }

    @Override public void loadData(String... params) {
        super.loadData(getModel() == null ? "0" : String.valueOf(getModel().getPageNumber()));
    }

    @Override public void resume() {

    }

    @Override public void pause() {

    }

    @Override public void onNewPageRequest(int lastItemIndex) {
        synchronized (model) {
            if (model.getLastItemIndex() < lastItemIndex) {
                model.setLastItemIndex(lastItemIndex);
                model.setPageNumber(model.getPageNumber() + 1);
                loadData();
            }
        }
    }

    public void onCommentClick(int photoId) {
        getTracking().LogEventClick("onCommentClick - " + photoId);

        if (view != null) {
            Bundle bundle = new Bundle();
            bundle.putString(PhotoCommentsActivity.EXTRA_PHOTO_ID, String.valueOf(photoId));

            view.onStartActivity(PhotoCommentsActivity.class, bundle);
        }
    }

    public void getComments(Subscriber subscriber, String photoId){
        comments.execute(subscriber, photoId);
    }
}
