package com.Lee.ExchangeGo;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobRelation;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UploadFileListener;
import com.Lee.ExchangeGo.UserData.Myuser;
import com.Lee.ExchangeGo.UserData.UserGoods;
import com.bmob.BmobProFile;
import com.bmob.btp.callback.DownloadListener;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2015/8/1.
 */
public class GoodsPageActivity extends Activity implements View.OnClickListener{
    private TextView tv_tittle,tv_content,tv_price,tv_nickname,tv_want;
    private RelativeLayout layout_huan,layout_monney;
    private Button btn_collection;
    private ImageView iv_connectbyqq;
    static int count=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_good_infor);

         init();

    }










    private void init(){
        tv_tittle= (TextView) findViewById(R.id.page_goodinfor_tittle);
        tv_content= (TextView) findViewById(R.id.page_goodinfor_content);
        tv_price= (TextView) findViewById(R.id.page_goodinfor_price);
        tv_want= (TextView) findViewById(R.id.page_goodinfor_want);
        tv_nickname= (TextView) findViewById(R.id.page_goodinfor_owner_name);
        //btn_collection= (Button) findViewById(R.id.page_goodinfor_btn_like);
        iv_connectbyqq= (ImageView) findViewById(R.id.page_goodinfor_pic_connectby_qq);

        layout_huan= (RelativeLayout) findViewById(R.id.goodpage_exchange);
        layout_monney= (RelativeLayout) findViewById(R.id.goodpage_monney);

        //获取上一个Activity传递来的参数
        Intent i=getIntent();
        String tittle=i.getStringExtra("tittle");
        String content=i.getStringExtra("content");
        String nickname=i.getStringExtra("nickname");


        BmobQuery<UserGoods> query=new BmobQuery<UserGoods>();
        query.addWhereEqualTo("good_tittle",tittle);
        query.addWhereEqualTo("good_content",content);
        query.addWhereEqualTo("nickname",nickname);
        query.findObjects(this,new FindListener<UserGoods>() {
            @Override
            public void onSuccess(List<UserGoods> userGoodses) {
                for (UserGoods u : userGoodses) {
                    tv_nickname.setText(u.getNickname());
                    tv_tittle.setText(u.getGood_tittle());
                    tv_content.setText(u.getGood_content());


                    //判断是换还是现金
                    if (u.getIsExchange()==true)
                        tv_want.setText("想换:"+u.getGood_want().toString());

                    else if (u.getIsExchange()==false){
                        layout_huan.setVisibility(View.INVISIBLE);
                        layout_monney.setVisibility(View.VISIBLE);
                        tv_price.setText("价格:" + u.getGood_price().toString() + "/元");
                    }
                    //btn_collection.setOnClickListener(GoodsPageActivity.this);
                    iv_connectbyqq.setOnClickListener(GoodsPageActivity.this);

                }
            }

            @Override
            public void onError(int i, String s) {
                Toast.makeText(getApplicationContext(),"error"+s,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {

        if(v==iv_connectbyqq){
            Myuser myuser=BmobUser.getCurrentUser(this,Myuser.class);
            String qq=myuser.getQq();
             String url="mqqwpa://im/chat?chat_type=wpa&uin="+qq;
             startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
         }
    }



}
