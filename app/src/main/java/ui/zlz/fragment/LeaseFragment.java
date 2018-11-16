package ui.zlz.fragment;

import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ui.zlz.R;
import ui.zlz.adapter.LeaseAdapter;
import ui.zlz.bean.Lease;

/**
 * Created by chenzhe on 2018/2/8.
 */

public class LeaseFragment extends BaseFragment {


    @Override
    protected int setContentView() {
        return R.layout.fragment_lease;
    }

    @Override
    protected void startLoad() {

    }

    @Override
    protected void initData() {
        ListView lv=findViewById(R.id.lv_lease);
        List<Lease> list=new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Lease lease=new Lease(i+1+".00%","223"+i,"12/22","111",true);
            list.add(lease);
        }
        LeaseAdapter adapter=new LeaseAdapter(getActivity(),list);
        lv.setAdapter(adapter);

    }
}

