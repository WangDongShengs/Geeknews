package com.wds.geeknews;

import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.wds.adapter.NodeAdapter;
import com.wds.util.XmlUtil;

import org.xmlpull.v1.XmlPullParser;

import javax.xml.parsers.SAXParser;
public class NoteActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private RecyclerView mRvContent;
    private TextView mTvNodeTitle;
    private LinearLayoutManager linearLayoutManager;
    private int mTitleHeight;
    private int mCurrentPosition;
    private ArrayMap<String, ArrayMap<String, String>> stringArrayMapArrayMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);


        XmlResourceParser xmlParser = this.getResources().getXml(R.xml.nodes);
        try {
            stringArrayMapArrayMap = XmlUtil.parseNodes(xmlParser);

        } catch (Exception e) {
            e.printStackTrace();
        }
        initView();
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.mipmap.video_webview_bar_back_d);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mRvContent = (RecyclerView) findViewById(R.id.rv_content);
        mTvNodeTitle = (TextView) findViewById(R.id.tv_node_title);
        linearLayoutManager = new LinearLayoutManager(this);
        mRvContent.setLayoutManager(linearLayoutManager);
        NodeAdapter nodeAdapter = new NodeAdapter(this, stringArrayMapArrayMap);
        mRvContent.setAdapter(nodeAdapter);
        mRvContent.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged( RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                mTitleHeight = mTvNodeTitle.getHeight();

            }

            @Override
            public void onScrolled( RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);


                View view = linearLayoutManager.findViewByPosition(mCurrentPosition + 1);
                if (view != null) {
                    if (view.getTop() <= mTitleHeight) {
                        mTvNodeTitle.setY(-(mTitleHeight - view.getTop()));
                    } else {
                        mTvNodeTitle.setY(0);
                    }
                }

                // 当切换到下一个的时候，悬浮的title复位。
                if (mCurrentPosition != linearLayoutManager.findFirstVisibleItemPosition()) {
                    mCurrentPosition = linearLayoutManager.findFirstVisibleItemPosition();
                    mTvNodeTitle.setY(0);
                    // 显示要显示的标题
                    if (stringArrayMapArrayMap != null) {
                        mTvNodeTitle.setText(stringArrayMapArrayMap.keyAt(mCurrentPosition));

                    }
                }
            }
        });
        mTvNodeTitle.setText(stringArrayMapArrayMap.keyAt(0));
    }
}
