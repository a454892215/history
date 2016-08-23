package lp.history.http.core;

import android.content.Context;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import commom.utils.LogUtil;
import lp.history.base.BaseEntity;

/**
 * Created by 刘攀
 */
public class HttpGetRequest {
    public static void get(final String url, final HttpCallback callback, Context context, final Class entityType, final boolean isLoadMore) {
        LogUtil.i("request url is " + url);
        RequestQueue mQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        BaseEntity baseEntity = DataHandler.encapsulateData(response, entityType);
                        if (baseEntity != null) {
                            if(isLoadMore){
                                callback.onLoadMoreSuccess(baseEntity);
                            }else{
                                callback.onSuccess(baseEntity);
                            }
                        } else {
                            LogUtil.i("encapsulate data fail :" + response);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                LogUtil.e("onErrorResponse:" + error.getMessage());
            }
        });
        mQueue.add(stringRequest);
    }
}
