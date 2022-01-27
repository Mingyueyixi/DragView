package com.simaple.dragview;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import yue.util.DragViewUtil;
import yue.view.DragView;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DragView dragView = (DragView) findViewById(R.id.dv_mian);
        TextView textView = (TextView) findViewById(R.id.tv_main);
        Button button = (Button) findViewById(R.id.button);

        dragView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShortToast("点击事件能够响应？？？");
            }
        });
        dragView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showLongToast("长按点击能响应？？？");
                return false;
            }
        });

        DragViewUtil.registerDragAction(textView);
        //延迟3秒响应拖曳
        DragViewUtil.registerDragAction(button,3000);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShortToast("DragViewUtil 拖动view能够响应点击事件？");
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShortToast("3秒之后才能够拖曳");
            }
        });

    }

}