package com.Lee.ExchangeGo.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.BmobUser;
import com.Lee.ExchangeGo.Image.RoundImageView;
import com.Lee.ExchangeGo.LoginActivity;
import com.Lee.ExchangeGo.PageMyInforActivity;
import com.Lee.ExchangeGo.R;
import com.Lee.ExchangeGo.Widget.head_pick_popupwindow;

public class FragmentPageMy extends Fragment implements View.OnClickListener{
    private View rootView;
    private RoundImageView head_img;
    private Button btn_out;

    private TextView tv_myinfor;



    public head_pick_popupwindow popupwindow;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        if (rootView == null)
        {
            rootView = inflater.inflate(R.layout.fragment_my, null);
        }
        // 缓存的rootView需要判断是否已经被加过parent，如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null)
        {
            parent.removeView(rootView);
        }



        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
       /*事件监听
               ......*/
        tv_myinfor= (TextView) getActivity().findViewById(R.id.page_my_tv_infor);
        btn_out= (Button) getActivity().findViewById(R.id.page_my_btn_out);
        head_img= (RoundImageView) getActivity().findViewById(R.id.img_view_head);
        tv_myinfor.setOnClickListener(this);
        btn_out.setOnClickListener(this);

    }



    private View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            popupwindow.dismiss();
                switch (v.getId())
                {
                    case R.id.head_pick_take_pic:
                        break;
                    case R.id.head_pick_pick_pic:
                        break;
                }
        }
    };

    @Override
    public void onClick(View v) {
        if(v==tv_myinfor){
            Intent i=new Intent();
            i.setClass(this.getActivity(),PageMyInforActivity.class);
            startActivity(i);

        }
        else if (v==btn_out){
            //清除缓存
            BmobUser.logOut(getActivity());
            BmobUser currentuser=BmobUser.getCurrentUser(getActivity());

            Toast.makeText(getActivity(),"注销成功！",Toast.LENGTH_SHORT).show();
            Intent i=new Intent(getActivity(), LoginActivity.class);
            startActivity(i);;
            getActivity().finish();
        }
        else if (v==head_img){

            PopupWindow pop=new PopupWindow(getActivity(),null);
            pop.showAtLocation(getActivity().findViewById(R.id.main_my), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);
            //popupwindow=new head_pick_popupwindow(getActivity(),onClickListener);
            //popupwindow.showAtLocation(getActivity().findViewById(R.id.main_my), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);
        }
    }
}

