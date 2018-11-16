package ui.zlz.activity.account.updateinfo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.jungly.gridpasswordview.GridPasswordView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import ui.zlz.R;
import ui.zlz.activity.Constants;
import ui.zlz.activity.account.Zlzapplication;
import ui.zlz.utility.DebugFlags;
import ui.zlz.utility.GetSign;
import ui.zlz.utility.SharedPrefUtil;
import ui.zlz.utility.ToastUtil;

public class InputPwdActivity extends Activity {

    private GridPasswordView gpv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_paying_password);
        Zlzapplication.addActivity(this);
        init();
    }

    private void init() {
        gpv = findViewById(R.id.pswView);
        ImageView imageView=findViewById(R.id.iv_ipp_back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        gpv.setOnPasswordChangedListener(new GridPasswordView.OnPasswordChangedListener() {
            @Override
            public void onTextChanged(String psw) {

            }

            @Override
            public void onInputFinish(String psw) {
                //输入完成就开始设置密码
                SetPwd();

            }
        });


    }

    private void SetPwd() {
        String str=GetSign.getSign();
        String[] strs=str.split(",");
        DebugFlags.logD("签名"+str);


        OkHttpUtils.post()

                .url(Constants.BASE_URL +"Api/User/set_pay_pwd")
                .addParams("t",strs[0])
                .addParams("r",strs[1])
                .addParams("e",strs[2])
                //   .addParams("mobile","13071266270")
                .addParams("token",SharedPrefUtil.getString(InputPwdActivity.this,"token",""))
                .addParams("pay_pwd",gpv.getPassWord())


                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        DebugFlags.logD("设置支付密码"+response);
                        try {
                            JSONObject js=new JSONObject(response);

                            if ("1".equals(js.getString("code"))){
                                ToastUtil.show(js.getString("message"));
                                SharedPrefUtil.saveString(InputPwdActivity.this,"ispay","1");
                                finish();

                            }else {
                                ToastUtil.show(js.getString("message"));

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
    }
}
