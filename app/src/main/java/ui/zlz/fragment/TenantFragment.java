package ui.zlz.fragment;


import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import ui.zlz.R;
import ui.zlz.adapter.FragmentAdapter;

/**

 */

public class TenantFragment extends BaseFragment {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    @Override
    protected int setContentView() {
        return R.layout.fragment_tenant;
    }

    @Override
    protected void startLoad() {

    }

    @Override
    protected void initData() {
         List<Fragment> fragments=new ArrayList<Fragment>();
         fragments.add(new BestFragment());
         fragments.add(new NewComerFragment());
         fragments.add(new LeaseFragment());
        FragmentPagerAdapter mAdapter;
//对TabLayout以及ViewPager的监听，以下皆在onCreateVi方法中完成
        mViewPager = findViewById(R.id.vp_content);
        mTabLayout = findViewById(R.id.tl_tab);
        android.support.v4.app.FragmentManager man = TenantFragment.this.getChildFragmentManager();
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
        mAdapter= new FragmentAdapter(man,fragments);
        mViewPager.setAdapter(mAdapter);//给ViewPager设置适配器
        mTabLayout.setupWithViewPager(mViewPager);//将TabLayout和ViewPager关联起来。
        mTabLayout.setTabsFromPagerAdapter(mAdapter);//给Tabs设置适配器
        setIndicator(mTabLayout,40,40);



    }












    public void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
         Field tabStrip = null;
        try {

            tabStrip = tabLayout.getDeclaredField("mTabStrip");

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }

    }

}
