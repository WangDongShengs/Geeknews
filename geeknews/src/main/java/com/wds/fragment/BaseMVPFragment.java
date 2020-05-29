package com.wds.fragment;

import android.view.View;

import com.wds.base.BasePresenter;

public abstract class BaseMVPFragment <P extends BasePresenter> extends BaseFragment {
    public P presenter;
    @Override
    protected void initView(View inflate) {
       presenter= initPresenter();
        if (presenter!=null){
            presenter.attachView(this);
        }
    }

    protected abstract P initPresenter();
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (presenter!=null){
            presenter.detachView();
        }
    }
}
