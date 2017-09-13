package lp.history.message;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;


import commom.utils.LogUtil;
import lp.history.R;
import lp.history.base.BaseActivity;

/**
 * Created by LiuPan on 2017/9/13.
 */

public class MessagePushActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_message_push);
    }

}
