package ui.zlz.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import ui.zlz.R;
import ui.zlz.bean.CouponBean;

public class CouponAdapter extends BaseAdapter {
    private List<CouponBean.DataBeanX.DataBean> list;
    private Context context;

    public CouponAdapter(Context context, List<CouponBean.DataBeanX.DataBean> list) {
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
                    R.layout.coupon, null);


            holder.je = (TextView) convertView.findViewById(R.id.tv_jine);
            holder.types = (TextView) convertView.findViewById(R.id.tv_types);
            holder.rlje =  convertView.findViewById(R.id.rl_tvje);
            holder.zdtz = (TextView) convertView.findViewById(R.id.tv_zdje);
            holder.type = (TextView) convertView.findViewById(R.id.tv_type);
holder.ll=convertView.findViewById(R.id.ll_text);
            holder.yxq = (TextView) convertView.findViewById(R.id.tv_yxq);
            holder.time = (TextView) convertView.findViewById(R.id.tv_user_time);


            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if ("2".equals(list.get(position).getType())){

            holder.rlje.setBackgroundColor(Color.parseColor("#FE2131"));
            holder.je.setText(list.get(position).getMoney()+"%");
            holder.type.setText(list.get(position).getMoney()+"%"+"加息劵");


        }else if("1".equals(list.get(position).getType())){
            holder.rlje.setBackgroundColor(Color.parseColor("#F99725"));
            holder.je.setText("¥"+list.get(position).getMoney());
            holder.type.setText("¥"+list.get(position).getMoney()+"代金券");
        }
        if (list.get(position).getIs_use().equals("1")){
            holder.ll.setBackgroundColor(Color.parseColor("#999999"));
        }else {

            holder.ll.setBackgroundColor(Color.parseColor("#ffffff"));
        }

 holder.zdtz.setText("使用限制"+list.get(position).getAstrict_sum()+"起");


        holder.yxq.setText("有效期 :"+list.get(position).getDay()+"天");
          holder.time.setText("过期时间  "+list.get(position).getPast_time());







        return convertView;
    }
    class ViewHolder {
        TextView je;
        TextView types;
        TextView  zdtz;
        TextView  type;

        TextView  yxq;
        TextView  time;

       RelativeLayout rlje;
       LinearLayout ll;




    }

}
