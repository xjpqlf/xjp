package ui.zlz.activity.account;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.Call;
import ui.zlz.R;
import ui.zlz.activity.Constants;
import ui.zlz.activity.MainActivity;
import ui.zlz.bean.LoginBean;
import ui.zlz.bean.QLoginBean;
import ui.zlz.bean.WebBean;
import ui.zlz.utility.DebugFlags;
import ui.zlz.utility.GetSign;
import ui.zlz.utility.SharedPrefUtil;
import ui.zlz.utility.ToastUtil;

public class LoginActivity extends Activity implements View.OnClickListener {
    private SsoHandler mSsoHandler;
    private Oauth2AccessToken mAccessToken;
    private TextView phone;
    private TextView pwd;
    private Tencent mTencent;
    private String openidString;
    private String acctoken;
    private MyReceiver mycodereceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Zlzapplication.addActivity(this);

          init();
        initWeiBoSDK();
        mSsoHandler = new SsoHandler(LoginActivity.this);
    }




    private void init() {

        //将123123123改为自己的
        mTencent = Tencent.createInstance("1107959498",getApplicationContext());
        mycodereceiver = new MyReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.zlz.app.view.Login");
        registerReceiver(mycodereceiver, filter);
        phone = findViewById(R.id.et_phone);
        pwd = findViewById(R.id.et_pwd);
        ImageView qq=findViewById(R.id.iv_qqlogin);
        ImageView wx=findViewById(R.id.iv_wxlogin);
        ImageView wb=findViewById(R.id.iv_wblogin);
        TextView fw=findViewById(R.id.tv_fwxy);
        TextView ys_=findViewById(R.id.tv_yszc);
        qq.setOnClickListener(this);
        wx.setOnClickListener(this);
        wb.setOnClickListener(this);
        fw.setOnClickListener(this);
        ys_.setOnClickListener(this);


    }



    public void goForgetPassword(View view){

        Intent intent = new Intent();

        intent.setClass(this,ForgetPaswordActivity.class);

        this.startActivity(intent);
    }

    public void goUserCenter(View view){
        Tologin();


    }

    private void Tologin() {
        String str=GetSign.getSign();
        String[] strs=str.split(",");
        DebugFlags.logD("签名"+str);


        OkHttpUtils.post()

                .url(Constants.BASE_URL +"Api/Login/login")
                .addParams("t",strs[0])
                .addParams("r",strs[1])
                .addParams("e",strs[2])
                //   .addParams("mobile","13071266270")
                .addParams("mobile",phone.getText().toString())
                .addParams("password",pwd.getText().toString())

                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        DebugFlags.logD("密码登录"+response);
                        try {
                            JSONObject json=new JSONObject(response);

                            //InfoDo infoDo = JSON.parseObject(strInfoDo, InfoDo.class);
                           LoginBean user=JSON.parseObject(response,LoginBean.class);
                          /*  DebugFlags.logD("登录"+user.getMessage());
                            DebugFlags.logD("登录"+user.getData().getSession_token());*/




                            ToastUtil.show(json.getString("message"));

                            if (user.isStatus()){
                                String data=json.getString("data");
                                JSONObject js=new JSONObject(data);
                                String token=js.getString("session_token");
                                String ispwd=js.getString("ispay_pwd");
                                SharedPrefUtil.saveString(LoginActivity.this,"token",token);
                                SharedPrefUtil.saveString(LoginActivity.this,"ispay",ispwd);




                                Intent intent=new Intent(LoginActivity.this,MainActivity.class);


                                startActivity(intent);
                                Zlzapplication.clearActivity();

                            }





                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });


    }

    public void loginByYzm(View view){
        AndPermission.with(this)
                .runtime()
                .permission(Permission.READ_PHONE_STATE)

                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        // data.get(0);
                        Log.d("permission", data.get(0));
                        Intent intent = new Intent();

                        intent.setClass(LoginActivity.this,LoginByYzmActivity.class);

                       startActivity(intent);
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        /**
                         * 当用户没有允许该权限时，回调该方法
                         */
                        Toast.makeText(LoginActivity.this, "没有获取读取手机号码，该功能无法使用", Toast.LENGTH_SHORT).show();
                        /**
                         * 判断用户是否点击了禁止后不再询问，AndPermission.hasAlwaysDeniedPermission(MainActivity.this, data)
                         */
                        if (AndPermission.hasAlwaysDeniedPermission(LoginActivity.this, data)) {
                            //true，弹窗再次向用户索取权限

                        }
                    }
                }).start();


    }

    public  void  goReg(View v){
        Intent intent = new Intent();

        intent.setClass(this,Reg.class);

        this.startActivity(intent);

    }





    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //qq登录
            case R.id.iv_qqlogin:
                mTencent.login(LoginActivity.this,"all",new BaseUiListener());

                break;
                //微信登录
            case R.id.iv_wxlogin:
               wxLogin();

                break;
                //微博登录
            case R.id.iv_wblogin:
                loginIn();
                break;
            case R.id.tv_fwxy:
              //服务协议

                startActivity(new Intent(LoginActivity.this,ServiceAgreementActivity.class));

                break;
            case R.id.tv_yszc:
               //隐私政策
                startActivity(new Intent(LoginActivity.this,PrivacyPolicyActivity.class));
                break;
        }

    }

    private class BaseUiListener implements IUiListener {
        public void onComplete(Object response) {
            // TODO Auto-generated method stub
     // Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
            /*
             * 下面隐藏的是用户登录成功后 登录用户数据的获取的方法
             * 共分为两种  一种是简单的信息的获取,另一种是通过UserInfo类获取用户较为详细的信息
             *有需要看看
             * */
            try {
                //获得的数据是JSON格式的，获得你想获得的内容
                //如果你不知道你能获得什么，看一下下面的LOG
                Log.v("----TAG--", "-------------"+response.toString());
                openidString = ((JSONObject) response).getString("openid");
                acctoken = ((JSONObject) response).getString("access_token");

                mTencent.setOpenId(openidString);

                mTencent.setAccessToken(((JSONObject) response).getString("access_token"),((JSONObject) response).getString("expires_in"));
                    go2Login(openidString,acctoken);

                Log.v("TAG", "--登录opeid-----------"+openidString);
                //access_token= ((JSONObject) response).getString("access_token");
                // expires_in = ((JSONObject) response).getString("expires_in");
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            /**到此已经获得OpneID以及其他你想获得的内容了
             QQ登录成功了，我们还想获取一些QQ的基本信息，比如昵称，头像什么的，这个时候怎么办？
             sdk给我们提供了一个类UserInfo，这个类中封装了QQ用户的一些信息，我么可以通过这个类拿到这些信息
             如何得到这个UserInfo类呢？  *//*

            QQToken qqToken = mTencent.getQQToken();
            UserInfo info = new UserInfo(getApplicationContext(), qqToken);

            //    info.getUserInfo(new BaseUIListener(this,"get_simple_userinfo"));
            info.getUserInfo(new IUiListener() {
                @Override
                public void onComplete(Object o) {
                    //用户信息获取到了

                    try {

                        Toast.makeText(getApplicationContext(), ((JSONObject) o).getString("nickname")+((JSONObject) o).getString("gender"), Toast.LENGTH_SHORT).show();
                        Log.v("UserInfo",o.toString());
                        Intent intent1 = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent1);
                        finish();
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

                @Override
                public void onError(UiError uiError) {
                    Log.v("UserInfo","onError");
                }

                @Override
                public void onCancel() {
                    Log.v("UserInfo","onCancel");
                }
            });*/

        }

        @Override
        public void onError(UiError uiError) {
            Toast.makeText(getApplicationContext(), "onError", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel() {
            Toast.makeText(getApplicationContext(), "onCancel", Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //新浪
        if(mSsoHandler!=null){
            mSsoHandler.authorizeCallBack(requestCode,resultCode,data);
        }
        //qq回调
        Tencent.onActivityResultData(requestCode, resultCode, data, new BaseUiListener());

        if(requestCode == com.tencent.connect.common.Constants.REQUEST_API) {
            if(resultCode == com.tencent.connect.common.Constants.REQUEST_LOGIN) {
                Tencent.handleResultData(data, new BaseUiListener());
            }
        }
    }

    private void wxLogin() {
        if (!Zlzapplication.mWxApi.isWXAppInstalled()) {
            ToastUtil.show("您还未安装微信客户端");
            return;
        }
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "diandi_wx_login";
        Zlzapplication.mWxApi.sendReq(req);

    }

    private void initWeiBoSDK() {
        WbSdk.install(this, new AuthInfo(this, Constants.SINA_APP_KEY,
                "https://api.weibo.com/oauth2/default.html",
                "email,direct_messages_read,direct_messages_write," +
                        "friendships_groups_read,friendships_groups_write,statuses_to_me_read," +
                        "follow_app_official_microblog," + "invitation_write"));




    }
    //微博登录
    private void loginIn() {
        mSsoHandler.authorize(new SelfWbAuthListener());
    }


    private class SelfWbAuthListener implements com.sina.weibo.sdk.auth.WbAuthListener {


        @Override
        public void onSuccess(final Oauth2AccessToken oauth2AccessToken) {
            runOnUiThread(new Runnable() {
                @Override public void run()
                {
                    mAccessToken = oauth2AccessToken;
                if (mAccessToken.isSessionValid()) {
                    // 显示 Token
                    //                        updateTokenView(false);
                    // 保存 Token 到 SharedPreferences
                    //                        AccessTokenKeeper.writeAccessToken(LoginActivity.this, mAccessToken);
                   Log.i("获取mAccessToken成功","获取mAccessToken成功"+mAccessToken);
                  Log.i("微博为", "=="+mAccessToken.getToken()+"");

                      go2weblogin(mAccessToken.getToken());



                    } }

            });
                }

        @Override
        public void cancel() {
            Log.i("取消授权","取消授权");
        }

        @Override
        public void onFailure(WbConnectErrorMessage wbConnectErrorMessage) {
            Log.i("授权失败","授权失败");
        }
    }
//微博登录
    private void go2weblogin(String s) {
        String str=GetSign.getSign();
        String[] strs=str.split(",");
        DebugFlags.logD("签名"+str);


        OkHttpUtils.post()

                .url(Constants.BASE_URL +"/Api/Login/wblogin")
                .addParams("t",strs[0])
                .addParams("r",strs[1])
                .addParams("e",strs[2])
                //   .addParams("mobile","13071266270")
                .addParams("token",SharedPrefUtil.getString(LoginActivity.this,"token",""))
                .addParams("access_token",s)





                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        DebugFlags.logD("微博"+response);
               WebBean wb=       JSON.parseObject(response,WebBean.class);
               if (wb.getCode()==1){

                   SharedPrefUtil.saveString(LoginActivity.this,"token",wb.getData().getSession_token());
                   SharedPrefUtil.saveString(LoginActivity.this,"nickname",wb.getData().getData().getNickname());
                   SharedPrefUtil.saveString(LoginActivity.this,"headerimg",wb.getData().getData().getHeader());
                   SharedPrefUtil.saveString(LoginActivity.this,"ispay",wb.getData().getIspay_pwd()+"");
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    finish();

               }else {
                   ToastUtil.show(wb.getMessage());
               }

                    }
                });



    }


    //qq拿到第三方opendid进行登录
    private void go2Login(String openid,String atoken) {
        String str=GetSign.getSign();
        String[] strs=str.split(",");
        DebugFlags.logD("签名"+str);


        OkHttpUtils.post()

                .url(Constants.BASE_URL +"Api/Login/qqlogin")
                .addParams("t",strs[0])
                .addParams("r",strs[1])
                .addParams("e",strs[2])
                //   .addParams("mobile","13071266270")
                .addParams("token",SharedPrefUtil.getString(LoginActivity.this,"token",""))
          .addParams("access_token",atoken)
          .addParams("openid",openid)




                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        DebugFlags.logD("qq登录"+response);
                     QLoginBean ql=   JSON.parseObject(response,QLoginBean.class);
                     if (1==ql.getCode()){
                         SharedPrefUtil.saveString(LoginActivity.this,"token",ql.getData().getSession_token());
                         SharedPrefUtil.saveString(LoginActivity.this,"nicename",ql.getData().getData().getNickname());
                         SharedPrefUtil.saveString(LoginActivity.this,"ispay",ql.getData().getIspay_pwd()+"");

                      startActivity(new Intent(LoginActivity.this,MainActivity.class));
                      finish();
                    //  Zlzapplication.clearActivity();
                     }else {
                         ToastUtil.show(ql.getMessage());
                     }


                    }
                });

    }



    //微信广播
    /**
     * 获取广播数据
     *
     *
     */
    public class MyReceiver extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            boolean userConfirm = intent.getBooleanExtra("userConfirm", false);

                // 用户确认授权登陆微信

                String code=intent.getStringExtra("code");

                go2wxLogin(code);
                //   Toast.makeText(LoginActivity.this,"我的微信信息"+"openid=="+openid+"uid=="+uid, Toast.LENGTH_LONG).show();





        }

    }

    //qq拿到第三方opendid进行登录
    private void go2wxLogin(String code) {
        String str=GetSign.getSign();
        String[] strs=str.split(",");
        DebugFlags.logD("签名"+str);


        OkHttpUtils.post()

                .url(Constants.BASE_URL +"/Api/Login/wxlogin")
                .addParams("t",strs[0])
                .addParams("r",strs[1])
                .addParams("e",strs[2])
                //   .addParams("mobile","13071266270")
                .addParams("token",SharedPrefUtil.getString(LoginActivity.this,"token",""))
                .addParams("code",code)





                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        DebugFlags.logD("微信登录"+response);
                        QLoginBean ql=   JSON.parseObject(response,QLoginBean.class);
                        if (1==ql.getCode()){
                            SharedPrefUtil.saveString(LoginActivity.this,"token",ql.getData().getSession_token());
                            SharedPrefUtil.saveString(LoginActivity.this,"ispay",ql.getData().getIspay_pwd()+"");
                            SharedPrefUtil.saveString(LoginActivity.this,"nicename",ql.getData().getData().getNickname());

                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                            finish();
                           // Zlzapplication.clearActivity();
                        }else {
                            //   ToastUtil.show(ql.getMessage());
                        }


                    }
                });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mycodereceiver!=null){
          unregisterReceiver(mycodereceiver);
        }
    }








}



