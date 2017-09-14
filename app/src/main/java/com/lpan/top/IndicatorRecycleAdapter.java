package com.lpan.top;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.lpan.R;

/**
 * Created by 刘攀
 */
public class IndicatorRecycleAdapter extends RecyclerView.Adapter {
    private Context context;
    private String[] titles = {"头条", "社会", "国内", "国际", "娱乐", "体育", "军事", "科技", "财经", "时尚"};
    RecyclerView recyclerView;
    public IndicatorRecycleAdapter(Context context, RecyclerView recyclerView) {
        this.context = context;
        this. recyclerView = recyclerView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(context, R.layout.vp_indicator_item, null);
        return new MyViewHolder(itemView);
    }
boolean isFirst = true;
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder) holder).vp_indicator_title.setText(titles[position]);
        ((MyViewHolder) holder).itemView.setTag(position);
        if(position==0&&isFirst){
            isFirst = false;
            clearSelect();
            onSelectedChanged(position, context);
        }
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }


    List<MyViewHolder> viewHolderList = new ArrayList<>();

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView vp_indicator_title;
        public View vp_indicator_selectedView;
        View itemView;

        public MyViewHolder(View view) {
            super(view);
            itemView = view;
            vp_indicator_title = (TextView) view.findViewById(R.id.vp_indicator_title);
            vp_indicator_selectedView = view.findViewById(R.id.vp_indicator_selectedView);
            view.setOnClickListener(this);
            viewHolderList.add(this);
        }

        @Override
        public void onClick(View v) {
            int index = (int) v.getTag();
            viewPager.setCurrentItem(index);
            clearSelect();
            onSelectedChanged(index, context);
        }
    }



    public void onSelectedChanged(int index, Context context) {
        if (index >=viewHolderList.size()) {
            index = index % viewHolderList.size();
        }
        viewHolderList.get(index).vp_indicator_title.setTextColor(context.getResources().getColor(R.color.checkedTextColor));
        viewHolderList.get(index).vp_indicator_selectedView.setVisibility(View.VISIBLE);
    }

    public void clearSelect(){
        for (int i = 0; i < viewHolderList.size(); i++) {
            viewHolderList.get(i).vp_indicator_title.setTextColor(context.getResources().getColor(R.color.textColor));
            viewHolderList.get(i).vp_indicator_selectedView.setVisibility(View.GONE);
        }
    }

    private ViewPager viewPager;
    public void setViewPager(ViewPager viewpager) {
        this.viewPager = viewpager;
    }
}
