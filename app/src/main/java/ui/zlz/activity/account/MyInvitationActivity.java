package ui.zlz.activity.account;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tencent.connect.share.QQShare;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import ui.zlz.R;
import ui.zlz.RewritePopwindow;
import ui.zlz.activity.Constants;
import ui.zlz.adapter.TabFragmentAdapter;
import ui.zlz.fragment.AwardFragment;
import ui.zlz.fragment.FriendFragment;
import ui.zlz.utility.DebugFlags;
import ui.zlz.utility.GetSign;
import ui.zlz.utility.SharedPrefUtil;
import ui.zlz.utility.ToastUtil;

public class MyInvitationActivity extends AppCompatActivity implements View.OnClickListener {
    TabLayout tabLayout;
    ViewPager viewPager;
    private List<String> tabList;
    private List<Fragment> fragmentList;
    private RewritePopwindow mPopwindow;
    private String usernum;
    private String jf;
    private String snum;
    private TextView yqcs;
    private TextView jf1;
    private TextView yqrs;
    private View mPopView;
    private Tencent mTencent;
    private PopupWindow mPopWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_invitation);
        mTencent = Tencent.createInstance("1107959498",getApplicationContext());
      init();
    }
    private void init() {

        tabLayout = findViewById(R.id.tl_ep);
        viewPager = findViewById(R.id.vp_ep);
        ImageView ivback=findViewById(R.id.iv_yqback);
        yqcs = findViewById(R.id.tv_yqcs);
        jf1 = findViewById(R.id.tv_yqjf);
        yqrs = findViewById(R.id.tv_yqrs);
        TextView erm=findViewById(R.id.tv_ewmyq);
        TextView share=findViewById(R.id.share);

        ivback.setOnClickListener(this);
        erm.setOnClickListener(this);
        share.setOnClickListener(this);

        initData();



    }



    private void initContent() {

        tabList = new ArrayList<>();
        tabList.add("奖励明细");
        tabList.add("好友"+"{"+usernum+"}");

        tabLayout.addTab(tabLayout.newTab().setText(tabList.get(0)));//添加tab选项卡
        tabLayout.addTab(tabLayout.newTab().setText(tabList.get(1)));

        tabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
        //此处代码设置无效，不知道为啥？？？xml属性是可以的
        //tabLayout.setTabTextColors(android.R.color.white, android.R.color.holo_red_dark);
        // 设置TabLayout两种状态

        fragmentList = new ArrayList<>();
        Fragment f1 = new AwardFragment();
        Fragment f2 = new FriendFragment();

        fragmentList.add(f1);
        fragmentList.add(f2);

        TabFragmentAdapter fragmentAdapter = new TabFragmentAdapter(getSupportFragmentManager(), fragmentList, tabList);
        viewPager.setAdapter(fragmentAdapter);//给ViewPager设置适配器
        tabLayout.setupWithViewPager(viewPager);//将TabLayout和ViewPager关联起来。
        tabLayout.setTabsFromPagerAdapter(fragmentAdapter);//给Tabs设置适配器

    }









    private void initData() {
        String str=GetSign.getSign();
        String[] strs=str.split(",");
        DebugFlags.logD("签名"+str);


        OkHttpUtils.post()

                .url(Constants.BASE_URL +"/Api/Share/share_data")
                .addParams("t",strs[0])
                .addParams("r",strs[1])
                .addParams("e",strs[2])
                //   .addParams("mobile","13071266270")
                .addParams("token",SharedPrefUtil.getString(MyInvitationActivity.this,"token",""))
                .addParams("page",1+"")


                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        DebugFlags.logD("邀请"+response);
                        try {
                            JSONObject js=new JSONObject(response);
                            if ("1".equals(js.getString("code"))){
                                JSONObject js1=new JSONObject(js.getString("data"));

                                snum = js1.getString("share_num");
                                jf = js1.getString("share_integral");
                                usernum = js1.getString("share_user_num");
                                yqcs.setText(snum);
                                jf1.setText(jf);
                                yqrs.setText(usernum);
                                initContent();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.iv_yqback:
                finish();
                break;
            case R.id.tv_ewmyq:
                getCode();

                break;
            case R.id.share:

                mPopwindow = new RewritePopwindow(MyInvitationActivity.this, itemsOnClick);
                mPopwindow.showAtLocation(view,
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);


                break;
        }

    }

    private void shareqq() {

        final Bundle params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);

        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        params.putString(QQShare.SHARE_TO_QQ_TITLE, "要分享的标题");
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY, "要分享的摘要");
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, "http://www.qq.com/news/1.html");
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL,"http://imgcache.qq.com/qzone/space_item/pre/0/66768.gif");
        params.putString(QQShare.SHARE_TO_QQ_APP_NAME, "租来赚");


        mTencent.shareToQQ(MyInvitationActivity.this, params, new BaseUiListener());
    }


    private void getCode() {
        String str=GetSign.getSign();
        String[] strs=str.split(",");
        DebugFlags.logD("签名"+str);


        OkHttpUtils.post()

                .url(Constants.BASE_URL +"/Api/Share/share/")
                .addParams("t",strs[0])
                .addParams("r",strs[1])
                .addParams("e",strs[2])
                //   .addParams("mobile","13071266270")
                .addParams("token",SharedPrefUtil.getString(MyInvitationActivity.this,"token",""))




                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        DebugFlags.logD("二维码"+response);
                        try {
                            JSONObject js=new JSONObject(response);
                            if ("1".equals(js.getString("code"))){
                                JSONObject js1=new JSONObject(js.getString("data"));
                                String code=js1.getString("code");
                                String url=js1.getString("qrcode_url");
                                showPop(code,url);


                            }else {
                                showPop("","");
                                ToastUtil.show(js.getString("message"));
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }




                    }
                });
    }

    private void showPop(String code, String url) {

        View contentView = LayoutInflater.from(MyInvitationActivity.this).inflate(R.layout.popwindow_myinvi, null);
        mPopWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopWindow.setContentView(contentView);
        TextView tvcode=contentView.findViewById(R.id.tv_yqcode);
        tvcode.setText("邀请码  "+code);
        ImageView iv= contentView.findViewById(R.id.iv_code);
        ImageView iv1= contentView.findViewById(R.id.iv_canle);
        Glide.with(MyInvitationActivity.this).load(url).fitCenter().into(iv);
        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopWindow.dismiss();
            }
        });

        setBackgroundAlpha(0.3f);
        // mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        View rootViews = findViewById(R.id.rl_invi);

        //显示PopupWindow
        View rootview = LayoutInflater.from(MyInvitationActivity.this).inflate(R.layout.activity_my_invitation, null);
        //   mPopWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
        mPopWindow.showAtLocation(rootview, Gravity.CENTER, 0, 30);
        mPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() { // popupWindow隐藏时恢复屏幕正常透明度

                setBackgroundAlpha(1.0f);
            }
        });






    }


    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = (MyInvitationActivity.this.getWindow().getAttributes());
        lp.alpha = bgAlpha;
        MyInvitationActivity.this.getWindow().setAttributes(lp);
    }



    private class BaseUiListener implements IUiListener {
        @Override
        public void onComplete(Object response) {
            ToastUtil.show("分享成功");


        }

        @Override
        public void onError(UiError uiError) {

        }

        @Override
        public void onCancel() {

        }
    }


    /**
     * 回调
     * @param requestCode
     * @param resultCode
     * @param data
     */ @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
         if (null != mTencent) {
             mTencent.onActivityResult(requestCode, resultCode, data); }
     }


    private void shareWx(int i) {


         //分享到朋友圈
        Bitmap bp = BitmapFactory.decodeResource(getResources(),
                R.mipmap.ic_launcher);
        WXImageObject wxImageObject = new WXImageObject(bp);
        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = wxImageObject;
        //设置缩略图
        Bitmap mBp = Bitmap.createScaledBitmap(bp, 120, 120, true);
        bp.recycle();
        msg.thumbData = bmpToByteArray(mBp, true);
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("img");//  transaction字段用
        req.message = msg;
        if (1==i){
        req.scene = SendMessageToWX.Req.WXSceneTimeline;
        }else if (2==i){

            req.scene = SendMessageToWX.Req.WXSceneSession;
        }
        Zlzapplication.mWxApi.sendReq(req);


    }
    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) { ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
        if (needRecycle) { bmp.recycle();
        } byte[] result = output.toByteArray();
        try { output.close();
        } catch (Exception e) { e.printStackTrace();
        } return result;
    }
    private String buildTransaction(final String type) { return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }



    //为弹出窗口实现监听类
    private View.OnClickListener itemsOnClick = new View.OnClickListener() {

        public void onClick(View v) {
            mPopwindow.dismiss();
            mPopwindow.backgroundAlpha(MyInvitationActivity.this, 1f);
            switch (v.getId()) {
                case R.id.weixinghaoyou:
                  shareWx(2);
                    break;
                case R.id.pengyouquan:
                   shareWx(1);
                    break;
                case R.id.qqhaoyou:
                 shareqq();
                    break;
                case R.id.qqkongjian:
                 shareqq();
                    break;
                default:
                    break;
            }
        }

    };


}
