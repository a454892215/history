package lp.history.module;
import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import org.xutils.common.util.LogUtil;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;
import java.util.List;
import lp.history.BaseActivity;
import lp.history.permission.PermissionActivity;
import lp.history.test.appinfo.ApplicationInfo;
import lp.history.test.appinfo.Info;
import lp.history.R;
import lp.history.entity.BaseEntity;
import lp.history.http.HttpUtil;
import lp.history.module.entity.HistoryEntity;
import lp.history.present.Presenter;

public class MainActivity extends BaseActivity implements Presenter {

    @ViewInject(R.id.history_list)
    ListView history_list;

    @ViewInject(R.id.history_item_title)
    View history_item_title;

    Presenter historyListPresent = new HistoryListPresenter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        x.view().inject(this);
        init();
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
     //   testAPN();
       testPermisisons();
       // BDTest();
     //   ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE} , 11);
      //  boolean b = ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE);

      //  ToastUtil.makeShort(""+b);
    }

    private void testAPN(){
       //获取当前apn
        Uri uri = Uri.parse("content://telephony/carriers/preferapn");
        Cursor cr = getContentResolver().query(uri, null, null, null, null);
        while(cr!=null && cr.moveToNext()){

            // APN id

            String id = cr.getString(cr.getColumnIndex("_id"));

            // APN name

            String apn = cr.getString(cr.getColumnIndex("apn"));

            LogUtil.i("id:"+id+"apn:"+apn);
        }

    }
   private void BDTest(){

   }

    /**
     * 测试预装和用户安装的app
     */
    private void testPreinstalledAppsAndUserApp(){
        List<Info.ApkInfo> apkInfos = ApplicationInfo.collectInstalledAppInfo(context);
        int size = apkInfos.size();
        LogUtil.i("================size:"+size);
        for (Info.ApkInfo app: apkInfos) {
            String appName = app.appName;
            LogUtil.i("=============appName:"+appName);
        }
    }

    /**
     * 测试权限
     */
    private void testPermisisons() {
        String[] permissions = new String[]{
//            Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS,
               Manifest.permission.CALL_PHONE,
               Manifest.permission.WRITE_EXTERNAL_STORAGE,
               Manifest.permission.READ_EXTERNAL_STORAGE,
               Manifest.permission.RECEIVE_MMS,
               Manifest.permission.READ_CONTACTS,
               Manifest.permission.WRITE_CONTACTS,
               Manifest.permission.RECEIVE_SMS,
               Manifest.permission.READ_SMS,
               Manifest.permission.SEND_SMS,
               Manifest.permission.READ_CALL_LOG,
               Manifest.permission.RECORD_AUDIO,
               Manifest.permission.PROCESS_OUTGOING_CALLS,
               Manifest.permission.ACCESS_COARSE_LOCATION,
 //              Manifest.permission.ACCESS_FINE_LOCATION,
//            Manifest.permission.READ_CALENDAR,
//            Manifest.permission.WRITE_CALENDAR,
//            Manifest.permission.CAMERA,
//            Manifest.permission.GET_ACCOUNTS,

//            Manifest.permission.READ_PHONE_STATE,
//            Manifest.permission.WRITE_CALL_LOG,
//            Manifest.permission.ADD_VOICEMAIL,
//            Manifest.permission.USE_SIP,
//            Manifest.permission.BODY_SENSORS,
//            Manifest.permission.RECEIVE_WAP_PUSH,
       };

      //  PermissionMonitorService.start(this,permissions);
               Intent intent = new Intent(context, PermissionActivity.class);
                intent.putExtra("ask_for_permissions",permissions);
                startActivity(intent);
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
