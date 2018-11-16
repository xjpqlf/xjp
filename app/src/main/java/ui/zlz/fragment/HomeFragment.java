package ui.zlz.fragment;


import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.sunfusheng.marqueeview.MarqueeView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;
import cn.bingoogolapple.bgabanner.BGALocalImageSize;
import okhttp3.Call;
import ui.zlz.R;
import ui.zlz.activity.Constants;
import ui.zlz.activity.account.CustomerServiceTL;
import ui.zlz.activity.account.MsgCenterActivity;
import ui.zlz.adapter.ViewPagerViewAdapter;
import ui.zlz.bean.AllTzBean;
import ui.zlz.bean.BannerBean;
import ui.zlz.bean.NoticeBean;
import ui.zlz.bean.RecommendBean;
import ui.zlz.home.HomeAboutTL;
import ui.zlz.home.HomeAttention;
import ui.zlz.home.HomeExplainTL;
import ui.zlz.home.HomeNoticeDetails;
import ui.zlz.home.HomePartner;
import ui.zlz.home.HomeRankingTL;
import ui.zlz.home.HomeSafety;
import ui.zlz.home.HomeYQWeb;
import ui.zlz.tenant.TenantProductServeActivity;
import ui.zlz.utility.DebugFlags;
import ui.zlz.utility.GetSign;
import ui.zlz.utility.SharedPrefUtil;
import ui.zlz.utility.ToastUtil;

/**

 */

public class HomeFragment extends BaseFragment implements View.OnClickListener {
    //定义一个myBGABanner;轮播图
    private BGABanner myBGABanner;
    private List<String> listImg;
    private MarqueeView marqueeView;
    private List<String> info;
    private List<NoticeBean.DataBeanX.DataBean> list2;
    private List<RecommendBean.DataBeanX.DataBean> list3;
    private ViewPager viewPager;
    //viewpager页面
    List<View> viewPagerContentList = new ArrayList<View>();
    private TextView tzrs;
    private TextView zq;
    private TextView cje;

    @Override
    protected int setContentView() {
        return R.layout.fragment_home;
    }
   //初始化数据
    @Override
    protected void startLoad() {
        getBanner();
        //获取首页公告
        getNotice();
        //获取推荐
        getRecommend();
        getTzall();

    }




    //加载数据
    @Override
    protected void initData() {
       //加载轮播图
        initBanner();
        initmq();

        tzrs = findViewById(R.id.touZiRenSu);
        zq = findViewById(R.id.weiYongHuZhuan);
        cje = findViewById(R.id.ChenJiaoE);

        viewPager = findViewById(R.id.vp_viewpager);
        listImg = new ArrayList<>();
        //来租攻略
        RelativeLayout lzgl2 = findViewById(R.id.rl_lzgl2);
        lzgl2.setOnClickListener(this);
        //安全保障
        RelativeLayout aqbz = findViewById(R.id.rl_aqbz2);
        aqbz.setOnClickListener(this);
        //关于我们
        RelativeLayout gywm = findViewById(R.id.rl_gywm);
        gywm.setOnClickListener(this);
        //来租排行
        RelativeLayout lzph = findViewById(R.id.rl_lzphb);
        lzph.setOnClickListener(this);
        //平台合作伙伴
        TextView hzhb = findViewById(R.id.tv_hzhb);
        hzhb.setOnClickListener(this);
        //关注我们
        TextView gzwm = findViewById(R.id.tv_gzwm);
        gzwm.setOnClickListener(this);
        //友情链接
        TextView yqlj = findViewById(R.id.tv_yqlj);
        yqlj.setOnClickListener(this);
        //点击推荐中立即加入
      //  LinearLayout ljjr = findViewById(R.id.ll_ljjr);
        //ljjr.setOnClickListener(this);
        //右上角客服
        ImageView kfxx = findViewById(R.id.iv_kfxx);
        kfxx.setOnClickListener(this);
        ImageView xiaoxi = findViewById(R.id.iv_xiaoxi);
        xiaoxi.setOnClickListener(this);



    }

    private void initmq() {
        marqueeView = (MarqueeView) findViewById(R.id.marqueeView);
        info = new ArrayList<>();
        marqueeView.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, TextView textView) {
                Intent intent1=new Intent(getActivity(),HomeNoticeDetails.class);
                intent1.putExtra("ctime",list2.get(position).getCreate_time());
                intent1.putExtra("content",list2.get(position).getContent());
              startActivity(intent1);
            }
        });
    }

    /*视图点击事件，根据点击的id获取对应的跳转页面*/
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //跳转到来租攻略
            case R.id.rl_lzgl2:
                Intent intent1 = new Intent(getActivity(),HomeExplainTL.class);
                startActivity(intent1);
            break;
            //跳转到安全保障
            case R.id.rl_aqbz2:
                Intent intent2 = new Intent(getActivity(), HomeSafety.class);
                startActivity(intent2);
            break;
             //跳转关于我们
            case R.id.rl_gywm:

                Intent intent6 = new Intent(getActivity(), HomeAttention.class);
                startActivity(intent6);
            break;
             //跳转到来租排行
            case R.id.rl_lzphb:
                Intent intent4 = new Intent(getActivity(), HomeRankingTL.class);
                startActivity(intent4);
            break;
            //平台合作伙伴
            case R.id.tv_hzhb:
                Intent intent5 = new Intent(getActivity(), HomePartner.class);
                startActivity(intent5);
                break;
            //关注我们
            case R.id.tv_gzwm:
                Intent intent3 = new Intent(getActivity(), HomeAboutTL.class);
                startActivity(intent3);
                break;
            //友情链接
            case R.id.tv_yqlj:
                Intent intent7 = new Intent(getActivity(), HomeYQWeb.class);
                startActivity(intent7);
                break;
          /*  //点击立即加入到产品详情
            case R.id.ll_ljjr:
                Intent intent8 = new Intent(getActivity(), TenantProductServeActivity.class);
                startActivity(intent8);
                break;*/
            //客服
           case R.id.iv_kfxx:
               Intent intent9 = new Intent(getActivity(), CustomerServiceTL.class);
                startActivity(intent9);
               break;
               //消息界面
            case R.id.iv_xiaoxi:
                Intent intent10 = new Intent(getActivity(), MsgCenterActivity.class);
                startActivity(intent10);
                break;
        }
    }

   /*初始化轮播图*/
       public void initBanner (){
        myBGABanner = findViewById(R.id.bga_banner_start);
        // Bitmap 的宽高在 maxWidth maxHeight 和 minWidth minHeight 之间
        BGALocalImageSize localImageSize = new BGALocalImageSize(720, 1280, 320, 640);

    }


    private void getBanner() {
        String str=GetSign.getSign();
        String[] strs=str.split(",");
        DebugFlags.logD("签名"+str);


        OkHttpUtils.post()

                .url(Constants.BASE_URL +"Api/Index/banner")
                .addParams("t",strs[0])
                .addParams("r",strs[1])
                .addParams("e",strs[2])
                //   .addParams("mobile","13071266270")

                .addParams("type","1")



                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        DebugFlags.logD("轮播"+response);
                 BannerBean banner=       JSON.parseObject(response,BannerBean.class);



                        if (banner.getCode()==1) {
                            List<BannerBean.DataBeanX.DataBean> list= banner.getData().getData();
                            initBanners(list);
                        }else {

                            ToastUtil.show(banner.getMessage());
                        }


                    }
                });

    }

    private void initBanners(List<BannerBean.DataBeanX.DataBean> list) {

        myBGABanner.setAdapter(new BGABanner.Adapter() {
            @Override
            public void fillBannerItem(BGABanner banner, View itemView, @Nullable Object model, int position) {
                Glide.with(getActivity())
                        .load(model)
                        .placeholder(R.mipmap.shouye2x_1)
                        .error(R.mipmap.shouye2x_1)
                        .centerCrop()
                        .dontAnimate()
                        .into((ImageView) itemView);

            }
        });

        for (int i = 0; i <list.size() ; i++) {
           listImg.add(Constants.BASE_URL+list.get(i).getImg_url().replaceAll("\\\\",""));

        }

        myBGABanner.setData(listImg, Arrays.asList("", "", ""));




    }

    private void getNotice() {


        String str=GetSign.getSign();
        String[] strs=str.split(",");
        DebugFlags.logD("签名"+str);


        OkHttpUtils.post()

                .url(Constants.BASE_URL +"Api/AboutUs/website_notice")
                .addParams("t",strs[0])
                .addParams("r",strs[1])
                .addParams("e",strs[2])
                .addParams("type","2")
                //   .addParams("mobile","13071266270")





                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        DebugFlags.logD("公告"+response);
                 NoticeBean noticeBean=       JSON.parseObject(response,NoticeBean.class);
                 if (noticeBean.getCode()==1){
                     list2 = noticeBean.getData().getData();
                     for (int i = 0; i < list2.size() ; i++) {
                        info.add(list2.get(i).getTitle());
                     }
                     marqueeView.startWithList(info, R.anim.anim_bottom_in, R.anim.anim_top_out);

                    }}
                });
    }
    private void getRecommend() {

        String str=GetSign.getSign();
        String[] strs=str.split(",");
        DebugFlags.logD("签名"+str);


        OkHttpUtils.post()

                .url(Constants.BASE_URL +"/Api/Index/recommend/")
                .addParams("t",strs[0])
                .addParams("r",strs[1])
                .addParams("e",strs[2])

                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        DebugFlags.logD("推荐"+response);
                        RecommendBean recommendBean=JSON.parseObject(response,RecommendBean.class);
                        if (recommendBean.getCode()==1){
                            list3 = recommendBean.getData().getData();
                            for (int i = 0; i < list3.size(); i++) { //获取viewpager界面所要添加的布局view
                                View vpView = View.inflate(getActivity(), R.layout.view_pager_recom_item, null);
                                //获取viewpager界面所要添加的布局view中的textview并设置数据
                                ImageView tv = vpView.findViewById(R.id.iv_recom);
                                Glide.with(getActivity()).load(Constants.BASE_URL +"Upload/"+ list3.get(i).getImg_url().replaceAll("\\\\","")).fitCenter().into(tv);

                                TextView tvrata = vpView.findViewById(R.id.benjinNunber);
                                TextView zz = vpView.findViewById(R.id.lilvNumber);
                                TextView qx = vpView.findViewById(R.id.tv_fwzq);
                                TextView content = vpView.findViewById(R.id.xinZuYiCi);
                                TextView join = vpView.findViewById(R.id.tv_go);
                                tvrata.setText(list3.get(i).getRate() + "+");
                                zz.setText(list3.get(i).getExpect_rate() + "%");
                                qx.setText(list3.get(i).getMonth() + "个月");

                                //添加到viewpager的数据中
                                viewPagerContentList.add(vpView);
                            }


                    initViewpager();





                        }else {
                            recommendBean.getMessage();
                        }

                    }
                });

    }
    private void getTzall() {
        String str=GetSign.getSign();
        String[] strs=str.split(",");
        DebugFlags.logD("签名"+str);


        OkHttpUtils.post()

                .url(Constants.BASE_URL +"Api/Index/user_tz_all/")
                .addParams("t",strs[0])
                .addParams("r",strs[1])
                .addParams("e",strs[2])

                //   .addParams("mobile","13071266270")





                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                 DebugFlags.logD("t投资人数"+response);
                        AllTzBean ab=JSON.parseObject(response,AllTzBean.class);
                        if (ab.getCode()==1){

                             tzrs.setText(ab.getData().getData().getSum_user());
                             zq.setText(ab.getData().getData().getAll_earn());
                             cje.setText(ab.getData().getData().getSum_money());

                        }else {

                            ToastUtil.show(ab.getMessage());
                        }


                    }
                });


    }
    private void initViewpager() {
           DebugFlags.logD("布局数量"+viewPagerContentList.size());
        for (int i = 0; i <viewPagerContentList.size() ; i++) {
            final int finalI = i;
            viewPagerContentList.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (TextUtils.isEmpty(SharedPrefUtil.getString(getActivity(),"token",""))){
                        ToastUtil.show("请登录");
                        return;
                    }



                  Intent intent=new Intent(getActivity(), TenantProductServeActivity.class);
                  intent.putExtra("recomdId",list3.get(finalI).getId());
                  startActivity(intent);
                }
            });
        }




        final ViewPagerViewAdapter viewPagerViewAdapter = new ViewPagerViewAdapter(viewPagerContentList);
        viewPager.setAdapter(viewPagerViewAdapter);


    }




    //解决重影 //
      @Override
    public void onStart() {
                super.onStart(); //
    marqueeView.startFlipping();   }
     @Override
      public void onStop() { //
         super.onStop(); //
  marqueeView.stopFlipping();    }


}

