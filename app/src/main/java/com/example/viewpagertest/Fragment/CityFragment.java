package com.example.viewpagertest.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.viewpagertest.CustomView.TalkDialog;
import com.example.viewpagertest.MainActivity;
import com.example.viewpagertest.R;

import java.util.ArrayList;

public class CityFragment extends Fragment {

    private Button npc;
    private String[] subtitle_array;
    private ArrayList<String> subtitle, name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_city, container, false);

        npc = view.findViewById(R.id.npc);
        subtitle = new ArrayList<>();
        name = new ArrayList<>();

        subtitle_array = view.getContext().getResources().getStringArray(R.array.city_npc);

        for (String s : subtitle_array){
            name.add(s.substring(0,s.indexOf(":")));
            subtitle.add(s.substring(s.indexOf(":")+1,s.indexOf("*")));
        }

//        for (int i=0 ; i < subtitle.size(); i++ ) {
//            Log.d("NPC200", ""+name.get(i)+"///"+subtitle.get(i));
//        }

        npc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TalkDialog talkDialog = new TalkDialog(v.getContext(),R.style.Theme_AppCompat_DayNight_Dialog,0,0,subtitle, name);

                talkDialog.show();
            }
        });

        return view;
    }
}
