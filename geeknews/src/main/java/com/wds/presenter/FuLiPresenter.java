package com.wds.presenter;

import com.wds.api.ApiService;
import com.wds.base.BasePresenter;
import com.wds.bean.CartBean;
import com.wds.bean.FuLiBean;
import com.wds.contract.CartContract;
import com.wds.contract.FuLiContract;
import com.wds.manager.HttpManager;
import com.wds.util.RxUtil;

import io.reactivex.subscribers.ResourceSubscriber;

public class FuLiPresenter extends BasePresenter<FuLiContract.View> implements FuLiContract.presenter {
    @Override
    public void getPresenter() {
        ApiService service = HttpManager.getInstance().getService(ApiService.gankUrl, ApiService.class);
        service.getFuLiBean().compose(RxUtil.<FuLiBean>rxScheduler())
                .subscribe(new ResourceSubscriber<FuLiBean>() {
                    @Override
                    public void onNext(FuLiBean fuLiBean) {
                        view.succeed(fuLiBean);
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
