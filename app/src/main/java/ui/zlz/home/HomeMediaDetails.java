package ui.zlz.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import ui.zlz.R;

public class HomeMediaDetails extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//Activity 启动时运行
        setContentView(R.layout.activity_home_media_details);//加载对应的布局页面
    }

}
