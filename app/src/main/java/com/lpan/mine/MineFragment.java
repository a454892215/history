package com.lpan.mine;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.lpan.R;
import com.lpan.messagepush.MessagePushActivity;
import com.lpan.mine.databinding.DataBindingDemoActivity;
import com.lpan.qrcodescanner.qrcode.QrCodeActivity;

public class MineFragment extends Fragment implements View.OnClickListener {
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(view==null){
            view = inflater.inflate(R.layout.mine_fragment, container, false);
            View mine_message_push = view.findViewById(R.id.mine_message_push);
            View  mine_qr_scanner =  view.findViewById(R.id.mine_qr_scanner);
            View  browser =  view.findViewById(R.id.mine_browser);
            View  mine_data_binding =  view.findViewById(R.id.mine_data_binding);
            View  mine_jni_test =  view.findViewById(R.id.mine_jni_test);

            mine_message_push.setOnClickListener(this);
            mine_qr_scanner.setOnClickListener(this);
            browser.setOnClickListener(this);
            mine_data_binding.setOnClickListener(this);
            mine_jni_test.setOnClickListener(this);
        }
        return view;
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.mine_message_push:
                startActivity(new Intent(getContext(),MessagePushActivity.class));
                break;
            case R.id.mine_qr_scanner:
                startActivity(new Intent(getContext(),QrCodeActivity.class));
                break;
            case R.id.mine_browser:
                startActivity(new Intent(getContext(),BrowserActivity.class));
                break;
            case R.id.mine_data_binding:
                startActivity(new Intent(getContext(),DataBindingDemoActivity.class));
                break;
            case R.id.mine_jni_test:
               startActivity(new Intent(getContext(),JniTestActivity.class));
                break;
        }
    }
}
