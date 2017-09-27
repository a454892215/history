package com.lpan.topnew;

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
import com.lpan.R;
import commom.http.HttpUtil;
import commom.http.core.HttpCallback;
import commom.utils.ToastUtil;

import com.lpan.widget.RefreshListView;

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
    //    String url = TopEntity.getUrl(newsType);
       // String sp_date = SharedPreferencesUtils.get(getContext(), url);
     //   String date = TimeUtils.getFormedDate().substring(0, 11);
     //    if(sp_date.equals(date)){
     //        DiskCacheHelper.readJsonFromDisk(TopEntity.getUrl(newsType).replace(".","_").replace("/","_"),getContext());
     //    }else{
             HttpUtil.get(TopEntity.getUrl(newsType), this, TopEntity.class, getActivity(), false);
   //     }

    }

    //   protected abstract void loadData();
    @Override
    public void onSuccess(TopEntity topEntity) {
        if(topEntity.result==null){
            ToastUtil.makeShort("没有数据");
            return;
        }

       // SharedPreferencesUtils.put(getContext(),topEntity.getUrl(newsType),topEntity.result.data.get(0).date);
       // DiskCacheHelper.writeJsonToDisk(topEntity.getUrl(newsType).replace(".","_").replace("/","_"),"",getContext());
        swipeRefreshLayout.setRefreshing(false);
        initUI(topEntity);

    }

    @Override
    public void onFailed() {
        ToastUtil.makeShort("加载数据失败");
        swipeRefreshLayout.setRefreshing(false);
    }

    TopAdapter topAdapter;

    private void initUI(TopEntity result) {
        if(topAdapter==null){
            topAdapter = new TopAdapter(getContext(), result.result.data);
            refreshListView.setAdapter(topAdapter);
        }else{
            topAdapter.setData( result.result.data);
        }
    }

    @Override
    public void onLoadMoreSuccess(TopEntity result) {
      //  topAdapter.addData(result.result.data);
    }

    @Override
    public void startLoadMore() {
        //   HttpUtil.get(TopEntity.getUrl(true), this, TopEntity.class, getActivity(), true);
    }
}
