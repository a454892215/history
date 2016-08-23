package lp.history.module;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import lp.history.R;
import lp.history.base.BaseEntity;
import lp.history.http.HttpUtil;
import lp.history.http.core.HttpCallback;
import lp.history.module.entity.HistoryEntity;
import lp.history.present.Presenter;
import lp.history.widget.RefreshListView;

public class HistoryListFragment extends Fragment implements Presenter, HttpCallback<HistoryEntity>, RefreshListView.RefreshingCallback {
    private RefreshListView history_list;

    Presenter historyListPresent = new HistoryListPresenter();
    View view;

    protected static Fragment getInstance() {
        return new HistoryListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.his_today_list, container, false);
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        history_list = (RefreshListView) view.findViewById(R.id.history_list);

        init();
    }


    private void init() {

        history_list.setLoadMore(this);
        HttpUtil.get(HistoryEntity.getUrl(false), this, HistoryEntity.class, getActivity(), false);
    }

    @Override
    public void initUI(View view, BaseEntity baseEntity) {
        historyListPresent.initUI(view, baseEntity);
    }

    @Override
    public void updateUI(View view, BaseEntity baseEntity) {
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
