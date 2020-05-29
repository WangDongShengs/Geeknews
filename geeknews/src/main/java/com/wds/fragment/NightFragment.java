package com.wds.fragment;

import android.support.v7.widget.AppCompatCheckBox;
import android.view.View;

import com.wds.bean.NightEvent;
import com.wds.geeknews.R;
import com.wds.util.SPUtils;

import org.greenrobot.eventbus.EventBus;

public class NightFragment extends BaseFragment{

    private AppCompatCheckBox night;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View inflate) {
        night = inflate.findViewById(R.id.cb_setting_night);
        //获取默认的夜间模式的状态
        final boolean status = SPUtils.getInstance().getBoolean("夜间", false);

        night.setChecked(status);

//        mSettingNightCb.setOnCheckedChangeListener();
        night.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final boolean isNight = SPUtils.getInstance().getBoolean("夜间", false);

                // 状态的保存  文件保存 sharedPrefrences(工具类)
                SPUtils.getInstance().put("夜间",!isNight);


                //发送eventbus ，通过UI切换夜间模式
                NightEvent nightEvent = new NightEvent();
                nightEvent.setNight(!isNight);
                EventBus.getDefault().post(nightEvent);

            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_night;
    }
}
