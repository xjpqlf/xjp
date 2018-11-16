package ui.zlz.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import ui.zlz.R;
import ui.zlz.activity.Constants;
import ui.zlz.activity.account.RechargeActivity;
import ui.zlz.activity.account.updateinfo.SettingPayingPasswordActivity;
import ui.zlz.adapter.UserCouponAdapter;
import ui.zlz.bean.Asset;
import ui.zlz.bean.CouponBean;
import ui.zlz.utility.DebugFlags;
import ui.zlz.utility.GetSign;
import ui.zlz.utility.SharedPrefUtil;
import ui.zlz.utility.ToastUtil;
import ui.zlz.widget.AlertDialog;
import ui.zlz.widget.MyDialog;

/**
 * 底部弹窗Fragment
 */
public class PayDetailFragment extends DialogFragment {
    private RelativeLayout rePayWay, rePayDetail, reBalance,reYhz;
    private LinearLayout LinPayWay;
    private ListView lv;
    private Button btnPay;
    private EditText mEditText;     //编辑框
    private EditText gridPasswordView;
    LinearLayout mEditLayout;

    private ImageView imageCloseOne,imageCloseTwo,imageCloseThree;
    private TextView price,yue;
    private List<CouponBean.DataBeanX.DataBean> list=new ArrayList<>();
    private  int i=1;
    private String id="";
    private TextView tv_yhq;

    public static PayDetailFragment newInstance(String id,String price,String gid) {
        PayDetailFragment fragment = new PayDetailFragment();
        Bundle args = new Bundle();

        args.putString("yue", id);
        args.putString("price", price);
        args.putString("goodid", gid);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //引入EditText的布局文件，找到EditText控件
        LayoutInflater editInflater = LayoutInflater.from(getActivity());
        mEditLayout = (LinearLayout) editInflater.inflate(R.layout.edit_layout, null);
        mEditText = (EditText) mEditLayout.findViewById(R.id.editText);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        Dialog dialog = new Dialog(getActivity(), R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        dialog.setContentView(R.layout.fragment_pay_detail);
        dialog.setCanceledOnTouchOutside(true); // 外部点击取消
        // 设置宽度为屏宽, 靠近屏幕底部。
        final Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.AnimBottom);
        final WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM; // 紧贴底部
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        lp.height = getActivity().getWindowManager().getDefaultDisplay().getHeight() * 3 / 5;
        window.setAttributes(lp);

        initView(dialog);


        if (getDialog() != null) {
            getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface anInterface, int keyCode, KeyEvent event) {
                    if(keyCode==KeyEvent.KEYCODE_ENTER&&event.getAction()==KeyEvent.ACTION_DOWN){
                        if(!TextUtils.isEmpty(gridPasswordView.getText().toString().trim())){
                            if("123456".equals(gridPasswordView.getText().toString().trim())){
                                //TODO 跳转支付宝支付
                            }
                        }
                    }else{
                        Toast.makeText(getContext(),"密码不能为空",Toast.LENGTH_SHORT).show();
                    }
                    return false;
                }
            });
        }



        return dialog;
    }

    private void initView(Dialog dialog) {



        price = dialog.findViewById(R.id.tv_price);
        yue = dialog.findViewById(R.id.tv_yue);

        //余额
        rePayWay = (RelativeLayout) dialog.findViewById(R.id.re_pay_yue);
        reYhz = (RelativeLayout) dialog.findViewById(R.id.rl_yhj);
        rePayDetail = (RelativeLayout) dialog.findViewById(R.id.re_pay_detail);//付款详情
        LinPayWay = (LinearLayout) dialog.findViewById(R.id.lin_pay_way);//付款方式
        lv = (ListView) dialog.findViewById(R.id.lv_bank);//付款方式（银行卡列表）

        btnPay = (Button) dialog.findViewById(R.id.btn_confirm_pay);
        gridPasswordView = (EditText) dialog.findViewById(R.id.pass_view);
        tv_yhq = dialog.findViewById(R.id.tv_yhj_pay);

        imageCloseOne= (ImageView) dialog.findViewById(R.id.close_one);
        imageCloseTwo= (ImageView) dialog.findViewById(R.id.close_two);
        if (getArguments()!=null){

            price.setText("¥"+getArguments().getString("price"));
          //  yue.setText(getArguments().getString("yue"));
        }
       getUserInfo();
              getVoucher(i);



        reYhz.setOnClickListener(listener);
      //  reBalance.setOnClickListener(listener);
        btnPay.setOnClickListener(listener);
        imageCloseOne.setOnClickListener(listener);
        imageCloseTwo.setOnClickListener(listener);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                id = list.get(i).getId();
                rePayDetail.setVisibility(View.VISIBLE);

                LinPayWay.setVisibility(View.GONE);
                if ("2".equals(list.get(i).getType())){



                    tv_yhq.setText(list.get(i).getMoney()+"%"+"加息劵");


                }else if("1".equals(list.get(i).getType())){

                   tv_yhq.setText("¥"+list.get(i).getMoney()+"代金券");

                }

            }
        });

    }

    private void getUserInfo() {
        String str=GetSign.getSign();
        String[] strs=str.split(",");
        DebugFlags.logD("签名"+str);


        OkHttpUtils.post()

                .url(Constants.BASE_URL +"/Api/User/asset/")
                .addParams("t",strs[0])
                .addParams("r",strs[1])
                .addParams("e",strs[2])
                //   .addParams("mobile","13071266270")
                .addParams("token",SharedPrefUtil.getString(getActivity(),"token",""))


                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        DebugFlags.logD("资产信息"+response);
                        Asset asset=JSON.parseObject(response,Asset.class);
                        DebugFlags.logD("资产信息"+asset.getData().getUser_earnings()+"=="+asset.getData().getUser_earnings());
                        yue.setText(asset.getData().getUser_money());





                    }
                });


    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Animation slide_left_to_left = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_left_to_left);
            Animation slide_right_to_left = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_right_to_left);
            Animation slide_left_to_right = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_left_to_right);
            Animation slide_left_to_left_in = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_left_to_left_in);
            switch (view.getId()) {
                case R.id.rl_yhj://跳转优惠券


                    if (list.size()==0){
                        ToastUtil.show("暂无优惠券");
                        return;
                    }
                    rePayDetail.startAnimation(slide_left_to_left);
                    rePayDetail.setVisibility(View.GONE);
                    LinPayWay.startAnimation(slide_right_to_left);
                    LinPayWay.setVisibility(View.VISIBLE);
                    break;
             /*   case R.id.re_balance:
                    rePayDetail.startAnimation(slide_left_to_left_in);
                    rePayDetail.setVisibility(View.VISIBLE);
                    LinPayWay.startAnimation(slide_left_to_right);
                    LinPayWay.setVisibility(View.GONE);
                    break;*/
                case R.id.btn_confirm_pay://确认付款
                 /*   rePayDetail.startAnimation(slide_left_to_left);
                    rePayDetail.setVisibility(View.GONE);*/
                 if ("1".equals(SharedPrefUtil.getString(getActivity(),"ispay",""))) {
                         if (Double.valueOf(getArguments().getString("price"))>(Double.valueOf(yue.getText().toString())))
                         {
                             showAlerCzDialog();
                             return;

                         }
                     showPwd();
                 }else {

                     showAlerDialog();

                 }
                    break;
                case R.id.close_one:
                    getDialog().dismiss();
                    break;
                case R.id.close_two:


                    rePayDetail.setVisibility(View.VISIBLE);

                    LinPayWay.setVisibility(View.GONE);


                    break;

                default:
                    break;
            }
        }
    };

    private void showPwd() {

        new MyDialog(getActivity())
                .setTitle("请输入支付密码")
                .setCancelable(false)
                .setView(mEditLayout)
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       //开始投资支付
                        DebugFlags.logD("投资"+mEditText.getText().toString()+"id"+id+"goods_id"+getArguments().getString("goodid")+"tz_money"+getArguments().getString("price"));
                      goToPay(mEditText.getText().toString());

                    }
                })
                .builder()
                .show();

    }
    //支付

    private void goToPay(String pwd) {

        String str=GetSign.getSign();
        String[] strs=str.split(",");
        DebugFlags.logD("签名"+str);


        OkHttpUtils.post()

                .url(Constants.BASE_URL +"/Api/User/user_invest/" )
                .addParams("t",strs[0])
                .addParams("r",strs[1])
                .addParams("e",strs[2])
                //   .addParams("mobile","13071266270")
                .addParams("token",SharedPrefUtil.getString(getActivity(),"token",""))
                .addParams("goods_id",getArguments().getString("goodid"))
                .addParams("tz_money",getArguments().getString("price"))
                .addParams("pay_pwd",pwd)
                .addParams("coupons_id",id)

                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        DebugFlags.logD("付款"+response);
                        try {
                            JSONObject js=new JSONObject(response);

                            if (js.getString("code").equals("1")){
                                ToastUtil.show(js.getString("message"));
                                getDialog().dismiss();
                            }else {
                                ToastUtil.show(js.getString("message"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });




    }


    private void showAlerDialog() {
        new AlertDialog(getActivity())
                .builder()
                .setTitle("提示")
                .setMsg("你还没有设置支付密码")
                .setPositiveButton("去设置", new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        startActivity(new Intent(getActivity(),SettingPayingPasswordActivity.class));

                    } })
                .setNegativeButton("取消", new View.OnClickListener() {
                    @Override public void onClick(View v) {

                    } })
                .show();


    }
    private void getVoucher(int i) {
        String str=GetSign.getSign();
        String[] strs=str.split(",");
        DebugFlags.logD("签名"+str);


        OkHttpUtils.post()

                .url(Constants.BASE_URL +"/Api/User/user_coupons")
                .addParams("t",strs[0])
                .addParams("r",strs[1])
                .addParams("e",strs[2])
                //   .addParams("mobile","13071266270")
                .addParams("page",i+"")
                .addParams("token",SharedPrefUtil.getString(getActivity(),"token",""))


                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        DebugFlags.logD("我的优惠券"+response);

                        CouponBean all=    JSON.parseObject(response,CouponBean.class);

                        if (all.getCode()==1) {
                            for (int j = 0; j <all.getData().getData().size() ; j++) {
                                if (all.getData().getData().get(j).getIs_use().equals("0")){
                                    list.add(all.getData().getData().get(j));
                                }
                            }




                            UserCouponAdapter adapter=new UserCouponAdapter(getActivity(),list);
                            lv.setAdapter(adapter);



                        } else {


                        }

                    }
                });




    }
    private void showAlerCzDialog() {
        new AlertDialog(getActivity())
                .builder()
                .setTitle("提示")
                .setMsg("余额不足请充值")
                .setPositiveButton("去充值", new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        startActivity(new Intent(getActivity(),RechargeActivity.class));

                    } })
                .setNegativeButton("取消", new View.OnClickListener() {
                    @Override public void onClick(View v) {

                    } })
                .show();


    }



}
