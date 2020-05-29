package com.wds.presenter;
import com.wds.api.ApiService;
import com.wds.base.BasePresenter;
import com.wds.bean.DailyListBean;
import com.wds.contract.DailyContract;
import com.wds.manager.HttpManager;
import com.wds.util.RxUtil;
import io.reactivex.subscribers.ResourceSubscriber;

public class DailyPresenter extends BasePresenter<DailyContract.View> implements DailyContract.Presenter {
    @Override
    public void getPresenter() {
        ResourceSubscriber<DailyListBean> resourceSubscriber = HttpManager.getInstance().getService(ApiService.zhihuUrl, ApiService.class).
                getLatestList().compose(RxUtil.<DailyListBean>rxScheduler())
                .subscribeWith(new ResourceSubscriber<DailyListBean>() {
                    @Override
                    public void onNext(DailyListBean dailyListBean) {
                        view.success(dailyListBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        view.error(t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
        addSubscribe(resourceSubscriber);
    }

    @Override
    public void beForePresenter(String date) {
        ResourceSubscriber<DailyListBean> resourceSubscriber = HttpManager.getInstance().getService(ApiService.zhihuUrl, ApiService.class).
                getBeForeLatestList(date).compose(RxUtil.<DailyListBean>rxScheduler())
                .subscribeWith(new ResourceSubscriber<DailyListBean>() {
                    @Override
                    public void onNext(DailyListBean dailyListBean) {
                        view.beForeSuccess(dailyListBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        view.error(t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
        addSubscribe(resourceSubscriber);
    }
}
