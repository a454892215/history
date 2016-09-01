package lp.history.widget;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;
import commom.utils.DensityUtils;

/**
 * Created by
 */
public class RefreshListView extends ListView implements AbsListView.OnScrollListener {
    TextView footer_tv;
    Context context;
    public RefreshListView(Context context) {
        this(context,null);
    }
    public RefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setOnScrollListener(this);
       this. context = context;
    }

    private void addFooter(Context context) {
        footer_tv = new TextView(context);
        footer_tv.setGravity(Gravity.CENTER);
        footer_tv.setHeight(DensityUtils.dp2px(context,50));
        footer_tv.setText("加载更多中...");
        this.addFooterView(footer_tv);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
    }
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    }

    RefreshingCallback loadMore;
    boolean isLoadingMore = false;
    public void  setLoadMore(RefreshingCallback loadMore){
        this.loadMore = loadMore;
        addFooter(context);
    }

    public void notifyLoadMoreFinished(){
        isLoadingMore = false;
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {
       // LogUtil.v("=onScroll======visibleItemCount:"+visibleItemCount+" firstVisibleItem:"+firstVisibleItem+" totalItemCount:"+totalItemCount);
        if(firstVisibleItem+visibleItemCount==totalItemCount&&!isLoadingMore&&firstVisibleItem!=0){
            if(loadMore!=null) {
                        isLoadingMore = true;
                        loadMore.startLoadMore();
            }
        }
    }

    public interface RefreshingCallback{
        void startLoadMore();
    }
}
