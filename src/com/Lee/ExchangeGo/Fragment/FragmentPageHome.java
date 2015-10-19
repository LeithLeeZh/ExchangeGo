package com.Lee.ExchangeGo.Fragment;

import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import com.Lee.ExchangeGo.R;
import com.Lee.ExchangeGo.UserData.Myuser;
import com.Lee.ExchangeGo.UserData.UserGoods;

import java.util.List;

public class FragmentPageHome extends Fragment{
     private View rootView;
    private TextView tv_test,tv_1,tv_2,tv_3,tv_4,tv_5_tv_6,tv_7,tv_8,tv_9,tv_10,tv_11,tv_12,tv_13,tv_14,tv_15,tv_16;
    private Button btn_test;
    private ImageView pic1,pic2,pic3,pic4,pic5,pic6,pic7,pic8,pic9,pic10,pic11,pic12,pic13,pic14,pic15,pic16;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {



        if (rootView == null)
        {
            rootView = inflater.inflate(R.layout.fragment_home, null);
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
        //pic1= (ImageView) getActivity().findViewById(R.id.pic1);

        //final ImageView[] view = {pic1,};
     /*  final TextView[] tv_tittle={tv_1,tv_2,tv_3,tv_4,tv_5_tv_6,tv_7,tv_8};
        final ImageView[] pic={pic1,pic2,pic3,pic4,pic5,pic6,pic7,pic8};
        tv_tittle[0]= (TextView) getActivity().findViewById(R.id.tv_1);
        tv_tittle[1]= (TextView) getActivity().findViewById(R.id.tv_2);
        tv_tittle[2]= (TextView) getActivity().findViewById(R.id.tv_3);
        tv_tittle[3]= (TextView) getActivity().findViewById(R.id.tv_4);
        tv_tittle[4]= (TextView) getActivity().findViewById(R.id.tv_5);
        tv_tittle[5]= (TextView) getActivity().findViewById(R.id.tv_6);
        tv_tittle[6]= (TextView) getActivity().findViewById(R.id.tv_7);
        tv_tittle[7]= (TextView) getActivity().findViewById(R.id.tv_8);
        //pic[0]= (ImageView) getActivity().findViewById(R.id.pic1);
        tv_2= (TextView) getActivity().findViewById(R.id.tv_2);
        BmobQuery<UserGoods> query=new BmobQuery<UserGoods>();
        query.addWhereEqualTo("isExchange",true);
        query.setLimit(8);
        final int[] i = {0};
        query.findObjects(getActivity(), new FindListener<UserGoods>() {
            @Override
            public void onSuccess(List<UserGoods> list) {
                for (UserGoods goods:list){
                    //for (int i=0;i<=tv_tittle.length;i++){

                        tv_tittle[i[0]].setText(goods.getGood_tittle());
                        i[0] = i[0] +1;
                    //}
                }
            }

            @Override
            public void onError(int i, String s) {

            }
        });*/

    }

}
