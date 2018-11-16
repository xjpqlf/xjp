package ui.zlz.activity.account;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import ui.zlz.R;
import ui.zlz.activity.Constants;
import ui.zlz.adapter.MyUnCardAdapter;
import ui.zlz.bean.UnCardBean;
import ui.zlz.utility.DebugFlags;
import ui.zlz.utility.GetSign;
import ui.zlz.utility.SharedPrefUtil;
import ui.zlz.utility.ToastUtil;
import ui.zlz.widget.ListViewForScrollView;

public class MyUnionPayingCardActivity extends Activity implements View.OnClickListener {

    private ImageView iv;
    private ListViewForScrollView lv;
    private RelativeLayout rl;
    private UnCardBean ucb;
    private MyUnCardAdapter adapter;
    private List<UnCardBean.DataBeanX.DataBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_union_paying_card);
        init();

    }

    private void initData() {
        adapter=new MyUnCardAdapter(MyUnionPayingCardActivity.this,list);
        lv.setAdapter(adapter);

    }

    private void init() {
        list=new ArrayList<>();
        iv = findViewById(R.id.iv_wdyhk_back);
        lv = findViewById(R.id.lv_myun);
        rl = findViewById(R.id.rl_addun);
        iv.setOnClickListener(this);
        rl.setOnClickListener(this);



        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent =new Intent(MyUnionPayingCardActivity.this,ClickingUnionPayingCardActivity.class);
                intent.putExtra("name",ucb.getData().getData().get(i).getCard_name() );
                intent.putExtra("num",ucb.getData().getData().get(i).getCard_num() );
                intent.putExtra("tel",ucb.getData().getData().get(i).getTel() );
                intent.putExtra("id",ucb.getData().getData().get(i).getId());
                startActivity(intent);
            }
        });


        getMyCard();

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_wdyhk_back:
                finish();
                break;
            case R.id.rl_addun:
                //跳转添加银行卡界面
                startActivity(new Intent(MyUnionPayingCardActivity.this,AddUnionPayingCardActivity.class));

                break;

        }



    }

//获取我的银行卡
    private void getMyCard() {

        String str=GetSign.getSign();
        String[] strs=str.split(",");
        DebugFlags.logD("签名"+str);


        OkHttpUtils.post()

                .url(Constants.BASE_URL +"/Api/bank/get_user_bank/")
                .addParams("t",strs[0])
                .addParams("r",strs[1])
                .addParams("e",strs[2])
                //   .addParams("mobile","13071266270")
                .addParams("token",SharedPrefUtil.getString(MyUnionPayingCardActivity.this,"token",""))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        DebugFlags.logD("银行卡"+response);
                        ucb = JSON.parseObject(response,UnCardBean.class);
                        if (ucb.getCode()==1){
                          list.clear();
                             list=ucb.getData().getData();

                            initData();

                        }else {
                            lv.setVisibility(View.GONE);

                            ToastUtil.show(ucb.getMessage());
                        }


                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        lv.setVisibility(View.VISIBLE);
        getMyCard();


    }
}
