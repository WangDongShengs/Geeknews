package com.wds.geeknews;

import com.wds.base.BasePresenter;

public abstract class BaseMVPActivity<P extends BasePresenter> extends BaseActivity {
   public P presenter;

    @Override
    protected void initView() {
        presenter =    initPresenter();
        if (presenter!=null){
            presenter.attachView(this);
        }
    }

    protected abstract P initPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter!=null){
            presenter.detachView();
        }
    }
}
