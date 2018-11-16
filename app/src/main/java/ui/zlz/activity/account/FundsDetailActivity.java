package ui.zlz.activity.account;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import ui.zlz.R;
import ui.zlz.activity.Constants;
import ui.zlz.bean.CapitalDetails;
import ui.zlz.utility.DebugFlags;
import ui.zlz.utility.GetSign;
import ui.zlz.utility.SharedPrefUtil;
import ui.zlz.utility.ToastUtil;

public class FundsDetailActivity extends Activity {

    private TextView tvtzje;
    private TextView tvzz;
    private TextView tvzzum;
    private TextView tvtz;
    private TextView tvtznum;
    private TextView tvzm;
    private TextView tvzmnum;
    private List<CapitalDetails.DataBeanX.DataBean> list;
    private LinearLayout hzhk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funds_detail);
        init();

    }

    private void init() {
        hzhk = findViewById(R.id.ll_hzgk);
       ImageView ivback= findViewById(R.id.iv_zjmx_back);
       ImageView ivsjmx= findViewById(R.id.iv_szmx);
        TextView tvye =findViewById(R.id.tv_ye_num);
        TextView tvzzc=findViewById(R.id.tv_zzcnum);
        TextView tvyzje =findViewById(R.id.tv_yzjenum);
        tvtzje = findViewById(R.id.tv_tzjenum);
        tvzz = findViewById(R.id.tv_zz);
        tvzzum = findViewById(R.id.tv_zznum);
        tvtz = findViewById(R.id.tv_tz);
        tvtznum = findViewById(R.id.tv_tznum);
        tvzm = findViewById(R.id.tv_zm);
        tvzmnum = findViewById(R.id.tv_zmnum);
        //点击返回键
        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //点击收支明细
        ivsjmx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FundsDetailActivity.this,IncomeAndExpenditureActivity.class));

            }
        });

        tvzzc.setText(SharedPrefUtil.getString(FundsDetailActivity.this,"zongzhichan",""));
        tvyzje.setText(SharedPrefUtil.getString(FundsDetailActivity.this,"leijishouyi",""));
        tvye.setText(SharedPrefUtil.getString(FundsDetailActivity.this,"yuee",""));


        getFundsData();


    }
    //获取资金明细

    private void getFundsData() {
        String str=GetSign.getSign();
        String[] strs=str.split(",");
        DebugFlags.logD("签名"+str);


        OkHttpUtils.post()

                .url(Constants.BASE_URL +"/Api/User/asset_detail")
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
                        DebugFlags.logD("资金明细"+response);
                CapitalDetails cap =   JSON.parseObject(response,CapitalDetails.class);
                if (cap.getCode()==1) {
                    list = cap.getData().getData();
                    tvtzje.setText(cap.getData().getAlready_tz());
                    for (int i = 0; i < list.size(); i++) {

                        if (list.get(i).getStatus().equals("1")) {
                            tvzz.setText("在租" + list.get(i).getGcount() + "笔");
                            tvzzum.setText(list.get(i).getAll_tz_money());

                        } else if (list.get(i).getStatus().equals("2")) {
                            tvtz.setText("退租" + list.get(i).getGcount() + "笔");
                            tvtznum.setText(list.get(i).getAll_tz_money());

                        } else if (list.get(i).getStatus().equals("3")) {
                            tvzm.setText("租满" + list.get(i).getGcount() + "笔");
                            tvzmnum.setText(list.get(i).getAll_tz_money());

                        }

                    }
                }else {
                    hzhk.setVisibility(View.GONE);
                    ToastUtil.show(cap.getMessage());
                }

                    }
                });


    }
}
