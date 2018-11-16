package ui.zlz.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import ui.zlz.fragment.BaseFragment;

/**
 * Created by chenzhe on 2018/2/8.
 */

public class MyAdapter extends FragmentPagerAdapter {

    List<BaseFragment> mFragment;

    @Override
    public Fragment getItem(int position) {
        return mFragment.get(position);
    }

    @Override
    public int getCount() {
        return mFragment.size();
    }

    public MyAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setFragments(List<BaseFragment> mFragment) {
        this.mFragment = mFragment;
    }


}
