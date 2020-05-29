package com.wds.fragment;

import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.wds.adapter.GankSubAdapter;
import com.wds.bean.GankBean;
import com.wds.contract.GankContract;
import com.wds.geeknews.R;
import com.wds.presenter.GankPresenter;
import com.wds.util.ScreenUtils;

public class GankSubFragment extends BaseMVPFragment<GankPresenter> implements GankContract.View<GankBean>{

    private RecyclerView recycler;
    private GankSubAdapter gankSubAdapter;

    @Override
    public void successUI(GankBean gankBean) {
        gankSubAdapter.addDataList(gankBean.getResults());
    }

    @Override
    public void error(String mag) {
  Toast.makeText(getActivity(), mag, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected GankPresenter initPresenter() {
        return new GankPresenter();
    }

    @Override
    protected void initData() {
    presenter.getGankListData();
    }

    @Override
    protected void initView(View inflate) {
        super.initView(inflate);
        AppBarLayout appbar = inflate.findViewById(R.id.appbar);
        ImageView img = inflate.findViewById(R.id.img);
        final ImageView imgBlur = inflate.findViewById(R.id.img_blur);
        recycler = inflate.findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        gankSubAdapter = new GankSubAdapter(getActivity());
        recycler.setAdapter(gankSubAdapter);
        Glide.with(getActivity()).load("http://ww1.sinaimg.cn/large/0065oQSqly1fs7l8ijitfj30jg0shdkc.jpg").into(img);
        imgBlur.setAlpha(0f);
        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                int dip2px = ScreenUtils.dip2px(getActivity(), 200f);
                float parse=(float) Math.abs(i)/dip2px;
                imgBlur.setAlpha(parse);
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_sub_gank;
    }
}
