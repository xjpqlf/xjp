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
import android.widget.ImageView;
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

public class ForgetPaswordActivity extends Activity {
    private TimeCount time;
    private TextView btnGetcode;
    private EditText etphone;
    private EditText etyzm;
    private ImageView ivback;
    private Button btnext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pasword);
        Zlzapplication.addActivity(this);
              init();
    }
    private void init() {
        btnGetcode=(TextView) findViewById(R.id.btn_getcodes);
        etphone = findViewById(R.id.et_forget_phone);
        etyzm = findViewById(R.id.et_forget_yzm);
        ivback = findViewById(R.id.iv_forget_back);
        btnext = findViewById(R.id.bt_forget_next);
        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnGetcode.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(etphone.getText().toString())){
                    ToastUtil.show("手机号码不能为空");
                    return;
                }
                time.start();
                getCode();
            }
        });


        time = new TimeCount(3000, 1000);

        btnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Next();
            }
        });






    }
//点击下一步后找回密码
    private void Next() {
        String str=GetSign.getSign();
        String[] strs=str.split(",");


        OkHttpUtils.post()
                .url(Constants.BASE_URL +"Api/Login/retrieve_password")
                .addParams("t",strs[0])
                .addParams("r",strs[1])
                .addParams("e",strs[2])
                .addParams("mobile",etphone.getText().toString())
                .addParams("code",etyzm.getText().toString())

                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        DebugFlags.logD(e.toString());

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            JSONObject json=new JSONObject(response);

                            if (json.getBoolean("status")){

                                Intent intent=new Intent(ForgetPaswordActivity.this,ChangePwdActivity.class);
                                intent.putExtra("phone",etphone.getText().toString());
                                startActivity(intent);

                            }else{

                                ToastUtil.show(json.getString("message"));
                            }
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
