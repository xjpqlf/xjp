package ui.zlz.tenant;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;
import okhttp3.Call;
import ui.zlz.R;
import ui.zlz.activity.Constants;
import ui.zlz.activity.account.LoginActivity;
import ui.zlz.activity.account.MsgCenterActivity;
import ui.zlz.activity.account.MyVoucherPackageActivity;
import ui.zlz.adapter.TabFragmentAdapter;
import ui.zlz.bean.GoodsDetailBean;
import ui.zlz.fragment.PayDetailFragment;
import ui.zlz.fragment.ProductQuestionFragment;
import ui.zlz.fragment.ProductRecordFragment;
import ui.zlz.fragment.ProductServiceFragment;
import ui.zlz.home.ContractActivity;
import ui.zlz.home.HomeSafety;
import ui.zlz.utility.DataUtils;
import ui.zlz.utility.DebugFlags;
import ui.zlz.utility.GetSign;
import ui.zlz.utility.SharedPrefUtil;
import ui.zlz.utility.ToastUtil;
import ui.zlz.widget.AlertDialog;
import ui.zlz.widget.ClearEditText;
import ui.zlz.widget.ViewPagerForScrollView;

public class TenantProductServeActivity extends AppCompatActivity implements View.OnClickListener {

    private BGABanner banner;
    private TextView fwzq;
    private TextView hbsy;
    private TextView tzze;
    private TextView ksrq;
    private TextView jsri;
    private ProgressBar pb;
    private GoodsDetailBean gb;
    private List<String> listImgs=new ArrayList<>();
    private TextView sytz;
    TabLayout tabLayout;
    ViewPagerForScrollView viewPager;
    private List<String> tabList;
    private List<Fragment> fragmentList;
    private ImageView iv;
    private ClearEditText editText;
    private String goodsid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenant_product_serve);
        initView();

    }

    private void initTab() {

        tabLayout = findViewById(R.id.tl_abt1);
        viewPager = findViewById(R.id.vp_abt1);

        tabList = new ArrayList<>();
        tabList.add("服务介绍");
        tabList.add("来租记录");
        tabList.add("常见问题");

        tabLayout.addTab(tabLayout.newTab().setText(tabList.get(0)));//添加tab选项卡
        tabLayout.addTab(tabLayout.newTab().setText(tabList.get(1)));
        tabLayout.addTab(tabLayout.newTab().setText(tabList.get(2)));

        tabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
        //此处代码设置无效，不知道为啥？？？xml属性是可以的
        //tabLayout.setTabTextColors(android.R.color.white, android.R.color.holo_red_dark);
        // 设置TabLayout两种状态

        fragmentList = new ArrayList<>();

        Fragment f1 =ProductServiceFragment.newInstance(gb.getData().getData().getService_content());//
        Fragment f2 =ProductRecordFragment.newInstance(getIntent().getStringExtra("recomdId"));//
        Fragment f3 = new ProductQuestionFragment(); //

        fragmentList.add(f1);
        fragmentList.add(f2);
        fragmentList.add(f3);

        TabFragmentAdapter fragmentAdapter = new TabFragmentAdapter(getSupportFragmentManager(), fragmentList, tabList);
        viewPager.setAdapter(fragmentAdapter);//给ViewPager设置适配器
        tabLayout.setupWithViewPager(viewPager);//将TabLayout和ViewPager关联起来。
        tabLayout.setTabsFromPagerAdapter(fragmentAdapter);//给Tabs设置适配器



    }

    private void initView() {
        ImageView ivback=findViewById(R.id.iv_goods_detail_back);
        ImageView ivmsg=findViewById(R.id.iv_goods_detail_msg);
        banner = findViewById(R.id.goods_detail_banner);
        fwzq = findViewById(R.id.tv_fwzq_fw);
        hbsy = findViewById(R.id.tv_hbsy_q);
        tzze = findViewById(R.id.tv_tzze_fw);
        ksrq = findViewById(R.id.tv_kstzrq);
        jsri = findViewById(R.id.tv_dqtzrq);
        sytz = findViewById(R.id.tv_tp_syze);
        pb = findViewById(R.id.weekBar);
        RelativeLayout rljb=findViewById(R.id.rl_wdyhq);
        RelativeLayout qybz=findViewById(R.id.rl_qybz);
        RelativeLayout fx=findViewById(R.id.rl_fxtshsh);
        RelativeLayout tzs=findViewById(R.id.rl_lzht);
        iv = findViewById(R.id.iv_ljtz_btn);
        editText = findViewById(R.id.et_je);
        ivback.setOnClickListener(this);
        ivmsg.setOnClickListener(this);
        rljb.setOnClickListener(this);
        qybz.setOnClickListener(this);
        fx.setOnClickListener(this);
        tzs.setOnClickListener(this);
        iv.setOnClickListener(this);






        if (getIntent()!=null){
            goodsid = getIntent().getStringExtra("recomdId");
            getGoodsDetail(getIntent().getStringExtra("recomdId"));


        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.iv_goods_detail_back:
                finish();
                break;
            //跳转消息页面
            case R.id.iv_goods_detail_msg:
                startActivity(new Intent(TenantProductServeActivity.this,MsgCenterActivity.class));

                break;
            //t跳转我的卷包
            case R.id.rl_wdyhq:
                startActivity(new Intent(TenantProductServeActivity.this,MyVoucherPackageActivity.class));
                break;
            //跳转企业保章
            case R.id.rl_qybz:
                startActivity(new Intent(TenantProductServeActivity.this,HomeSafety.class));

                break;
            //跳转风险提示书
            case R.id.rl_fxtshsh:
                startActivity(new Intent(TenantProductServeActivity.this,ProductRisk.class));

                break;
            //跳转来租合同
            case R.id.rl_lzht:
                Intent intent=new Intent(TenantProductServeActivity.this,ContractActivity.class);
                intent.putExtra("id",getIntent().getStringExtra("recomdId") );
                startActivity(intent);

                break;
                //点击立即投资
            case  R.id.iv_ljtz_btn:
                if (TextUtils.isEmpty(editText.getText().toString())){
                    ToastUtil.show("请输入投资金额");
                    return;
                }
                if (Integer.parseInt(editText.getText().toString())>Integer.parseInt(gb.getData().getData().getMoney_pond())){

                  //  ToastUtil.show("输入金额不能大于剩余可投资金额");
                }



                PayDetailFragment payDetailFragment=PayDetailFragment.newInstance(gb.getData().getData().getMoney_pond(),editText.getText().toString(),goodsid);
                payDetailFragment.show(getSupportFragmentManager(),"payDetailFragment");

                break;
        }
    }

    private void showPay() {


    }

    private void getGoodsDetail(String id) {

        String str=GetSign.getSign();
        String[] strs=str.split(",");
        DebugFlags.logD("签名"+str);


        OkHttpUtils.post()

                .url(Constants.BASE_URL +"/Api/Goods/goods_detail/")
                .addParams("t",strs[0])
                .addParams("r",strs[1])
                .addParams("e",strs[2])
                .addParams("id",id)
                .addParams("token",SharedPrefUtil.getString(TenantProductServeActivity.this,"token",""))

                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        DebugFlags.logD("产品详情"+response);
                        gb = JSON.parseObject(response,GoodsDetailBean.class);
                       // ToastUtil.show(gb.getMessage());
                        if (gb.getCode()==2){
                          showAlerDialog();
                        }else if(gb.getCode()==1){

                           initData();
                           initBanner();
                            initTab();

                        }else {

                            ToastUtil.show(gb.getMessage());
                        }

                    }
                });



    }
    //初始化轮播图

    private void initBanner() {
        banner.setAutoPlayAble(false);

        banner.setAdapter(new BGABanner.Adapter() {
            @Override
            public void fillBannerItem(BGABanner banner, View itemView, @Nullable Object model, int position) {
                Glide.with(TenantProductServeActivity.this)
                        .load(model)
                        .placeholder(R.mipmap.shouye2x_1)
                        .error(R.mipmap.shouye2x_1)
                        .centerCrop()
                        .dontAnimate()
                        .into((ImageView) itemView);

            }
        });

        for (int i = 0; i <gb.getData().getGoods_img().size() ; i++) {
            listImgs.add(Constants.BASE_URL+gb.getData().getGoods_img().get(i).replaceAll("\\\\",""));

        }

        banner.setData(listImgs, Arrays.asList("", "", ""));




    }

    private void initData() {
  sytz.setText("剩余可投资金额"+gb.getData().getData().getSurplus_money()+"元");
        fwzq.setText(gb.getData().getData().getMonth()+"个月");
        hbsy.setText(gb.getData().getData().getRate()+"+"+gb.getData().getData().getExpect_rate()+"%");
        tzze.setText(gb.getData().getData().getMoney_pond());
        ksrq.setText(gb.getData().getData().getCtime());
         jsri.setText(getTime(gb.getData().getData().getCtime(),gb.getData().getData().getMonth()));
        long day=    DataUtils.getDaySub(gb.getData().getData().getCtime(),DataUtils.getSystemTime());
         long allday=DataUtils.getDaySub(gb.getData().getData().getCtime(),getTime(gb.getData().getData().getCtime(),gb.getData().getData().getMonth()));
          pb.setProgress(Integer.parseInt(day+"")*100/Integer.parseInt(allday+""));




    }


    private   void showAlerDialog() {

        new AlertDialog(TenantProductServeActivity.this)
                .builder()
                .setTitle("重新登录提示")
                .setCancelable(false)
                .setMsg("你的账号已在其他设备登录,请重新登录登录")
                .setPositiveButton("登录", new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        startActivity(new Intent(TenantProductServeActivity.this,LoginActivity.class));
                            finish();
                    } })
                .setNegativeButton("取消", new View.OnClickListener() {
                    @Override public void onClick(View v) {
                               finish();
                    } })
                .show();


    }





    public String getTime(String data,String mouth){

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

        Date dt= null;
        String reStr="";
        try {
            dt = sdf.parse(data);
            Calendar rightNow = Calendar.getInstance(); rightNow.setTime(dt);
            //日期加3个月
            rightNow.add(Calendar.MONTH,Integer.parseInt(mouth));


            Date dt1=rightNow.getTime();
           reStr = sdf.format(dt1);


        } catch (ParseException e) {
            e.printStackTrace();
        }


        return reStr;
    }


}
