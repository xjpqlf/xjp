package ui.zlz.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import ui.zlz.R;
import ui.zlz.activity.Constants;
import ui.zlz.activity.account.LoginActivity;
import ui.zlz.adapter.WeekRankAdapter;
import ui.zlz.bean.WeekBean;
import ui.zlz.home.IncomeActivity;
import ui.zlz.utility.DataUtils;
import ui.zlz.utility.DebugFlags;
import ui.zlz.utility.GetSign;
import ui.zlz.utility.SharedPrefUtil;
import ui.zlz.utility.ToastUtil;
import ui.zlz.widget.AlertDialog;

public class RankingMonthFragment extends BaseFragment {

    private SmartRefreshLayout refreshLayout;
    private ListView lv;
    private List<WeekBean.DataBeanX.DataBean> list=new ArrayList<>();
    private  int i=1;

    @Override
    protected int setContentView() {
        return R.layout.fragment_ranking_month;
    }

    @Override
    protected void startLoad() {

        if (!DataUtils.isNetworkAvailable(getActivity())){

            ToastUtil.show("你的网络出现问题,请检查网络");


        }else {


            getRankWeek(i);
        }
    }



    @Override
    protected void initData() {
        refreshLayout = findViewById(R.id.sr_rank_mon);
        lv = findViewById(R.id.lv_rank_mon);



        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {

                if (DataUtils.isNetworkAvailable(getActivity())){
                    list.clear();
                    i=1;
                    getRankWeek(1);}else {
                    ToastUtil.show("你的网络出现问题,请检查网络");
                }
                refreshlayout.finishRefresh(1000/*,false*/);//传入false表示刷新失败

            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {

                if (DataUtils.isNetworkAvailable(getActivity())){
                    i++;
                    getMoreData(i);}else {
                    ToastUtil.show("你的网络出现问题,请检查网络");
                }
                refreshlayout.finishLoadmore(1000);
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getActivity(),IncomeActivity.class);
                intent.putExtra("rankid",list.get(i).getG_id());
                startActivity(intent);
            }
        });


    }
    private void getRankWeek(int i) {
        String str=GetSign.getSign();
        String[] strs=str.split(",");
        DebugFlags.logD("签名"+str);


        OkHttpUtils.post()

                .url(Constants.BASE_URL +"/Api/Goods/index/")
                .addParams("t",strs[0])
                .addParams("r",strs[1])
                .addParams("e",strs[2])
                //   .addParams("mobile","13071266270")

                .addParams("token",SharedPrefUtil.getString(getActivity(),"token",""))
                .addParams("type","2")
                .addParams("page",i+"")



                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        DebugFlags.logD("月排行"+response);
                        WeekBean wb=   JSON.parseObject(response,WeekBean.class);
                        if (wb.getCode()==1){
                            list.clear();
                            list.addAll(wb.getData().getData());
                            WeekRankAdapter adapter=new WeekRankAdapter(getActivity(),list);
                            lv.setAdapter(adapter);


                        }else if (wb.getCode()==2){


                            showAlerDialog();




                        }else {

                            ToastUtil.show(wb.getMessage());
                        }
                    }
                });
    }

    private void getMoreData(int i) {
        String str=GetSign.getSign();
        String[] strs=str.split(",");
        DebugFlags.logD("签名"+str);


        OkHttpUtils.post()


                .url(Constants.BASE_URL +"/Api/Goods/index/")
                .addParams("t",strs[0])
                .addParams("r",strs[1])
                .addParams("e",strs[2])
                //   .addParams("mobile","13071266270")

                .addParams("token",SharedPrefUtil.getString(getActivity(),"token",""))
                .addParams("type","2")
                .addParams("page",i+"")


                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        DebugFlags.logD("我的月" + response);

                        WeekBean wb=   JSON.parseObject(response,WeekBean.class);


                        if (wb.getCode()==1) {

                            list.addAll(wb.getData().getData());
                            WeekRankAdapter adapter = new WeekRankAdapter(getActivity(), list);
                            lv.setAdapter(adapter);

                        }else if (wb.getCode()==2){

                        }


                        else {
                            ToastUtil.show(wb.getMessage());

                        }




                    }
                });




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
