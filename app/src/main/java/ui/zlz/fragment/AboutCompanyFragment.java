package ui.zlz.fragment;

import android.content.Intent;
import android.view.View;
import android.webkit.WebView;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import ui.zlz.R;
import ui.zlz.activity.Constants;
import ui.zlz.activity.account.LoginActivity;
import ui.zlz.activity.account.Zlzapplication;
import ui.zlz.bean.AboutCompanyBean;
import ui.zlz.utility.DebugFlags;
import ui.zlz.utility.GetSign;
import ui.zlz.utility.ToastUtil;
import ui.zlz.widget.AlertDialog;

public class AboutCompanyFragment extends BaseFragment{

    private WebView webView;

    @Override
    protected int setContentView() {
        return R.layout.fragment_about_company;
    }

    @Override
    protected void startLoad() {
        getAbout();

    }



    @Override
    protected void initData() {
        webView = findViewById(R.id.wb_mywebview);

    }


    private void getAbout() {
        String str=GetSign.getSign();
        String[] strs=str.split(",");
        DebugFlags.logD("签名"+str);


        OkHttpUtils.post()

                .url(Constants.BASE_URL +"Api/AboutUs/company_introduce")
                .addParams("t",strs[0])
                .addParams("r",strs[1])
                .addParams("e",strs[2])



                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        DebugFlags.logD("关于"+response);
                        AboutCompanyBean  acb=JSON.parseObject(response,AboutCompanyBean.class);
                        if (acb.getCode()==1){
                        webView.    loadDataWithBaseURL(null,
                                   acb.getData().getData().getContent(), "text/html", "utf-8", null);

                        }else if (acb.getCode()==2){

                            showAlerDialog();
                        }

                        else {
                            ToastUtil.show(acb.getMessage());
                        }

                    }
                });

    }
    private   void showAlerDialog() {

        new AlertDialog(getActivity())
                .builder()
                .setTitle("重新登录提示")
                .setCancelable(false)
                .setMsg("你的账号已在其他设备登录,请重新登录登录")
                .setPositiveButton("登录", new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        startActivity(new Intent(getActivity(),LoginActivity.class));
                        Zlzapplication.clearActivity();


                    } })
                .setNegativeButton("取消", new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        getActivity().finish();
                    } })
                .show();


    }
}
