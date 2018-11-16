package ui.zlz.activity.account;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qiyukf.unicorn.api.ConsultSource;
import com.qiyukf.unicorn.api.Unicorn;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import java.util.ArrayList;
import java.util.List;

import ui.zlz.R;
import ui.zlz.adapter.TabFragmentAdapter;
import ui.zlz.fragment.CustomerNewbieFragment;
import ui.zlz.fragment.CustomernExitFragment;
import ui.zlz.fragment.CustomernInviteFragment;

public class CustomerServiceTL extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    private List<String> tabList;
    private List<Fragment> fragmentList;
    private PopupWindow mPopWindow;
    private TextView phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_service_tl);
        init();
    }

    private void init() {
   ImageView ivback=findViewById(R.id.iv_wdkf_back);
   ivback.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
           finish();
       }
   });
        tabLayout = findViewById(R.id.tl_kf_question);
        viewPager = findViewById(R.id.vp_kf_question);
        RelativeLayout rlphone = findViewById(R.id.rl_kfdh_bt);
        RelativeLayout rlkf = findViewById(R.id.rl_zxkf_bt);
        rlphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AndPermission.with(CustomerServiceTL.this)
                        .runtime()
                        .permission(Permission.CALL_PHONE)

                        .onGranted(new Action<List<String>>() {
                            @Override
                            public void onAction(List<String> data) {
                                // data.get(0);

                            showPop();
                            }
                        })
                        .onDenied(new Action<List<String>>() {
                            @Override
                            public void onAction(List<String> data) {
                                /**
                                 * 当用户没有允许该权限时，回调该方法
                                 */
                                Toast.makeText(CustomerServiceTL.this, "没有获取打电话权限，该功能无法使用", Toast.LENGTH_SHORT).show();
                                /**
                                 * 判断用户是否点击了禁止后不再询问，AndPermission.hasAlwaysDeniedPermission(MainActivity.this, data)
                                 */
                                if (AndPermission.hasAlwaysDeniedPermission(CustomerServiceTL.this, data)) {
                                    //true，弹窗再次向用户索取权限

                                }
                            }
                        }).start();

            }
        });

        rlkf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title="我的客服";
                /**
                 * 设置访客来源，标识访客是从哪个页面发起咨询的，用于客服了解用户是从什么页面进入。
                 * 三个参数分别为：来源页面的url，来源页面标题，来源页面额外信息（保留字段，暂时无用）。
                 * 设置来源后，在客服会话界面的"用户资料"栏的页面项，可以看到这里设置的值。
                 */
                ConsultSource source = new ConsultSource("app", "我的客服", "custom information string");
                /**
                 * 请注意： 调用该接口前，应先检查Unicorn.isServiceAvailable()，
                 * 如果返回为false，该接口不会有任何动作
                 *
                 * @param context 上下文
                 * @param title   聊天窗口的标题
                 * @param source  咨询的发起来源，包括发起咨询的url，title，描述信息等
                 */
                Unicorn.openServiceActivity(CustomerServiceTL.this, title, source);






            }
        });

        initContent();

    }

    private void showPop() {
        View contentView = LayoutInflater.from(CustomerServiceTL.this).inflate(R.layout.popwindow_customer, null);
        mPopWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopWindow.setContentView(contentView);
        phone =contentView. findViewById(R.id.tv_phone);

        TextView iv = contentView.findViewById(R.id.tv_sure);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //拨打客服
                callPhone(phone.getText().toString());


                mPopWindow.dismiss();

            }
        });

        TextView iv1 = contentView.findViewById(R.id.tv_cancle);
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
        View rootview = LayoutInflater.from(CustomerServiceTL.this).inflate(R.layout.customer_service_tl, null);
        //   mPopWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
        mPopWindow.showAtLocation(rootview, Gravity.CENTER, 0, 0);
        mPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() { // popupWindow隐藏时恢复屏幕正常透明度

                setBackgroundAlpha(1.0f);
            }
        });


    }

    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = (CustomerServiceTL.this.getWindow().getAttributes());
        lp.alpha = bgAlpha;
        CustomerServiceTL.this.getWindow().setAttributes(lp);
    }

    private void initContent() {

        tabList = new ArrayList<>();
        tabList.add("新手专享");
        tabList.add("退租服务");
        tabList.add("邀请好友");
        tabLayout.addTab(tabLayout.newTab().setText(tabList.get(0)));//添加tab选项卡
        tabLayout.addTab(tabLayout.newTab().setText(tabList.get(1)));
        tabLayout.addTab(tabLayout.newTab().setText(tabList.get(2)));
        tabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
        //此处代码设置无效，不知道为啥？？？xml属性是可以的
        //tabLayout.setTabTextColors(android.R.color.white, android.R.color.holo_red_dark);
        // 设置TabLayout两种状态

        fragmentList = new ArrayList<>();
        Fragment f1 = new CustomerNewbieFragment();//新手专享
        Fragment f2 = new CustomernExitFragment();//退租服务
        Fragment f3 = new CustomernInviteFragment(); //邀请好友
        fragmentList.add(f1);
        fragmentList.add(f2);
        fragmentList.add(f3);
        TabFragmentAdapter fragmentAdapter = new TabFragmentAdapter(getSupportFragmentManager(), fragmentList, tabList);
        viewPager.setAdapter(fragmentAdapter);//给ViewPager设置适配器
        tabLayout.setupWithViewPager(viewPager);//将TabLayout和ViewPager关联起来。
        tabLayout.setTabsFromPagerAdapter(fragmentAdapter);//给Tabs设置适配器

    }




    private void callPhone(String num) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + num);
        intent.setData(data);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(intent);

    }


}
