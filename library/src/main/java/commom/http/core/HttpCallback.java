package commom.http.core;

public interface HttpCallback<T> {
    void onSuccess(T result);
    void onFailed();
    void onLoadMoreSuccess(T result);
}
