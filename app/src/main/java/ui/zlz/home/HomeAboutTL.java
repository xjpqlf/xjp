package ui.zlz.home;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import ui.zlz.R;
import ui.zlz.activity.account.Zlzapplication;
import ui.zlz.adapter.TabFragmentAdapter;
import ui.zlz.fragment.AboutCompanyFragment;
import ui.zlz.fragment.AboutMediaFragment;
import ui.zlz.fragment.AboutNoticeFragment;
import ui.zlz.fragment.AboutTeamFragment;

public class HomeAboutTL extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    private List<String> tabList;
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_about_tl);
        init();
    }

    private void init() {

        tabLayout = findViewById(R.id.tl_abt);
        viewPager = findViewById(R.id.vp_abt);

        ImageView imageView=findViewById(R.id.iv_gyzlz_back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Zlzapplication.addActivity(this);
        initContent();

    }

    private void initContent() {

        tabList = new ArrayList<>();
        tabList.add("公司简介");
        tabList.add("管理团队");
        tabList.add("媒体报道");
        tabList.add("网站公告");
        tabLayout.addTab(tabLayout.newTab().setText(tabList.get(0)));//添加tab选项卡
        tabLayout.addTab(tabLayout.newTab().setText(tabList.get(1)));
        tabLayout.addTab(tabLayout.newTab().setText(tabList.get(2)));
        tabLayout.addTab(tabLayout.newTab().setText(tabList.get(3)));
        tabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
        //此处代码设置无效，不知道为啥？？？xml属性是可以的
        //tabLayout.setTabTextColors(android.R.color.white, android.R.color.holo_red_dark);
        // 设置TabLayout两种状态

        fragmentList = new ArrayList<>();
        Fragment f1 = new AboutCompanyFragment();//公司简介
        Fragment f2 = new AboutTeamFragment();//管理团队
        Fragment f3 = new AboutMediaFragment(); //媒体报道
        Fragment f4 = new AboutNoticeFragment();//网站公告
        fragmentList.add(f1);
        fragmentList.add(f2);
        fragmentList.add(f3);
        fragmentList.add(f4);
        TabFragmentAdapter fragmentAdapter = new TabFragmentAdapter(getSupportFragmentManager(), fragmentList, tabList);
        viewPager.setAdapter(fragmentAdapter);//给ViewPager设置适配器
        tabLayout.setupWithViewPager(viewPager);//将TabLayout和ViewPager关联起来。
        tabLayout.setTabsFromPagerAdapter(fragmentAdapter);//给Tabs设置适配器

    }

}
