package ui.zlz.activity.account;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

public class Reg extends Activity {
    private TimeCount time;
    private TextView btnGetcode;
    private Button btnlogin;
    private EditText etaccount;
    private EditText code;
    private EditText pwd;
    private String acc;
    private String pwds;
    private String codes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        Zlzapplication.addActivity(this);
        init();
    }




    private void init() {
        btnGetcode= findViewById(R.id.btn_getcode);
        btnlogin =  findViewById(R.id.btn_reg_login);
        etaccount =findViewById(R.id.et_account);
        code =findViewById(R.id.et_yzm);
        pwd = findViewById(R.id.et_password);
        codes = code.getText().toString();
        pwds = pwd.getText().toString();
        acc = etaccount.getText().toString();



        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(etaccount.getText().toString())){
                    ToastUtil.show("手机号码不能为空");
                    return;
                }
                if (TextUtils.isEmpty( code.getText().toString())){
                    ToastUtil.show("验证码不能为空");
                    return;
                }
                if (TextUtils.isEmpty( pwd.getText().toString())){
                    ToastUtil.show("密码不能为空");
                    return;
                }
                Register();



            }
        });



        btnGetcode.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //点击获取验证码


                if (TextUtils.isEmpty(etaccount.getText().toString())){
                    ToastUtil.show("手机号码不能为空");
                    time.start();
                }else {
                    getCode();


                    time.start();
                }
            }
        });


        time = new TimeCount(4000, 1000);


    }
//注册登录
    private void Register() {
        String str=GetSign.getSign();
        String[] strs=str.split(",");

        OkHttpUtils.post()
                .url(Constants.BASE_URL +"Api/Login/register")
                .addParams("t",strs[0])
                .addParams("r",strs[1])
                .addParams("e",strs[2])
                .addParams("mobile",etaccount.getText().toString())
                .addParams("code",code.getText().toString())
                .addParams("type","register")

                .addParams("password",pwd.getText().toString())

                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        DebugFlags.logD("注册"+response);
                        try {
                            JSONObject json=new JSONObject(response);
                            if (json.getString("code").equals("1")){
                                finish();
                            }
                            ToastUtil.show(json.getString("message"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });



    }

    private void getCode() {
        String str=GetSign.getSign();
        String[] strs=str.split(",");


        OkHttpUtils.post()
                .url(Constants.BASE_URL +"Api/Login/get_msg_code")
                .addParams("t",strs[0])
                .addParams("r",strs[1])
                .addParams("e",strs[2])
                .addParams("mobile",etaccount.getText().toString())

                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        DebugFlags.logD("获取验证"+response);
                        try {
                            JSONObject json=new JSONObject(response);
                            if ("200".equals(json.get("code"))){


                            }
                            else {
                                ToastUtil.show(json.getString("message"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });


    }


    public void goLogin(View view){

        Intent intent = new Intent();

        intent.setClass(this,LoginActivity.class);

        this.startActivity(intent);
    }
    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            btnGetcode.setBackgroundColor(Color.parseColor("#fafafa"));
            btnGetcode.setClickable(false);
            btnGetcode.setText(millisUntilFinished / 1000 +" 秒后可重新发送");
        }

        @Override
        public void onFinish() {
            btnGetcode.setText("重新获取验证码");
            btnGetcode.setClickable(true);
            btnGetcode.setBackgroundColor(Color.parseColor("#fafafa"));

        }
    }

}
