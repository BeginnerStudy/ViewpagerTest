package com.example.viewpagertest.CustomView;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.viewpagertest.R;

import java.util.ArrayList;

public class TalkDialog extends Dialog {

    //add
    private Window window = null;

    private Button next_btn, skip_btn;
    private TextView talk;
    private ImageView npc_image;

    private ArrayList<String> subtitle, name;
    private int progress = 0;

    public TalkDialog(@NonNull Context context) {
        super(context);
    }

    //override
    public TalkDialog(@NonNull Context context, int themeResId, int x, int y, ArrayList<String> subtitle, ArrayList<String> name) {
        super(context, themeResId);
        //add
        setWindow(x,y);

        this.subtitle = subtitle;
        this.name = name;
    }

    protected TalkDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    //add
    private void setWindow(int x, int y){
        window = getWindow();
        window.setBackgroundDrawableResource(android.R.color.transparent);
        WindowManager.LayoutParams params = window.getAttributes();
        params.x = x;
        params.y = y;
        window.setAttributes(params);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.talkdialog_view, null);

        npc_image = view.findViewById(R.id.npc_image);
        next_btn = view.findViewById(R.id.next_btn);
        skip_btn = view.findViewById(R.id.skip_btn);
        talk = view.findViewById(R.id.talk);

        talk.setText(subtitle.get(progress));

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress++;

                if (name.get(progress).equals("NPC")){
                    npc_image.setImageResource(R.mipmap.boy);
                }else if(name.get(progress).equals("玩家")){
                    npc_image.setImageResource(R.drawable.ic_person);
                }

                if (progress - subtitle.size() == -1){
                    progress = 0;
                    talk.setText(subtitle.get(subtitle.size()-1));
                    next_btn.setVisibility(View.GONE);
                    skip_btn.setText("END");
                }else {
                    talk.setText(subtitle.get(progress));
                }

            }
        });

        skip_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });

        setContentView(view);
        super.onCreate(savedInstanceState);
    }

    //override
    @Override
    public void show() {
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        super.show();
    }
}
