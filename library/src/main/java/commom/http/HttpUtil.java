package commom.http;
import android.content.Context;
import commom.http.core.HttpCallback;
import commom.http.core.HttpGetRequest;

public class HttpUtil {
    public static void get(String url, HttpCallback call, Class entityType, Context context,boolean isLoadMore) {
        HttpGetRequest.get(url, call, context, entityType,isLoadMore);
    }
}

