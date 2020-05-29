package com.wds.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.wds.bean.TabEvent;
import com.wds.geeknews.GoldManagerActivity;
import com.wds.geeknews.R;
import com.wds.util.ConstantsUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class GoldFragment extends BaseFragment {
    public static String[] typeStr = {"Android", "iOS", "前端", "后端", "设计", "产品", "阅读", "工具资源"};
    private ImageView img;
    private ViewPager voewPager;
    private TabLayout tabLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getEvent(TabEvent tabEvent){
        tabLayout.removeAllTabs();
        for (int i = 0; i < ConstantsUtil.isSelected.size(); i++) {
            if (ConstantsUtil.isSelected.get(i)){
                tabLayout.addTab(tabLayout.newTab().setText(ConstantsUtil.titleList.get(i)));
            }
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void initData() { }

    @Override
    protected void initView(View inflate) {
        for (int i = 0; i < typeStr.length; i++) {
            ConstantsUtil.titleList.add(typeStr[i]);
            if (i%2==0){
                ConstantsUtil.isSelected.add(true);
            }else {
                ConstantsUtil.isSelected.add(false);
            }
        }
        tabLayout = inflate.findViewById(R.id.tabLayout);
        voewPager = inflate.findViewById(R.id.viewPager);
        img = inflate.findViewById(R.id.img);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        for (int i = 0; i < ConstantsUtil.isSelected.size(); i++) {
            if (ConstantsUtil.isSelected.get(i)){
                tabLayout.addTab(tabLayout.newTab().setText(ConstantsUtil.titleList.get(i)));
            }
        }
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), GoldManagerActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_gold;
    }
}
