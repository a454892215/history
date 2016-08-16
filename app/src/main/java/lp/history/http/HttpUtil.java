package lp.history.http;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import commom.utils.LogUtil;
import commom.utils.ToastUtil;
import lp.history.entity.BaseEntity;

public class HttpUtil {
    public static void get(final String url, final HttpUtil.CallBack callback, Context context, final Class entityType) {
        LogUtil.i("LLp request url is " + url);
        RequestQueue mQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  Log.d("TAG", response);
                        LogUtil.i("LLp:===========onSuccess===============:" + response + "　entityType：" + entityType);
                        BaseEntity baseEntity = encapsulateData(response, entityType);
                        if (baseEntity != null) {
                            callback.onSuccess(baseEntity);
                        } else {
                            LogUtil.i("LLp:===========请求数据成功 封装数据失败===============:" + response);
                            ToastUtil.makeLong("封装数据失败");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage(), error);
            }
        });
        mQueue.add(stringRequest);
    }

    public static void get(String url, HttpUtil.CallBack call, Class entityType, Context context, String... params) {
        int length = params.length;
        if (length > 0) {
            for (int i = 0; i < length; i++) {
                url += params[i];
            }
        }
        HttpUtil.get(url, call, context, entityType);
    }

    public static BaseEntity encapsulateData(String result, Class entityType) {
        return (BaseEntity) new Gson().fromJson(result, entityType);
    }

    public interface CallBack<T> {
        void onSuccess(T result);
    }
}

