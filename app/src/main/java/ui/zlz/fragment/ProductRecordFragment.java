package ui.zlz.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import ui.zlz.R;
import ui.zlz.activity.Constants;
import ui.zlz.adapter.RecordAdapter;
import ui.zlz.bean.LzRecordBean;
import ui.zlz.utility.DataUtils;
import ui.zlz.utility.DebugFlags;
import ui.zlz.utility.GetSign;

/**
        * Created by chenzhe on 2018/11/8.
        * 在产品详情来租记录
        */
public class ProductRecordFragment extends BaseFragment {


    private ListView lv;
    private RelativeLayout rl;
    private ImageView iv;
    private TextView tv;

    public static ProductRecordFragment newInstance(String id) {
              ProductRecordFragment fragment = new ProductRecordFragment();
              Bundle args = new Bundle();

              args.putString("id", id);
              fragment.setArguments(args);
              return fragment;
          }

    @Override
    protected int setContentView() {
        return R.layout.fragment_product_record;
    }

    @Override
    protected void startLoad() {

        if (!DataUtils.isNetworkAvailable(getContext())){
              rl.setVisibility(View.VISIBLE);
              lv.setVisibility(View.GONE);
              return;


        }



        if (getArguments() != null) {

           String id = getArguments().getString("id");
            DebugFlags.logD("前面传过来的"+id);
            getRecord(id);

        }



    }

    private void getRecord(String id) {


        String str=GetSign.getSign();
        String[] strs=str.split(",");
        DebugFlags.logD("签名"+str);


        OkHttpUtils.post()

                .url(Constants.BASE_URL +"Api/goods/user_tz_log/")
                .addParams("t",strs[0])
                .addParams("r",strs[1])
                .addParams("e",strs[2])
                .addParams("goods_id",id)


                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        DebugFlags.logD("来租记录"+response);
                 LzRecordBean lz=  JSON.parseObject(response,LzRecordBean.class);
                 if (lz.getCode()==1){
                     rl.setVisibility(View.GONE);
                     lv.setVisibility(View.VISIBLE);
                     List<LzRecordBean.DataBeanX.DataBean> listlz=lz.getData().getData();
                     RecordAdapter adapter=new RecordAdapter(getActivity(),listlz);
                     lv.setAdapter(adapter);




                 }else if (lz.getCode()==0){

                     rl.setVisibility(View.VISIBLE);
                     lv.setVisibility(View.GONE);
                     tv.setText("暂无记录");
                     iv.setImageResource(R.mipmap.noinfo);

                 }

                    }
                });

    }

    @Override
    protected void initData() {
        lv = findViewById(R.id.lv_record);
        rl = findViewById(R.id.rl_noinfo);
        iv = findViewById(R.id.iv_noinfo);
        tv = findViewById(R.id.tv_noinfo);

    }
}
