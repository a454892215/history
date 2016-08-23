package lp.history.widget;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;
import commom.utils.DensityUtils;

/**
 * Created by 刘攀 on 2016/8/19.
 */
public class RefreshListView extends ListView implements AbsListView.OnScrollListener {
    public RefreshListView(Context context) {
        this(context,null);

    }

    public RefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setOnScrollListener(this);
        TextView textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setHeight(DensityUtils.dp2px(context,50));
        textView.setText("加载更多中...");
        this.addFooterView(textView);
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
    }

    public void notifyLoadMoreFinished(){
        isLoadingMore = false;
    }


    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {
       // LogUtil.v("=onScroll======visibleItemCount:"+visibleItemCount+" firstVisibleItem:"+firstVisibleItem+" totalItemCount:"+totalItemCount);
        if(firstVisibleItem+visibleItemCount==totalItemCount&&!isLoadingMore&&firstVisibleItem!=0){
            if(loadMore!=null) {
            //    postDelayed(new Runnable() {
                //    @Override
                 //   public void run() {
                        isLoadingMore = true;
                        loadMore.startLoadMore();
               //     }
             //   },1000); //too fast

            }
        }
    }

    public interface RefreshingCallback{
        void startLoadMore();
    }
}
