package com.wds.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wds.bean.V2exListBean;

import java.util.ArrayList;
import java.util.List;

import com.wds.geeknews.R;
import butterknife.BindView;

/**
 * Created by codeest on 16/12/23.
 */

public class V2exAdapter extends RecyclerView.Adapter<V2exAdapter.ViewHolder> {

    private Context mContext;
    private LayoutInflater inflater;
    private List<V2exListBean> mList =new ArrayList<>();

    private static final String TAG = "V2exAdapter";
    public V2exAdapter(Context context) {
        this.mContext = context;

        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_vtex, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        V2exListBean bean = mList.get(position);
        Glide.with(mContext).load(bean.getImgUrl()).into(holder.ivTopicFace);
        holder.tvTopicTitle.setText(bean.getTitle());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void updateData(List<V2exListBean> mList) {
        this.mList .addAll(mList);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_topic_face)
        ImageView ivTopicFace;
        @BindView(R.id.tv_topic_title)
        TextView tvTopicTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ivTopicFace = itemView.findViewById(R.id.iv_topic_face);
            tvTopicTitle = itemView.findViewById(R.id.tv_topic_title);
        }
    }
}
