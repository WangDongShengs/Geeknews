package com.wds.base;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BasePresenter <V> implements IBasePresenter<V>{
    public V view;
    private WeakReference<V> vWeakReference;
    private CompositeDisposable compositeDisposable;

    @Override
    public void attachView(V view) {
        vWeakReference = new WeakReference<>(view);
        this.view=vWeakReference.get();

    }

    @Override
    public void detachView() {
        if (view!=null){
            view=null;
        }
        if (compositeDisposable!=null){
            //取消连接
            compositeDisposable.dispose();
        }
    }

    public void addSubscribe(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }
}
