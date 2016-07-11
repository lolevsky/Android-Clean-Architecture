package me.lolevsky.nasaplanetary.presenter;

import android.support.annotation.NonNull;

import me.lolevsky.nasaplanetary.domain.interactor.BaseInteractor;
import me.lolevsky.nasaplanetary.mapper.IModelDataMapper;
import me.lolevsky.nasaplanetary.view.IView;
import rx.Subscriber;

public abstract class BasePresenter<T extends IView, M, K> implements Presenter<T, M> {
    T view;
    M model;
    BaseInteractor interaptor;
    IModelDataMapper<K, M> modelDataMapper;

    BasePresenter() {

    }

    public BasePresenter(BaseInteractor interaptor, IModelDataMapper modelDataMapper) {
        this.interaptor = interaptor;
        this.modelDataMapper = modelDataMapper;
    }

    @Override public void loadData() {
        if (view != null) {
            view.onLoading();
        }

        interaptor.execute(new Subscriber<K>() {
            @Override public void onCompleted() {

            }

            @Override public void onError(Throwable e) {
                if (view != null) {
                    view.onError(e.getMessage());
                }
            }

            @Override public void onNext(K response) {
                setModel(modelDataMapper.transform(response));
            }
        });
    }

    @Override public void setView(@NonNull T view) {
        this.view = view;
    }

    @Override public M getModel() {
        return model;
    }

    @Override public void setModel(M model) {
        this.model = model;
        if (view != null) {
            view.onComplete(model);
        }
    }

    @Override public void destroy() {
        interaptor.unsubscribe();
        view = null;
    }
}
