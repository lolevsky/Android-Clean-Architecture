package me.lolevsky.nasaplanetary.view;

import org.parceler.Parcels;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import me.lolevsky.nasaplanetary.presenter.Presenter;

public abstract class BaseActivity<T extends IView, M> extends AppCompatActivity implements IView<M> {
    private final String SAVE_INSTANCE_STATE = "SaveInstanceState";

    abstract Presenter getPresenter();

    @Override protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putParcelable(SAVE_INSTANCE_STATE, Parcels.wrap(getPresenter().getModel()));
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            getPresenter().setModel(Parcels.unwrap(savedInstanceState.getParcelable(SAVE_INSTANCE_STATE)));
        }
    }

    @Override protected void onStart() {
        super.onStart();
        getPresenter().setView(this);
    }

    @Override protected void onStop() {
        super.onStop();
        getPresenter().setView(null);
    }

    @Override protected void onPause() {
        super.onPause();
        getPresenter().pause();
    }

    @Override protected void onResume() {
        super.onResume();
        getPresenter().resume();
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        getPresenter().destroy();
    }
}
