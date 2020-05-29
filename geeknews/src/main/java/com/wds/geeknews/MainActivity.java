package com.wds.geeknews;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.wds.bean.NightEvent;
import com.wds.fragment.GankFragment;
import com.wds.fragment.GoldFragment;
import com.wds.fragment.NightFragment;
import com.wds.fragment.V2exFragment;
import com.wds.fragment.WxFragment;
import com.wds.fragment.ZhiHuFragment;
import com.wds.util.SPUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.layout)
    FrameLayout layout;
    @BindView(R.id.content)
    LinearLayout content;
    @BindView(R.id.navigation)
    NavigationView navigation;
    @BindView(R.id.drawer)
    DrawerLayout drawer;

    public static final int ZHIHU_TYPE = 0;
    public static final int WEIXIN_TYPE = 1;
    public static final int GANK_TYPE = 2;
    public static final int GOLD_TYPE = 3;
    public static final int V2EX_TYPE = 4;
    public static final int NIGHT_TYPE = 5;
    private FragmentManager supportFragmentManager;
    private ZhiHuFragment zhiHuFragment;
    private WxFragment wxFragment;
    private GankFragment gankFragment;
    private Fragment lastFragment;
    private int type;
    private MenuItem lastMenuItem;
    private GoldFragment goldFragment;
    private V2exFragment v2exFragment;
    private NightFragment nightFragment;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() { }

    @Override
    protected void initView() {
/*        boolean aBoolean = SPUtils.getInstance().getBoolean("夜间");
        useNightMode(aBoolean);*/
        toolbar.setTitle("知乎日报");
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        //fragment 管理器
        supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();

        for (int i = 0; i < supportFragmentManager.getFragments().size(); i++) {
            List<Fragment> fragments = supportFragmentManager.getFragments();
            Fragment fragment = fragments.get(i);

            if (fragment instanceof ZhiHuFragment) {
                zhiHuFragment = (ZhiHuFragment) fragment;
            }
            if (fragment instanceof WxFragment) {
                wxFragment = (WxFragment) fragment;
            }
            if (fragment instanceof GankFragment) {
                gankFragment = (GankFragment) fragment;
            }
            if (fragment instanceof GoldFragment) {
                goldFragment = (GoldFragment) fragment;
            }
            if (fragment instanceof V2exFragment) {
                v2exFragment = (V2exFragment) fragment;
            }
            if (fragment instanceof NightFragment) {
                nightFragment = (NightFragment) fragment;
            }


        }
// 把知乎加载到管理器 且显示
        if (zhiHuFragment == null)
            zhiHuFragment = new ZhiHuFragment();

        if (wxFragment == null)
            wxFragment = new WxFragment();

        if (gankFragment == null)
            gankFragment = new GankFragment();

        if (goldFragment == null)
            goldFragment = new GoldFragment();

        if (v2exFragment == null)
            v2exFragment = new V2exFragment();

        if (nightFragment == null)
            nightFragment = new NightFragment();


        if (supportFragmentManager.getFragments().size() == 0) {

            fragmentTransaction
                    .add(R.id.layout, zhiHuFragment)
                    .add(R.id.layout, wxFragment)
                    .add(R.id.layout, gankFragment)
                    .add(R.id.layout, goldFragment)
                    .add(R.id.layout, v2exFragment)
                    .add(R.id.layout, nightFragment)
                    .show(zhiHuFragment)
                    .hide(wxFragment)
                    .hide(gankFragment)
                    .hide(goldFragment)
                    .hide(v2exFragment)
                    .hide(nightFragment)
                    .commit();
            lastFragment = zhiHuFragment;
            lastMenuItem = navigation.getMenu().findItem(R.id.zhihu);
        } else {
            lastFragment = nightFragment;
            lastMenuItem = navigation.getMenu().findItem(R.id.setting);

            fragmentTransaction.show(nightFragment).commit();
        }

        lastMenuItem.setChecked(true);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.zhihu:
                            toolbar.setTitle("知乎日报");
                            type=ZHIHU_TYPE;
                        break;
                        case R.id.wx:
                            toolbar.setTitle("微信精选");
                            type=WEIXIN_TYPE;
                        break;
                        case R.id.gank:
                            toolbar.setTitle("干货集中营");
                            type=GANK_TYPE;
                        break;
                        case R.id.gold:
                            toolbar.setTitle("稀土掘金");
                            type=GOLD_TYPE;
                        break;
                        case R.id.vtex:
                            toolbar.setTitle("V2EX");
                            type=V2EX_TYPE;
                        break;
                    case R.id.setting:
                            toolbar.setTitle("设置");
                            type=NIGHT_TYPE;
                        break;

                    case R.id.map:
                        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                        startActivity(intent);
                        break;
                }
                switchFragment(menuItem);
                drawer.closeDrawer(Gravity.LEFT);
                return false;
            }
        });
    }

    public Fragment getCurrFragment() {
        switch (type){
                case ZHIHU_TYPE:
                if (zhiHuFragment==null){
                    zhiHuFragment=new ZhiHuFragment();
                }
                return zhiHuFragment;
                case WEIXIN_TYPE:
                if (wxFragment==null){
                    wxFragment=new WxFragment();
                }
                return wxFragment;
                case GANK_TYPE:
                if (gankFragment==null){
                    gankFragment=new GankFragment();
                }
                return gankFragment;
            case GOLD_TYPE:
                if (goldFragment==null){
                    goldFragment=new GoldFragment();
                }
                return goldFragment;
                case V2EX_TYPE:
                if (v2exFragment==null){
                    v2exFragment=new V2exFragment();
                }
                return v2exFragment;

            case NIGHT_TYPE:
                if (nightFragment==null){
                    nightFragment=new NightFragment();
                }
                return nightFragment;

        }
        return null;
    }
    public void switchFragment(MenuItem menuItem) {
        Fragment currFragment = getCurrFragment();
        if (currFragment==lastFragment){
            return;
        }
        supportFragmentManager.beginTransaction()
                .show(currFragment)
                .hide(lastFragment)
                .commit();
        lastFragment=currFragment;
        lastMenuItem.setChecked(false);
        lastMenuItem.setCheckable(true);
        lastMenuItem=menuItem;
        menuItem.setCheckable(true);
        menuItem.setChecked(true);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getEvent(NightEvent nightEvent){
        boolean night = nightEvent.isNight();
        useNightMode(night);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
