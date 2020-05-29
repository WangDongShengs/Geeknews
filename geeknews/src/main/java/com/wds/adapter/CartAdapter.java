package com.wds.adapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wds.bean.CartBean;
import com.wds.bean.CartEvent;
import com.wds.geeknews.R;

import org.greenrobot.eventbus.EventBus;

public class CartAdapter extends BaseAdapter<CartBean.DataBean.CartListBean> {
    public CartAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayout() {
        return R.layout.cart_item;
    }

    @Override
    protected void bindData(BaseViewHolder baseViewHolder, final CartBean.DataBean.CartListBean cartListBean) {
        CheckBox checkbox = (CheckBox) baseViewHolder.getViewById(R.id.checkbox);
        ImageView img = (ImageView) baseViewHolder.getViewById(R.id.img);
        TextView name = (TextView) baseViewHolder.getViewById(R.id.name);
        TextView price = (TextView) baseViewHolder.getViewById(R.id.price);
        TextView count = (TextView) baseViewHolder.getViewById(R.id.count);
        Glide.with(context).load(cartListBean.getList_pic_url()).into(img);
        name.setText(cartListBean.getGoods_name());
        price.setText(cartListBean.getRetail_price()+"");
        count.setText("X"+cartListBean.getNumber());
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                cartListBean.setChecked(b);
                compute();
            }
        });
        checkbox.setChecked(cartListBean.isChecked());
    }

    private void compute() {
        int price=0;
        int number=0;
        for (int i = 0; i < datalist.size(); i++) {
            CartBean.DataBean.CartListBean cartListBean = datalist.get(i);
            if (cartListBean.isChecked()){
                price=+cartListBean.getRetail_price()*cartListBean.getNumber();
                number=+cartListBean.getNumber();
            }

        }
        CartEvent cartEvent = new CartEvent();
        cartEvent.number=number;
        cartEvent.price=price;
        EventBus.getDefault().post(cartEvent);
    }
}
