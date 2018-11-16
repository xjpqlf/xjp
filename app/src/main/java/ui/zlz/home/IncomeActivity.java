package ui.zlz.home;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import ui.zlz.R;
import ui.zlz.activity.Constants;
import ui.zlz.bean.EarnBean;
import ui.zlz.utility.DebugFlags;
import ui.zlz.utility.GetSign;
import ui.zlz.utility.ToastUtil;

public class IncomeActivity extends Activity {

    private TextView tv1;
    private TextView tv2;
    private TextView ze;
    private TextView syze;
    private TextView istz;
    private TextView qt;
    private TextView qx;
    private LineChart lineChart;
    private TextView zzll;
    private XAxis xAxis;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.income_activity);
        initView();
        getData();
    }

    private void initView() {
        ImageView iv = findViewById(R.id.iv_yqweb_back);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tv1 = findViewById(R.id.tv_yqll);
        tv2 = findViewById(R.id.tv_yqll2);
        ze = findViewById(R.id.tv_zje);
        syze = findViewById(R.id.tv_syzje);
        qt = findViewById(R.id.tv_qtje);
        qx = findViewById(R.id.tv_qx);
        istz = findViewById(R.id.tv_istz);
        zzll = findViewById(R.id.tv_zjll);
        lineChart = findViewById(R.id.lc);




        Description description = new Description();
        description.setText("");
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override public String getFormattedValue
                    (float value, AxisBase axis) {
                return String.valueOf((int) value ).concat("月"); } });
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置x轴的显示位置

        description.setTextColor(Color.RED);
        description.setTextSize(20);
        lineChart.setDescription(description);//设置图表描述信息
        lineChart.setNoDataText("没有数据");//没有数据时显示的文字
        lineChart. setEnabled(false);


        lineChart.setNoDataTextColor(Color.BLUE);//没有数据时显示文字的颜色
        lineChart.setDrawGridBackground(false);//chart 绘图区后面的背景矩形将绘制
        lineChart.setDrawBorders(false);//禁止绘制图表边框的线
        //lineChart.setBorderColor(); //设置 chart 边框线的颜色。
        //lineChart.setBorderWidth(); //设置 chart 边界线的宽度，单位 dp。
        //lineChart.setLogEnabled(true);//打印日志
        //lineChart.notifyDataSetChanged();//刷新数据
        //lineChart.invalidate();//重绘
        lineChart.getXAxis().setDrawAxisLine(false);

        lineChart.getXAxis().setDrawGridLines(false);
        lineChart.getAxisLeft().setDrawAxisLine(false);

        lineChart.getAxisRight().setDrawAxisLine(false);
    }

    private void initChart(int x, List<Float> y) {


        ArrayList<Entry> values1 = new ArrayList<>();

        for (int i = 0; i <x ; i++) {
            values1.add(new Entry(i+1, y.get(i)));
            DebugFlags.logD(values1.get(i).getX()+"折线收益"+values1.get(i).getY());
        }

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setLabelCount(values1.size(),true);


        //LineDataSet每一个对象就是一条连接线
        LineDataSet set1;


        //判断图表中原来是否有数据
        if (lineChart.getData() != null &&
                lineChart.getData().getDataSetCount() > 0) {
            //获取数据1
            set1 = (LineDataSet) lineChart.getData().getDataSetByIndex(0);
            set1.setValues(values1);

            //刷新数据
            lineChart.getData().notifyDataChanged();
            lineChart.notifyDataSetChanged();
        } else {
            //设置数据1  参数1：数据源 参数2：图例名称
            set1 = new LineDataSet(values1, "");
            set1.setColor(Color.BLACK);
            set1.setCircleColor(Color.BLACK);
            set1.setLineWidth(1f);//设置线宽

            set1.setCircleRadius(3f);//设置焦点圆心的大小
            set1.setDrawCircles(false);
            set1.setDrawValues(false);
            set1.enableDashedHighlightLine(10f, 5f, 0f);//点击后的高亮线的显示样式
            set1.setHighlightLineWidth(2f);//设置点击交点后显示高亮线宽
            set1.setHighlightEnabled(false);//是否禁用点击高亮线
            set1.setHighLightColor(Color.RED);//设置点击交点后显示交高亮线的颜色
            set1.setValueTextSize(9f);//设置显示值的文字大小
            set1.setDrawFilled(false);//设置禁用范围背景填充

            //格式化显示数据
            final DecimalFormat mFormat = new DecimalFormat("###,###,##0");
          set1.setValueFormatter(new IValueFormatter() {
                @Override
                public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                    return mFormat.format(value);
                }
            });




            //保存LineDataSet集合
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1); // add the datasets

            //创建LineData对象 属于LineChart折线图的数据集合
            LineData data = new LineData(dataSets);
            // 添加到图表中
            lineChart.setData(data);
            //绘制图表
            lineChart.invalidate();

        }

    }
    private void getData() {
        String str = GetSign.getSign();
        String[] strs = str.split(",");
        DebugFlags.logD("签名" + str);


        OkHttpUtils.post()

                .url(Constants.BASE_URL + "/Api/goods/earnings/")
                .addParams("t", strs[0])
                .addParams("r", strs[1])
                .addParams("e", strs[2])
                //   .addParams("mobile","13071266270")


                .addParams("goods_id", getIntent().getStringExtra("rankid"))


                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        DebugFlags.logD("收益表" + response);
                        EarnBean earnBean = JSON.parseObject(response, EarnBean.class);
                        if (earnBean.getCode() == 1) {
                            tv1.setText(earnBean.getData().getData().getRate() + "%");
                            zzll.setText(earnBean.getData().getData().getExpect_rate() + "%");
                            double d = Double.valueOf(earnBean.getData().getData().getRate()) + Double.valueOf(earnBean.getData().getData().getExpect_rate());
                            tv2.setText(d + "");
                            ze.setText("总金额" + earnBean.getData().getData().getMoney_pond() + "元");
                            syze.setText("剩余可投" + earnBean.getData().getData().getResidue_tz() + "元");
                            qt.setText("起投金额" + earnBean.getData().getData().getLeast_money() + "元");
                            qx.setText("项目期限" + earnBean.getData().getData().getMonth() + "个月");
                            if (earnBean.getData().getData().getIs_stick().equals("0")) {

                                istz.setText("未投资");
                            } else {
                                istz.setText("已投资");
                            }
                           int x=  Integer.parseInt(earnBean.getData().getData().getMonth());
                            List<Float> y=new ArrayList<>();
                            for (int i = 0; i <x ; i++) {
                                if (i == x - 1) {
                                    y.add((float) d);
                                } else {
                                    y.add((float) ((i + 1) * Double.valueOf(earnBean.getData().getData().getRate()) / x));
                                }



                            }
                            initChart(x, y);

                        } else if (earnBean.getCode() == 2) {

                        } else {
                            ToastUtil.show(earnBean.getMessage());
                        }

                    }
                });


    }





}