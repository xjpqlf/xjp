package ui.zlz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import ui.zlz.activity.account.AccountInfoActivity;
import ui.zlz.activity.account.MyOrdersActivity;
import ui.zlz.activity.account.MyVoucherPackageActivity;
import ui.zlz.activity.account.RechargeActivity;
import ui.zlz.activity.account.TiXianActivity;
import ui.zlz.welfare.WelfareActivity;

public class UserCenterActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_center);
    }


    public void recharge(View view){

        Intent intent = new Intent();

        intent.setClass(this,RechargeActivity.class);

        this.startActivity(intent);
    }

    public void tixian(View view){

        Intent intent = new Intent();

        intent.setClass(this,TiXianActivity.class);

        this.startActivity(intent);
    }
    //我的券包
    public void myVoucherPackage(View view){
        Intent intent = new Intent();
        intent.setClass(this,MyVoucherPackageActivity.class);
        this.startActivity(intent);
    }
    //订单查询
    public void myOrders(View view){
        Intent intent = new Intent();
        intent.setClass(this,MyOrdersActivity.class);
        this.startActivity(intent);
    }
    public void goAccountInfo(View view){

        Intent intent = new Intent();

        intent.setClass(this,AccountInfoActivity.class);

        this.startActivity(intent);
    }
    //TestTab
    public void tesTab(){
        Intent intent = new Intent();
        intent.setClass(this,TestTabActivity.class);
        this.startActivity(intent);
    }
    //去往福利页面
    public  void goWelfare(View view){
        Intent intent = new Intent();
        intent.setClass(this,WelfareActivity.class);
        this.startActivity(intent);
    }
}
