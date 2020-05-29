package com.wds.contract;

import com.wds.base.IBasePresenter;
import com.wds.base.IBaseView;

public interface TopicContract {
    interface View<T> extends IBaseView{
        void succeed(T t);
    }
    interface presenter extends IBasePresenter<View>{
        void topicPresenter();
    }
}
