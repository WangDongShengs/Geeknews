package com.wds.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.wds.bean.GankBean;
import com.wds.geeknews.R;

public class GankSubAdapter extends BaseAdapter<GankBean.ResultsBean> {
    public GankSubAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayout() {
        return R.layout.sub_item;
    }

    @Override
    protected void bindData(BaseViewHolder baseViewHolder, GankBean.ResultsBean resultsBean) {
        TextView title = (TextView) baseViewHolder.getViewById(R.id.title);
        TextView createdAt = (TextView) baseViewHolder.getViewById(R.id.createdAt);
        title.setText(resultsBean.getDesc());
        createdAt.setText(resultsBean.getCreatedAt());
    }
}
