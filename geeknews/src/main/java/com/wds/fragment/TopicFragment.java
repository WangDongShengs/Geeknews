package com.wds.fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.wds.adapter.TopicAdapter;
import com.wds.bean.SectionListBean;
import com.wds.contract.TopicContract;
import com.wds.geeknews.R;
import com.wds.presenter.TopicPresenter;

import java.util.List;

public class TopicFragment extends BaseMVPFragment<TopicPresenter> implements TopicContract.View<SectionListBean> {

    private TopicAdapter topicAdapter;

    @Override
    public void succeed(SectionListBean sectionListBean) {
        List<SectionListBean.DataBean> data = sectionListBean.getData();
        topicAdapter.addDataList(data);
    }


    @Override
    public void error(String mag) {
        Toast.makeText(getActivity(), mag, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected TopicPresenter initPresenter() {
        return new TopicPresenter();
    }

    @Override
    protected void initData() {
        presenter.topicPresenter();
    }

    @Override
    protected void initView(View inflate) {
        super.initView(inflate);
        RecyclerView recycler = inflate.findViewById(R.id.recycler);
        recycler.setLayoutManager(new GridLayoutManager(getActivity(),2));
        topicAdapter = new TopicAdapter(getActivity());
        recycler.setAdapter(topicAdapter);
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_topic;
    }


}
