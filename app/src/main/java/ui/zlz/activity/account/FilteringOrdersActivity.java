package ui.zlz.activity.account;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;

import java.text.SimpleDateFormat;
import java.util.Date;

import ui.zlz.R;
import ui.zlz.utility.ToastUtil;

public class FilteringOrdersActivity extends Activity implements View.OnClickListener {

    private TextView ks;
    private TextView js;
    private TimePickerView pvTime;
    private  int time;
    private TextView tx;
    private TextView cz;
    private int type=0;
    private  boolean iselectks=false;
    private  boolean iselectjs=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtering_orders);
        init();
    }

    private void init() {
        initTimePicker();
        ImageView iv= findViewById(R.id.iv_ddsx_back);
      ImageView ivks= findViewById(R.id.iv_xzks_dd);
      ImageView ivjs= findViewById(R.id.iv_xzjs_dd);
        Button bt=findViewById(R.id.bt_cx_dd);
        ks = findViewById(R.id.tv_ksrq_dd);
        js = findViewById(R.id.tv_jsrq_dd);
        tx = findViewById(R.id.tv_tx_dd);
        cz = findViewById(R.id.tv_cz_dd);

        iv.setOnClickListener(this);
        ivks.setOnClickListener(this);
        ivjs.setOnClickListener(this);
        bt.setOnClickListener(this);
        tx.setOnClickListener(this);
        cz.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_ddsx_back:
                finish();
                break;
            case R.id.iv_xzks_dd:
                time=1;
                pvTime.show(view);
                break;
            case R.id.iv_xzjs_dd:
                time=2;
                pvTime.show(view);
                break;
            case R.id.tv_tx_dd:
                type=1;
                tx.setTextColor(Color.parseColor("#ffffff"));
                tx.setBackgroundColor(Color.parseColor("#F6931E"));
                cz.setTextColor(Color.parseColor("#9e9e9e"));
                cz.setBackground(getResources().getDrawable(R.drawable.etsr));
                break;
            case R.id.tv_cz_dd:
                type=2;
                cz.setTextColor(Color.parseColor("#ffffff"));
                cz.setBackgroundColor(Color.parseColor("#F6931E"));
                tx.setTextColor(Color.parseColor("#9e9e9e"));
                tx.setBackground(getResources().getDrawable(R.drawable.etsr));
                break;
                //点击查询
            case R.id.bt_cx_dd:
                if (type==0){
                    ToastUtil.show("请选择筛选类型");
                    return;
                }
                if (!iselectks){
                    ToastUtil.show("请选择起始日期");
                    return;
                }
                if (!iselectjs){

                    ToastUtil.show("请选择结束日期");
                    return;
                }
                Intent  intent=new Intent(FilteringOrdersActivity.this,MyOrdersActivity.class);
                if (1==type){
                    intent.putExtra("type","tixian");
                }else if (2==type){
                    intent.putExtra("type","chongzhi");
                }
                intent.putExtra("ks",ks.getText().toString());
                intent.putExtra("js",js.getText().toString());
                startActivity(intent);
                finish();


                break;
        }

    }



    private String getTime(Date date) {//可根据需要自行截取数据显示
        Log.d("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }


    private void initTimePicker() {//Dialog 模式下，在底部弹出

        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                //  Toast.makeText(FilteringFinancialRecordActivity.this, getTime(date), Toast.LENGTH_SHORT).show();

                if (time==1){
                    iselectks=true;
                    ks.setText(getTime(date));

                }else if (time==2){
                        iselectjs=true;
                    js.setText(getTime(date));

                }

            }
        })
                .setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                    @Override
                    public void onTimeSelectChanged(Date date) {
                        Log.i("pvTime", "onTimeSelectChanged");
                    }
                })
                .setType(new boolean[]{true, true, true,false,false,false})
                .isDialog(true) //默认设置false ，内部实现将DecorView 作为它的父控件。
                .build();

        Dialog mDialog = pvTime.getDialog();
        if (mDialog != null) {

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);

            params.leftMargin = 0;
            params.rightMargin = 0;
            pvTime.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
            }
        }
    }

}
