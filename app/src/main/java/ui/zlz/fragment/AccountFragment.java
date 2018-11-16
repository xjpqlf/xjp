package ui.zlz.fragment;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import ui.zlz.R;
import ui.zlz.activity.Constants;
import ui.zlz.activity.account.AccountInfoActivity;
import ui.zlz.activity.account.AllLeasesActivity;
import ui.zlz.activity.account.CustomerServiceTL;
import ui.zlz.activity.account.FundsDetailActivity;
import ui.zlz.activity.account.IncomeAndExpenditureActivity;
import ui.zlz.activity.account.LoginActivity;
import ui.zlz.activity.account.MsgCenterActivity;
import ui.zlz.activity.account.MyInvitationActivity;
import ui.zlz.activity.account.MyOrdersActivity;
import ui.zlz.activity.account.MyVoucherPackageActivity;
import ui.zlz.activity.account.RechargeActivity;
import ui.zlz.activity.account.TiXianActivity;
import ui.zlz.bean.Asset;
import ui.zlz.utility.DebugFlags;
import ui.zlz.utility.GetSign;
import ui.zlz.utility.SharedPrefUtil;
import ui.zlz.utility.ToastUtil;
import ui.zlz.widget.AlertDialog;

/**
 * Created by chenzhe on 2018/2/8.
 */

public class AccountFragment extends BaseFragment{


    private TextView tvsum;
    private TextView ye;
    private TextView lj;
    private TextView tixian;
    private CircleImageView ivtx;

    @Override
    protected int setContentView() {
        return R.layout.fragment_account;
    }

    @Override
    protected void startLoad() {
        if (!TextUtils.isEmpty(SharedPrefUtil.getString(getActivity(),"token",""))){
            getInfo();
        }
    }

    @Override
    protected void initData() {

       DebugFlags.logD("个人信息"+SharedPrefUtil.getString(getActivity(),"token",""));

        tvsum = findViewById(R.id.tv_sunmoney_num);
        ye = findViewById(R.id.tv_ye);
        lj = findViewById(R.id.tv_lj);
        TextView tixian= findViewById(R.id.tv_tx);
        TextView cz= findViewById(R.id.tv_cz);
        ivtx = findViewById(R.id.iv_tx);
        ImageView ivxx=findViewById(R.id.iv_xiaoxi);
        ImageView ivjl=findViewById(R.id.iv_srjl);
        RelativeLayout jb=findViewById(R.id.rl_jb);
        RelativeLayout tj=findViewById(R.id.rl_tj);
        RelativeLayout ddcx=findViewById(R.id.rl_ddcx);
        RelativeLayout zq=findViewById(R.id.rl_zq);
        RelativeLayout yq=findViewById(R.id.rl_yq);
        RelativeLayout kf=findViewById(R.id.rl_kf);
        if (!TextUtils.isEmpty(SharedPrefUtil.getString(getActivity(),"headerImg",""))){
            Glide.with(getActivity()).load(Constants.BASE_URL+SharedPrefUtil.getString(getActivity(),"headerImg","")).error(R.mipmap.touxiang).fitCenter().into(ivtx);
        }
        {


            getUserInfo();
        }


        ivxx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),MsgCenterActivity.class));
            }
        });
        ivjl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),IncomeAndExpenditureActivity.class));
            }
        });
        //头像点击
        ivtx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),AccountInfoActivity.class));
            }
        });







        //充值
        cz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),RechargeActivity.class));
            }
        }); //提现
        tixian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),TiXianActivity.class));
            }
        });
        //我的券包
       jb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),MyVoucherPackageActivity.class));

            }
        });
        //账户统计
        tj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),FundsDetailActivity.class));

            }
        });
        //订单查询
        ddcx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),MyOrdersActivity.class));
            }
        });
        //我的租权
        zq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),AllLeasesActivity.class));
            }
        });
        //好友邀请
        yq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),MyInvitationActivity.class));

            }
        });
        //在线客服
        kf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),CustomerServiceTL.class));

            }
        });

    }
//获取图像
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
                .addParams("token",SharedPrefUtil.getString(getActivity(),"token",""))


                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            org.json.JSONObject json =new org.json.JSONObject(response);
                            DebugFlags.logD(response+"个人信息头像");
                            String data=json.getString("data");
                            JSONObject datas =new JSONObject(data);
                            String data1=datas.getString("data");

                            JSONObject js=new JSONObject(data1);
                            String headerImg=js.getString("header");

                            SharedPrefUtil.saveString(getActivity(),"headerImg",headerImg);
                            if (!headerImg.startsWith("http")) {
                                Glide.with(getActivity()).load(Constants.BASE_URL + headerImg.replaceAll("\\\\", "")).error(R.mipmap.touxiang).fitCenter().into(ivtx);
                            }else {
                                Glide.with(getActivity()).load( headerImg.replaceAll("\\\\", "")).error(R.mipmap.touxiang).fitCenter().into(ivtx);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

    }

    private void getInfo() {

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


                        if (asset.getCode()==1) {
                            DebugFlags.logD("资产信息"+asset.getData().getUser_earnings()+"=="+asset.getData().getUser_earnings());
                            SharedPrefUtil.saveString(getActivity(), "zongzhichan", asset.getData().getAll_asset());

                            SharedPrefUtil.saveString(getActivity(), "leijishouyi", asset.getData().getUser_earnings());
                            SharedPrefUtil.saveString(getActivity(), "yuee", asset.getData().getUser_money());


                            tvsum.setText(asset.getData().getAll_asset());
                            ye.setText(asset.getData().getUser_money());
                            lj.setText(asset.getData().getUser_earnings());
                        }else if (asset.getCode()==2){
                            showAlerDialog();


                        }else {
                            ToastUtil.show(asset.getMessage());
                        }

                    }
                });

    }


    @Override
    public void onResume() {
        super.onResume();

        if (!SharedPrefUtil.getString(getActivity(),"headerImg","").startsWith("http")) {
            Glide.with(getActivity()).load(Constants.BASE_URL + SharedPrefUtil.getString(getActivity(),"headerImg","").replaceAll("\\\\", "")).error(R.mipmap.touxiang).fitCenter().into(ivtx);
        }else {
            Glide.with(getActivity()).load( SharedPrefUtil.getString(getActivity(),"headerImg","").replaceAll("\\\\", "")).error(R.mipmap.touxiang).fitCenter().into(ivtx);

        }

    }

    private   void showAlerDialog() {

        new AlertDialog(getActivity())
                .builder()
                .setTitle("重新登录提示")
                .setCancelable(false)
                .setMsg("你的账号已在其他设备登录,请重新登录登录")
                .setPositiveButton("登录", new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        startActivity(new Intent(getActivity(),LoginActivity.class));
                       getActivity(). finish();
                    } })
                .setNegativeButton("取消", new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        getActivity().finish();
                    } })
                .show();


    }

}
