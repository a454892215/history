package com.lpan.present;

import android.view.View;

/**
 * Created by Administrator on 2016/8/7.
 */
public interface Presenter{

   void initUI(View view, Object baseEntity);

    void updateUI(View view, Object baseEntity);
}
