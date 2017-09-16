package com.lpan.histoday;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.lpan.R;

import commom.http.HttpUtil;
import commom.http.core.HttpCallback;
import com.lpan.histoday.entity.HistoryEntity;
import com.lpan.present.Presenter;
import com.lpan.widget.RefreshListView;

public class HistoryListFragment extends Fragment implements HttpCallback<HistoryEntity>, RefreshListView.RefreshingCallback {
    private RefreshListView history_list;

    Presenter historyListPresent = new HistoryListPresenter();
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.today_fragmentlist, container, false);
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(history_list==null){
            history_list = (RefreshListView) view.findViewById(R.id.top_list);
            init();
        }
    }


    private void init() {
        history_list.setLoadMore(this);
        HttpUtil.get(HistoryEntity.getUrl(false), this, HistoryEntity.class, getActivity(), false);
    }


    public void initUI(View view, Object baseEntity) {
        historyListPresent.initUI(view, baseEntity);
    }


    public void updateUI(View view, Object baseEntity) {
        historyListPresent.updateUI(view, baseEntity);
    }

    @Override
    public void onSuccess(HistoryEntity result) {
        initUI(history_list, result);
    }

    @Override
    public void onLoadMoreSuccess(HistoryEntity result) {
        updateUI(history_list, result);
        history_list.notifyLoadMoreFinished();
    }

    @Override
    public void startLoadMore() {
        HttpUtil.get(HistoryEntity.getUrl(true), this, HistoryEntity.class, getActivity(), true);
    }
}
