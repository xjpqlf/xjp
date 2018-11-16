package ui.zlz.activity.account.updateinfo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import ui.zlz.R;


public class SecurityInfoActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_info);
        init();
    }

    private void init() {
      ImageView ivsm= findViewById(R.id.iv_gosmyz);
      ImageView ivback= findViewById(R.id.iv_sec);
      ImageView ivsj= findViewById(R.id.iv_gosjyz);
      ImageView ivyx= findViewById(R.id.iv_goyxyz);
      ImageView ivmm= findViewById(R.id.iv_gommdl);
      ImageView ivzf= findViewById(R.id.iv_gozfmm);
        RelativeLayout rlsmyz=findViewById(R.id.rl_smyz);
        RelativeLayout rlsjyz=findViewById(R.id.rl_sjyz);
        RelativeLayout rlyxyz=findViewById(R.id.rl_yxyz);
        RelativeLayout rlxgmm=findViewById(R.id.rl_xgmm);
        RelativeLayout rlzfmm=findViewById(R.id.rl_zfmm);

        rlsmyz.setOnClickListener(this);
        rlsjyz.setOnClickListener(this);
        rlyxyz.setOnClickListener(this);
        rlxgmm.setOnClickListener(this);
        ivback.setOnClickListener(this);

        rlzfmm.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
//去实名验证
            case R.id.rl_smyz:
                startActivity(new Intent(SecurityInfoActivity.this,VerifiedActivity.class));
                break;
                //返回
            case R.id.iv_sec:
                finish();
                break;
                //手机验证
                case R.id.rl_sjyz:
                    startActivity(new Intent(SecurityInfoActivity.this,BindphoneActivity.class));
                break;
                //邮箱验证

            case R.id.rl_yxyz:
                startActivity(new Intent(SecurityInfoActivity.this,BindingEmailActivity.class));
                break;
                //修改密码
            case R.id.rl_xgmm:
                startActivity(new Intent(SecurityInfoActivity.this,UpdatePasswordActivity.class));

                break;
                //支付密码修改
            case R.id.rl_zfmm:
                startActivity(new Intent(SecurityInfoActivity.this,SettingPayingPasswordActivity.class));
                break;

        }

    }
}
