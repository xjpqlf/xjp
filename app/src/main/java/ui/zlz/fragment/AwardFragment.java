package ui.zlz.fragment;

import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.Call;
import ui.zlz.R;
import ui.zlz.activity.Constants;
import ui.zlz.adapter.AwardAdapter;
import ui.zlz.bean.AwardAndFriend;
import ui.zlz.utility.DebugFlags;
import ui.zlz.utility.GetSign;
import ui.zlz.utility.SharedPrefUtil;
import ui.zlz.utility.ToastUtil;

public class AwardFragment extends BaseFragment {

    private SmartRefreshLayout refreshLayout;
    private ListView listView;
    private  int i=1;

    private List<AwardAndFriend> list2;
    private List<AwardAndFriend> list;

    @Override
    protected int setContentView() {
        return R.layout.award;
    }

    @Override
    protected void startLoad() {
        getData(1);



    }


    @Override
    protected void initData() {
        refreshLayout = findViewById(R.id.refreshLayout_award);
        listView = findViewById(R.id.lv_award);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                //list.clear();
                getData(1);
                refreshlayout.finishRefresh(1000/*,false*/);//传入false表示刷新失败

            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                i++;
              getMoreData(i);
                refreshlayout.finishLoadmore(1000);
            }
        });
    }

    private void getData(final int i) {
        String str=GetSign.getSign();
        String[] strs=str.split(",");
        DebugFlags.logD("签名"+str);


        OkHttpUtils.post()

                .url(Constants.BASE_URL +"/Api/share/invite_user")
                .addParams("t",strs[0])
                .addParams("r",strs[1])
                .addParams("e",strs[2])
                //   .addParams("mobile","13071266270")
                .addParams("token",SharedPrefUtil.getString(getActivity(),"token",""))
                .addParams("page",i+"")



                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        DebugFlags.logD("奖励"+i+"==="+response);
                        try {
                            JSONObject js=new JSONObject(response);
                            if ("1".equals(js.getString("code"))){
                                String data =js.getString("data");
                                JSONObject js1=new JSONObject(data);
                               String datas= js1.getString("data ");
                        list2=  JSON.parseArray(datas,AwardAndFriend.class);
                                AwardAdapter awardAdapter=new AwardAdapter(getActivity(),list2);
                                listView.setAdapter(awardAdapter);


                            }else {
                                ToastUtil.show("没有数据");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });

    }


  //加载更多
    private void getMoreData(final int i) {

        String str=GetSign.getSign();
        String[] strs=str.split(",");
        DebugFlags.logD("签名"+str);


        OkHttpUtils.post()

                .url(Constants.BASE_URL +"/Api/User/income_log")
                .addParams("t",strs[0])
                .addParams("r",strs[1])
                .addParams("e",strs[2])
                //   .addParams("mobile","13071266270")
                .addParams("token",SharedPrefUtil.getString(getActivity(),"token",""))
                .addParams("page",i+"")


                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {


                    }

                    @Override
                    public void onResponse(String response, int id) {
                        DebugFlags.logD("消息更多"+i+"=="+response);
                        try {
                            JSONObject js=new JSONObject(response);
                            String code =js.getString("code");
                            if ("0".equals(code)){
                                ToastUtil.show("没有更多内容了");
                            }else {
                                try {
                                    JSONObject js2=new JSONObject(response);
                                    String data =js2.getString("data");
                                    JSONObject js1=new JSONObject(data);
                                    String datas=js1.getString("data");
                                     list= JSON.parseArray(datas,AwardAndFriend.class);
                                     list2.addAll(list);
                                    AwardAdapter adapter=new AwardAdapter(getActivity(),list2);
                                    listView.setAdapter(adapter);


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }


                        } catch (JSONException e) {

                            e.printStackTrace();
                        };




                    }
                });


    }

}
