package lp.history.base;
import android.content.Context;
import android.widget.BaseAdapter;

import java.util.List;

import commom.utils.LogUtil;

public abstract class MyBaseAdapter extends BaseAdapter {
    protected List<?> list;
    protected Context context;

    public MyBaseAdapter(Context context, List<?> list) {
        LogUtil.i("LLpp:=====================:size"+list.size());
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}