package com.Lee.ExchangeGo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.*;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.SaveListener;
import com.Lee.ExchangeGo.Fragment.FragmentPageDeliver;
import com.Lee.ExchangeGo.UserData.Myuser;
import com.Lee.ExchangeGo.UserData.UserGoods;
import com.bmob.BTPFileResponse;
import com.bmob.BmobProFile;
import com.bmob.btp.callback.UploadListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by leithlee on 15/10/13.
 */
public class PageGoodDeliver extends Activity
{
    private EditText et_tittle,et_content,et_price,et_want;
    private Button btn_take,btn_pick,btn_cancel;
    private Button btn_fuck;
    private ImageView img_goodpic;
    private LinearLayout add_layout;
    private LinearLayout layout_huan,layout_monney;
    private TextView btn_deli_submit;
    private RadioGroup radioGroup;
    private RadioButton radioButton_huan,radioButton_monney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_good_deliver);

        et_tittle= (EditText) findViewById(R.id.page_deli_add_tittle);
        et_content= (EditText) findViewById(R.id.page_deli_add_content);
        et_price= (EditText) findViewById(R.id.page_deli_et_price);
        et_want= (EditText) findViewById(R.id.page_deli_et_want);
        img_goodpic= (ImageView) findViewById(R.id.page_deli_add_imageview);
        radioGroup= (RadioGroup) findViewById(R.id.radio_choice);
        radioButton_huan= (RadioButton) findViewById(R.id.radio_huan);
       // radioButton_huan.setChecked(true);
        radioButton_monney= (RadioButton) findViewById(R.id.radio_monney);
        layout_huan= (LinearLayout) findViewById(R.id.exchange_gou);
        layout_monney= (LinearLayout) findViewById(R.id.monney_gou);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId)
                {
                    case R.id.radio_huan:
                        radioButton_monney.setChecked(false);
                        radioButton_huan.setChecked(true);
                        layout_huan.setVisibility(View.VISIBLE);
                        layout_monney.setVisibility(View.INVISIBLE);
                        break;
                    case R.id.radio_monney:
                        radioButton_huan.setChecked(false);
                        radioButton_monney.setChecked(true);
                        layout_huan.setVisibility(View.INVISIBLE);
                        layout_monney.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });
        //img_goodpic.setOnClickListener(this);
       // btn_deli_submit= (TextView) findViewById(R.id.page_deli_btn_deliver);
       // btn_deli_submit.setOnClickListener(this);

        //监听
        img_goodpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i, 1);
            }
        });






    }






    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==1 && resultCode==RESULT_OK){
            Bitmap bitmap= (Bitmap) data.getExtras().get("data");
            File basefile=FileHelper.getPicBaseFile();
            if (basefile==null){
                Log.i("Error", "SD卡不可用!");
                return;
            }
            final String filename=getFileName();
            SavePhoto(filename,bitmap,basefile);
            img_goodpic.setImageBitmap(bitmap);

            //上传操作
            btn_deli_submit= (TextView) findViewById(R.id.page_deli_btn_deliver);
            btn_deli_submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String tittle = et_tittle.getText().toString();
                    final String content = et_content.getText().toString();
                    final String price = et_price.getText().toString();
                    final String want = et_want.getText().toString();


                    String filepath = "sdcard/" + FileHelper.filepath + "/" + filename;
                    //Toast.makeText(getApplicationContext(), "11111111" + filepath, Toast.LENGTH_SHORT).show();


                    BTPFileResponse response = BmobProFile.getInstance(getApplicationContext()).upload(filepath, new UploadListener() {
                        @Override
                        public void onSuccess(String s, String s1, BmobFile bmobFile) {
                            Myuser user = Myuser.getCurrentUser(getApplicationContext(), Myuser.class);
                            UserGoods good = new UserGoods();
                            good.setUser(user);
                            good.setNickname(user.getNickname());
                            good.setGood_tittle(tittle);
                            good.setGood_content(content);
                            good.setGood_image(bmobFile);
                            if (radioButton_monney.isChecked()) {
                                good.setGood_price(Integer.valueOf(price));
                                good.setIsExchange(false);
                            } else if (radioButton_huan.isChecked()){
                                good.setGood_want(want);
                                good.setIsExchange(true);
                            }

                            good.save(getApplicationContext(), new SaveListener() {
                                @Override
                                public void onSuccess() {

                                    Intent i = new Intent(PageGoodDeliver.this, FragmentPageDeliver.class);
                                    startActivity(i);
                                    finish();
                                    Toast.makeText(getApplicationContext(), "发布成功!可在我的发布中查看", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onFailure(int i, String s) {

                                }
                            });

                        }

                        @Override
                        public void onProgress(int i) {
                                Toast.makeText(getApplicationContext(),"进度:"+i,Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(int i, String s) {
                            Toast.makeText(getApplicationContext(), "Error" + s, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });





        }


        super.onActivityResult(requestCode, resultCode, data);
    }


    //获取文件名

    private String getFileName(){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA);
        Date crudate= Calendar.getInstance().getTime();
        String time=simpleDateFormat.format(crudate);
        String filename=time+".jpg";

        return filename;
    }


    //保存照片
    private void SavePhoto(String filename,Bitmap bitmap,File basefile){
        FileOutputStream outputStream=null;
        File imgfile=new File(basefile,"/"+filename);


        try {
            outputStream = new FileOutputStream(imgfile);
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        finally {
            try{
                outputStream.flush();;
                outputStream.close();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }



    }
}
