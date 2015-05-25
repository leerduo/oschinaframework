package com.dystu.oschinademo01;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends Fragment {

    private MySwipeRefreshLayout swipeRefreshLayout;

    private ListView listView;

    private NewsAdapter adapter;

    private ArrayList<News> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pull_refresh_listview, container, false);
        swipeRefreshLayout = (MySwipeRefreshLayout) view.findViewById(R.id.swiperefreshlayout);
        listView = (ListView) view.findViewById(R.id.listview);
        swipeRefreshLayout.setFooterView(getActivity(),listView,R.layout.list_cell_footer);
        swipeRefreshLayout.setColorSchemeColors();
        //加载颜色是循环播放的
        swipeRefreshLayout.setColorSchemeResources(
                R.color.swiperefresh_color1, R.color.swiperefresh_color2,
                R.color.swiperefresh_color3, R.color.swiperefresh_color4);
        list = new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            list.add(new News(R.drawable.widget_today_icon,
                    "MATLAB元编程介绍",
                    "这篇文章对Matlab中的元编程进行了简单的介绍。Matlab是一个古老而又高度专业化的语言。由于这一原因，缺乏",
                    "oschina", "32分钟前", "10"));
        }
        adapter = new NewsAdapter(getActivity(), list);
        listView.setAdapter(adapter);
        swipeRefreshLayout.setOnRefreshListener(new MySwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                            List<News> addList = new ArrayList<>();
                            addList.add(new News(R.drawable.widget_today_icon,
                                    "新添加",
                                    "新添加的内容",
                                    "imooc", "一分钟前", "10"));
                            list.addAll(0, addList);
                            adapter.notifyDataSetChanged();
                            swipeRefreshLayout.setRefreshing(false);
                        }

                }, 3000);
            }
        });
        swipeRefreshLayout.setOnLoadListener(new MySwipeRefreshLayout.OnLoadListener() {
            @Override
            public void onLoad() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list.add(new News(R.drawable.widget_today_icon,
                                "新添加",
                                "新添加的内容",
                                "csdn", "一分钟前", "10"));
                        adapter.notifyDataSetChanged();
                        //加载完以后调用该方法
                        swipeRefreshLayout.setLoading(false);
                    }

                }, 3000);
            }
        });
        return view;
    }


}
