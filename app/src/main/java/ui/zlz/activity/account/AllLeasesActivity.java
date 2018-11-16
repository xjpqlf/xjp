package ui.zlz.activity.account;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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
import ui.zlz.adapter.AllLeasesAdapter;
import ui.zlz.bean.AllLaeses;
import ui.zlz.utility.DebugFlags;
import ui.zlz.utility.GetSign;
import ui.zlz.utility.SharedPrefUtil;
import ui.zlz.utility.ToastUtil;

public class AllLeasesActivity extends Activity implements View.OnClickListener {

    private TextView tzzline;
    private TextView ytz;
    private TextView ytzline;
    private TextView yjq;
    private TextView yjqline;
    private TextView tzz;
    private ImageView ivtzz;
    private ImageView ivytz;
    private ImageView ivyjq;
    private SmartRefreshLayout refreshLayout;
    private ListView lv;
    private List<AllLaeses.DataBeanX.DataBean> list;
    private int i=1;
    private int type=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_leases);
        init();
    }

    private void init() {
      ImageView ivback= findViewById(R.id.iv_all_back);
        lv = findViewById(R.id.lv_allleases);
        ivtzz = findViewById(R.id.iv_tzz);
        ivytz = findViewById(R.id.iv_ytz);
        ivyjq = findViewById(R.id.iv_yjq);
        tzz = findViewById(R.id.tv_tzz);
        tzzline = findViewById(R.id.tv_tzz_line);
        ytz = findViewById(R.id.tv_ytz);
        ytzline = findViewById(R.id.tv_ytz_line);
        yjq = findViewById(R.id.tv_yjq);
        yjqline = findViewById(R.id.tv_yjq_line);
        refreshLayout = findViewById(R.id.refreshLayout_all);
        tzz.setOnClickListener(this);
       ivback.setOnClickListener(this);
        ytz.setOnClickListener(this);
        yjq.setOnClickListener(this);
        getData( 1, 1 );



            refreshLayout.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh(RefreshLayout refreshlayout) {
                    list.clear();
                    if (1==type){
                    getData(1,1);
                    }else if (2==type){

                        getData(2,1);
                    }else if (3==type){

                        getData(3,1);
                    }
                    refreshlayout.finishRefresh(1000/*,false*/);//传入false表示刷新失败

                }
            });
            refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
                @Override
                public void onLoadmore(RefreshLayout refreshlayout) {
                    i++;

                    if (1==type){
                        getMoreData(1,i);
                    }else if (2==type){

                        getMoreData(2,i);
                    }else if (3==type){

                        getMoreData(3,i);
                    }



                    refreshlayout.finishLoadmore(1000);
                }
            });





    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_all_back:
                finish();
                break;
            case R.id.tv_tzz:
              type=1;
                tzz.setTextColor(Color.parseColor("#F6931E"));
                tzzline.setBackgroundColor(Color.parseColor("#F6931E"));
                ivtzz.setImageDrawable(getResources().getDrawable(R.mipmap.xuan_ze1));
                ytz.setTextColor(Color.parseColor("#7b7b7b"));
                ytzline.setBackgroundColor(Color.parseColor("#ffffff"));
                ivytz.setImageDrawable(getResources().getDrawable(R.mipmap.xuan_ze2));
                yjq.setTextColor(Color.parseColor("#7b7b7b"));
                yjqline.setBackgroundColor(Color.parseColor("#ffffff"));
                ivyjq.setImageDrawable(getResources().getDrawable(R.mipmap.xuan_ze2));
                getData( 1, 1 );






                break;
            case R.id.tv_ytz:
                 type=2;
                ytz.setTextColor(Color.parseColor("#F6931E"));
                ytzline.setBackgroundColor(Color.parseColor("#F6931E"));
                ivytz.setImageDrawable(getResources().getDrawable(R.mipmap.xuan_ze1));
                tzz.setTextColor(Color.parseColor("#7b7b7b"));
                tzzline.setBackgroundColor(Color.parseColor("#ffffff"));
                ivtzz.setImageDrawable(getResources().getDrawable(R.mipmap.xuan_ze2));
                yjq.setTextColor(Color.parseColor("#7b7b7b"));
                yjqline.setBackgroundColor(Color.parseColor("#ffffff"));
                ivyjq.setImageDrawable(getResources().getDrawable(R.mipmap.xuan_ze2));
                getData( 2, 1 );
                break;
            case R.id.tv_yjq:
                type=3;
                yjq.setTextColor(Color.parseColor("#F6931E"));
                yjqline.setBackgroundColor(Color.parseColor("#F6931E"));
                ivyjq.setImageDrawable(getResources().getDrawable(R.mipmap.xuan_ze1));
                ytz.setTextColor(Color.parseColor("#7b7b7b"));
                ytzline.setBackgroundColor(Color.parseColor("#ffffff"));
                ivytz.setImageDrawable(getResources().getDrawable(R.mipmap.xuan_ze2));
                tzz.setTextColor(Color.parseColor("#7b7b7b"));
                tzzline.setBackgroundColor(Color.parseColor("#ffffff"));
                ivtzz.setImageDrawable(getResources().getDrawable(R.mipmap.xuan_ze2));
                getData( 3 ,1 );
                break;


        }
    }


    private void getData(int type,int page) {
        String str=GetSign.getSign();
        String[] strs=str.split(",");
        DebugFlags.logD("签名"+str);


        OkHttpUtils.post()

                .url(Constants.BASE_URL +"/Api/Goods/all_rent")
                .addParams("t",strs[0])
                .addParams("r",strs[1])
                .addParams("e",strs[2])
                //   .addParams("mobile","13071266270")
                .addParams("token",SharedPrefUtil.getString(this,"token",""))
                .addParams("type",type+"")
                .addParams("page",page+"")







                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        DebugFlags.logD("租权"+response);
                        try {
                            JSONObject jsonObject =new JSONObject(response);

                      AllLaeses allLaeses=  JSON.parseObject(response,AllLaeses.class);
                      if(allLaeses.getCode()==1) {
                          list = allLaeses.getData().getData();
                          AllLeasesAdapter adapter = new AllLeasesAdapter(AllLeasesActivity.this, list);
                          lv.setAdapter(adapter);

                      }else {
                          ToastUtil.show(allLaeses.getMessage());
                      }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });

    }

    //加载更多
    private void getMoreData(int type,int page) {

        String str=GetSign.getSign();
        String[] strs=str.split(",");
        DebugFlags.logD("签名"+str);


        OkHttpUtils.post()

                .url(Constants.BASE_URL +"/Api/Goods/all_rent")
                .addParams("t",strs[0])
                .addParams("r",strs[1])
                .addParams("e",strs[2])
                //   .addParams("mobile","13071266270")
                .addParams("token",SharedPrefUtil.getString(this,"token",""))

                .addParams("type",type+"")
                .addParams("page",page+"")


                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {


                    }

                    @Override
                    public void onResponse(String response, int id) {
                        DebugFlags.logD("消息更多"+response);
                        try {
                            JSONObject js =new JSONObject(response);

                            String code =js.getString("code");
                            if ("0".equals(code)){
                                ToastUtil.show(js.getString("没有更多数据了"));
                            }else {
                                AllLaeses allLaeses=  JSON.parseObject(response,AllLaeses.class);
                                list = allLaeses.getData().getData();
                                AllLeasesAdapter adapter=new AllLeasesAdapter(AllLeasesActivity.this, list);
                                lv.setAdapter(adapter);
                            }


                        } catch (JSONException e) {

                            e.printStackTrace();
                        };




                    }
                });


    }

}
