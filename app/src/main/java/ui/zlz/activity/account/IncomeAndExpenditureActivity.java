package ui.zlz.activity.account;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ui.zlz.R;
import ui.zlz.adapter.TabFragmentAdapter;
import ui.zlz.fragment.ExpenditureFragment;
import ui.zlz.fragment.IncomeFragment;

public class IncomeAndExpenditureActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager ;

    private List<String> tabList;
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_and_expenditure);
        init();
    }

    private void init() {
        ImageView ivback= findViewById(R.id.iv_shzhjl_back);
        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        TextView tvsx=findViewById(R.id.tv_sx);
        tvsx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //去筛选界面
                startActivity(new Intent(IncomeAndExpenditureActivity.this,FilteringFinancialRecordActivity.class));
               // finish();
            }
        });

        tabLayout = findViewById(R.id.tl_tab);

        viewPager  = findViewById(R.id.vp_content);


        initContent();

    }

    private void initContent() {

        tabList = new ArrayList<>();
        tabList.add("收益记录");
        tabList.add("投资记录");
        tabLayout.addTab(tabLayout.newTab().setText(tabList.get(0)));//添加tab选项卡
        tabLayout.addTab(tabLayout.newTab().setText(tabList.get(1)));
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);//设置tab模式，当前为系统默认模式
        //此处代码设置无效，不知道为啥？？？xml属性是可以的
//        tabLayout.setTabTextColors(android.R.color.white, android.R.color.holo_red_dark);//设置TabLayout两种状态


       fragmentList = new ArrayList<>();
        Fragment f1 = new IncomeFragment();
        Fragment f2 = new ExpenditureFragment();
        fragmentList.add(f1);
        fragmentList.add(f2);
        TabFragmentAdapter fragmentAdapter = new TabFragmentAdapter(getSupportFragmentManager(), fragmentList, tabList);
        viewPager.setAdapter(fragmentAdapter);//给ViewPager设置适配器
        tabLayout.setupWithViewPager(viewPager);//将TabLayout和ViewPager关联起来。
        tabLayout.setTabsFromPagerAdapter(fragmentAdapter);//给Tabs设置适配器

    }


    @Override
    protected void onResume() {
        super.onResume();
        tabLayout.getTabAt(1).select();
        viewPager.setCurrentItem(1);



    }
}
