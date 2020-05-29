package com.wds.geeknews;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.wds.bean.DailyDetailBean;
import com.wds.contract.DailyDetailContract;
import com.wds.presenter.DailyDetailPresenter;
import com.wds.util.HtmlUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DailyDetailActivity extends BaseMVPActivity<DailyDetailPresenter> implements DailyDetailContract.View<DailyDetailBean> {
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    @Override
    public void success(DailyDetailBean dailyDetailBean) {
        Glide.with(this).load(dailyDetailBean.getImage()).into(img);
        toolbar.setTitle(dailyDetailBean.getTitle());
        setSupportActionBar(toolbar);
        String htmlData = HtmlUtil.createHtmlData(dailyDetailBean.getBody(), dailyDetailBean.getCss(), (List<String>) dailyDetailBean.getJs());
        webView.loadData(htmlData,HtmlUtil.MIME_TYPE,HtmlUtil.ENCODING);
    }

    @Override
    public void error(String mag) {
        Toast.makeText(this, mag, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected DailyDetailPresenter initPresenter() {
        return new DailyDetailPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_daily_detail;
    }

    @Override
    protected void initData() {
        String id = getIntent().getStringExtra("id");
        presenter.getPresenter(id);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
