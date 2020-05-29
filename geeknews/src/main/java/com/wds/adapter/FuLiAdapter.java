package com.wds.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wds.bean.FuLiBean;
import com.wds.geeknews.R;

import java.util.Random;

public class FuLiAdapter extends BaseAdapter<FuLiBean.ResultsBean> {

    public FuLiAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayout() {
        return R.layout.fuli_item
                ;
    }

    @Override
    protected void bindData(BaseViewHolder baseViewHolder, FuLiBean.ResultsBean resultsBean) {
        ImageView img = (ImageView) baseViewHolder.getViewById(R.id.img);
        Glide.with(context).load(resultsBean.getUrl()).into(img);
        Random random = new Random();
        int i = random.nextInt(500);
        int height= i<300? 300:i;
        ViewGroup.LayoutParams layoutParams = img.getLayoutParams();
        layoutParams.height=height;
        img.setLayoutParams(layoutParams);
    }
}
