package com.wds.geeknews;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.wds.adapter.GoldManager;
import com.wds.bean.TabEvent;
import com.wds.util.ConstantsUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoldManagerActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private GoldManager goldManager;
    ItemTouchHelper itemTouchHelper=new ItemTouchHelper(new ItemTouchHelper.Callback() {
        @Override
        public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            int dragFlags = ItemTouchHelper.UP|ItemTouchHelper.DOWN;
            return makeMovementFlags(dragFlags,0);
        }

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
            int oldPos = viewHolder.getAdapterPosition();
            int newPos = viewHolder1.getAdapterPosition();

            // 更新数据集合里的位置
            Collections.swap(ConstantsUtil.titleList,oldPos,newPos);
            Collections.swap(ConstantsUtil.isSelected,oldPos,newPos);

            // 适配器 局部刷新
            goldManager.notifyItemMoved(oldPos,newPos);
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        }
    });
    @Override
    protected int getLayout() {
        return R.layout.activity_gold_manager;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        toolbar.setTitle("管理页面");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.video_webview_bar_back_d);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        recycler.setLayoutManager(new LinearLayoutManager(this));
        goldManager = new GoldManager(this);
        recycler.setAdapter(goldManager);
        itemTouchHelper.attachToRecyclerView(recycler);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().post(new TabEvent());
    }
}
