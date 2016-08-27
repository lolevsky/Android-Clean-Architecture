package me.lolevsky.nasaplanetary.view.screens.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.lolevsky.nasaplanetary.MainApplication;
import me.lolevsky.nasaplanetary.R;
import me.lolevsky.nasaplanetary.adapters.MainViewAdapter;
import me.lolevsky.nasaplanetary.adapters.OnItemClicked;
import me.lolevsky.nasaplanetary.domain.imageloader.IImageLoader;
import me.lolevsky.nasaplanetary.domain.remoteconfig.IRemoteConfig;
import me.lolevsky.nasaplanetary.model.MainScreenModule;
import me.lolevsky.nasaplanetary.view.BaseActivity;
import me.lolevsky.nasaplanetary.view.presenter.Presenter;
import me.lolevsky.nasaplanetary.view.screens.marsphoto.MarsPhotosActivity;
import me.lolevsky.nasaplanetary.view.screens.planetoryapod.PlanetaryApodActivity;

public class MainActivity extends BaseActivity<MainScreenModule> implements OnItemClicked {
    @Inject MainApplication application;
    @Inject MainPresenter mainPresenter;
    @Inject IImageLoader imageLoader;
    @Inject IRemoteConfig remoteConfig;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.recycler) RecyclerView recyclerView;
    @BindView(R.id.coordinator_layout) CoordinatorLayout coordinatorLayout;
    @BindView(R.id.image) ImageView imageBackground;

    MainViewAdapter mainViewAdapter;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainApplication) getApplication()).getApplicationComponent().inject(this);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        initList();

        imageLoader.loadImage(R.drawable.back_main, imageBackground);

        if (savedInstanceState == null) {
            mainPresenter.loadData();
        }

        checkPlayServices();
    }

    private void initList(){
        mainViewAdapter = new MainViewAdapter(imageLoader, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(application, LinearLayoutManager.VERTICAL, true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mainViewAdapter);
    }

    @Override protected Presenter getPresenter() {
        return mainPresenter;
    }

    @Override public void onLoading() {

    }

    @Override public void onLoadingMore() {

    }

    @Override public void onComplete(MainScreenModule model) {
        if (model != null) {
            mainViewAdapter.setList(model.getEntityList());
        }
    }

    @Override public void onError(String error) {
        Snackbar.make(coordinatorLayout, error, Snackbar.LENGTH_SHORT).show();
        coordinatorLayout.setVisibility(View.VISIBLE);
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        MenuItem about = menu.findItem(R.id.menu_about);
        switch (remoteConfig.getExperimentVariant(IRemoteConfig.EXPERIMENT_HOME_SCREEN_ABOUT_MENU)){
            case VARIANT_A:
                about.setIcon(R.drawable.ic_info_outline_white_24dp);
                about.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
                break;
            case VARIANT_B:
                about.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
                break;
            default:
                about.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        }

        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_about:
                getPresenter().getTracking().LogEventClick("About");
                showDialogAbout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showDialogAbout(){
        final SpannableString m = new SpannableString(getString(R.string.dialog_about_text));
        Linkify.addLinks(m, Linkify.WEB_URLS);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.title_about);
        builder.setMessage(m);
        builder.setPositiveButton(R.string.dialog_ok, null);
        AlertDialog alertDialog = builder.show();

        TextView textView = ((TextView) alertDialog.findViewById(android.R.id.message));
        if(textView != null){
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    public void onItemClicked(int position) {
        Intent intent = null;

        switch (position) {
            case 0:
                getPresenter().getTracking().LogEventClick("Open PlanetaryApodActivity");

                intent = new Intent(this, PlanetaryApodActivity.class);
                break;
            case 1:
                getPresenter().getTracking().LogEventClick("Open MarsPhotosActivity");

                intent = new Intent(this, MarsPhotosActivity.class);
                break;
            default:
                return;
        }

        startActivity(intent);
    }
}
