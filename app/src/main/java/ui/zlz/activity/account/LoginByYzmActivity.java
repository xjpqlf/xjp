package ui.zlz.activity.account;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import ui.zlz.activity.Constants;
import ui.zlz.R;
import ui.zlz.activity.MainActivity;
import ui.zlz.bean.UserBean;
import ui.zlz.utility.DebugFlags;
import ui.zlz.utility.GetSign;
import ui.zlz.utility.SharedPrefUtil;
import ui.zlz.utility.ToastUtil;


public class LoginByYzmActivity extends Activity {
    protected String[] needPermissions = {

            Manifest.permission.READ_PHONE_STATE//读取手机状态权限
    };
    private static final int PERMISSON_REQUESTCODE = 0;
    /**
     * 判断是否需要检测，防止不停的弹框
     */
    private boolean isNeedCheck = true;
    private TextView tvphone;
    private EditText etyzm;
    private TextView tvgetcode;
    private Button btlogin;
    private TextView tvts;
    private TimeCount time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_by_yzm);
        Zlzapplication.addActivity(this);
        init();
    }

    private void init() {
        TelephonyManager tm = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        tvphone = findViewById(R.id.tv_phone_yzm);
        tvts = findViewById(R.id.tv_yzmts);
        etyzm = findViewById(R.id.et_logyzm);
        tvgetcode = findViewById(R.id.btn_getyzmcode);
        btlogin = findViewById(R.id.bt_loginby_yzm);
        ImageView iv=findViewById(R.id.iv_byyzm_back);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        String te1 = tm.getLine1Number();//获取本机号码
        tvphone.setText(te1);

       tvgetcode.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //点击获取验证码
               time.start();

                getCode();
            }


        });


        time = new TimeCount(4000, 1000);

        btlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(etyzm.getText().toString())){
                 ToastUtil.show("请输入验证码");
                 return;
                }else {

                    Tologin();

                }
            }
        });


    }
//登录
    private void Tologin() {
        String str=GetSign.getSign();
        String[] strs=str.split(",");


        OkHttpUtils.post()

                .url(Constants.BASE_URL +"Api/Login/code_login")
                .addParams("t",strs[0])
                .addParams("r",strs[1])
                .addParams("e",strs[2])
                //   .addParams("mobile","13071266270")
               .addParams("mobile",tvphone.getText().toString().substring(3,tvphone.length()))
                .addParams("code",etyzm.getText().toString())

                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        DebugFlags.logD("验证码登录1"+response);
                        try {
                            JSONObject json=new JSONObject(response);

                                //InfoDo infoDo = JSON.parseObject(strInfoDo, InfoDo.class);
                                UserBean user=JSON.parseObject(response,UserBean.class);
                                DebugFlags.logD("验证码登录"+user.getData().getSession_token());


                                ToastUtil.show(json.getString("message"));

                                 if (user.getCode()==1){
                                     SharedPrefUtil.saveString(LoginByYzmActivity.this,"token",user.getData().getSession_token());
                                     SharedPrefUtil.saveString(LoginByYzmActivity.this,"ispay",user.getData().getIspay_pwd()+"");
                                     Intent intent=new Intent(LoginByYzmActivity.this,MainActivity.class);
                                     intent.putExtra("userInfo",user);

                                     startActivity(intent);
                                     Zlzapplication.clearActivity();

                                 }else {

                                     ToastUtil.show(user.getMessage());
                                 }





                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });


    }

    //获取验证码
private void getCode() {
    String str=GetSign.getSign();
    String[] strs=str.split(",");


    OkHttpUtils.post()

            .url(Constants.BASE_URL +"Api/Login/get_msg_code")
            .addParams("t",strs[0])
            .addParams("r",strs[1])
            .addParams("e",strs[2])
         .addParams("mobile",tvphone.getText().toString().substring(3,tvphone.length()))
            //    .addParams("mobile","13071266270")


            .build()
            .execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {

                }

                @Override
                public void onResponse(String response, int id) {
                    DebugFlags.logD("验证码登录获取验证"+response);

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
         //  tvgetcode.setBackgroundColor(Color.parseColor("#fafafa"));
            tvgetcode.setClickable(false);
            tvgetcode.setText(millisUntilFinished / 1000 +" 秒后可重新发送");
        }

        @Override
        public void onFinish() {
            tvgetcode.setText("重新获取验证码");
            tvgetcode.setClickable(true);
       //     tvgetcode.setBackgroundColor(Color.parseColor("#fafafa"));

        }
    }
    /**
     * 获取权限集中需要申请权限的列表
     *
     * @param permissions
     * @return
     * @since 2.5.0
     */
    private List<String> findDeniedPermissions(String[] permissions) {
        List<String> needRequestPermissonList = new ArrayList<String>();
        for (String perm : permissions) {
            if (ContextCompat.checkSelfPermission(this,
                    perm) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                    this, perm)) {
                needRequestPermissonList.add(perm);
            }
        }
        return needRequestPermissonList;
    }

    /**
     * 检查权限
     */
    private void checkPermissions(String... permissions) {
        List<String> needRequestPermissonList = findDeniedPermissions(permissions);
        if (null != needRequestPermissonList
                && needRequestPermissonList.size() > 0) {
            ActivityCompat.requestPermissions(this,
                    needRequestPermissonList.toArray(
                            new String[needRequestPermissonList.size()]),
                    PERMISSON_REQUESTCODE);
        }
    }

    /**
     * 检测是否有的权限都已经授权
     *
     * @param grantResults
     */
    private boolean verifyPermissions(int[] grantResults) {
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] paramArrayOfInt) {
        if (requestCode == PERMISSON_REQUESTCODE) {
            if (!verifyPermissions(paramArrayOfInt)) {
                showMissingPermissionDialog();
                isNeedCheck = false;
            }
        }
    }
    /**
     * 弹出对话框, 提示用户手动授权
     *
     */
    private void showMissingPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.notifyTitle);
        builder.setMessage(R.string.notifyMsg);
        // 拒绝授权 退出应用
        builder.setNegativeButton(R.string.cancel,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        //同意授权
        builder.setPositiveButton(R.string.setting,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startAppSettings();
                    }
                });
        builder.setCancelable(false);
        builder.show();
    }
    /**
     * 启动应用的设置
     *
     */
    private void startAppSettings() {
        Intent intent = new Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (isNeedCheck) {
            checkPermissions(needPermissions);
        }
    }

}



