package ui.zlz.welfare;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import ui.zlz.R;
import ui.zlz.RewritePopwindow;

public class TopExclusiveContentActivity extends Activity {

    private RewritePopwindow mPopwindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_exclusive_content);
    }

    public void share(View view){
        mPopwindow = new RewritePopwindow(TopExclusiveContentActivity.this, itemsOnClick);
        mPopwindow.showAtLocation(view,Gravity.CENTER, 0, 0);
    }
    //为弹出窗口实现监听类
    private View.OnClickListener itemsOnClick = new View.OnClickListener() {

        public void onClick(View v) {
            mPopwindow.dismiss();
            mPopwindow.backgroundAlpha(TopExclusiveContentActivity.this, 1f);
            switch (v.getId()) {
                case R.id.weixinghaoyou:
                    Toast.makeText(TopExclusiveContentActivity.this, "微信好友", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.pengyouquan:
                    Toast.makeText(TopExclusiveContentActivity.this, "朋友圈", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.qqhaoyou:
                    Toast.makeText(TopExclusiveContentActivity.this, "QQ好友", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.qqkongjian:
                    Toast.makeText(TopExclusiveContentActivity.this, "QQ空间", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }

    };
}
