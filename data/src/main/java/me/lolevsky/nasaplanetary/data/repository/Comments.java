package me.lolevsky.nasaplanetary.data.repository;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import android.support.annotation.NonNull;

import java.util.Calendar;

import dagger.internal.Preconditions;
import me.lolevsky.nasaplanetary.data.net.request.PhotoComment;
import me.lolevsky.nasaplanetary.domain.repository.IComments;
import me.lolevsky.nasaplanetary.domain.storage.DatabaseNames;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.subscriptions.Subscriptions;

public class Comments implements IComments {
    private DatabaseReference databaseReference;

    public Comments(DatabaseReference databaseReference) {
        this.databaseReference = Preconditions.checkNotNull(databaseReference);
    }

    @Override public Observable getComments(String photoId) {
        Query query = databaseReference.child(DatabaseNames.TABLE_COMMENTS).child(photoId).orderByChild("date");
        return observe(query);
    }

    @Override public Observable sendComment(final String photoId, final String message) {
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(final Subscriber<? super Boolean> subscriber) {
                databaseReference.child(DatabaseNames.TABLE_COMMENTS)
                                 .child(String.valueOf(photoId))
                                 .push()
                                 .setValue(new PhotoComment(Calendar.getInstance().getTime(), message))
                                 .addOnCompleteListener(new OnCompleteListener<Void>() {
                                     @Override public void onComplete(@NonNull Task<Void> task) {
                                         subscriber.onNext(task.isSuccessful());
                                         subscriber.onCompleted();
                                     }
                                 })
                                 .addOnFailureListener(new OnFailureListener() {
                                     @Override public void onFailure(@NonNull Exception e) {
                                         subscriber.onError(new FirebaseException(e.getMessage()));
                                     }
                                 });
            }
        });
    }

    //example from https://gist.github.com/gsoltis/86210e3259dcc6998801
    private Observable<DataSnapshot> observe(final Query ref) {
        return Observable.create(new Observable.OnSubscribe<DataSnapshot>() {
            @Override
            public void call(final Subscriber<? super DataSnapshot> subscriber) {
                final ValueEventListener listener = ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        subscriber.onNext(dataSnapshot);
                    }

                    @Override public void onCancelled(DatabaseError databaseError) {
                        subscriber.onError(new FirebaseException(databaseError.getMessage()));
                    }
                });

                // When the subscription is cancelled, remove the listener
                subscriber.add(Subscriptions.create(new Action0() {
                    @Override
                    public void call() {
                        ref.removeEventListener(listener);
                    }
                }));
            }
        });
    }
}
