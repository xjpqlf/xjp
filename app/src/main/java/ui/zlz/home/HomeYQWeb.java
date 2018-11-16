package ui.zlz.home;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import ui.zlz.R;

public class HomeYQWeb extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//Activity 启动时运行
        setContentView(R.layout.activity_home_yqweb);//加载对应的布局页面
        initView();
    }

    private void initView() {
        ImageView iv=findViewById(R.id.iv_yqweb_back);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
