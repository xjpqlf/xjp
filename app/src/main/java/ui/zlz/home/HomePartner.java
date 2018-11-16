package ui.zlz.home;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import ui.zlz.R;
import ui.zlz.activity.Constants;
import ui.zlz.adapter.PartnerAdapter;
import ui.zlz.bean.PaetnerBean;
import ui.zlz.utility.DebugFlags;
import ui.zlz.utility.GetSign;
import ui.zlz.utility.ToastUtil;

public class HomePartner extends Activity {
    private List<String> img;
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//Activity 启动时运行
        setContentView(R.layout.activity_home_partner);//加载对应的布局页面
        initView();
        initData();

    }



    private void initView() {

        gridView = (GridView)findViewById(R.id.gridView);
        ImageView ivback=findViewById(R.id.iv_pthzhb_back);
        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



    }


    private void initData() {
        String str=GetSign.getSign();
        String[] strs=str.split(",");
        DebugFlags.logD("签名"+str);


        OkHttpUtils.post()

                .url(Constants.BASE_URL +"/Api/Index/cooperation/")
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
                     DebugFlags.logD("合作平台"+response);
                        PaetnerBean pb=JSON.parseObject(response,PaetnerBean.class);
                        if (pb.getCode()==1){
                       List<PaetnerBean.DataBeanX.DataBean> lpb=pb.getData().getData();
                            PartnerAdapter adapter=new PartnerAdapter(HomePartner.this,lpb);
                            gridView.setAdapter(adapter);



                        }else {

                            ToastUtil.show(pb.getMessage());
                        }
                    }
                });

    }
}
