package commom.http.core;
public class HttpCallbackAdapter<T> implements HttpCallback<T>{
    @Override
    public void onSuccess(T result) {
    }

    @Override
    public void onFailed() {

    }

    @Override
    public void onLoadMoreSuccess(T result) {
    }
}
