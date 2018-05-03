package com.example.tranvanmanh.twamp;

import android.content.ClipData;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by tranvanmanh on 4/18/2018.
 */

public class ItemDetailAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<ItemDetail> itemDetailList;

    public ItemDetailAdapter(Context context, int layout, List<ItemDetail> itemDetailList) {
        this.context = context;
        this.layout = layout;
        this.itemDetailList = itemDetailList;
    }

    @Override
    public int getCount() {
        return itemDetailList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{

        TextView tvnameMetric, tvresult;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);

            holder.tvnameMetric = (TextView) view.findViewById(R.id.tvnameMetric);
            holder.tvresult = (TextView) view.findViewById(R.id.tvresult);

            view.setTag(holder);

        }
        else {
            holder = (ViewHolder) view.getTag();
        }

        holder.tvresult.setText(itemDetailList.get(i).getResult());
        holder.tvnameMetric.setText(itemDetailList.get(i).getNameMetric());
        return view;
    }
}
