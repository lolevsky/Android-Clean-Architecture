package me.lolevsky.nasaplanetary.data.tracking;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crash.FirebaseCrash;

import android.os.Bundle;

import dagger.internal.Preconditions;
import me.lolevsky.nasaplanetary.domain.remoteconfig.IRemoteConfig;
import me.lolevsky.nasaplanetary.domain.tracking.ITracking;

public class Tracking implements ITracking {
    private final String CATEGORY_SCREEN = "screen";
    private final String CATEGORY_CLICK = "click";

    FirebaseAnalytics firebaseAnalytics;

    public Tracking(FirebaseAnalytics firebaseAnalytics) {
        this.firebaseAnalytics = Preconditions.checkNotNull(firebaseAnalytics);
    }

    @Override
    public void LogEventScreen(String eventName) {
        LogEvent(CATEGORY_SCREEN, eventName);
    }

    @Override
    public void LogEventClick(String eventName) {
        LogEvent(CATEGORY_CLICK, eventName);
    }

    @Override public void LogEvent(String category, String eventName) {
        Bundle parameters = new Bundle();
        parameters.putString(FirebaseAnalytics.Param.CONTENT_TYPE, eventName);

        synchronized (firebaseAnalytics) {
            firebaseAnalytics.logEvent(category, parameters);
        }
    }

    @Override public void LogException(String exception) {
        FirebaseCrash.log(exception);
    }

    @Override public void setUserProperty(String experimentName, IRemoteConfig.ExperimentVariant experimentVariant) {
        synchronized (firebaseAnalytics) {
            firebaseAnalytics.setUserProperty(experimentName, experimentVariant.name());
        }
    }
}
