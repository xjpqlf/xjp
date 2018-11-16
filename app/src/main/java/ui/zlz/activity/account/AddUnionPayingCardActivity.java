package ui.zlz.activity.account;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import ui.zlz.R;
import ui.zlz.activity.Constants;
import ui.zlz.utility.DebugFlags;
import ui.zlz.utility.GetSign;
import ui.zlz.utility.SharedPrefUtil;
import ui.zlz.utility.ToastUtil;

public class AddUnionPayingCardActivity extends Activity {

    private EditText name;
    private EditText num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_union_paying_card);
        initView();
    }

    private void initView() {
        name = findViewById(R.id.et_ckr);
        ImageView ivback=findViewById(R.id.iv_tjyhk_back);
        num = findViewById(R.id.et_ckrnum);
        Button bt=findViewById(R.id.bt_add);
        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCard(name.getText().toString(), num.getText().toString());
            }
        });
    }

    private void addCard(String s, String s1) {

        String str=GetSign.getSign();
        String[] strs=str.split(",");
        DebugFlags.logD("签名"+str);


        OkHttpUtils.post()

                .url(Constants.BASE_URL +"Api/User/bind_bankcard")
                .addParams("t",strs[0])
                .addParams("r",strs[1])
                .addParams("e",strs[2])


                .addParams("token",SharedPrefUtil.getString(AddUnionPayingCardActivity.this,"token",""))
                .addParams("real_name",s)
                .addParams("bank_number",s1)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        DebugFlags.logD("添加银行卡"+response);
                        try {
                            JSONObject js=new JSONObject(response);
                          ToastUtil.show( js.getString("message"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });


    }
}