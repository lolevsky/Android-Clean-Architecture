package me.lolevsky.nasaplanetary.data.tracking;

import com.google.firebase.analytics.FirebaseAnalytics;

import dagger.internal.Preconditions;
import me.lolevsky.nasaplanetary.domain.tracking.ITracking;

public class Tracking implements ITracking {
    FirebaseAnalytics firebaseAnalytics;

    public Tracking(FirebaseAnalytics firebaseAnalytics) {
        this.firebaseAnalytics = Preconditions.checkNotNull(firebaseAnalytics);
    }

}
