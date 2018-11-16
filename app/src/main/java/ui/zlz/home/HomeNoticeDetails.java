package ui.zlz.home;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ui.zlz.R;

public class HomeNoticeDetails extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//Activity 启动时运行
        setContentView(R.layout.activity_home_notice_details);//加载对应的布局页面
        initView();
    }

    private void initView() {
        TextView content=findViewById(R.id.tv_gongaobiaoti);
        TextView ctime=findViewById(R.id.tv_noticeTime1);
        ImageView ivback=findViewById(R.id.iv_wzhgg_back);
        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        if (getIntent()!=null){
            content.setText(getIntent().getStringExtra("content"));
            ctime.setText(getIntent().getStringExtra("ctime"));
        }

    }

}
