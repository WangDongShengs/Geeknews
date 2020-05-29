package com.wds.contract;

import com.wds.base.IBasePresenter;
import com.wds.base.IBaseView;
import com.wds.bean.CartBean;

public interface CartContract {
    interface View extends IBaseView{
        void succeed(CartBean cartBean);
    }
    interface presenter extends IBasePresenter<View>{
        void getPresenter();
    }
}
