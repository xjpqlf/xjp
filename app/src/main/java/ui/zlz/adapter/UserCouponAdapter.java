package ui.zlz.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import ui.zlz.R;
import ui.zlz.bean.CouponBean;

public class UserCouponAdapter extends BaseAdapter {
    private List<CouponBean.DataBeanX.DataBean> list;
    private Context context;

    public UserCouponAdapter(Context context, List<CouponBean.DataBeanX.DataBean> list) {
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
                    R.layout.mycoupon, null);

             holder.jes=convertView.findViewById(R.id.tv_jines);
             holder.rl=convertView.findViewById(R.id.rl_tvjes);
             holder.types=convertView.findViewById(R.id.tv_mytypes);
             holder.yxq=convertView.findViewById(R.id.tv_yxqs);
             holder.times=convertView.findViewById(R.id.tv_user_times);



            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if ("2".equals(list.get(position).getType())){

            holder.rl.setBackgroundResource(R.mipmap.jia_xi_quan2x);

            holder.jes.setText(list.get(position).getMoney()+"%"+"加息劵");
            holder.types.setText("加息劵："+list.get(position).getAstrict_sum()+"起");

        }else if("1".equals(list.get(position).getType())){
            holder.rl.setBackgroundColor(Color.parseColor("#F99725"));
            holder.rl.setBackgroundResource(R.mipmap.dai_jin_quan2x);
            holder.jes.setText("¥"+list.get(position).getMoney()+"代金券");
            holder.types.setText("代金券："+list.get(position).getAstrict_sum()+"起");
        }





        holder.yxq.setText("有效期 :"+list.get(position).getDay()+"天");
          holder.times.setText("使用期限  "+list.get(position).getPast_time());







        return convertView;
    }
    class ViewHolder {
        TextView jes;
        TextView types;
        TextView  yxq;
        TextView  times;
        RelativeLayout rl;






    }

}
