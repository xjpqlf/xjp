package ui.zlz.activity.account;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import ui.zlz.activity.Constants;
import ui.zlz.R;
import ui.zlz.utility.DebugFlags;
import ui.zlz.utility.GetSign;
import ui.zlz.utility.ToastUtil;

public class ChangePwdActivity extends Activity {

    private ImageView ivback;
    private EditText et_pwda;
    private EditText et_pwd;
    private RelativeLayout rl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pwd);
        Zlzapplication.addActivity(this);
        init();
    }

    private void init() {
        ivback = findViewById(R.id.iv_cpwd_back);
        et_pwda = findViewById(R.id.et_newpwdagin);
        et_pwd = findViewById(R.id.et_pwd);
        rl = findViewById(R.id.bt_ok);

        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
           rl.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   if (TextUtils.isEmpty(et_pwd.getText().toString())||TextUtils.isEmpty(et_pwda.getText().toString())){

                       ToastUtil.show("新密码不能为空");
                   }else if(!et_pwd.getText().toString().equals(et_pwda.getText().toString())){

                       ToastUtil.show("两次输入密码不一样");
                   }else {

                       ChangePwd();

                   }
               }
           });
    }

    private void ChangePwd() {

        String str=GetSign.getSign();
        String[] strs=str.split(",");


        OkHttpUtils.post()
                .url(Constants.BASE_URL +"Api/Login/set_new_password")
                .addParams("t",strs[0])
                .addParams("r",strs[1])
                .addParams("e",strs[2])
                .addParams("password",et_pwd.getText().toString())
                .addParams("mobile",getIntent().getStringExtra("phone"))

                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        DebugFlags.logD("修改密码"+response);
                        try {
                            JSONObject json=new JSONObject(response);
                            if (json.getBoolean("status")){

                                startActivity(new Intent(ChangePwdActivity.this,LoginActivity.class));
                                finish();
                            }

                            ToastUtil.show(json.getString("message"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });



    }
}
