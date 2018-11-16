package ui.zlz.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ui.zlz.R;
import ui.zlz.activity.Constants;
import ui.zlz.bean.Income;


public class IncomeAdapter extends BaseAdapter {


    private List<Income> list;
    private Context context;

    public IncomeAdapter(Context context, List<Income> list) {
        this.context = context;
        this.list = list;
    }

    public int getCount() {

        return list.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return list.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup arg2) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.income_item, null);


            holder.icon = (ImageView) convertView.findViewById(R.id.iv_icome);
            holder.tztime = (TextView) convertView.findViewById(R.id.tv_tztime);
            holder.zuqi = (TextView) convertView.findViewById(R.id.tv_fwtime);
            holder.fbtime = (TextView) convertView.findViewById(R.id.tv_fbtime);
            holder.money = (TextView) convertView.findViewById(R.id.tv_tzmoney);


            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long lt = new Long(list.get(position).getCtime());
        Date date = new Date(lt);
        String  res = simpleDateFormat.format(date);
        if (list.get(position).getImg_url().startsWith("U")){
            Glide.with(context).load(Constants.BASE_URL+list.get(position).getImg_url()).fitCenter().into(holder.icon);

        }
        else {
            Glide.with(context).load(Constants.BASE_URL+"Upload/"+list.get(position).getImg_url()).fitCenter().into(holder.icon);
        }
        holder.tztime.setText("充值时间 ："+res);
        holder.zuqi.setText("服务周期:  "+list.get(position).getMonth()+"个月");
        holder.fbtime.setText(res);
        holder.money.setText(list.get(position).getMoney_pond());





        return convertView;
    }
    class ViewHolder {
        ImageView  icon;
        TextView  tztime;
        TextView  zuqi;
        TextView  fbtime;
        TextView  money;



    }
}
