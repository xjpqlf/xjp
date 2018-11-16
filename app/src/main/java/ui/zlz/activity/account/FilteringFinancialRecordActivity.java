package ui.zlz.activity.account;

import android.app.Activity;
import android.app.Dialog;
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

public class FilteringFinancialRecordActivity extends Activity implements View.OnClickListener {

    private TextView qs;
    private TextView js;
    private TextView zc;
    private TextView sr;
    private TimePickerView pvTime;
    private  int time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtering_financial_record);
        init();
    }

    private void init() {
        initTimePicker();
      Button bt=  findViewById(R.id.bt_cx);
        qs = findViewById(R.id.tv_ksrq);
        js = findViewById(R.id.tv_jsrq);
        zc = findViewById(R.id.tv_zcsx);
        sr = findViewById(R.id.tv_srsx);
        ImageView ivks=findViewById(R.id.iv_xzks);
        ImageView ivjs=findViewById(R.id.iv_xzjs);
        ImageView ivback=findViewById(R.id.iv_ddsx_back);
        bt.setOnClickListener(this);


      sr.setOnClickListener(this);
        zc.setOnClickListener(this);
       ivks.setOnClickListener(this);
       ivjs.setOnClickListener(this);
       ivback.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //点击查询按钮
            case R.id.bt_cx:
                break;
                //点击支出按钮
            case R.id.tv_zcsx:
                zc.setTextColor(Color.parseColor("#ffffff"));
                zc.setBackgroundColor(Color.parseColor("#F6931E"));
                sr.setTextColor(Color.parseColor("#9e9e9e"));
               sr.setBackground(getResources().getDrawable(R.drawable.etsr));

                break;
                //点击收入按钮
                case R.id.tv_srsx:
                    sr.setTextColor(Color.parseColor("#ffffff"));
                    sr.setBackgroundColor(Color.parseColor("#F6931E"));
                   zc.setTextColor(Color.parseColor("#9e9e9e"));
                    zc.setBackground(getResources().getDrawable(R.drawable.etsr));
                break;
                //选择开始日历
            case R.id.iv_xzks:
                 time=1;
                pvTime.show(view);


                break;
                //选择结束日历
            case R.id.iv_xzjs:
                time=2;
                pvTime.show(view);
                break;
                //返回按钮
            case R.id.iv_ddsx_back:
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
                qs.setText(getTime(date));

               }else if (time==2){

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
