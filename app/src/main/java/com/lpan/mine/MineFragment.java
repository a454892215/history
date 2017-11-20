package com.lpan.mine;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.lpan.R;
import com.lpan.messagepush.MessagePushActivity;
import com.lpan.qrcodescanner.qrcode.QrCodeActivity;

public class MineFragment extends Fragment {
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(view==null){
            view = inflater.inflate(R.layout.mine_fragment, container, false);
            View mine_message_push = view.findViewById(R.id.mine_message_push);
            View  mine_qr_scanner =  view.findViewById(R.id.mine_qr_scanner);
            View  browser =  view.findViewById(R.id.mine_browser);
            mine_message_push.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getContext(),MessagePushActivity.class));
                }
            });
            mine_qr_scanner.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getContext(),QrCodeActivity.class));
                }
            });
            browser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getContext(),BrowserActivity.class));
                }
            });



/*            Bitmap src = BitmapFactory.decodeResource(getResources(), R.mipmap.react); //获取Bitmap图片
            RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), src); //创建RoundedBitmapDrawable对象
            float size = getResources().getDimension(R.dimen.mine_head_size) / 2;
            LogUtil.i("========onCreateView===========size:"+size);
            roundedBitmapDrawable.setCornerRadius(size+50); //设置圆角半径（根据实际需求）
          //  roundedBitmapDrawable.setAntiAlias(true); //设置反走样
            mine_img_head.setImageDrawable(roundedBitmapDrawable); //显示圆角图片*/



      /*      Bitmap src = BitmapFactory.decodeResource(getResources(),  R.mipmap.icon_drawer_portrait);
            Bitmap dst;
//将长方形图片裁剪成正方形图片
            if (src.getWidth() >= src.getHeight()){
                dst = Bitmap.createBitmap(src, src.getWidth()/2 - src.getHeight()/2, 0, src.getHeight()-10, src.getHeight()-10);
            }else{
                dst = Bitmap.createBitmap(src, 0, src.getHeight()/2 - src.getWidth()/2, src.getWidth(), src.getWidth()
                );
            }
            RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), dst);
            roundedBitmapDrawable.setCornerRadius(dst.getWidth() / 2); //设置圆角半径为正方形边长的一半
            roundedBitmapDrawable.setAntiAlias(true);
            mine_img_head.setImageDrawable(roundedBitmapDrawable);*/
        }

        return view;
    }


}
