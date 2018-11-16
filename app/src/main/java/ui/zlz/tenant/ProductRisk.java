package ui.zlz.tenant;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ui.zlz.R;

public class ProductRisk extends Activity {

    private String s="指遭受无法预见、无法克服、无法避免等不可抗力的客观情况所导致的借款项目无法按时还款的风险，" +
            "包括但不限于洪水、地震及其它自然灾害、战争、骚乱、火灾、突发性公共卫生事件、政府征用、" +
            "没收、法律法规变化或其他突发事件，导致来租项目非正常暂停或终止，从而造成出借人本金和/或利息收益遭受损失。" +
            "\n\n上述投资风险并不保证提示本平台网络投资业务的全部风险，您通过本平台参与网络借贷前，应全面了解相关政策法规，" +
            "根据您自身的投资目的、投资期限、投资经验、资产状况等判断是否具备相应的风险承受能力。";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenant_risk_message);
        initView();
    }

    private void initView() {
        TextView tv=findViewById(R.id.riskTitle);
        ImageView iv=findViewById(R.id.iv_fxts_back);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tv.setText(s);

    }
}
