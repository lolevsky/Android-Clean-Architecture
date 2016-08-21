package me.lolevsky.nasaplanetary.view.screens.planetoryapod;

import javax.inject.Inject;

import dagger.internal.Preconditions;
import me.lolevsky.nasaplanetary.data.net.result.ApodResponse;
import me.lolevsky.nasaplanetary.domain.interactor.PlanetaryApodInteraptor;
import me.lolevsky.nasaplanetary.domain.tracking.ITracking;
import me.lolevsky.nasaplanetary.mapper.ApodModelDataMapper;
import me.lolevsky.nasaplanetary.model.ApodModel;
import me.lolevsky.nasaplanetary.view.presenter.BasePresenter;
import me.lolevsky.nasaplanetary.view.IView;

public class PlanetaryApodPresenter extends BasePresenter<IView, ApodModel, ApodResponse> {
    @Inject
    public PlanetaryApodPresenter(PlanetaryApodInteraptor planetaryApodInteraptor,
                                  ApodModelDataMapper apodModelDataMapper,
                                  ITracking tracking) {
        super(Preconditions.checkNotNull(planetaryApodInteraptor), Preconditions.checkNotNull(apodModelDataMapper), Preconditions.checkNotNull(tracking));
        tracking.LogEventScreen("PlanetaryApodScreen");
    }

    @Override public void resume() {

    }

    @Override public void pause() {

    }

    @Override public void pagingAddNewData(ApodModel newModel) {

    }

    @Override public void onNewPageRequest(int lastItemIndex) {

    }
}
