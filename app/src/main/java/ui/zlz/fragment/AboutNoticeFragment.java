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
import ui.zlz.adapter.NetNoticeAdapter;
import ui.zlz.bean.NoticeNetBean;
import ui.zlz.home.NoticeActivity;
import ui.zlz.utility.DebugFlags;
import ui.zlz.utility.GetSign;

public class AboutNoticeFragment extends BaseFragment {

    private WebView webView;
    private ListView lv;
    private List<NoticeNetBean.DataBeanX.DataBean> list;

    protected int setContentView() {
    return R.layout.fragment_about_notice;
}

    @Override
    protected void startLoad() {
          getAbout();
    }

    @Override
    protected void initData(){
        lv = findViewById(R.id.lv_notice);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getActivity(),NoticeActivity.class);
                intent.putExtra("con",list.get(i).getContent());
                startActivity(intent);


            }
        });
    }
    private void getAbout() {
        String str=GetSign.getSign();
        String[] strs=str.split(",");
        DebugFlags.logD("签名"+str);


        OkHttpUtils.post()

                .url(Constants.BASE_URL +"Api/AboutUs/website_notice")
                .addParams("t",strs[0])
                .addParams("r",strs[1])
                .addParams("e",strs[2])
                .addParams("type","1")



                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        DebugFlags.logD("关于公告"+response);
                      NoticeNetBean nn=  JSON.parseObject(response,NoticeNetBean.class);
                      if (nn.getCode()==1){
                          list = nn.getData().getData();
                          NetNoticeAdapter adapter=new NetNoticeAdapter(getActivity(), list);
                          lv.setAdapter(adapter);
                      }


                    }
                });

    }
}