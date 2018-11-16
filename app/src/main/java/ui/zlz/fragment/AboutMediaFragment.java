package ui.zlz.fragment;

import android.content.Intent;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import ui.zlz.R;
import ui.zlz.activity.Constants;
import ui.zlz.adapter.MediaAdapter;
import ui.zlz.bean.AboutMediaBean;
import ui.zlz.home.MediaDetailActivity;
import ui.zlz.utility.DebugFlags;
import ui.zlz.utility.GetSign;
import ui.zlz.utility.ToastUtil;

public class AboutMediaFragment extends BaseFragment {

    private WebView webView;
    private ListView lv;
    private List<AboutMediaBean.DataBeanX.DataBean> list;

    @Override
protected int setContentView() {
    return R.layout.fragment_about_media;
}

    @Override
    protected void startLoad() {
        getAbout();

    }

    @Override
    protected void initData() {
        lv = findViewById(R.id.lv_media);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent=new Intent(getActivity(),MediaDetailActivity.class);
                intent.putExtra("content",list.get(i).getContent());
                startActivity(intent);
            }
        });
    }

    private void getAbout() {
        String str=GetSign.getSign();
        String[] strs=str.split(",");
        DebugFlags.logD("签名"+str);


        OkHttpUtils.post()

                .url(Constants.BASE_URL +"Api/AboutUs/media_report")
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
                        DebugFlags.logD("关于媒体"+response);
                        AboutMediaBean amb=JSON.parseObject(response,AboutMediaBean.class);
                        if (amb.getCode()==1){
                            list = amb.getData().getData();
                            MediaAdapter adapter=new MediaAdapter(getActivity(), list);
                            lv.setAdapter(adapter);

                        }else {
                            ToastUtil.show(amb.getMessage());
                        }

                    }
                });

    }
}