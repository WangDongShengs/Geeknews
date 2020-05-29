package com.wds.contract;

import com.wds.base.IBasePresenter;
import com.wds.base.IBaseView;
import com.wds.bean.CartBean;
import com.wds.bean.FuLiBean;

public interface FuLiContract {
    interface View extends IBaseView{
        void succeed(FuLiBean fuLiBean);
    }
    interface presenter extends IBasePresenter<View>{
        void getPresenter();
    }
}
