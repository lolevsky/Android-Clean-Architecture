package me.lolevsky.nasaplanetary.view;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import org.parceler.Parcels;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import me.lolevsky.nasaplanetary.view.presenter.Presenter;

public abstract class BaseActivity<M> extends AppCompatActivity implements IView<M> {
    private final String SAVE_INSTANCE_STATE = "SaveInstanceState";

    Dialog errorDialog;

    protected abstract Presenter getPresenter();

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

    @Override public void onStartActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        intent.putExtras(bundle);

        startActivity(intent);
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

    protected boolean checkPlayServices() {
        final int playServicesStatus = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);
        if (playServicesStatus != ConnectionResult.SUCCESS) {
            if (errorDialog == null || (errorDialog != null && !errorDialog.isShowing())) {
                //If google play services in not available show an error dialog and return
                errorDialog = GoogleApiAvailability.getInstance().getErrorDialog(this, playServicesStatus, 0, new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        finish();
                    }
                });
                errorDialog.show();
            }
            return false;
        }

        return true;
    }
}
