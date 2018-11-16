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
import ui.zlz.adapter.TabFragmentAdapter;
import ui.zlz.fragment.RankingMonthFragment;
import ui.zlz.fragment.RankingWeekFragment;
import ui.zlz.fragment.RankingYearFragment;

public class HomeRankingTL extends AppCompatActivity {

        TabLayout tabLayout;
        ViewPager viewPager;
        private List<String> tabList;
        private List<Fragment> fragmentList;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            //创建本页activity
            setContentView(R.layout.home_ranking_tl);

            init();
        }

        private void init() {
            ImageView iv=findViewById(R.id.iv_lzph_back);
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
           //获取控件
            tabLayout = findViewById(R.id.tl_ranking);
            viewPager = findViewById(R.id.vp_ranking);

            initContent();

        }

        private void initContent() {
            //将菜单文字放入LIST 集合中
            tabList = new ArrayList<>();
            tabList.add("周排行榜");
            tabList.add("月排行榜");
            tabList.add("年排行榜");
            //添加tab选项卡
            tabLayout.addTab(tabLayout.newTab().setText(tabList.get(0)));
            tabLayout.addTab(tabLayout.newTab().setText(tabList.get(1)));
            tabLayout.addTab(tabLayout.newTab().setText(tabList.get(2)));
            tabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
             //fragment放入list 集合中
            fragmentList = new ArrayList<>();
            Fragment f1 = new RankingWeekFragment();//周
            Fragment f2 = new RankingMonthFragment();//月
            Fragment f3 = new RankingYearFragment();//年
            fragmentList.add(f1);
            fragmentList.add(f2);
            fragmentList.add(f3);
            //将两个list集合放入封装的适配器类中
            TabFragmentAdapter fragmentAdapter = new TabFragmentAdapter(getSupportFragmentManager(), fragmentList, tabList);
            viewPager.setAdapter(fragmentAdapter);//给ViewPager设置适配器
            tabLayout.setupWithViewPager(viewPager);//将TabLayout和ViewPager关联起来。
            tabLayout.setTabsFromPagerAdapter(fragmentAdapter);//给Tabs设置适配器

        }
}
