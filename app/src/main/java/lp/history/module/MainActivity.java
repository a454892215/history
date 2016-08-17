package lp.history.module;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import lp.history.base.BaseActivity;
import lp.history.R;
import lp.history.entity.BaseEntity;
import lp.history.http.HttpUtil;
import lp.history.module.entity.HistoryEntity;
import lp.history.present.Presenter;
import lp.history.record.CallRecorder;
import lp.history.test.TestManager;

public class MainActivity extends BaseActivity implements Presenter {

    ListView history_list;
    View history_item_title;

    Presenter historyListPresent = new HistoryListPresenter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        history_list = (ListView) findViewById(R.id.history_list);
        history_item_title = findViewById(R.id.history_item_title);
        init();
        CallRecorder.record(this);
    }


    private void init() {
        history_item_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             test();
            }
        });
        HistoryEntity historyEntity = new HistoryEntity();
        HttpUtil.CallBack callBack = new HttpUtil.CallBack<HistoryEntity>() {
            @Override
            public void onSuccess(HistoryEntity result) {
                initUI(history_list, result);
            }
        };
        HttpUtil.get(historyEntity.URL,callBack, HistoryEntity.class , context);
    }

    /**
     * 测试使用
     */
    private void test() {
       // TestManager.testdeviceOwner(this);
      //  TestManager.testAPN(this);
    }
    @Override
    public void initUI(View view, BaseEntity baseEntity) {
        historyListPresent.initUI(view, baseEntity);
    }

    @Override
    public void updateUI(View view, BaseEntity baseEntity) {
        historyListPresent.updateUI(view, baseEntity);
    }
}
