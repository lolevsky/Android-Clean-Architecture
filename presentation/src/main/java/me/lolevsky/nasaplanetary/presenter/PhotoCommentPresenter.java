package me.lolevsky.nasaplanetary.presenter;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;

import me.lolevsky.nasaplanetary.domain.storage.DatabaseNames;
import me.lolevsky.nasaplanetary.model.objects.MarsPhotoComments;
import me.lolevsky.nasaplanetary.model.objects.PhotoComment;
import me.lolevsky.nasaplanetary.view.IView;

public class PhotoCommentPresenter implements Presenter<IView, MarsPhotoComments> {
    DatabaseReference databaseReference;

    IView view;
    MarsPhotoComments model;

    @Inject
    public PhotoCommentPresenter() {
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }


    @Override public void resume() {

    }

    @Override public void pause() {

    }

    @Override public void destroy() {
        view = null;
    }

    @Override public void loadData(String... params) {
        model = new MarsPhotoComments();
        model.setPhotoId(Integer.valueOf(params[0]));

        Query query = databaseReference.child(DatabaseNames.TABLE_COMMENTS).child(params[0]).orderByChild("date");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<Map<String, PhotoComment>> t = new GenericTypeIndicator<Map<String, PhotoComment>>() {
                };

                Map<String, PhotoComment> stringList = dataSnapshot.getValue(t);

                if (stringList != null && stringList.size() > 0) {
                    List<PhotoComment> comments = new ArrayList<>(stringList.values());

                    Collections.sort(comments, new Comparator<PhotoComment>() {
                        @Override public int compare(PhotoComment t1, PhotoComment t2) {
                            return t1.getDate().compareTo(t2.getDate());
                        }
                    });

                    model.setComments(comments);
                }

                if(view != null){
                    view.onComplete(model);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                if (view != null) {
                    view.onError(databaseError.getMessage());
                }
            }
        });
    }

    @Override public void setView(@NonNull IView view) {
        this.view = view;
    }

    @Override public MarsPhotoComments getModel() {
        return model;
    }

    @Override public void setModel(MarsPhotoComments model) {
        this.model = model;
    }

    public void sendComment(String message, OnCompleteListener onCompleteListener) {
        databaseReference.child(DatabaseNames.TABLE_COMMENTS)
                         .child(String.valueOf(model.getPhotoId()))
                         .push()
                         .setValue(new PhotoComment(Calendar.getInstance().getTime(), message))
                         .addOnCompleteListener(onCompleteListener);
    }
}
