package com.wds.api;


import com.wds.bean.CartBean;
import com.wds.bean.DailyDetailBean;
import com.wds.bean.DailyListBean;
import com.wds.bean.FuLiBean;
import com.wds.bean.GankBean;
import com.wds.bean.SectionListBean;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    //    http://news-at.zhihu.com/api/4/news/latest

    String zhihuUrl = "http://news-at.zhihu.com/";

    @GET("api/4/news/latest")
    Flowable<DailyListBean> getLatestList();

    @GET("api/4/news/before/{date}")
    Flowable<DailyListBean> getBeForeLatestList(@Path("date") String date);
   /**
     * 专栏日报
     */
    @GET("api/4/sections")
    Observable<SectionListBean> getSectionList();

     /**
     * 日报详情
     * http://news-at.zhihu.com/api/4/news/9713242
     */
    @GET("api/4/news/{id}")
    Observable<DailyDetailBean> getDetailInfo(@Path("id") String id);





    String cartUrl=" http://cdwan.cn/";

    //    http://cdwan.cn/api/cart/index
    @GET("api/cart/index")
    Flowable<CartBean> getCartListBean();


    String gankUrl ="https://gank.io/";
    /**
     *
     * @param type  类型  如：Android Ios
     * @param num  每页的总数
     * @return
     */
    //https://gank.io/api/data/Android/10/10
    @GET("api/data/{type}/{num}/10")
    Flowable<GankBean> getGankListBean(@Path("type") String type, @Path("num") String num);

    @GET("api/data/%E7%A6%8F%E5%88%A9/20/3")
    Flowable<FuLiBean> getFuLiBean();

    String TAB_HOST = "https://www.v2ex.com/?tab=";

    @GET("")
    Flowable<ResponseBody> getV2EXListData(@Query("tab") String type);

}
