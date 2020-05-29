package com.wds.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wds.bean.SectionListBean;
import com.wds.geeknews.R;
import com.wds.util.ScreenUtils;

public class TopicAdapter extends BaseAdapter<SectionListBean.DataBean> {
    int width;
    public TopicAdapter(Context context) {
        super(context);
        width= ScreenUtils.getScreenWidth(context);
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_item;
    }

    @Override
    protected void bindData(BaseViewHolder baseViewHolder, SectionListBean.DataBean dataBean) {
        ImageView img = (ImageView) baseViewHolder.getViewById(R.id.img);
        TextView name = (TextView) baseViewHolder.getViewById(R.id.name);
        ViewGroup.LayoutParams layoutParams = img.getLayoutParams();
        layoutParams.width=width/2;
        layoutParams.height=width/2;
        img.setLayoutParams(layoutParams);
        name.setText(dataBean.getName());
        Glide.with(context).load(dataBean.getThumbnail()).into(img);
    }
}
