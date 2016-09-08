package lp.history.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import commom.utils.DensityUtils;
import lp.history.R;

/**
 * Created by 刘攀
 */
public class ViewPagerIndicator extends HorizontalScrollView implements View.OnClickListener {
    Context context;

    public ViewPagerIndicator(Context context) {
        this(context, null);
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        setBackgroundColor(getResources().getColor(R.color.head_color));
        setHorizontalScrollBarEnabled(false);
    }

    LinearLayout llt_content;

    private List<TextView> listTitles = new ArrayList();
    private List<View> listSelected = new ArrayList();
    public void setTitles(String... titles) {
        llt_content = new LinearLayout(context);
        llt_content.setOrientation(LinearLayout.HORIZONTAL);
        for (int i = 0; i < titles.length; i++) {
            View itemView = getItemView(i, titles[i]);
            llt_content.addView(itemView);
        }
        this.addView(llt_content);
    }

    @NonNull
    private View getItemView(int i, String title) {
        View itemView = View.inflate(context, R.layout.vp_indicator_item, null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.width = 0;
        params.weight = 1;
        params.height = DensityUtils.dp2px(context, 40);
        itemView.setLayoutParams(params);
        itemView.setOnClickListener(this);
        itemView.setTag(i);
        TextView vp_indicator_title = (TextView) itemView.findViewById(R.id.vp_indicator_title);
        View vp_indicator_selectedView = itemView.findViewById(R.id.vp_indicator_selectedView);
        vp_indicator_title.setText(title);
        listTitles.add(vp_indicator_title);
        listSelected.add(vp_indicator_selectedView);
        if(i==0){
            listTitles.get(i).setTextColor(getResources().getColor(R.color.checkedTextColor));
            listSelected.get(i).setVisibility(VISIBLE);
        }
        return itemView;
    }

    private ViewPager viewPager;
    public void setViewPager(ViewPager viewpager) {
        this.viewPager = viewpager;
    }

    /**
     * Called when a view has been clicked.
     */
    @Override
    public void onClick(View v) {
        int index = (int) v.getTag();
        viewPager.setCurrentItem(index);
        onSelectedChanged(index);
    }

    public void onSelectedChanged(int index) {
        for (int i = 0; i < llt_content.getChildCount(); i++) {
            listTitles.get(i).setTextColor(getResources().getColor(R.color.textColor));
            listSelected.get(i).setVisibility(GONE);
        }
        listTitles.get(index).setTextColor(getResources().getColor(R.color.checkedTextColor));
        listSelected.get(index).setVisibility(VISIBLE);
    }

}
