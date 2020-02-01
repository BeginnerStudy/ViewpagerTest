package com.example.viewpagertest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Switch;

import com.example.viewpagertest.Fragment.CityFragment;
import com.example.viewpagertest.Fragment.ForestFragment;
import com.example.viewpagertest.Fragment.MountainFragment;
import com.example.viewpagertest.Fragment.PortFragment;
import com.example.viewpagertest.Fragment.TeleportFragment;

import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;
import java.util.EventListener;

public class MainActivity extends AppCompatActivity {

    public static NoScrollViewPager viewPager;//使用自訂義viewpager
    public static ImageButton btn_left, btn_right, btn_down, btn_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewpager);
        btn_left = findViewById(R.id.btn_left);
        btn_right = findViewById(R.id.btn_right);
        btn_down = findViewById(R.id.btn_down);
        btn_up = findViewById(R.id.btn_up);

        final ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), 0);
        viewPagerAdapter.addFragment(new PortFragment());//港口
        viewPagerAdapter.addFragment(new ForestFragment());//森林
        viewPagerAdapter.addFragment(new TeleportFragment());//嵌套上下滑動viewpager的fragment
        viewPagerAdapter.addFragment(new MountainFragment());//山丘

        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(2);//初始在城市
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //在每個區域的邊界取消顯示移動按鍵
                int i = viewPager.getCurrentItem();
                switch (i){
                    case 0 :
                        btn_left.setVisibility(View.GONE);
                        btn_right.setVisibility(View.VISIBLE);
                        btn_up.setVisibility(View.GONE);
                        btn_down.setVisibility(View.GONE);
                        break;
                    case 1 :
                        btn_left.setVisibility(View.VISIBLE);
                        btn_right.setVisibility(View.VISIBLE);
                        btn_up.setVisibility(View.GONE);
                        btn_down.setVisibility(View.GONE);
                        break;
                    case 2 :
                        btn_left.setVisibility(View.VISIBLE);
                        btn_right.setVisibility(View.VISIBLE);
                        btn_up.setVisibility(View.VISIBLE);
                        btn_down.setVisibility(View.VISIBLE);
                        break;
                    case 3 :
                        btn_left.setVisibility(View.VISIBLE);
                        btn_right.setVisibility(View.GONE);
                        btn_up.setVisibility(View.GONE);
                        btn_down.setVisibility(View.GONE);
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        btn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = viewPager.getCurrentItem()+1;
                viewPager.setCurrentItem(i);
            }
        });

        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = viewPager.getCurrentItem()-1;
                viewPager.setCurrentItem(i);
            }
        });

        btn_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TeleportFragment.setVerticalViewPager(TeleportFragment.pageNum()-1);
            }
        });

        btn_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TeleportFragment.setVerticalViewPager(TeleportFragment.pageNum()+1);
            }
        });
    }

    public static void setStopScroll(int page){
        //當畫面在特定區域時 停止左右滑動
        switch (page){
            case 0:
                viewPager.setStopScroll(true);
                break;
            case 1:
                viewPager.setStopScroll(false);
                break;
            case 2:
                viewPager.setStopScroll(true);
                break;
        }
    }

    public static void setVisible (int page){
        //在每個區域的邊界取消顯示移動按鍵
        switch (page){
            case 0:
                btn_left.setVisibility(View.GONE);
                btn_right.setVisibility(View.GONE);
                btn_up.setVisibility(View.GONE);
                btn_down.setVisibility(View.VISIBLE);
                break;
            case 1:
                btn_left.setVisibility(View.VISIBLE);
                btn_right.setVisibility(View.VISIBLE);
                btn_up.setVisibility(View.VISIBLE);
                btn_down.setVisibility(View.VISIBLE);
                break;
            case 2:
                btn_left.setVisibility(View.GONE);
                btn_right.setVisibility(View.GONE);
                btn_up.setVisibility(View.VISIBLE);
                btn_down.setVisibility(View.GONE);
                break;
        }
    }

    public static void setViewPager(int pager){
        viewPager.setCurrentItem(pager);
    }
}
