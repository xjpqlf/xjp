package ui.zlz;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;



public class MyCustomerServiceActivity extends Activity {

    private TabHost tabHost;
    private TabWidget tw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_customer_service);

        tabHost = (TabHost)findViewById(android.R.id.tabhost);
        tabHost.setup();

        LayoutInflater inflater = LayoutInflater.from(this);
        inflater.inflate(R.layout.activity_my_customer_service_tab1,tabHost.getTabContentView());
        inflater.inflate(R.layout.activity_my_customer_service_tab2,tabHost.getTabContentView());
        inflater.inflate(R.layout.activity_my_customer_service_tab3,tabHost.getTabContentView());

        tabHost.addTab(tabHost.newTabSpec("tab01").setIndicator("新手专享").setContent(R.id.mcstab1));
        tabHost.addTab(tabHost.newTabSpec("tab02").setIndicator("退租服务").setContent(R.id.mcstab2));
        tabHost.addTab(tabHost.newTabSpec("tab03").setIndicator("邀请好友").setContent(R.id.mcstab3));

        tw = tabHost.getTabWidget();
        for (int i=0;i<tw.getChildCount();i++){
             TextView tv =(TextView) tw.getChildAt(i).findViewById(android.R.id.title);
             if(tabHost.getCurrentTab()==i) {
                 tv.setTextSize(18);
                 tv.setTextColor(0xffF6931E);
             }else{
                 tv.setTextSize(16);
                 tv.setTextColor(0xffaaaaaa);
             }
        }
        //选择监听器
        //tabHost.setOnTabChangedListener(new tabChangedListener());
    }

}

