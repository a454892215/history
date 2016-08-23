package lp.history.present;

import android.view.View;

import lp.history.base.BaseEntity;
import lp.history.http.core.HttpCallback;

/**
 * Created by Administrator on 2016/8/7.
 */
public interface Presenter{

   void initUI(View view, BaseEntity baseEntity);

    void updateUI(View view, BaseEntity baseEntity);
}
