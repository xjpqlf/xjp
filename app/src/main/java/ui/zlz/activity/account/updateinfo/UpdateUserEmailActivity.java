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

public class UpdateUserEmailActivity extends Activity implements View.OnClickListener {

    private EditText etemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_email);
        init();
    }

    private void init() {
      ImageView iv=  findViewById(R.id.iv_email_back);
        etemail = findViewById(R.id.et_email);
        Button button=findViewById(R.id.bt_updata_email);
        iv.setOnClickListener(this);
        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_email_back:
                finish();
                break;
                case R.id.bt_updata_email:
                  UpdataEmail();

                break;

        }

    }

    private void UpdataEmail() {

        String str=GetSign.getSign();
        String[] strs=str.split(",");
        DebugFlags.logD("签名"+str);


        OkHttpUtils.post()

                .url(Constants.BASE_URL +"Api/User/edit_user_email")
                .addParams("t",strs[0])
                .addParams("r",strs[1])
                .addParams("e",strs[2])
                //   .addParams("mobile","13071266270")
                .addParams("token",SharedPrefUtil.getString(UpdateUserEmailActivity.this,"token",""))
                .addParams("email",etemail.getText().toString())


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
