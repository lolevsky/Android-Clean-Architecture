package me.lolevsky.nasaplanetary.data.remoteconfig;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import me.lolevsky.nasaplanetary.data.R;
import me.lolevsky.nasaplanetary.domain.remoteconfig.IRemoteConfig;

public class RemoteConfig implements IRemoteConfig{
    FirebaseRemoteConfig firebaseRemoteConfig;

    @Override
    public void init(boolean isDebug) {
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
                        }
                    }
                });
    }

    @Override
    public boolean getBoolean(String key) {
        return firebaseRemoteConfig.getBoolean(key);
    }
}
