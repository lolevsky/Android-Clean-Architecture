package me.lolevsky.nasaplanetary.presenter;

import javax.inject.Inject;

import dagger.internal.Preconditions;
import me.lolevsky.nasaplanetary.data.net.result.ApodResponse;
import me.lolevsky.nasaplanetary.domain.interactor.PlanetaryApodInteraptor;
import me.lolevsky.nasaplanetary.mapper.ApodModelDataMapper;
import me.lolevsky.nasaplanetary.model.ApodModel;
import me.lolevsky.nasaplanetary.view.IView;

public class PlanetaryApodPresenter extends BasePresenter<IView, ApodModel, ApodResponse> {
    @Inject
    public PlanetaryApodPresenter(PlanetaryApodInteraptor planetaryApodInteraptor,
                                  ApodModelDataMapper apodModelDataMapper) {
        super(Preconditions.checkNotNull(planetaryApodInteraptor), Preconditions.checkNotNull(apodModelDataMapper));
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
