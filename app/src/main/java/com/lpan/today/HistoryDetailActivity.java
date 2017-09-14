package com.lpan.today;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lpan.base.BaseActivity;
import com.lpan.R;
import commom.http.core.HttpCallback;
import commom.http.HttpUtil;
import commom.http.core.HttpCallbackAdapter;
import com.lpan.today.entity.HistoryDetailEntity;


public class HistoryDetailActivity extends BaseActivity {
    TextView his_detail_content;
    ImageView his_detail_pic;
    TextView his_detail_title;


   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_detail_acy);

       his_detail_content = (TextView) findViewById(R.id.his_detail_content);
       his_detail_pic = (ImageView) findViewById(R.id.his_detail_pic);
       his_detail_title = (TextView) findViewById(R.id.his_detail_title);

       String id = getIntent().getStringExtra("id");
       init(id);


       his_detail_title.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
           }
       });
   }

    private void init(String id) {
        HistoryDetailEntity historyDetailEntity = new HistoryDetailEntity();
        HttpCallback callback = new HttpCallbackAdapter<HistoryDetailEntity>(){
            @Override
            public void onSuccess(HistoryDetailEntity result) {
                his_detail_title.setText(result.result.get(0).title);
                his_detail_content.setText(result.result.get(0).content);
                if(!TextUtils.isEmpty(result.result.get(0).pic)){
                    his_detail_pic.setVisibility(View.VISIBLE);
                    Glide.with(context) .load(result.result.get(0).pic).into(his_detail_pic);
                }
            }
        };
        HttpUtil.get(historyDetailEntity.getUrl(id),callback,HistoryDetailEntity.class ,context,false);
    }

}
