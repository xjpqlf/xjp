package ui.zlz.activity.account;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import ui.zlz.R;
import ui.zlz.activity.Constants;
import ui.zlz.adapter.CouponAdapter;
import ui.zlz.bean.CouponBean;
import ui.zlz.utility.DataUtils;
import ui.zlz.utility.DebugFlags;
import ui.zlz.utility.GetSign;
import ui.zlz.utility.SharedPrefUtil;
import ui.zlz.widget.AlertDialog;

public class MyVoucherPackageActivity extends Activity {

    private List<CouponBean> list;
    private ListView lv;
    private  int i=1;
    private List<CouponBean.DataBeanX.DataBean> list1;
    private SmartRefreshLayout refreshLayout;
    private ImageView ivnoinfo;
    private TextView tvnoinfo;
    private RelativeLayout rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_voucher_package);
        init();
    }

    private void init() {
        ImageView ivback=findViewById(R.id.iv_back);
        ivnoinfo = findViewById(R.id.iv_noinfo);
        tvnoinfo = findViewById(R.id.tv_noinfo);
        rl = findViewById(R.id.rl_noinfo);

        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        lv = findViewById(R.id.lv_qb);
        list = new ArrayList<>();
        list1 = new ArrayList<>();

        if (!DataUtils.isNetworkAvailable(MyVoucherPackageActivity.this)){

            rl.setVisibility(View.VISIBLE);
            ivnoinfo.setImageResource(R.mipmap.nonet);
            tvnoinfo.setText("请检查网络设置");
            refreshLayout.setEnableRefresh(false);
            refreshLayout.setEnableLoadmore(false);

            return;
        }else {
            lv.setVisibility(View.VISIBLE);
            rl.setVisibility(View.GONE);
        }


              getVoucher(i);
    }

    private void getVoucher(int i) {
        String str=GetSign.getSign();
        String[] strs=str.split(",");
        DebugFlags.logD("签名"+str);


        OkHttpUtils.post()

                .url(Constants.BASE_URL +"/Api/User/user_coupons")
                .addParams("t",strs[0])
                .addParams("r",strs[1])
                .addParams("e",strs[2])
                //   .addParams("mobile","13071266270")
                .addParams("page",i+"")
                .addParams("token",SharedPrefUtil.getString(MyVoucherPackageActivity.this,"token",""))


                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        DebugFlags.logD("我的券包"+response);

                    CouponBean all=    JSON.parseObject(response,CouponBean.class);

                    if (all.getCode()==1){

                        lv.setVisibility(View.VISIBLE);
                        rl.setVisibility(View.GONE);
                         list1.clear();
                        list1.addAll(all.getData().getData());
                        CouponAdapter adapter=new CouponAdapter(MyVoucherPackageActivity.this,list1);
                        lv.setAdapter(adapter);
                    }else if (all.getCode()==2){
                        showAlerDialog();
                    }

                    else {
                       rl.setVisibility(View.VISIBLE);
                       lv.setVisibility(View.GONE);
                       ivnoinfo.setImageResource(R.mipmap.noinfo);


                    }

                    }
                });




    }





    private   void showAlerDialog() {

        new AlertDialog(MyVoucherPackageActivity.this)
                .builder()
                .setTitle("重新登录提示")
                .setCancelable(false)
                .setMsg("你的账号已在其他设备登录,请重新登录登录")
                .setPositiveButton("登录", new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        startActivity(new Intent(MyVoucherPackageActivity.this,LoginActivity.class));
                        finish();
                    } })
                .setNegativeButton("取消", new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        finish();
                    } })
                .show();


    }

}
