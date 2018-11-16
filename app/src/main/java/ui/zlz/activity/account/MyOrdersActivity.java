package ui.zlz.activity.account;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import ui.zlz.R;
import ui.zlz.activity.Constants;
import ui.zlz.adapter.OrderAdapter;
import ui.zlz.bean.Order;
import ui.zlz.utility.DebugFlags;
import ui.zlz.utility.GetSign;
import ui.zlz.utility.SharedPrefUtil;
import ui.zlz.utility.ToastUtil;

public class MyOrdersActivity extends Activity implements View.OnClickListener {

    private SmartRefreshLayout refreshLayout;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);
        init();
        initData();
    }



    private void init() {
       ImageView ivback= findViewById(R.id.iv_ddcx_back);
        refreshLayout = findViewById(R.id.refreshLayout_order);
        lv = findViewById(R.id.lv_order);
        TextView tv=findViewById(R.id.tv_shx);
        tv.setOnClickListener(this);
        ivback.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_ddcx_back:
                finish();
                break;
            case R.id.tv_shx:
                startActivity(new Intent(MyOrdersActivity.this,FilteringOrdersActivity.class));
                break;
        }

    }

    private void initData() {
        String str=GetSign.getSign();
        String[] strs=str.split(",");
        DebugFlags.logD("签名"+str);


        OkHttpUtils.post()

                .url(Constants.BASE_URL +"/Api/Order/index")
                .addParams("t",strs[0])
                .addParams("r",strs[1])
                .addParams("e",strs[2])
                //   .addParams("mobile","13071266270")
                .addParams("token",SharedPrefUtil.getString(this,"token",""))








                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        DebugFlags.logD("订单"+response);
                     Order order=   JSON.parseObject(response,Order.class);
                     if (1==order.getCode()){
                   List <Order.DataBeanX.DataBean>list= order.getData().getData();
                         OrderAdapter adapter=new OrderAdapter(MyOrdersActivity.this,list);
                         lv.setAdapter(adapter);


                     }else {

                         ToastUtil.show("没有数据");
                     }

                    }
                });
    }
}
