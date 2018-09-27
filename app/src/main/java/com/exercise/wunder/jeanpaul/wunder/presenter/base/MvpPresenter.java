package com.exercise.wunder.jeanpaul.wunder.presenter.base;

import com.exercise.wunder.jeanpaul.wunder.presentation.base.MvpView;

/**
 * Created by jean paul on 9/21/18.
 */
public interface MvpPresenter<P extends MvpView> {
    /**
     * Called when an {@code MvpView} is attached to this presenter.
     *
     * @param view The attached {@code MvpView}
     */
    void onAttach(P view);

    /**
     * Called when the view is resumed according to Android components
     * NOTE: this method will only be called for presenters that override it.
     */
    void onResume();

    /**
     * Called when an {@code MvpView} is detached from this presenter.
     */
    void onDetach();
}
