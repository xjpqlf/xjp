package ui.zlz.home;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import ui.zlz.R;

public class NoticeActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notice_activity);
        initView();
    }

    private void initView() {
        WebView webView=findViewById(R.id.wb_mywebvien);
        if (getIntent()!=null){

            String content=getIntent().getStringExtra("con");
            webView.loadDataWithBaseURL(null,
                    content, "text/html", "utf-8", null);
        }
    }
}
