package com.wds.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.wds.geeknews.R;

import java.util.ArrayList;

public class GankFragment extends BaseFragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View inflate) {
        viewPager = inflate.findViewById(R.id.viewPager);
        tabLayout = inflate.findViewById(R.id.tabLayout);
        final ArrayList<Fragment> list = new ArrayList<>();
        list.add(new GankSubFragment());
        list.add(new FuLiFragment());
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
        tabLayout.getTabAt(0).setText("ANDROID");
        tabLayout.getTabAt(1).setText("IOS");
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_gank;
    }
}
