package com.wds.contract;

import com.wds.base.IBasePresenter;
import com.wds.base.IBaseView;

public interface DailyDetailContract {
    interface View<T> extends IBaseView {
        void success(T t);
    }

    interface Presenter extends IBasePresenter<View> {
        void getPresenter(String id);
    }
}
