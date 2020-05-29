package com.wds.contract;


import com.wds.base.IBasePresenter;
import com.wds.base.IBaseView;
import com.wds.bean.V2exListBean;

import java.util.List;

/**
 * 购物车   契约类
 */
public interface V2exContract {
    interface View extends IBaseView {
        void successUI(List<V2exListBean> v2exListBeanList);

    }

    interface Presenter extends IBasePresenter<View> {

        void getV2exListData(String type);
    }

}
