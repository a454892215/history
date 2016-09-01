package lp.history.top;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.List;
import commom.utils.LogUtil;
import commom.utils.ViewHolder;
import lp.history.R;
import lp.history.base.MyBaseAdapter;
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

        TopEntity.ResultBean.DataBean resultBean  = (TopEntity.ResultBean.DataBean) list.get(position);
        LogUtil.d("LLpp:路径："+resultBean.thumbnail_pic_s+" title:"+resultBean.title);
        Glide.with(context).load(resultBean.thumbnail_pic_s).into(top_img_1);
        top_title.setText(resultBean.title);
        return convertView;
    }
}
