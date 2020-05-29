package com.wds.manager;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpManager {
    private HttpManager() {
    }
    private static HttpManager httpManager;
    public static HttpManager getInstance(){
        if (httpManager==null){
            synchronized (HttpManager.class){
                if (httpManager==null){
                    httpManager=new HttpManager();
                }
            }
        }
        return httpManager;
    }

    public <T> T getService(String url,Class<T> tClass){
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(url)
                .build()
                .create(tClass);
    }
}
