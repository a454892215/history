package lp.history.http;
import android.content.Context;
import lp.history.http.core.HttpCallback;
import lp.history.http.core.HttpGetRequest;

public class HttpUtil {
    public static void get(String url, HttpCallback call, Class entityType, Context context,boolean isLoadMore) {
        HttpGetRequest.get(url, call, context, entityType,isLoadMore);
    }
}

