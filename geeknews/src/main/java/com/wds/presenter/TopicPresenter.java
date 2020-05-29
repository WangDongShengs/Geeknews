package com.wds.presenter;

import com.wds.api.ApiService;
import com.wds.base.BasePresenter;
import com.wds.bean.SectionListBean;
import com.wds.contract.TopicContract;
import com.wds.manager.HttpManager;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TopicPresenter extends BasePresenter<TopicContract.View> implements TopicContract.presenter {
    @Override
    public void topicPresenter() {
        ApiService service = HttpManager.getInstance().getService(ApiService.zhihuUrl, ApiService.class);
        service.getSectionList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SectionListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SectionListBean sectionListBean) {
                        view.succeed(sectionListBean);
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
