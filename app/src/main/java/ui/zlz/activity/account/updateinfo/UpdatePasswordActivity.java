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

public class UpdatePasswordActivity extends Activity implements View.OnClickListener {

    private EditText newpwd;
    private EditText olderpwd;
    private EditText newpwdagin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);
        init();
    }

    private void init() {
        ImageView ivback=findViewById(R.id.iv_update_pwd);
        olderpwd = findViewById(R.id.et_oldpwd);
        newpwd = findViewById(R.id.et_newpwd);
        newpwdagin = findViewById(R.id.et_newpwd_agins);
        Button bt=findViewById(R.id.bt_update_pwd);
        ivback.setOnClickListener(this);
        bt.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.iv_update_pwd:
                finish();
                break;
            case R.id.bt_update_pwd:

                   UpdaePwd();
                   
                   

                break;
        }

    }

    private void UpdaePwd() {

        String str=GetSign.getSign();
        String[] strs=str.split(",");
        DebugFlags.logD("签名"+str);


        OkHttpUtils.post()

                .url(Constants.BASE_URL +"Api/User/edit_login_pwd")
                .addParams("t",strs[0])
                .addParams("r",strs[1])
                .addParams("e",strs[2])
                //   .addParams("mobile","13071266270")
                .addParams("token",SharedPrefUtil.getString(this,"token",""))
                .addParams("old_pwd",olderpwd.getText().toString())
                .addParams("new_pwd",newpwd.getText().toString())
                .addParams("repeat_new_pwd",newpwdagin.getText().toString())


                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            JSONObject js=new JSONObject(response);
                            js.getString("message");
                            ToastUtil.show(js.getString("message"));



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

    }
}
