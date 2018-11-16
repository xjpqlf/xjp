package ui.zlz.fragment;

import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ui.zlz.R;
import ui.zlz.adapter.BestHotAdapter;
import ui.zlz.bean.BestHot;

/**
 * Created by chenzhe on 2018/2/8.
 */

public class BestFragment extends BaseFragment {


    @Override
    protected int setContentView() {
        return R.layout.fragment_best;
    }

    @Override
    protected void startLoad() {

    }

    @Override
    protected void initData() {
        ListView lv=findViewById(R.id.lv_hot);
        List<BestHot> list=new ArrayList();
        for (int i = 0; i < 10; i++) {
            BestHot hot1=new BestHot(i+".00%","五个月");
            list.add(hot1);
        }
        BestHotAdapter adapter=new BestHotAdapter(getActivity(),list);
        lv.setAdapter(adapter);


    }
}

