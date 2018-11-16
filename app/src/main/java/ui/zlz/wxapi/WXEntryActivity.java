package ui.zlz.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import ui.zlz.activity.account.Zlzapplication;
import ui.zlz.utility.DebugFlags;
import ui.zlz.utility.ToastUtil;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    private static final int RETURN_MSG_TYPE_LOGIN = 1;
    private static final int RETURN_MSG_TYPE_SHARE = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //如果没回调onResp，八成是这句没有写
        Zlzapplication.mWxApi.handleIntent(getIntent(), this);
    }

    // 微信发送请求到第三方应用时，会回调到该方法
    @Override
    public void onReq(BaseReq req) {
    }

    // 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
    //app发送消息给微信，处理返回消息的回调
    @Override
    public void onResp(BaseResp resp) {
      //  ToastUtil.show(resp.errStr);

        switch (resp.errCode) {

            case BaseResp.ErrCode.ERR_AUTH_DENIED:
            case BaseResp.ErrCode.ERR_USER_CANCEL:



                if (RETURN_MSG_TYPE_SHARE == resp.getType())  ToastUtil.show("分享失败");
                else  ToastUtil.show("登录失败");



                break;
            case BaseResp.ErrCode.ERR_OK:
                switch (resp.getType()) {
                    case RETURN_MSG_TYPE_LOGIN:
                        //拿到了微信返回的code,立马再去请求access_token
                        String code = ((SendAuth.Resp) resp).code;
                        DebugFlags.logD("code微信 = " + code);

                        Intent intent1 = new Intent(
                                "com.zlz.app.view.Login");
                        intent1.putExtra("code", code);
                        sendBroadcast(intent1);
                        finish();


                        //就在这个地方，用网络库什么的或者自己封的网络api，发请求去咯，注意是get请求



                        break;

                    case RETURN_MSG_TYPE_SHARE:
                        ToastUtil.show("微信分享成功");
                        finish();
                        break;
                }
                break;
        }
    }






    /**
     * 获取微信的个人信息
     * @param access_token
     * @param openid
     */
    private void getUserMesg(final String access_token, final String openid) {
        String path = "https://api.weixin.qq.com/sns/userinfo?access_token="
                + access_token
                + "&openid="
                + openid;
        DebugFlags.logD("getUserMesg：" + path);
        OkHttpUtils.get()
                .url(path).build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            String nickname = jsonObject.getString("nickname");
                            int sex = Integer.parseInt(jsonObject.get("sex").toString());
                            String headimgurl = jsonObject.getString("headimgurl");

                            DebugFlags.logD("用户基本信息:");
                            DebugFlags.logD("nickname:" + nickname);
                            DebugFlags.logD("sex:" + sex);
                            DebugFlags.logD("headimgurl:" + headimgurl);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                      //  finish();
                    }
                });



    }

}

