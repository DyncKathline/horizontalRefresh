package com.kathline.horizontalrefreshlayout;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kathline.horizontalrefresh.HorizontalRefreshLayout;
import com.kathline.horizontalrefresh.RefreshCallBack;
import com.kathline.horizontalrefresh.refreshhead.NiceRefreshHeader;

import java.util.ArrayList;
import java.util.List;

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

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(layout);
        mLayoutAdapter = new LayoutAdapter(this);
        mRecyclerView.setAdapter(mLayoutAdapter);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLongClickable(true);

//        List<Integer> list = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            list.add(i);
//        }
//        mLayoutAdapter.setItems(list);
//        refreshLayout.startAutoRefresh(HorizontalRefreshLayout.LEFT);
    }

    @Override
    public void onLeftRefreshing() {
        refreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                List<Integer> list = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    list.add(i);
                }
                mLayoutAdapter.setItems(list);
                refreshLayout.onRefreshComplete();
            }
        }, 2000);
    }

    @Override
    public void onRightRefreshing() {
        refreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                List<Integer> list = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    list.add(i);
                }
                mLayoutAdapter.addItems(list);
                refreshLayout.onRefreshComplete();
                if(mLayoutAdapter.getItemCount() > 20) {
                    refreshLayout.setRightEnable(false);
                }
            }
        }, 2000);
    }

}
