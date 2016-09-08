package lp.history.top;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import commom.utils.LogUtil;
import lp.history.R;
import lp.history.http.HttpUtil;
import lp.history.http.core.HttpCallback;
import lp.history.widget.RefreshListView;

/**
 * Created by 刘攀
 */
public class TopFragment extends Fragment implements HttpCallback<TopEntity>, RefreshListView.RefreshingCallback {

    protected static TopFragment getInstance(NewsType newsType) {
        TopFragment baseFragment = new TopFragment();
        baseFragment.setNewsType(newsType);
        return baseFragment;
    }

    private String newsType;

    private void setNewsType(NewsType newsType) {
        this.newsType = newsType.getType();
    }

    private View view;
    RefreshListView refreshListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LogUtil.i("==========onCreateView=============newsType:" + newsType);
        if (view == null) {
            view = inflater.inflate(R.layout.top_fragmentlist, container, false);
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LogUtil.i("==========onViewCreated=============:");
        if (refreshListView == null) {
            initView(view);
            loadData();
        }


    }
    private SwipeRefreshLayout swipeRefreshLayout;
    private void initView(View view) {
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.top_swipe_ref);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });
        refreshListView = (RefreshListView) view.findViewById(R.id.top_list);
        (refreshListView).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TopEntity.ResultBean.DataBean item = (TopEntity.ResultBean.DataBean) topAdapter.getItem(position);
                if (item != null) {
                    Intent intent = new Intent(view.getContext(), WebActivity.class);
                    intent.putExtra("url", item.url);
                    view.getContext().startActivity(intent);
                }
            }
        });
    }

    private void loadData() {
        HttpUtil.get(TopEntity.getUrl(newsType), this, TopEntity.class, getActivity(), false);
    }

    //   protected abstract void loadData();
    @Override
    public void onSuccess(TopEntity result) {
        initUI(result);
    }

    TopAdapter topAdapter;

    private void initUI(TopEntity result) {
        if(topAdapter==null){
            topAdapter = new TopAdapter(getContext(), result.result.data);
            refreshListView.setAdapter(topAdapter);
        }else{
            topAdapter.setData( result.result.data);
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onLoadMoreSuccess(TopEntity result) {
        topAdapter.addData(result.result.data);
    }

    @Override
    public void startLoadMore() {
        //   HttpUtil.get(TopEntity.getUrl(true), this, TopEntity.class, getActivity(), true);
    }
}
