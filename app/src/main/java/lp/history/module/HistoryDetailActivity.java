package lp.history.module;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.BackgroundColorSpan;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.HashMap;

import lp.history.BaseActivity;
import lp.history.R;
import lp.history.entity.BaseEntity;
import lp.history.http.HttpUtil;
import lp.history.module.entity.HistoryDetailEntity;
import lp.history.utils.IOUtility;
import lp.history.utils.ToastUtil;

public class HistoryDetailActivity extends BaseActivity {
    @ViewInject(R.id.his_detail_content)
    TextView his_detail_content;

    @ViewInject(R.id.his_detail_pic)
    ImageView his_detail_pic;

    @ViewInject(R.id.his_detail_title)
    TextView his_detail_title;


   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_detail_acy);
       x.view().inject(this);
       Intent intent = getIntent();
       String id = intent.getStringExtra("id");
       init(id);


       his_detail_title.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               IOUtility.writeToSD("\n2222222222==HistoryDetailActivity====2222222222222",(Activity)context);
           }
       });
   }

    private void init(String id) {
        HistoryDetailEntity historyDetailEntity = new HistoryDetailEntity();
        HttpUtil.CallBack  callback = new HttpUtil.CallBack<HistoryDetailEntity>(){

            @Override
            public void onSuccess(HistoryDetailEntity result) {
                his_detail_title.setText(result.result.title);
                his_detail_content.setText(result.result.content);
                if(!TextUtils.isEmpty(result.result.pic)){
                    his_detail_pic.setVisibility(View.VISIBLE);
                    Glide.with(context) .load(result.result.pic).into(his_detail_pic);
                }
            }
        };
        HttpUtil.get(historyDetailEntity.URL,callback,HistoryDetailEntity.class ,context,id);
    }

}
