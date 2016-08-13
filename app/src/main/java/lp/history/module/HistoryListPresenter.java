package lp.history.module;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import lp.history.entity.BaseEntity;
import lp.history.module.entity.HistoryEntity;
import lp.history.present.Presenter;

public class HistoryListPresenter implements Presenter{

    @Override
    public void initUI(View view, BaseEntity baseEntity) {
        final HistoryListAdapter historyListAdapter = new HistoryListAdapter(view.getContext(), ((HistoryEntity) baseEntity).result);
        ((ListView)view).setAdapter(historyListAdapter);
        ((ListView)view).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HistoryEntity.ResultBean item = (HistoryEntity.ResultBean) historyListAdapter.getItem(position);
                Intent intent = new Intent(view.getContext(),HistoryDetailActivity.class);
                intent.putExtra("id",item._id);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public void updateUI(View view, BaseEntity baseEntity) {

    }
}
