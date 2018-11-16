package ui.zlz.activity.account.updateinfo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import ui.zlz.R;
import ui.zlz.activity.Constants;
import ui.zlz.utility.DebugFlags;
import ui.zlz.utility.GetSign;
import ui.zlz.utility.SharedPrefUtil;
import ui.zlz.utility.ToastUtil;

public class BindphoneActivity extends Activity {
    private TextView btnGetcode;
    private TimeCount time;
    private EditText etphone;
    private EditText etcode;
    private EditText etpwd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bind_phone);
        init();
    }

    private void init() {
        time = new TimeCount(4000, 1000);
        btnGetcode=     findViewById(R.id.btn_getcode);
        etphone = findViewById(R.id.et_xgaccount);
        etcode = findViewById(R.id.et_xgyzm);
        ImageView ivback=findViewById(R.id.iv_xgsj_back);
        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        etpwd = findViewById(R.id.et_xgpassword);
        Button bt=findViewById(R.id.btn_xgsj);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BindNewphone();

            }
        });

        btnGetcode.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //点击获取验证码


                if (TextUtils.isEmpty(etphone.getText().toString())){
                    ToastUtil.show("手机号码不能为空");
                    time.start();
                    return;
                }else {
                    getCode();


                    time.start();
                }
            }
        });
    }
//绑定手机
private void BindNewphone() {
    String str=GetSign.getSign();
    String[] strs=str.split(",");


    OkHttpUtils.post()
            .url(Constants.BASE_URL +"Api/User/edit_user_mobile")
            .addParams("t",strs[0])
            .addParams("r",strs[1])
            .addParams("e",strs[2])
            .addParams("mobile",etphone.getText().toString())
            .addParams("code",etcode.getText().toString())
         .addParams("token",SharedPrefUtil.getString(BindphoneActivity.this,"token",""))
            .addParams("password",etpwd.getText().toString())

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
    private void getCode() {
        String str=GetSign.getSign();
        String[] strs=str.split(",");


        OkHttpUtils.post()
                .url(Constants.BASE_URL +"Api/Login/get_msg_code")
                .addParams("t",strs[0])
                .addParams("r",strs[1])
                .addParams("e",strs[2])
                .addParams("mobile",etphone.getText().toString())

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
}
