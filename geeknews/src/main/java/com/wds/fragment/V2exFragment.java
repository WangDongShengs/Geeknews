package com.wds.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.wds.geeknews.NoteActivity;
import com.wds.geeknews.R;

import java.util.ArrayList;
import java.util.List;

public class V2exFragment extends BaseFragment {
    public static String[] typeStr = {"技术", "创意", "好玩", "Apple", "酷工作", "交易", "城市", "问与答", "最热", "全部", "R2"};
    public static String[] type = {"tech", "creative", "play", "apple", "jobs", "deals", "city", "qna", "hot", "all", "r2"};
    List<Fragment> fragments = new ArrayList<>();
    private ViewPager mVtexMainVp;
    private ImageView mVtexMenuIv;
    private TabLayout mVtexMainTab;

    @Override
    protected void initData() {
        for (int i = 0; i < typeStr.length; i++) {
            VtexPagerFragment vtexPagerFragment = new VtexPagerFragment();

            Bundle bundle = new Bundle();
            bundle.putString("type",type[i]);
            vtexPagerFragment.setArguments(bundle);
            fragments.add(vtexPagerFragment);

            mVtexMainTab.addTab(mVtexMainTab.newTab());

        }
        // viewPager fragment  tablayout
        mVtexMainVp.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragments.get(i);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return typeStr[position];
            }
        });

        mVtexMainTab.setupWithViewPager(mVtexMainVp);

    }

    @Override
    protected void initView(View itemView) {
        mVtexMainTab = (TabLayout) itemView.findViewById(R.id.tab_vtex_main);
        mVtexMenuIv = (ImageView) itemView.findViewById(R.id.iv_vtex_menu);
        mVtexMainVp = (ViewPager) itemView.findViewById(R.id.vp_vtex_main);


        mVtexMainTab.setTabMode(TabLayout.MODE_SCROLLABLE);

        mVtexMenuIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), NoteActivity.class));
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_vtex;
    }
}
