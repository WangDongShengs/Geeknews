package com.wds.base;

public interface IBasePresenter<V> {
    void attachView(V view);
    void detachView();
}
