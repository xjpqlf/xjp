package ui.zlz.activity.account.updateinfo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import ui.zlz.R;
import ui.zlz.activity.account.Zlzapplication;

public class SettingPayingPasswordActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_paying_password);
        Zlzapplication.addActivity(this);
        init();
    }

    private void init() {
       ImageView iv= findViewById(R.id.iv_paypwd_back);
      TextView tv= findViewById(R.id.tv_pwd_pay);
        Button bt=findViewById(R.id.bt_pay_pwd);

        iv.setOnClickListener(this);
        tv.setOnClickListener(this);
        bt.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case  R.id.iv_paypwd_back:
                finish();
                break;
            case  R.id.tv_pwd_pay:
                startActivity(new Intent(SettingPayingPasswordActivity.this,InputPwdActivity.class));

                break;
            case  R.id.bt_pay_pwd:
                startActivity(new Intent(SettingPayingPasswordActivity.this,InputPwdActivity.class));
                break;
        }

    }
}
