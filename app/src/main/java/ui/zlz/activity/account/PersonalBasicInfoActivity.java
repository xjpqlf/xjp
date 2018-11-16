package ui.zlz.activity.account;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

import okhttp3.Call;
import ui.zlz.R;
import ui.zlz.activity.Constants;
import ui.zlz.activity.account.updateinfo.UpdateHouseActivity;
import ui.zlz.activity.account.updateinfo.UpdateJobsInfoActivity;
import ui.zlz.activity.account.updateinfo.UpdateUserEmailActivity;
import ui.zlz.activity.account.updateinfo.UpdateUserNameActivity;
import ui.zlz.adapter.SpinnearBean;
import ui.zlz.utility.DebugFlags;
import ui.zlz.utility.GetSign;
import ui.zlz.utility.SharedPrefUtil;
import ui.zlz.utility.ToastUtil;

public class PersonalBasicInfoActivity extends Activity implements View.OnClickListener {

    private ImageView sfrz;
    private ImageView sjrz;
    private TextView tv;
    private TextView tvphone;
    private ArrayList<SpinnearBean> mHobbyList;
    private ArrayList<String> mHobbyNameList;//用于选择器显示
    private OptionsPickerView mHobbyPickerView;//选择器
    public static final String LISTROOTNODE = "spinnerList";
    public static final String KEY_LISTITEM_NAME = "paraName";
    public static final String KEY_LISTITEM_VALUE = "paraValue";
    public static final String KEY_LISTITEM_CHECKCOLOR = "checkColor";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_basic_info);
        init();
    }

    private void init() {
       ImageView ivback= findViewById(R.id.iv_person_back);
       RelativeLayout name= findViewById(R.id.rl_name);
        sfrz = findViewById(R.id.iv_sfrz);
        sjrz = findViewById(R.id.iv_sjrz);
        tv = findViewById(R.id.tv_hasindefind);
        tvphone = findViewById(R.id.tv_hasphoneindefind);
        RelativeLayout email= findViewById(R.id.rl_emil);
        RelativeLayout edu= findViewById(R.id.rl_edu);
        RelativeLayout work= findViewById(R.id.rl_work);
        RelativeLayout house= findViewById(R.id.rl_house);
       ivback.setOnClickListener(this);
        name.setOnClickListener(this);

        email.setOnClickListener(this);
        edu.setOnClickListener(this);
        work.setOnClickListener(this);
        house.setOnClickListener(this);
        getUserInfo();

    }
//获取个人信息
    private void getUserInfo() {
        String str=GetSign.getSign();
        String[] strs=str.split(",");
        DebugFlags.logD("签名"+str);


        OkHttpUtils.post()

                .url(Constants.BASE_URL +"Api/User/get_user_info")
                .addParams("t",strs[0])
                .addParams("r",strs[1])
                .addParams("e",strs[2])
                //   .addParams("mobile","13071266270")
                .addParams("token",SharedPrefUtil.getString(PersonalBasicInfoActivity.this,"token",""))


                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        DebugFlags.logD("个人基础信息"+response);

                        try {
                            JSONObject json =new JSONObject(response);
                            String data=json.getString("data");
                            JSONObject datas =new JSONObject(data);
                            String data1=datas.getString("data");
                            JSONObject js=new JSONObject(data1);
                         String isaut=   js.getString("is_authentication");
                         String mobile=   js.getString("mobile");
                            DebugFlags.logD("个人基础信息"+isaut);
                            DebugFlags.logD("个人基础信息"+mobile);
                            if ("1".equals(isaut)) {
                                tv.setText("已认证");
                                sfrz.setVisibility(View.VISIBLE);
                                sfrz.setImageResource(R.mipmap.ren_zheng);

                            }else {
                                tv.setText("未认证");
                                sfrz.setVisibility(View.GONE);
                            }
                            if (TextUtils.isEmpty(mobile)){
                                tvphone.setText("未认证");
                                sjrz.setImageResource(R.mipmap.ren_zheng);

                            }else {
                                sjrz.setVisibility(View.VISIBLE);
                              tvphone.setText("已认证");


                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });


    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){

            case R.id.iv_person_back:
                finish();
                break;
            case R.id.rl_name:
                startActivity(new Intent(PersonalBasicInfoActivity.this,UpdateUserNameActivity.class));
                break;


            case R.id.rl_emil:
                startActivity(new Intent(PersonalBasicInfoActivity.this,UpdateUserEmailActivity.class));
                break;
            case R.id.rl_edu:
                //学历选择器
                //========================================初始化爱好列表集合========================================
                mHobbyList = new ArrayList<SpinnearBean>();
                mHobbyNameList = new ArrayList<String>();

                //模拟获取数据集合
                try{
                    mHobbyList = parseJsonArray("spinners.txt");
                }catch (Exception e) {
                    e.printStackTrace();
                }
                for(SpinnearBean spinnearBean : mHobbyList){
                    mHobbyNameList.add(spinnearBean.getParaName());
                }



                initHobbyOptionPicker();
                mHobbyPickerView.show();



                break;
            case R.id.rl_work:
                startActivity(new Intent(PersonalBasicInfoActivity.this,UpdateJobsInfoActivity.class));
                break;

            case R.id.rl_house:
                startActivity(new Intent(PersonalBasicInfoActivity.this,UpdateHouseActivity.class));
                break;


        }

    }

    private void initHobbyOptionPicker() {

        mHobbyPickerView = new OptionsPickerBuilder(PersonalBasicInfoActivity.this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = mHobbyNameList.get(options1);
               //上传学历
                UpdaEdu(tx);

            }
        })
                .setDecorView((RelativeLayout)findViewById(R.id.activity_rootview))//必须是RelativeLayout，不设置setDecorView的话，底部虚拟导航栏会显示在弹出的选择器区域
                .setTitleText("选择学历")//标题文字
                .setTitleSize(20)//标题文字大小
                .setTitleColor(getResources().getColor(R.color.pickerview_title_text_color))//标题文字颜色
                .setCancelText("取消")//取消按钮文字
                .setCancelColor(getResources().getColor(R.color.pickerview_cancel_text_color))//取消按钮文字颜色
                .setSubmitText("确定")//确认按钮文字
                .setSubmitColor(getResources().getColor(R.color.pickerview_submit_text_color))//确定按钮文字颜色
                .setContentTextSize(20)//滚轮文字大小
                .setTextColorCenter(getResources().getColor(R.color.pickerview_center_text_color))//设置选中文本的颜色值
                .setLineSpacingMultiplier(1.8f)//行间距
                .setDividerColor(getResources().getColor(R.color.pickerview_divider_color))//设置分割线的颜色
                .setSelectOptions(0)//设置选择的值
                .build();

        mHobbyPickerView.setPicker(mHobbyNameList);//添加数据


    }

    private void UpdaEdu(String tx) {

            String str=GetSign.getSign();
            String[] strs=str.split(",");


            OkHttpUtils.post()
                    .url(Constants.BASE_URL +"Api/User/edit_user_education")
                    .addParams("t",strs[0])
                    .addParams("r",strs[1])
                    .addParams("e",strs[2])
                    .addParams("token",SharedPrefUtil.getString(PersonalBasicInfoActivity.this,"token",""))
                    .addParams("education",tx)

                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {

                        }

                        @Override
                        public void onResponse(String response, int id) {
                            DebugFlags.logD("更新学历"+response);
                            try {
                                JSONObject json=new JSONObject(response);
                                if ("1".equals(json.get("code"))){
                                    ToastUtil.show(json.getString("message"));

                                }
                                else {
                                    ToastUtil.show(json.getString("message"));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    });


        }








    private ArrayList<SpinnearBean> parseJsonArray(String fileName) throws Exception{

        ArrayList<SpinnearBean> itemsList = new ArrayList<SpinnearBean>();

        String jsonStr = getStringFromAssert(PersonalBasicInfoActivity.this, fileName);
        if(jsonStr.equals("")){
            return null;
        }
        JSONObject allData = new JSONObject(jsonStr);  //全部内容变为一个项
        JSONArray jsonArr = allData.getJSONArray(LISTROOTNODE); //取出数组
        for(int x = 0;x<jsonArr.length();x++){
            SpinnearBean model = new SpinnearBean();
            JSONObject jsonobj = jsonArr.getJSONObject(x);
            model.setParaName(jsonobj.getString(KEY_LISTITEM_NAME));
            model.setParaValue(jsonobj.getString(KEY_LISTITEM_VALUE));
            if(jsonobj.has(KEY_LISTITEM_CHECKCOLOR)){
                model.setCheckColor(jsonobj.getString(KEY_LISTITEM_CHECKCOLOR));
            }
            model.setSelectedState(false);
            itemsList.add(model);
            model = null;
        }
        return itemsList;
    }

    /**
     * 访问assets目录下的资源文件，获取文件中的字符串
     * @param filePath - 文件的相对路径，例如："listdata.txt"或者"/www/listdata.txt"
     * @return 内容字符串
     * */
    public String getStringFromAssert(Context mContext, String filePath) {

        String content = ""; // 结果字符串
        try {
            InputStream is = mContext.getResources().getAssets().open(filePath);// 打开文件
            int ch = 0;
            ByteArrayOutputStream out = new ByteArrayOutputStream(); // 实现了一个输出流
            while ((ch = is.read()) != -1) {
                out.write(ch); // 将指定的字节写入此 byte 数组输出流
            }
            byte[] buff = out.toByteArray();// 以 byte 数组的形式返回此输出流的当前内容
            out.close(); // 关闭流
            is.close(); // 关闭流
            content = new String(buff, "UTF-8"); // 设置字符串编码
        } catch (Exception e) {
            Toast.makeText(mContext, "对不起，没有找到指定文件！", Toast.LENGTH_SHORT)
                    .show();
        }
        return content;
    }

}
