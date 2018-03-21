package com.lpan.mine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.lpan.R;
import com.lpan.mine.jnitest.HelloJni;
import java.util.Arrays;
public class JniTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jni_test);
        TextView tv_jni_info = findViewById(R.id.tv_jni_info);
        //JNI方法测试
        String text = HelloJni.test();
        String  obj_text= new HelloJni().testObjMethod();
        int add = HelloJni.testIntParams(5, 8);
        String string = HelloJni.testStringParams("我a-bch,嘟嘟嘟");

        int[] arr = new int[]{10,5,40,88};
        arr = new HelloJni().testIntArrParams(arr);
        int result = new HelloJni().testCInvokeJava();

        String info = text
                +"\n"+obj_text
                +"\n"+add
                +"\n"+"经过C处理后的字符串是："+string
                +"\n"+ "经过C处理后的int数组是："+Arrays.toString(arr)
                +"\n"+"java代码调用C代码，C代码再调用java代码 结果是："+result;
        tv_jni_info.setText(info);

    }

}
