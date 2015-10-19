package com.Lee.ExchangeGo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by Administrator on 2015/9/9.
 */
public class BootPage extends Activity {
    private final int SPLASH_DISPLAY_LENGHT = 1500; //延迟三秒
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bootpage);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(BootPage.this,LoginActivity.class);
                BootPage.this.startActivity(intent);
                BootPage.this.finish();
            }
        },SPLASH_DISPLAY_LENGHT);
    }
}
