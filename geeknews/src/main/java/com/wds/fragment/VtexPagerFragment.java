package com.wds.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wds.adapter.V2exAdapter;
import com.wds.bean.V2exListBean;
import com.wds.contract.V2exContract;
import com.wds.geeknews.R;
import com.wds.presenter.V2exPresenter;

import java.util.List;

public class VtexPagerFragment extends BaseMVPFragment<V2exPresenter> implements V2exContract.View {

    private RecyclerView mMainView;
    private V2exAdapter v2exAdapter;

    @Override
    protected void initView(View inflate) {
        super.initView(inflate);
        mMainView = (RecyclerView) inflate.findViewById(R.id.view_main);
        mMainView.setLayoutManager(new LinearLayoutManager(getActivity()));
        v2exAdapter = new V2exAdapter(getActivity());
        mMainView.setAdapter(v2exAdapter);
    }

    @Override
    protected void initData() {

        String type = getArguments().getString("type");
        presenter.getV2exListData(type);
    }

    @Override
    protected int getLayout() {
        return R.layout.vtex_page;
    }


    @Override
    public void successUI(List<V2exListBean> v2exListBeanList) {
        v2exAdapter.updateData(v2exListBeanList);
    }


    @Override
    public void error(String mag) {

    }

    @Override
    protected V2exPresenter initPresenter() {
        return new V2exPresenter();
    }
}
