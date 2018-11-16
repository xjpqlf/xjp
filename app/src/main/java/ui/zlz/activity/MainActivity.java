


package ui.zlz.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import ui.zlz.R;
import ui.zlz.activity.account.LoginActivity;
import ui.zlz.activity.account.Zlzapplication;
import ui.zlz.adapter.MyAdapter;
import ui.zlz.fragment.AccountFragment;
import ui.zlz.fragment.BaseFragment;
import ui.zlz.fragment.HomeFragment;
import ui.zlz.fragment.TenantFragment;
import ui.zlz.fragment.WelfareFragment;
import ui.zlz.utility.SharedPrefUtil;
import ui.zlz.utility.ToastUtil;
import ui.zlz.widget.AlertDialog;
import ui.zlz.widget.NoScrollViewPager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private List<BaseFragment> mFragment = new ArrayList<>();

    private LinearLayout ll_tab;
    private long exitAppTimeCount;
    private NoScrollViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Zlzapplication.addActivity(this);
        ll_tab = findViewById(R.id.ll_tab);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setScanScroll(false);
        ll_tab.getChildAt(0).setSelected(true);

        mFragment.add(new HomeFragment());
        mFragment.add(new TenantFragment());
        mFragment.add(new WelfareFragment());
        mFragment.add(new AccountFragment());

        FragmentManager fragmentManager = getSupportFragmentManager();
        MyAdapter myAdapter = new MyAdapter(fragmentManager);
        myAdapter.setFragments(mFragment);
        // viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(myAdapter);
        viewPager.setCurrentItem(0);
        for (int i = 0; i < ll_tab.getChildCount(); i++) {
            ll_tab.getChildAt(i).setTag(i);
            ll_tab.getChildAt(i).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {

        int tag = (int) v.getTag();
        if (TextUtils.isEmpty(SharedPrefUtil.getString(MainActivity.this, "token", ""))) {
            if (tag == 3) {
       showAlerDialog();

            }else {
                resetChoose();
                ll_tab.getChildAt(tag).setSelected(true);
                viewPager.setCurrentItem(tag, false);
            }

        } else {
            resetChoose();
            ll_tab.getChildAt(tag).setSelected(true);
            viewPager.setCurrentItem(tag, false);
        }


    }

    private void showAlerDialog() {
        new AlertDialog(MainActivity.this)
                .builder()
                .setTitle("提示")
                .setMsg("你还没有登录,请登录")
                .setPositiveButton("登录", new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        startActivity(new Intent(MainActivity.this,LoginActivity.class));

                    } })
                .setNegativeButton("取消", new View.OnClickListener() {
                        @Override public void onClick(View v) {

                        } })
                .show();


    }

    private void resetChoose() {
        for (int i = 0; i < ll_tab.getChildCount(); i++) {
            ll_tab.getChildAt(i).setSelected(false);
        }
    }


    @Override
    public void onBackPressed() {

        if (System.currentTimeMillis() - exitAppTimeCount > 2000) {
            ToastUtil.show("再按一次退出程序");
            exitAppTimeCount = System.currentTimeMillis();
        } else {
            super.onBackPressed();

        }


    }

}