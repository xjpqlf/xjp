package ui.zlz.activity.account;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

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

public class ClickingUnionPayingCardActivity extends Activity {

private PopupWindow mPopWindow;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicking_union_paying_card);
    initView();



    }

    private void initView() {
       ImageView iv= findViewById(R.id.iv_click_back);
       ImageView bind= findViewById(R.id.iv_bind);
        TextView  name=findViewById(R.id.tv_name);
        TextView  num=findViewById(R.id.tv_num);
        TextView  tel=findViewById(R.id.tv_untel);
        if (getIntent()!=null){
            name.setText(getIntent().getStringExtra("name"));
            tel.setText("交易限额咨询 ："+getIntent().getStringExtra("tel"));
            num.setText(getIntent().getStringExtra("num"));
            id = getIntent().getStringExtra("id");

        }
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        bind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPop();
            }
        });


    }
    private void showPop() {

        View contentView = LayoutInflater.from(ClickingUnionPayingCardActivity.this).inflate(R.layout.popwindow_unpay, null);
        mPopWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopWindow.setContentView(contentView);


        TextView iv= contentView.findViewById(R.id.tv_jcbd);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unBindCard();
                mPopWindow.dismiss();

            }
        });

        TextView iv1= contentView.findViewById(R.id.tv_cancle_bind);
        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopWindow.dismiss();
            }
        });



        setBackgroundAlpha(0.3f);
        // mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        View rootViews = findViewById(R.id.rl_invi);

        //显示PopupWindow
        View rootview = LayoutInflater.from(ClickingUnionPayingCardActivity.this).inflate(R.layout.activity_clicking_union_paying_card, null);
        //   mPopWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
        mPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() { // popupWindow隐藏时恢复屏幕正常透明度

                setBackgroundAlpha(1.0f);
            }
        });






    }

    private void unBindCard() {



        String str=GetSign.getSign();
        String[] strs=str.split(",");
        DebugFlags.logD("签名"+str);


        OkHttpUtils.post()

                .url(Constants.BASE_URL +"Api/User/untie_bankcard")
                .addParams("t",strs[0])
                .addParams("r",strs[1])
                .addParams("e",strs[2])


                .addParams("token",SharedPrefUtil.getString(ClickingUnionPayingCardActivity.this,"token",""))
                  .addParams("bank_id",id)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        DebugFlags.logD("解除绑定"+response);
                        try {
                            JSONObject js=new JSONObject(response);
                            ToastUtil.show(js.getString("message"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });

    }


    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = (ClickingUnionPayingCardActivity.this.getWindow().getAttributes());
        lp.alpha = bgAlpha;
        ClickingUnionPayingCardActivity.this.getWindow().setAttributes(lp);
    }
}
