package ui.zlz.fragment;

import android.os.Bundle;
import android.widget.TextView;

import ui.zlz.R;
/**
 * Created by chenzhe on 2018/11/8.
 * 在产品详情之服务介绍
 */
public class ProductServiceFragment extends BaseFragment {



    public static ProductServiceFragment newInstance(String content) {
        ProductServiceFragment fragment = new ProductServiceFragment();
        Bundle args = new Bundle();

        args.putString("content", content);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int setContentView() {
        return R.layout.fragment_product_service;
    }

    @Override
    protected void startLoad() {

    }

    @Override
    protected void initData() {
        TextView tv=findViewById(R.id.tv_conten_s);
        if (getArguments()!=null)
        {
            String content = getArguments().getString("content");
            tv.setText(content);
        }
    }
}