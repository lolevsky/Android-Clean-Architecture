package me.lolevsky.nasaplanetary.view.screens.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import me.lolevsky.nasaplanetary.MainApplication;
import me.lolevsky.nasaplanetary.domain.remoteconfig.IRemoteConfig;
import me.lolevsky.nasaplanetary.view.screens.main.MainActivity;

public class SplashActivity extends AppCompatActivity {
    @Inject IRemoteConfig remoteConfig;// needed for creating instance and init.

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainApplication) getApplication()).getApplicationComponent().inject(this);

        //give the opportunity for remote config get data.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }

    @Override
    public void onBackPressed() {

    }
}