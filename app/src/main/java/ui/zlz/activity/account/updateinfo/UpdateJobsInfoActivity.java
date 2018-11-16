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

public class UpdateJobsInfoActivity extends Activity implements View.OnClickListener {

    private EditText etwork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_jobs_info);
        init();
    }

    private void init() {
      ImageView ivback= findViewById(R.id.iv_back_work);
        etwork = findViewById(R.id.et_work);
        Button bt=findViewById(R.id.bt_udatework);
        ivback.setOnClickListener(this);
        bt.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.iv_back_work:
                finish();
                break;
            case R.id.bt_udatework:
                Updatework();

        }
    }

    private void Updatework() {

        String str=GetSign.getSign();
        String[] strs=str.split(",");
        DebugFlags.logD("签名"+str);


        OkHttpUtils.post()

                .url(Constants.BASE_URL +"Api/User/edit_user_name")
                .addParams("t",strs[0])
                .addParams("r",strs[1])
                .addParams("e",strs[2])
                //   .addParams("mobile","13071266270")
                .addParams("token",SharedPrefUtil.getString(UpdateJobsInfoActivity.this,"token",""))
                .addParams("realname",etwork.getText().toString())

                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        DebugFlags.logD("更新工作"+response);
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
