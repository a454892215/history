package lp.history;

import android.content.Context;
import android.widget.BaseAdapter;

import org.xutils.common.util.LogUtil;

import java.util.List;

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
