package commom.http.core;

import android.content.Context;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import commom.utils.LogUtil;

/**
 * Created by LiuPan
 */
public class HttpGetRequest {
    public static  <T> void  get(final String url, final HttpCallback callback, final Context context,
                                 final Class<T> entityType, final boolean isLoadMore) {
        final RequestQueue mQueue = Volley.newRequestQueue(context);
        final StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        T entity = DataHandler.encapsulateData(response, entityType);
                        if (entity != null) {
                            if(isLoadMore){
                                callback.onLoadMoreSuccess(entity);
                            }else{
                                callback.onSuccess(entity);
                            }
                        } else {
                            LogUtil.i("encapsulate data fail :" + response);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onFailed();
                LogUtil.e("onErrorResponse:" + error.getMessage());
            }
        });
        mQueue.add(stringRequest);
    }
}
