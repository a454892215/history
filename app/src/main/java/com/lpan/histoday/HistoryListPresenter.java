package com.lpan.histoday;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.lpan.histoday.entity.HistoryEntity;
import com.lpan.present.Presenter;

public class HistoryListPresenter implements Presenter{
    HistoryListAdapter historyListAdapter;
    @Override
    public void initUI(View view, Object baseEntity) {
         historyListAdapter = new HistoryListAdapter(view.getContext(), ((HistoryEntity) baseEntity).result);
        ((ListView)view).setAdapter(historyListAdapter);
        ((ListView)view).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HistoryEntity.ResultBean item = (HistoryEntity.ResultBean) historyListAdapter.getItem(position);
                if(item!=null){
                    Intent intent = new Intent(view.getContext(),HistoryDetailActivity.class);
                    intent.putExtra("id",item._id);
                    view.getContext().startActivity(intent);
                }
            }
        });
    }

    @Override
    public void updateUI(View view, Object baseEntity) {
        historyListAdapter.addData( ((HistoryEntity) baseEntity).result);
    }
}
