package com.dystu.oschinademo01;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class NewsAdapter extends BaseAdapter {

    private Context context;

    private ArrayList<News> list;

    public NewsAdapter(Context context, ArrayList<News> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.list_cell_news,null);
            holder = new ViewHolder();
            holder.iv_tip = (ImageView) convertView.findViewById(R.id.iv_tip);
            holder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            holder.tv_description = (TextView) convertView.findViewById(R.id.tv_description);
            holder.tv_source = (TextView) convertView.findViewById(R.id.tv_source);
            holder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            holder.tv_comment_count = (TextView) convertView.findViewById(R.id.tv_comment_count);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.iv_tip.setBackgroundResource(R.drawable.widget_today_icon);
        holder.tv_title.setText(list.get(position).getTv_title());
        holder.tv_description.setText(list.get(position).getTv_description());
        holder.tv_source.setText(list.get(position).getTv_source());
        holder.tv_time.setText(list.get(position).getTv_time());
        holder.tv_comment_count.setText(list.get(position).getTv_comment_count());
        return convertView;
    }


    static class ViewHolder{
        ImageView iv_tip;
        TextView tv_title;
        TextView tv_description;
        TextView tv_source;
        TextView tv_time;
        TextView tv_comment_count;
    }
}
