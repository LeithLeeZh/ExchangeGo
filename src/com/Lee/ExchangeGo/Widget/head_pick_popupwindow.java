package com.Lee.ExchangeGo.Widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import com.Lee.ExchangeGo.R;

/**
 * Created by leithlee on 15/10/11.
 */
public class head_pick_popupwindow extends PopupWindow{
    private View popwindow;
    private Button btn_take,btn_pick,btn_cancel;

    public head_pick_popupwindow(Activity context,View.OnClickListener onClickListener){
        super(context);
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        popwindow=inflater.inflate(R.layout.head_pick_menu,null);
        btn_cancel= (Button) popwindow.findViewById(R.id.head_pick_cancel);
        btn_pick= (Button) popwindow.findViewById(R.id.head_pick_pick_pic);
        btn_take= (Button) popwindow.findViewById(R.id.head_pick_take_pic);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();//销毁
            }
        });

        btn_pick.setOnClickListener(onClickListener);
        btn_take.setOnClickListener(onClickListener);

        this.setContentView(popwindow);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.FILL_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果

        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);



    }
}
