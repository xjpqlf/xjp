package ui.zlz.activity.account;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.Call;
import ui.zlz.R;
import ui.zlz.activity.Constants;
import ui.zlz.bean.Msg;
import ui.zlz.utility.DebugFlags;
import ui.zlz.utility.GetSign;
import ui.zlz.utility.SharedPrefUtil;

public class MsgDetailActivity extends Activity {

    private TextView tv_title;
    private TextView time;
    private TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_detail);
        init();
    }

    private void init() {
       ImageView iv= findViewById(R.id.iv_msg_back_detail);
       iv.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               finish();
           }
       });


        tv_title = findViewById(R.id.tv_msg_detail_title);
        time = findViewById(R.id.tv_msg_detail_time);
        content = findViewById(R.id.tv_msg_detail_conten);
        getDetail();

    }

    private void getDetail() {

        String str=GetSign.getSign();
        String[] strs=str.split(",");
        DebugFlags.logD("签名"+str);


        OkHttpUtils.post()

                .url(Constants.BASE_URL +"/Api/Msg/msg_detail")
                .addParams("t",strs[0])
                .addParams("r",strs[1])
                .addParams("e",strs[2])
                //   .addParams("mobile","13071266270")
                .addParams("token",SharedPrefUtil.getString(this,"token",""))
                .addParams("msg_id",getIntent().getStringExtra("id"))


                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                         DebugFlags.logD("re消息详情"+response);
                        try {
                            org.json.JSONObject js=new org.json.JSONObject(response);
                            String data=   js.getString("data");
                            Msg.DataBeanX datas= JSON.parseObject(data, Msg.DataBeanX.class);
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            long lt = new Long(datas.getData().get(0).getCtime());
                            Date date = new Date(lt);
                            String  res = simpleDateFormat.format(date);

                          tv_title.setText(datas.getData().get(0).getTitle());
                          time.setText(res);
                          content.setText(datas.getData().get(0).getContent());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });

    }
}
