package ui.zlz.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class ViewPagerViewAdapter extends PagerAdapter {
    List<View> viewPagerContentList;
    public ViewPagerViewAdapter(List<View> viewPagerContentList) {
        this.viewPagerContentList = viewPagerContentList;
    }
        @Override
        public int getCount() {
        if (viewPagerContentList != null && viewPagerContentList.size() > 0)
        {
            return viewPagerContentList.size();
        } else {
            return 0;
        }
    }
        @Override
        public boolean isViewFromObject(View arg0, Object arg1)
        { return arg0 == arg1;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
        @Override
        public Object instantiateItem(ViewGroup container, int position)
        {
            container.addView(viewPagerContentList.get(position));
        return viewPagerContentList.get(position);
        }
        @Override
        public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}