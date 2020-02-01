package com.example.viewpagertest.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.viewpagertest.MainActivity;
import com.example.viewpagertest.R;
import com.example.viewpagertest.ViewPagerAdapter;

import java.util.ArrayList;

import fr.castorflex.android.verticalviewpager.VerticalViewPager;

public class TeleportFragment extends Fragment {

    private static VerticalViewPager verticalViewPager; //第三方外掛 上下滑動的ViewPager
    ViewPagerAdapter viewPagerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teleport, container, false);

        verticalViewPager = view.findViewById(R.id.vertical_pager);
        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(),0);

        viewPagerAdapter.addFragment(new PrairieFragment());//草原
        viewPagerAdapter.addFragment(new CityFragment());//城市
        viewPagerAdapter.addFragment(new LakekFragment());//湖泊

        verticalViewPager.setAdapter(viewPagerAdapter);
        verticalViewPager.setCurrentItem(1);//初始在城市
        verticalViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int i = verticalViewPager.getCurrentItem();
                MainActivity.setVisible(i);
                MainActivity.setStopScroll(i);//當畫面在特定區域時 停止左右滑動
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        return view;
    }

    public static void setVerticalViewPager(int pager){
        verticalViewPager.setCurrentItem(pager);
    }

    public static int pageNum(){
        return verticalViewPager.getCurrentItem();
    }
}
