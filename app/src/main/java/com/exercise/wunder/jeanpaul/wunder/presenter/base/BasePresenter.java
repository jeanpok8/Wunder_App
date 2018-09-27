package com.exercise.wunder.jeanpaul.wunder.presenter.base;

import com.exercise.wunder.jeanpaul.wunder.presentation.base.MvpView;

import java.lang.ref.WeakReference;

/**
 * Created by jean paul on 9/21/18.
 */
public abstract class BasePresenter<P extends MvpView> implements MvpPresenter<P> {

    private WeakReference<P> viewRef;

    @Override
    public void onAttach(P view) {
        viewRef = new WeakReference<>(view);
    }

    @Override
    public void onResume() {
        // Not mandatory for all views, if views are interested in receiving this event, they should
        // override this method
    }

    @Override
    public void onDetach() {
        if (viewRef != null) {
            viewRef.clear();
            viewRef = null;
        }

    }

    /**
     * @return True if the view this presenter is attached to still exists and not garbage collected
     * since we are holding it through a {@code WeakReference}
     */
    protected boolean isViewAttached() {
        return viewRef != null && viewRef.get() != null;
    }

    protected P getView() {
        return viewRef.get();
    }

}