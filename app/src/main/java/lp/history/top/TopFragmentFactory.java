package lp.history.top;



import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 刘攀
 */
public class TopFragmentFactory {
    public static List<Fragment> getFragments() {
        ArrayList<Fragment> arrayList = new ArrayList();
        arrayList.add(TopFragment.getInstance(NewsType.TOP));
        arrayList.add(TopFragment.getInstance(NewsType.SHEHUI));
        arrayList.add(TopFragment.getInstance(NewsType.GUONEI));
        arrayList.add(TopFragment.getInstance(NewsType.GUOJI));
        arrayList.add(TopFragment.getInstance(NewsType.YULE));
        arrayList.add(TopFragment.getInstance(NewsType.TIYU));
        arrayList.add(TopFragment.getInstance(NewsType.JUNSHI));
        arrayList.add(TopFragment.getInstance(NewsType.KEJI));
        arrayList.add(TopFragment.getInstance(NewsType.CAIJING));
        arrayList.add(TopFragment.getInstance(NewsType.SHISHANG));
        return arrayList;
    }
}
