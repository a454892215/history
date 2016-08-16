package lp.history.module;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.List;
import commom.utils.LogUtil;
import commom.utils.ViewHolder;
import lp.history.base.MyBaseAdapter;
import lp.history.R;
import lp.history.module.entity.HistoryEntity;



public class HistoryListAdapter extends MyBaseAdapter {

    public HistoryListAdapter(Context context,List<HistoryEntity.ResultBean> list) {
        super(context,list);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = View.inflate(context,R.layout.history_item,null);
        }
        ImageView history_item_pic= ViewHolder.get(convertView, R.id.history_item_pic);
        TextView history_item_title= ViewHolder.get(convertView, R.id.history_item_title);
        TextView history_item_desc= ViewHolder.get(convertView, R.id.history_item_desc);
        HistoryEntity.ResultBean resultBean = (HistoryEntity.ResultBean) list.get(position);
        Glide.with(context) .load(resultBean.pic).into(history_item_pic);
        LogUtil.i("LLpp:路径："+resultBean.pic);
        if(TextUtils.isEmpty(resultBean.pic)){
            history_item_pic.setVisibility(View.GONE);
        }else{
            history_item_pic.setVisibility(View.VISIBLE);
        }
        history_item_title.setText(resultBean.title);
        history_item_desc.setText(resultBean.des);
        return convertView;
    }
}
