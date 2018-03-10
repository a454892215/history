package com.lpan.mine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lpan.R;
import com.lpan.mine.jnitest.HelloJni;

import commom.utils.ToastUtil;

public class JniTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jni_test);

        String test = HelloJni.test();
        ToastUtil.makeLong(test);
    }

}
