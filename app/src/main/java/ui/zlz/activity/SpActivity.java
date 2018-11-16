package ui.zlz.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import ui.zlz.R;
import ui.zlz.activity.account.LoginActivity;
import ui.zlz.utility.SharedPrefUtil;

public class SpActivity extends AppCompatActivity implements View.OnClickListener {

    private int recLen = 3;//跳过倒计时提示5秒
    private TextView tv;
    Timer timer = new Timer();
    private Handler handler;
    private Runnable runnable;

    /**
     * 判断是否需要检测，防止不停的弹框
     */
    private boolean isNeedCheck = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);









        //定义全屏参数
        int flag= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //设置当前窗体为全屏显示
        getWindow().setFlags(flag, flag);
        setContentView(R.layout.activity_sp);
        initView();
        timer.schedule(task, 1000, 1000);//等待时间一秒，停顿时间一秒
        /**
         * 正常情况下不点击跳过
         */
        handler = new Handler();
        handler.postDelayed(runnable = new Runnable() {
            @Override
            public void run() {

                    Intent intent = new Intent(SpActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();

            }
        }, 3000);//延迟5S后发送handler信息

    }

    private void initView() {
        tv = findViewById(R.id.tv);//跳过
        tv.setOnClickListener(this);//跳过监听
    }

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            runOnUiThread(new Runnable() { // UI thread
                @Override
                public void run() {
                    recLen--;
                    tv.setText("跳过 " + recLen);
                    tv.setBackgroundColor(getResources().getColor(R.color.welcome_back));
                    if (recLen < 0) {
                        timer.cancel();
                        tv.setVisibility(View.GONE);//倒计时到0隐藏字体
                    }
                }
            });
        }
    };

    /**
     * 点击跳过
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv:
                //从闪屏界面跳转到首界面
               if (TextUtils.isEmpty(SharedPrefUtil.getString(SpActivity.this,"user","0"))||TextUtils.isEmpty(SharedPrefUtil.getString(SpActivity.this,"pwd","0"))){

                    Intent intent = new Intent(SpActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();

               }else {
                    Intent intent = new Intent(SpActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                if (runnable != null) {
                    handler.removeCallbacks(runnable);
                }
                break;
            default:
                break;
        }
    }



}
