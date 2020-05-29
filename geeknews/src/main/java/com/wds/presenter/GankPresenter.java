package com.wds.presenter;


import android.util.Log;

import com.wds.api.ApiService;
import com.wds.base.BasePresenter;
import com.wds.bean.GankBean;
import com.wds.contract.GankContract;
import com.wds.manager.HttpManager;
import com.wds.util.RxUtil;

import io.reactivex.subscribers.ResourceSubscriber;

public class GankPresenter extends BasePresenter<GankContract.View> implements GankContract.Presenter{
    private static final String TAG = "GankPresenter";
    @Override
    public void getGankListData() {
        ApiService service = HttpManager.getInstance().getService(ApiService.gankUrl, ApiService.class);
        service.getGankListBean("Android","10").compose(RxUtil.<GankBean>rxScheduler())
                .subscribe(new ResourceSubscriber<GankBean>() {
                    @Override
                    public void onNext(GankBean gankBean) {
                        view.successUI(gankBean);
                        Log.d(TAG, "onNext: "+gankBean.toString());
                    }

                    @Override
                    public void onError(Throwable t) {
                        view.error(t.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
