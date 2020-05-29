package com.wds.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wds.adapter.DailyAdapter;
import com.wds.bean.CalendarEvent;
import com.wds.bean.DailyListBean;
import com.wds.contract.DailyContract;
import com.wds.geeknews.CalendarActivity;
import com.wds.geeknews.R;
import com.wds.presenter.DailyPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class DailyFragment extends BaseMVPFragment<DailyPresenter> implements DailyContract.View<DailyListBean>,View.OnClickListener {


    private DailyAdapter dailyAdapter;
    private static final String TAG = "DailyFragment";
    private RecyclerView recycler;
    private FloatingActionButton fab_calender;
    private String date;

    @Override
    public void error(String mag) {
        Toast.makeText(getActivity(), mag, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected DailyPresenter initPresenter() {
        return new DailyPresenter();
    }

    @Override
    protected void initData() {
        //注册eventbus 方便接收日历数据
        EventBus.getDefault().register(this);
        presenter.getPresenter();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiverDate(CalendarEvent event) {
        date = event.date;
        presenter.beForePresenter(date);
        Log.d(TAG, "receiverDate: " + date);

    }

    @Override
    protected void initView(View inflate) {
        super.initView(inflate);
        RecyclerView recycler = inflate.findViewById(R.id.recycler);
        fab_calender = inflate.findViewById(R.id.fab_calender);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        dailyAdapter = new DailyAdapter(getActivity());
        recycler.setAdapter(dailyAdapter);
        fab_calender.setOnClickListener(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_daily;
    }

    @Override
    public void success(DailyListBean dailyListBean) {
        dailyAdapter.setBanner(dailyListBean.getTop_stories());
        dailyAdapter.setStoryList(dailyListBean.getStories(),"集客新闻");
    }

    @Override
    public void beForeSuccess(DailyListBean dailyListBean) {
        //dailyAdapter.setBeForeStoryList(dailyListBean.getStories(),dailyListBean.getDate());
        dailyAdapter.setBeForeStoryList(dailyListBean.getStories(),date);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View view) {
        // TODO 20/05/11 跳转到日历界面
        startActivity(new Intent(getActivity(), CalendarActivity.class));
    }
}
