package me.lolevsky.nasaplanetary.data.remoteconfig;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import me.lolevsky.nasaplanetary.data.R;
import me.lolevsky.nasaplanetary.domain.remoteconfig.IRemoteConfig;
import me.lolevsky.nasaplanetary.domain.tracking.ITracking;

public class RemoteConfig implements IRemoteConfig{
    FirebaseRemoteConfig firebaseRemoteConfig;
    final ITracking tracking;

    public RemoteConfig(boolean isDebug, final ITracking tracking) {
        this.tracking = tracking;

        firebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(isDebug)
                .build();

        firebaseRemoteConfig.setConfigSettings(configSettings);
        firebaseRemoteConfig.setDefaults(R.xml.remote_config_defaults);

        firebaseRemoteConfig.fetch()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            firebaseRemoteConfig.activateFetched();

                            tracking.setUserProperty(EXPERIMENT_HOME_SCREEN_ABOUT_MENU, getExperimentVariant(EXPERIMENT_HOME_SCREEN_ABOUT_MENU));
                        }
                    }
                });
    }

    @Override
    public ExperimentVariant getExperimentVariant(String key) {
        String result = firebaseRemoteConfig.getString(key);

        if(result != null && !result.isEmpty()){
            return ExperimentVariant.valueOf(result.toUpperCase());
        }
        return ExperimentVariant.NONE;
    }
}
