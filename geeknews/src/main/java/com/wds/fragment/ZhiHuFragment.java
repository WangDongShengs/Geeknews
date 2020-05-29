package com.wds.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.wds.geeknews.R;

import java.util.ArrayList;

public class ZhiHuFragment extends BaseFragment {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void initData() {
        final ArrayList<Fragment> list = new ArrayList<>();
        list.add(new DailyFragment());
        list.add(new TopicFragment());
        list.add(new HotFragment());
        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("日报");
        tabLayout.getTabAt(1).setText("专栏");
        tabLayout.getTabAt(2).setText("热门");
    }

    @Override
    protected void initView(View inflate) {
        viewPager = inflate.findViewById(R.id.viewPager);
        tabLayout = inflate.findViewById(R.id.tabLayout);

    }

    @Override
    protected int getLayout() {
        return R.layout.layout_zhihu;
    }
}
