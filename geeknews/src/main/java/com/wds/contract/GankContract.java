package com.wds.contract;

import com.wds.base.IBasePresenter;
import com.wds.base.IBaseView;


public interface GankContract {
    interface View<T> extends IBaseView{
        void successUI(T t);

    }

    interface Presenter extends IBasePresenter<View>{

        void getGankListData();
    }

}
