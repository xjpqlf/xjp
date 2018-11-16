package ui.zlz.fragment;

import android.widget.TextView;

import ui.zlz.R;

/**
 * Created by chenzhe on 2018/11/8.
 * 在产品详情之常见问题
 */
public class ProductQuestionFragment extends BaseFragment {
    private String s="1、是否可以提前终止持有租权以收回来租的资金？\n可以，投资成功后完成投资如果不满一个月终止租权,将按照本金退还到账户资金中,满于1个月以上您可以通过未到期退租的方式提前收回来租的资金。\n2、对散标进行投资安全？\n租来赚以严谨负责的态度对每笔投资进行严格筛选，同时具备专业的投资后管理团队和高效的来租流程，最大限度的保护投资者的权益。\n3、来租人提前撤租怎么办？\n自提前撤租开始之后，正常利息停止预算。\n按照下面公式计算：\n当承租人租期不满一个月中途退租,平台将返回来租本金,中间利率收益为0\n当承租人租期满一个月以上.中途退租,将按照以下方式计算\n计算公式:本金 + 本金 * (当前租期 / 合约租期) * 利率%\n当承租人租期合约期,平台自动计算收益\n计算公式:本金 + 本金 *  (基本利率 + 预期利率) %";
    @Override
    protected int setContentView() {
        return R.layout.fragment_product_question;
    }

    @Override
    protected void startLoad() {

    }

    @Override
    protected void initData() {
        TextView tv=findViewById(R.id.tv_content_service);
        tv.setText(s);
    }
}
