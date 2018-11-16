package ui.zlz.activity.account;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;

import java.util.List;

import okhttp3.Call;
import ui.zlz.R;
import ui.zlz.activity.Constants;
import ui.zlz.adapter.MsgAdapter;
import ui.zlz.bean.Msg;
import ui.zlz.utility.DebugFlags;
import ui.zlz.utility.GetSign;
import ui.zlz.utility.SharedPrefUtil;
import ui.zlz.utility.ToastUtil;

public class MsgCenterActivity extends Activity {

    private ListView lv;
    private ImageView imageView;
    private List<Msg.DataBeanX.DataBean> list;
    private  int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_center);
        init();
        initData();
    }

    private void initData() {
       getData(0);



    }

    private void getData(int i) {

        String str=GetSign.getSign();
        String[] strs=str.split(",");
        DebugFlags.logD("签名"+str);


        OkHttpUtils.post()

                .url(Constants.BASE_URL +"/Api/Msg/index")
                .addParams("t",strs[0])
                .addParams("r",strs[1])
                .addParams("e",strs[2])
                //   .addParams("mobile","13071266270")
                .addParams("token",SharedPrefUtil.getString(this,"token",""))
                .addParams("page",i+"")


                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {


                    }

                    @Override
                    public void onResponse(String response, int id) {
                        DebugFlags.logD("消息"+response);
                        Msg msg   =    JSON .parseObject(response,Msg.class);
                        list = msg.getData().getData();
                        MsgAdapter adapter=new MsgAdapter(MsgCenterActivity.this, list);
                        lv.setAdapter(adapter);


                    }
                });


    }

    private void init() {
        lv = findViewById(R.id.lv_msg);
        imageView = findViewById(R.id.iv_msg_back);
        RefreshLayout refreshLayout = findViewById(R.id.refreshLayout);

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                list.clear();
                getData(0);
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
    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent=new Intent(MsgCenterActivity.this,MsgDetailActivity.class);
            intent.putExtra("id",list.get(i).getId());
            startActivity(intent);
        }
    });



        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

//加载更多
    private void getMoreData(int i) {

        String str=GetSign.getSign();
        String[] strs=str.split(",");
        DebugFlags.logD("签名"+str);


        OkHttpUtils.post()

                .url(Constants.BASE_URL +"/Api/Msg/index")
                .addParams("t",strs[0])
                .addParams("r",strs[1])
                .addParams("e",strs[2])
                //   .addParams("mobile","13071266270")
                .addParams("token",SharedPrefUtil.getString(this,"token",""))
                .addParams("page",i+"")


                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {


                    }

                    @Override
                    public void onResponse(String response, int id) {
                        DebugFlags.logD("消息更多"+response);
                        try {
                            org.json.JSONObject js=new org.json.JSONObject(response);
                            String code =js.getString("code");
                            if ("0".equals(code)){
                                ToastUtil.show(js.getString("没有更多内容了"));
                            }else {
                                Msg msg   =    JSON .parseObject(response,Msg.class);
                                list = msg.getData().getData();
                                MsgAdapter adapter=new MsgAdapter(MsgCenterActivity.this, list);
                                lv.setAdapter(adapter);
                            }


                        } catch (JSONException e) {

                            e.printStackTrace();
                        };




                    }
                });


    }
}
