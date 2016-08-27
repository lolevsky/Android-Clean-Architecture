package me.lolevsky.nasaplanetary.view.presenter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import me.lolevsky.nasaplanetary.domain.interactor.BaseInteractor;
import me.lolevsky.nasaplanetary.domain.tracking.ITracking;
import me.lolevsky.nasaplanetary.mapper.IModelDataMapper;
import me.lolevsky.nasaplanetary.utils.PageController;
import me.lolevsky.nasaplanetary.view.IView;
import rx.Subscriber;

public abstract class BasePresenter<T extends IView, M, K> implements Presenter<T, M>, PageController.onNewPageRequest {
    protected T view;
    protected M model;
    BaseInteractor interaptor;
    IModelDataMapper<K, M> modelDataMapper;
    boolean isSupportPaging = false;
    PageController pageController;
    protected ITracking tracking;

    BasePresenter() {

    }

    public BasePresenter(BaseInteractor interaptor, IModelDataMapper modelDataMapper, ITracking tracking) {
        this.interaptor = interaptor;
        this.modelDataMapper = modelDataMapper;
        this.tracking = tracking;
    }

    public PageController getPageController() {
        return pageController;
    }

    public void initPageController(RecyclerView recyclerView) {
        this.pageController = new PageController(recyclerView, this);
        isSupportPaging = true;
    }

    public abstract void pagingAddNewData(M newModel);

    @Override public void loadData(String... params) {
        if (view != null) {
            if (isSupportPaging && model != null) {
                view.onLoadingMore();
            } else {
                view.onLoading();
            }
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
                M tempMode = modelDataMapper.transform(response);
                if (isSupportPaging) {
                    pageController.setOnLoadFinish(true);
                    if (model == null) {
                        setModel(tempMode);
                    } else {
                        pagingAddNewData(tempMode);
                        if (view != null) {
                            view.onComplete(model);
                        }
                    }
                } else {
                    setModel(tempMode);
                }
            }
        }, params);
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

    @Override public ITracking getTracking() {
        return tracking;
    }
}
