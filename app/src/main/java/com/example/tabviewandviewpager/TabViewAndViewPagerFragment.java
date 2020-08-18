package com.example.tabviewandviewpager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.HashMap;

//Fragment 로 사용할 경우
public class TabViewAndViewPagerFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private FragmentStateAdapter pagerAdapter;
    ArrayList<HashMap<String, Object>> bestTimetable;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tabview, container, false);
        tabLayout = root.findViewById(R.id.layout_tab);
        viewPager2 = root.findViewById(R.id.pager_content);
        pagerAdapter = new ScreenSlidePagerAdapter(getActivity());
        viewPager2.setAdapter(pagerAdapter);


        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if (positionOffsetPixels == 0) {
                    viewPager2.setCurrentItem(position);
                    tabLayout.getTabAt(position).select();
                }
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return root;
    }


   // 슬라이드 될때마다 프래그먼트를 변경시켜 페이지를 보여주는 어뎁터
    private class ScreenSlidePagerAdapter extends FragmentStateAdapter {
        public ScreenSlidePagerAdapter(FragmentActivity fa) {
            super(fa);
        }

        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    PopularFragment popularFragment = new PopularFragment();
                    return popularFragment;
                case 1:
                    New_timetableFragment newTimetableFragment = new New_timetableFragment();
                    return newTimetableFragment;
                case 2:
                    My_timetableFragment myTimetableFragment = new My_timetableFragment();
                    return myTimetableFragment;
                default:
                    return null;
            }
        }

        @Override
        public int getItemCount() {
            return tabLayout.getTabCount();
        }
    }
}
