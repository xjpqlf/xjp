package ui.zlz.fragment;

import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ui.zlz.R;
import ui.zlz.adapter.NewComerAdapter;
import ui.zlz.bean.NewComer;

/**
 * Created by chenzhe on 2018/2/8.
 */

public class NewComerFragment extends BaseFragment {


    @Override
    protected int setContentView() {
        return R.layout.fragment_newcomer;
    }

    @Override
    protected void startLoad() {

    }

    @Override
    protected void initData() {
        ListView lv= findViewById(R.id.lv_newcomer);
        List<NewComer>list=new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            NewComer nc=new NewComer(i+1+".00%",i+"个月",false);
            list.add(nc);
        }
        NewComerAdapter  adapter=new NewComerAdapter(getActivity(),list);
        lv.setAdapter(adapter);

    }
}

