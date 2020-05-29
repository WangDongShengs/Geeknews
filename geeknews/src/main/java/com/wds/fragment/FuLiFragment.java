package com.wds.fragment;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.wds.adapter.FuLiAdapter;
import com.wds.bean.FuLiBean;
import com.wds.contract.FuLiContract;
import com.wds.geeknews.R;
import com.wds.presenter.FuLiPresenter;

public class FuLiFragment extends BaseMVPFragment<FuLiPresenter> implements FuLiContract.View {

    private FuLiAdapter fuLiAdapter;

    @Override
    public void succeed(FuLiBean fuLiBean) {
        fuLiAdapter.addDataList(fuLiBean.getResults());
    }

    @Override
    public void error(String mag) {
        Toast.makeText(getActivity(), mag, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected FuLiPresenter initPresenter() {
        return new FuLiPresenter();
    }

    @Override
    protected void initView(View inflate) {
        super.initView(inflate);
        RecyclerView recycler = inflate.findViewById(R.id.recycler);
        recycler.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        fuLiAdapter = new FuLiAdapter(getActivity());
        recycler.setAdapter(fuLiAdapter);
    }

    @Override
    protected void initData() {
        presenter.getPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_fuli;
    }
}
