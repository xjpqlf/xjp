package ui.zlz.home;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import ui.zlz.R;

public class MediaDetailActivity extends Activity {

    private WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.media_activity);
        initView();
    }

    private void initView() {
        webView = findViewById(R.id.wb_mywebviem);
        ImageView iv=findViewById(R.id.iv_media_back);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        if (getIntent()!=null){

            String content=getIntent().getStringExtra("content");
            webView.loadDataWithBaseURL(null,
                    content, "text/html", "utf-8", null);
        }
    }
}
