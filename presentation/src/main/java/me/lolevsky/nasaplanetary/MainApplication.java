package me.lolevsky.nasaplanetary;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.database.FirebaseDatabase;

import android.app.Application;

import me.lolevsky.nasaplanetary.di.ApplicationComponent;
import me.lolevsky.nasaplanetary.di.DaggerApplicationComponent;
import me.lolevsky.nasaplanetary.di.DataModule;
import me.lolevsky.nasaplanetary.di.DomainModule;
import me.lolevsky.nasaplanetary.di.NetworkModule;
import me.lolevsky.nasaplanetary.di.Rx;

public class MainApplication extends Application {

    private ApplicationComponent component;
    private Boolean isPersistent = false;

    @Override public void onCreate() {
        super.onCreate();
        inject();
    }

    private void inject(){
        component = DaggerApplicationComponent.builder()
                                              .networkModule(new NetworkModule())
                                              .dataModule(new DataModule(this))
                                              .domainModule(new DomainModule())
                                              .rx(new Rx())
                                              .build();
        component.inject(this);
    }

    public ApplicationComponent getApplicationComponent(){
        return component;
    }

    public void setPersistence() {
        synchronized (isPersistent) {
            if (!isPersistent) {
                final int playServicesStatus = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);
                if (playServicesStatus == ConnectionResult.SUCCESS) {
                    FirebaseDatabase.getInstance().setPersistenceEnabled(true);
                    isPersistent = true;
                }
            }
        }
    }
}
