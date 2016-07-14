package me.lolevsky.nasaplanetary.presenter;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import android.os.Bundle;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import dagger.internal.Preconditions;
import me.lolevsky.nasaplanetary.data.net.result.MarsPhotosResponse;
import me.lolevsky.nasaplanetary.domain.interactor.MarsPhotosInteraptor;
import me.lolevsky.nasaplanetary.domain.storage.DatabaseNames;
import me.lolevsky.nasaplanetary.mapper.MarsPhotosModelDataMapper;
import me.lolevsky.nasaplanetary.model.MarsPhotosModel;
import me.lolevsky.nasaplanetary.view.IView;
import me.lolevsky.nasaplanetary.view.PhotoCommentsActivity;

public class MarsPhotosPresenter extends BasePresenter<IView, MarsPhotosModel, MarsPhotosResponse> {
    DatabaseReference databaseReference;
    Map<Integer, Query> queryMap = new HashMap<>();

    @Inject
    public MarsPhotosPresenter(MarsPhotosInteraptor marsPhotosInteraptor,
                               MarsPhotosModelDataMapper marsPhotosModelDataMapper) {
        super(Preconditions.checkNotNull(marsPhotosInteraptor), Preconditions.checkNotNull(marsPhotosModelDataMapper));

        databaseReference = FirebaseDatabase.getInstance().getReference();
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
        if (view != null) {
            Bundle bundle = new Bundle();
            bundle.putString(PhotoCommentsActivity.EXTRA_PHOTO_ID, String.valueOf(photoId));

            view.onStartActivity(PhotoCommentsActivity.class, bundle);
        }
    }

    public void removeMarsPhotoComments(int photoId, ValueEventListener valueEventListener) {
        synchronized (queryMap) {
            if (queryMap.containsKey(photoId)) {
                Query query = queryMap.get(photoId);
                query.removeEventListener(valueEventListener);

                queryMap.remove(photoId);
            }
        }
    }

    public void getMarsPhotoComments(int photoId, ValueEventListener valueEventListener) {
        synchronized (queryMap) {
            if (!queryMap.containsKey(photoId)) {
                Query query = databaseReference.child(DatabaseNames.TABLE_COMMENTS).child(String.valueOf(photoId));
                query.addValueEventListener(valueEventListener);

                queryMap.put(photoId, query);
            }
        }
    }
}
