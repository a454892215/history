package com.lpan.messagepush;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import com.lpan.base.BaseActivity;

import com.lpan.R;
/**
 * Created by LiuPan on 2017/9/13.
 */

public class MessagePushActivity extends BaseActivity {
    public static List<String> logList = new CopyOnWriteArrayList<String>();

    private TextView mLogView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        XiaomiPushInitAssistant.setMessagePushActivity(this);
        mLogView = (TextView) findViewById(R.id.log);
        // 设置别名
        findViewById(R.id.set_alias).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final EditText editText = new EditText(MessagePushActivity.this);
                new AlertDialog.Builder(MessagePushActivity.this)
                        .setTitle(R.string.set_alias)
                        .setView(editText)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String alias = editText.getText().toString();
                                MiPushClient.setAlias(MessagePushActivity.this, alias, null);
                            }

                        })
                        .setNegativeButton(R.string.cancel, null)
                        .show();
            }
        });
        // 撤销别名
        findViewById(R.id.unset_alias).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final EditText editText = new EditText(MessagePushActivity.this);
                new AlertDialog.Builder(MessagePushActivity.this)
                        .setTitle(R.string.unset_alias)
                        .setView(editText)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String alias = editText.getText().toString();
                                MiPushClient.unsetAlias(MessagePushActivity.this, alias, null);
                            }

                        })
                        .setNegativeButton(R.string.cancel, null)
                        .show();

            }
        });
        // 设置帐号
        findViewById(R.id.set_account).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final EditText editText = new EditText(MessagePushActivity.this);
                new AlertDialog.Builder(MessagePushActivity.this)
                        .setTitle(R.string.set_account)
                        .setView(editText)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String account = editText.getText().toString();
                                MiPushClient.setUserAccount(MessagePushActivity.this, account, null);
                            }

                        })
                        .setNegativeButton(R.string.cancel, null)
                        .show();

            }
        });
        // 撤销帐号
        findViewById(R.id.unset_account).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final EditText editText = new EditText(MessagePushActivity.this);
                new AlertDialog.Builder(MessagePushActivity.this)
                        .setTitle(R.string.unset_account)
                        .setView(editText)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String account = editText.getText().toString();
                                MiPushClient.unsetUserAccount(MessagePushActivity.this, account, null);
                            }

                        })
                        .setNegativeButton(R.string.cancel, null)
                        .show();
            }
        });
        // 设置标签
        findViewById(R.id.subscribe_topic).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final EditText editText = new EditText(MessagePushActivity.this);
                new AlertDialog.Builder(MessagePushActivity.this)
                        .setTitle(R.string.subscribe_topic)
                        .setView(editText)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String topic = editText.getText().toString();
                                MiPushClient.subscribe(MessagePushActivity.this, topic, null);
                            }

                        })
                        .setNegativeButton(R.string.cancel, null)
                        .show();
            }
        });
        // 撤销标签
        findViewById(R.id.unsubscribe_topic).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final EditText editText = new EditText(MessagePushActivity.this);
                new AlertDialog.Builder(MessagePushActivity.this)
                        .setTitle(R.string.unsubscribe_topic)
                        .setView(editText)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String topic = editText.getText().toString();
                                MiPushClient.unsubscribe(MessagePushActivity.this, topic, null);
                            }

                        })
                        .setNegativeButton(R.string.cancel, null)
                        .show();
            }
        });
        // 设置接收消息时间
        findViewById(R.id.set_accept_time).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new TimeIntervalDialog(MessagePushActivity.this, new TimeIntervalDialog.TimeIntervalInterface() {

                    @Override
                    public void apply(int startHour, int startMin, int endHour,
                                      int endMin) {
                        MiPushClient.setAcceptTime(MessagePushActivity.this, startHour, startMin, endHour, endMin, null);
                    }

                    @Override
                    public void cancel() {
                        //ignore
                    }

                })
                        .show();
            }
        });
        // 暂停推送
        findViewById(R.id.pause_push).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                MiPushClient.pausePush(MessagePushActivity.this, null);
            }
        });

        findViewById(R.id.resume_push).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                MiPushClient.resumePush(MessagePushActivity.this, null);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshLogInfo();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        XiaomiPushInitAssistant.setMessagePushActivity(null);
    }

    public void refreshLogInfo() {
        String AllLog = "";
        for (String log : logList) {
            AllLog = AllLog + log + "\n\n";
        }
        mLogView.setText(AllLog);
    }

}
