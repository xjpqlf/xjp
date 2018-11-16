package ui.zlz.home;

import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import ui.zlz.R;
import ui.zlz.activity.Constants;
import ui.zlz.bean.ContractBean;
import ui.zlz.utility.DebugFlags;
import ui.zlz.utility.GetSign;
import ui.zlz.utility.SharedPrefUtil;

public class ContractActivity extends Activity {

    private TextView ht;
    private TextView jf;
    private TextView sfz;
    private TextView dh;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contract);
        initView();
    }

    private void initView() {
        ImageView iv=findViewById(R.id.iv_back_con);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ht = findViewById(R.id.tv_bh1);
        jf = findViewById(R.id.tv_jf);
        sfz = findViewById(R.id.tv_sfz);
        dh = findViewById(R.id.tv_dh);
        ht.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG );
        jf.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG );
        sfz.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG );
        dh.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG );
        if (getIntent()!=null){
            getData(getIntent().getStringExtra("id"));
        }


    }

    private void getData(String id) {

        String str=GetSign.getSign();
        String[] strs=str.split(",");
        DebugFlags.logD("签名"+str);


        OkHttpUtils.post()

                .url(Constants.BASE_URL +"/Api/user/pact")
                .addParams("t",strs[0])
                .addParams("r",strs[1])
                .addParams("e",strs[2])
                .addParams("goods_id",id)
                .addParams("token",SharedPrefUtil.getString(ContractActivity.this,"token",""))

                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                           DebugFlags.logD("合同"+response);
                  ContractBean cb=      JSON.parseObject(response,ContractBean.class);
                  ht.setText("合同编号 ："+cb.getData().getGood_data().getOrder_no());
                  jf.setText("甲方："+cb.getData().getUser_data().getRealname());
                  sfz.setText("身份证号码："+cb.getData().getUser_data().getId_card_number());
                  dh.setText("联系电话 ："+cb.getData().getUser_data().getMobile());



                    }
                });

    }
}
