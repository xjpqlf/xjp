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

public class BindingEmailActivity extends Activity implements View.OnClickListener {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binding_email);
        init();
    }

    private void init() {
        ImageView iv=findViewById(R.id.iv_email_back);
        editText = findViewById(R.id.et_email);
        Button bt=findViewById(R.id.bt_bind_email);
        iv.setOnClickListener(this);
        bt.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_email_back:
                finish();
            break;

            case R.id.bt_bind_email:
                BindEmail();
                break;

        }

    }

    private void BindEmail() {

        String str=GetSign.getSign();
        String[] strs=str.split(",");
        DebugFlags.logD("签名"+str);


        OkHttpUtils.post()

                .url(Constants.BASE_URL +"Api/User/edit_user_email")
                .addParams("t",strs[0])
                .addParams("r",strs[1])
                .addParams("e",strs[2])
                //   .addParams("mobile","13071266270")
                .addParams("token",SharedPrefUtil.getString(this,"token",""))
                .addParams("email",editText.getText().toString())


                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        DebugFlags.logD("更新姓名"+response);
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
