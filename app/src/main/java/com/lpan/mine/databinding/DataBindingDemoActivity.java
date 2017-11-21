package com.lpan.mine.databinding;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lpan.R;
import com.lpan.databinding.TestDataBindAtyBinding;

import commom.utils.ToastUtil;


public class DataBindingDemoActivity extends AppCompatActivity {
    TestDataBindAtyBinding testDataBindAtyBinding;
    Dog dog = new Dog("阿黄",5);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //dataBinding中 该方法被取代
   //     setContentView(R.layout.test_data_bind_aty);
        //ActivityDataBindingDemoBinding该类是根据activity_data_binding_demo的命名自动生成的
        testDataBindAtyBinding = DataBindingUtil.setContentView(this, R.layout.test_data_bind_aty);
        //方式一：利用xml中View的ID来设置 firstText secondText 是自动根据ID生成的成员变量
        testDataBindAtyBinding.firstText.setText("我是firstText新数据");
        testDataBindAtyBinding.secondText.setText("我是secondText新数据");
        //方式二 在xml中申明绑定的数据对象类型 在代码中传递对象
        testDataBindAtyBinding.setDog(dog);
        //在xml中绑定代码设置的监听回调方法
        testDataBindAtyBinding.setPresenter(new Presenter());


    }

   public class Presenter{
      public void onTextChanged(CharSequence s, int start, int before, int count){
          dog.setName(s.toString());
          testDataBindAtyBinding.setDog(dog);
      }
       public void onClick(View view){
           ToastUtil.makeShort("嘿嘿 被点击了");
       }
       public void onClickGetData(Dog dog){
           ToastUtil.makeShort(dog.getName());
       }

    }
}
