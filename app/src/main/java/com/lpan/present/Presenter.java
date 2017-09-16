package com.lpan.present;

import android.view.View;


public interface Presenter{

   void initUI(View view, Object baseEntity);

    void updateUI(View view, Object baseEntity);
}
