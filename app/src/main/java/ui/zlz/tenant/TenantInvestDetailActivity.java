package ui.zlz.tenant;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import ui.zlz.R;

public class TenantInvestDetailActivity extends Activity {
    private RelativeLayout dengEBenXi_ball;//等息本金栏

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenant_invest_detail);
        //加载popup对应的界面布局文件
        View hintView = this.getLayoutInflater().inflate(R.layout.activity_tenant_jump_hint, null);
        //创建popupWindow对象
        final PopupWindow dengEPopup = new PopupWindow(hintView, 1020, 1000);
        //获取按键位置
        dengEBenXi_ball = findViewById(R.id.dengEBenXi_ball);
        //添加点击事件 匿名内部类
        dengEBenXi_ball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //以下拉方式显示
                dengEPopup.showAsDropDown(view);
                //将PopupWindow显示在指定位置
                dengEPopup.showAtLocation(findViewById(R.id.hint_id), Gravity.CENTER, 20, 20);
            }
        });
        //获取PopupWindow中的“关闭”按钮
        hintView.findViewById(R.id.colseHint_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //关闭
                dengEPopup.dismiss();
            }
        });
    }

}