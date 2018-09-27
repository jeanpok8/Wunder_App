package com.exercise.wunder.jeanpaul.wunder.presentation.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.exercise.wunder.jeanpaul.wunder.presenter.base.MvpPresenter;

/**
 * Created by jean paul on 9/21/18.
 */
public abstract class BaseActivity<T extends MvpPresenter> extends AppCompatActivity implements MvpView {

    private T presenter;

    protected
    @NonNull
    T getPresenter() {
        if (presenter == null)
            presenter = createPresenter();
        if (presenter == null)
            throw new IllegalStateException("createPresenter() implementation returns null!");
        return presenter;
    }

    protected abstract T createPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPresenter().onAttach(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getPresenter().onDetach();
    }
}
