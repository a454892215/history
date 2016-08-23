package lp.history.module;
import android.os.Bundle;
import android.view.View;

import lp.history.R;
import lp.history.base.BaseActivity;
import lp.history.test.TestManager;

public class MainActivity extends BaseActivity {

    private View history_item_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_aty);
        history_item_title = findViewById(R.id.history_item_title);
        history_item_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test();
            }
        });
        setFragment(HistoryListFragment.getInstance(),R.id.main_content_fl);
    }

    /**
     * 测试使用
     */
    private void test() {
        TestManager.test(this);
    }
}
