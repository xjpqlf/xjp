package ui.zlz.activity.account.updateinfo;

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

public class VerifiedActivity extends Activity implements View.OnClickListener {

    private EditText etname;
    private EditText etnum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verified);
        init();
    }

    private void init() {
        ImageView iv=findViewById(R.id.iv_idcard_back);
        etname = findViewById(R.id.et_idname);
        etnum = findViewById(R.id.et_idnum);
        Button bt=findViewById(R.id.bt_updata_id);
        iv.setOnClickListener(this);
        bt.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

         case   R.id.iv_idcard_back:
            finish();
            break;

            case R.id.bt_updata_id:
                UpdateId();

            break;
        }
    }

    private void UpdateId() {

        String str=GetSign.getSign();
        String[] strs=str.split(",");
        DebugFlags.logD("签名"+str);


        OkHttpUtils.post()

                .url(Constants.BASE_URL +"/Api/User/realname")
                .addParams("t",strs[0])
                .addParams("r",strs[1])
                .addParams("e",strs[2])
                //   .addParams("mobile","13071266270")
                .addParams("token",SharedPrefUtil.getString(VerifiedActivity.this,"token",""))
                .addParams("realname",etname.getText().toString())
                .addParams("card_number",etnum.getText().toString())

                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        DebugFlags.logD("实名验证"+response);
                        try {
                            JSONObject js=new JSONObject(response);
                            ToastUtil.show(js.getString("message"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });


    }
}
