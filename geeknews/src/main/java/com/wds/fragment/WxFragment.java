package com.wds.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.wds.adapter.CartAdapter;
import com.wds.bean.CartBean;
import com.wds.bean.CartEvent;
import com.wds.contract.CartContract;
import com.wds.geeknews.R;
import com.wds.presenter.CartPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class WxFragment extends BaseMVPFragment<CartPresenter> implements CartContract.View {

    private RecyclerView recycler;
    private TextView count;
    private TextView total;
    private CartAdapter cartAdapter;
    private CheckBox checkbox;

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        presenter.getPresenter();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getEvent(CartEvent cartEvent){
        count.setText("全部：("+cartEvent.number+")");
        total.setText("总价："+cartEvent.price+" 元");
    }
    @Override
    protected CartPresenter initPresenter() {
        return new CartPresenter();
    }

    @Override
    protected void initView(View inflate) {
        super.initView(inflate);
        recycler = inflate.findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        cartAdapter = new CartAdapter(getActivity());
        recycler.setAdapter(cartAdapter);
        checkbox = inflate.findViewById(R.id.checkbox);
        count = inflate.findViewById(R.id.count);
        total = inflate.findViewById(R.id.total_price);
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_wx;
    }

    @Override
    public void succeed(CartBean cartBean) {
        cartAdapter.addDataList(cartBean.getData().getCartList());
    }

    @Override
    public void error(String mag) {
        Toast.makeText(getActivity(), mag, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
