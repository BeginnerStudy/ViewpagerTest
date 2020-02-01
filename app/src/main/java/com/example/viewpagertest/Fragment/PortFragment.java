package com.example.viewpagertest.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.viewpagertest.MainActivity;
import com.example.viewpagertest.R;

public class PortFragment extends Fragment {

    private Button btn_ship;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_port, container, false);
        btn_ship = view.findViewById(R.id.btn_ship);
        btn_ship.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity.setViewPager(2);
                TeleportFragment.setVerticalViewPager(2);
            }
        });
        return view;
    }

}
