package lp.history.top;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import lp.history.R;
import lp.history.widget.ViewPagerIndicator;

public class ViewPagerFragment extends Fragment {
    private ViewPager main_content_vp;
    private ViewPagerIndicator top_indicator;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = View.inflate(getActivity(), R.layout.view_pager_fragment, null);
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       if(main_content_vp==null) {
           initUI(view);
       }
    }

    private void initUI(View view) {
        main_content_vp = (ViewPager) view.findViewById(R.id.main_content_vp);
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getChildFragmentManager(), TopFragmentFactory.getFragments());
        top_indicator = (ViewPagerIndicator) view.findViewById(R.id.top_indicator);
        top_indicator.setTitles("头条", "社会", "国内", "国际", "娱乐", "体育", "军事", "科技", "财经", "时尚");
        top_indicator.setViewPager(main_content_vp);
        main_content_vp.setAdapter(fragmentAdapter);
        main_content_vp.addOnPageChangeListener(onPageChangeListener);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        main_content_vp.removeOnPageChangeListener(onPageChangeListener);
    }

    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            top_indicator.onSelectedChanged(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };

}
