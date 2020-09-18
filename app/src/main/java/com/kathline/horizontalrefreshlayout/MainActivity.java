package com.kathline.horizontalrefreshlayout;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kathline.horizontalrefresh.HorizontalRefreshLayout;
import com.kathline.horizontalrefresh.RefreshCallBack;
import com.kathline.horizontalrefresh.refreshhead.NiceRefreshHeader;

public class MainActivity extends AppCompatActivity implements RefreshCallBack {
    private HorizontalRefreshLayout refreshLayout;
    protected RecyclerView mRecyclerView;
    private LayoutAdapter mLayoutAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        refreshLayout = (HorizontalRefreshLayout) findViewById(R.id.refresh);
        refreshLayout.setRefreshCallback(this);
        refreshLayout.setRefreshHeader(new NiceRefreshHeader(this), HorizontalRefreshLayout.LEFT);
        refreshLayout.setRefreshHeader(new NiceRefreshHeader(this), HorizontalRefreshLayout.RIGHT);

        mRecyclerView = (RecyclerView) findViewById(R.id.viewpager);
        LinearLayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(layout);
        mLayoutAdapter = new LayoutAdapter(this, mRecyclerView);
        mRecyclerView.setAdapter(mLayoutAdapter);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLongClickable(true);

        //refreshLayout.startAutoRefresh(HorizontalRefreshLayout.LEFT);
    }

    @Override
    public void onLeftRefreshing() {
        refreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshLayout.onRefreshComplete();
            }
        }, 2000);
    }

    @Override
    public void onRightRefreshing() {
        refreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mLayoutAdapter.getMore();
                refreshLayout.onRefreshComplete();
            }
        }, 2000);
    }

}
