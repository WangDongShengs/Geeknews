package com.wds.presenter;

import com.wds.api.ApiService;
import com.wds.base.BasePresenter;
import com.wds.bean.CartBean;
import com.wds.contract.CartContract;
import com.wds.manager.HttpManager;
import com.wds.util.RxUtil;

import io.reactivex.subscribers.ResourceSubscriber;

public class CartPresenter extends BasePresenter<CartContract.View> implements CartContract.presenter {
    @Override
    public void getPresenter() {
        ApiService service = HttpManager.getInstance().getService(ApiService.cartUrl, ApiService.class);
        service.getCartListBean().compose(RxUtil.<CartBean>rxScheduler())
                .subscribe(new ResourceSubscriber<CartBean>() {
                    @Override
                    public void onNext(CartBean cartBean) {
                        view.succeed(cartBean);
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
