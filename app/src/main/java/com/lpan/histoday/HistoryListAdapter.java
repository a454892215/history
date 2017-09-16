package com.lpan.histoday;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import java.util.List;

import commom.utils.ViewHolder;
import com.lpan.base.MyBaseAdapter;
import com.lpan.R;
import com.lpan.histoday.entity.HistoryEntity;



public class HistoryListAdapter extends MyBaseAdapter {

    public HistoryListAdapter(Context context,List<HistoryEntity.ResultBean> list) {
        super(context,list);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = View.inflate(context,R.layout.history_item,null);
        }
        final ImageView history_item_pic= ViewHolder.get(convertView, R.id.history_item_pic);
        TextView history_item_title= ViewHolder.get(convertView, R.id.history_item_title);
        TextView history_item_desc= ViewHolder.get(convertView, R.id.history_item_desc);
        final HistoryEntity.ResultBean resultBean = (HistoryEntity.ResultBean) list.get(position);
      //  LogUtil.d("LLpp:路径："+resultBean.pic+" title:"+resultBean.title);
        if(TextUtils.isEmpty(resultBean.pic)){
            history_item_pic.setVisibility(View.GONE);
        }else{
            history_item_pic.setVisibility(View.VISIBLE);
            Glide.with(context) .load(resultBean.pic).into(history_item_pic);
        /*    Glide.with(context).load(resultBean.pic).asBitmap().into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                }
                @Override
                public void onLoadFailed(Exception e, Drawable errorDrawable) {
                    super.onLoadFailed(e, errorDrawable);
                    LogUtil.e("LLpp:加载失败："+resultBean.pic+" title:"+resultBean.title);
                    history_item_pic.setVisibility(View.GONE);
                }
            }); //方法中设置asBitmap可以设置回调类型*/
        }
        history_item_title.setText(resultBean.title);
        history_item_desc.setText(resultBean.des);
        return convertView;
    }
}
