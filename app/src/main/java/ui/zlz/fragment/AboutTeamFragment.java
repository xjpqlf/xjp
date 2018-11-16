package ui.zlz.fragment;

import android.webkit.WebView;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import ui.zlz.R;
import ui.zlz.activity.Constants;
import ui.zlz.bean.AboutCompanyBean;
import ui.zlz.utility.DebugFlags;
import ui.zlz.utility.GetSign;
import ui.zlz.utility.ToastUtil;

public class AboutTeamFragment extends BaseFragment{

    private WebView webView;

    @Override
    protected int setContentView() {
        return R.layout.fragment_about_team;
    }

    @Override
    protected void startLoad() {
getAbout();
    }

    @Override
    protected void initData() {

        webView = findViewById(R.id.wb_mywebviews);
    }



    private void getAbout() {
        String str=GetSign.getSign();
        String[] strs=str.split(",");
        DebugFlags.logD("签名"+str);


        OkHttpUtils.post()

                .url(Constants.BASE_URL +"Api/AboutUs/manage_team")
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
                        DebugFlags.logD("团队"+response);
                        AboutCompanyBean acb=JSON.parseObject(response,AboutCompanyBean.class);
                        if (acb.getCode()==1){
                            webView.    loadDataWithBaseURL(null,
                                    acb.getData().getData().getContent(), "text/html", "utf-8", null);

                        }else {
                            ToastUtil.show(acb.getMessage());
                        }

                    }
                });

    }
}


