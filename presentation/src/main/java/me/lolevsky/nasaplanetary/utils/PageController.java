package me.lolevsky.nasaplanetary.utils;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

public class PageController extends RecyclerView.OnScrollListener {
    private final String TAG = PageController.class.getSimpleName();
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    GridLayoutManager layoutManager;
    onNewPageRequest listener;
    private boolean loading = true;

    public PageController(RecyclerView recyclerView, onNewPageRequest listener) {
        if (!(recyclerView.getLayoutManager() instanceof GridLayoutManager)) {
            new Throwable("support only GridLayoutManager");
        }

        this.layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
        this.listener = listener;

        recyclerView.addOnScrollListener(this);
    }

    public void setOnLoadFinish(boolean isLoadFinish) {
        loading = isLoadFinish;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        if (dy > 0) //check for scroll down
        {
            if (layoutManager == null) {
                new Throwable("LayoutManager not defined");
            }

            visibleItemCount = layoutManager.getChildCount();
            totalItemCount = layoutManager.getItemCount();
            pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();

            if (loading) {
                if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                    loading = false;

                    listener.onNewPageRequest(totalItemCount);
                }
            }
        }
    }

    public interface onNewPageRequest {
        void onNewPageRequest(int lastItemIndex);
    }
}

