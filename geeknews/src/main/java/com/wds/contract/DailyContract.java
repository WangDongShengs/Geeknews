package com.wds.contract;

import com.wds.base.IBasePresenter;
import com.wds.base.IBaseView;

public interface DailyContract {
    interface View<T> extends IBaseView{
        void success(T t);
        void beForeSuccess(T t);
    }

    interface Presenter extends IBasePresenter<View> {
        void getPresenter();
        void beForePresenter(String date);
    }
}
