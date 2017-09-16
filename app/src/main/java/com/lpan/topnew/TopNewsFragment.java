package com.lpan.topnew;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.lpan.R;

public class TopNewsFragment extends Fragment {
    private ViewPager main_content_vp;
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
    RecyclerView recyclerView;
    private void initUI(View view) {
        main_content_vp = (ViewPager) view.findViewById(R.id.main_content_vp);
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getChildFragmentManager(), TopFragmentFactory.getFragments());
        recyclerView   = (RecyclerView) view.findViewById(R.id.top_indicator_ry);
        setRecyclerView(recyclerView);
        main_content_vp.setAdapter(fragmentAdapter);
        main_content_vp.addOnPageChangeListener(onPageChangeListener);
    }
    IndicatorRecycleAdapter indicatorRecycleAdapter;
    private void setRecyclerView(RecyclerView recyclerView) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);recyclerView.setSelected(true);
        layoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        indicatorRecycleAdapter = new IndicatorRecycleAdapter(getActivity(),recyclerView);
        indicatorRecycleAdapter.setViewPager(main_content_vp);
        recyclerView.setAdapter( indicatorRecycleAdapter);
        // recyclerView.addItemDecoration( new DividerGridItemDecoration(this));
        recyclerView.setItemAnimator( new DefaultItemAnimator());
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
        public void onPageSelected(final int position) {
            recyclerView.scrollToPosition(position);
            indicatorRecycleAdapter.clearSelect();
            recyclerView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    indicatorRecycleAdapter.onSelectedChanged(position,getActivity());
                }
            },10);

        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };

}
