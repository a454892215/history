package lp.history;
import android.os.Bundle;
import android.widget.RadioGroup;

import java.util.Observer;

import lp.history.base.BaseActivity;
import lp.history.mine.MineFragment;
import lp.history.test.TestManager;
import lp.history.today.HistoryListFragment;
import lp.history.top.ViewPagerFragment;

public class MainActivity extends BaseActivity {
    private MineFragment mineFragment;
    private ViewPagerFragment viewPagerFragment;
    private HistoryListFragment historyListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_aty);
        initFragment();
        initFooter();

    }

    private void initFragment() {
        viewPagerFragment = new ViewPagerFragment();
        addFragment(viewPagerFragment, R.id.main_fl_content_top);
        showFragment(viewPagerFragment);
        mineFragment = new MineFragment();
        addFragment(mineFragment, R.id.main_fl_content_mine);
        hideFragment(mineFragment);
        historyListFragment = new HistoryListFragment();
        addFragment(historyListFragment, R.id.main_fl_content_today);
        hideFragment(historyListFragment);
    }

    private void initFooter() {
        RadioGroup main_rp = (RadioGroup) findViewById(R.id.main_rp);
        main_rp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.main_rb_top:
                        hideFragment(mineFragment);
                        hideFragment(historyListFragment);
                        showFragment(viewPagerFragment);
                        break;
                    case R.id.main_rb_today:
                        hideFragment(viewPagerFragment);
                        hideFragment(mineFragment);
                        showFragment(historyListFragment);
                        break;
                    case R.id.main_rb_mine:
                        hideFragment(viewPagerFragment);
                        hideFragment(historyListFragment);
                        showFragment(mineFragment);
                        break;
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        TestManager.test(this);
    }
}