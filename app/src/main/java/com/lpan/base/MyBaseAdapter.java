package com.lpan.base;
import android.content.Context;
import android.widget.BaseAdapter;

import java.util.List;

import commom.utils.LogUtil;

public abstract class MyBaseAdapter extends BaseAdapter {
    protected List list;
    protected Context context;

    public MyBaseAdapter(Context context, List<?> list) {
        LogUtil.i("=========MyBaseAdapter============:size"+list.size());
        this.list = list;
        this.context = context;
    }

    public void addData(List list){
        this.list.addAll(list);
        LogUtil.i("=====addData()==size:"+ this.list.size());
        this.notifyDataSetChanged();
    }

    public void setData(List list){
        this.list.clear();
        this.list.addAll(list);
        LogUtil.i("=====setData()==size:"+ this.list.size());
        this.notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        if(list.size()>position){
            return list.get(position);
        }
       return null; //Bug
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}
