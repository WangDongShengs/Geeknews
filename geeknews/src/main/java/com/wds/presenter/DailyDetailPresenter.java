package com.wds.presenter;

import com.wds.api.ApiService;
import com.wds.base.BasePresenter;
import com.wds.bean.CartBean;
import com.wds.bean.DailyDetailBean;
import com.wds.contract.DailyDetailContract;
import com.wds.manager.HttpManager;
import com.wds.util.RxUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DailyDetailPresenter extends BasePresenter<DailyDetailContract.View> implements DailyDetailContract.Presenter {
    @Override
    public void getPresenter(String id) {
        ApiService service = HttpManager.getInstance().getService(ApiService.zhihuUrl, ApiService.class);
        service.getDetailInfo(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DailyDetailBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DailyDetailBean dailyDetailBean) {
                        view.success(dailyDetailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.error(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
