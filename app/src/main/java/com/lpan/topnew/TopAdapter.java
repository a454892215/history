package com.lpan.topnew;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.List;

import commom.utils.ViewHolder;
import com.lpan.R;
import com.lpan.base.MyBaseAdapter;
public class TopAdapter extends MyBaseAdapter {
    public TopAdapter(Context context, List<TopEntity.ResultBean.DataBean> list) {
        super(context,list);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = View.inflate(context,R.layout.top_item,null);
        }
        TextView top_title= ViewHolder.get(convertView, R.id.top_title);
        ImageView top_img_1= ViewHolder.get(convertView, R.id.top_img_1);
        TextView top_author= ViewHolder.get(convertView, R.id.top_author);
      //  TextView top_time= ViewHolder.get(convertView, R.id.top_time);
        TopEntity.ResultBean.DataBean resultBean  = (TopEntity.ResultBean.DataBean) list.get(position);
      //  LogUtil.d("LLpp:路径："+resultBean.thumbnail_pic_s+" title:"+resultBean.title);
        Glide.with(context).load(resultBean.thumbnail_pic_s).into(top_img_1);
        top_title.setText(resultBean.title.toString().replaceAll("\\s*", ""));
      //  LogUtil.d("(resultBean.title:"+resultBean.title.toString().replaceAll("\\s*", ""));
        top_author.setText(resultBean.author_name);
      //  top_time.setText();
        return convertView;
    }
}
