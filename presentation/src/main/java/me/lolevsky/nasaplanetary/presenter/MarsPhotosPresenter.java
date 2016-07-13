package me.lolevsky.nasaplanetary.presenter;

import javax.inject.Inject;

import dagger.internal.Preconditions;
import me.lolevsky.nasaplanetary.data.net.result.MarsPhotosResponse;
import me.lolevsky.nasaplanetary.domain.interactor.MarsPhotosInteraptor;
import me.lolevsky.nasaplanetary.mapper.MarsPhotosModelDataMapper;
import me.lolevsky.nasaplanetary.model.MarsPhotosModel;
import me.lolevsky.nasaplanetary.view.IView;

public class MarsPhotosPresenter extends BasePresenter<IView, MarsPhotosModel, MarsPhotosResponse> {
    @Inject
    public MarsPhotosPresenter(MarsPhotosInteraptor marsPhotosInteraptor,
                               MarsPhotosModelDataMapper marsPhotosModelDataMapper) {
        super(Preconditions.checkNotNull(marsPhotosInteraptor), Preconditions.checkNotNull(marsPhotosModelDataMapper));
    }

    @Override public void loadData(String... params) {
        super.loadData(getModel() == null ? "0" : String.valueOf(getModel().getPageNumber()));
    }

    @Override public void resume() {

    }

    @Override public void pause() {

    }
}
