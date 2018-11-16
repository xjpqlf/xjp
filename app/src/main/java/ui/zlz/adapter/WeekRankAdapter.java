package ui.zlz.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import ui.zlz.R;
import ui.zlz.activity.Constants;
import ui.zlz.bean.WeekBean;

public class WeekRankAdapter extends BaseAdapter {
    private List<WeekBean.DataBeanX.DataBean> list;
    private Context context;

    public WeekRankAdapter(Context context, List<WeekBean.DataBeanX.DataBean> list) {
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
                    R.layout.week_item, null);
        holder.title=    convertView.findViewById(R.id.tv_week_title);
        holder.iv=    convertView.findViewById(R.id.iv_week);
        holder.ll=    convertView.findViewById(R.id.tv_week_ll);
        holder.istz=  convertView.findViewById(R.id.tv_week_istz);
        holder.sy=  convertView.findViewById(R.id.tv_week_sy);
        holder.pb=convertView.findViewById(R.id.pb_weekbar);




            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
       holder.title.setText(list.get(position).getName());
       holder.ll.setText(list.get(position).getRate()+"%+"+list.get(position).getExpect_rate()+"%");
       holder.sy.setText("剩余可投资"+list.get(position).getSurplus_tz()+"元");
        Glide.with(context).load(Constants.BASE_URL+list.get(position).getImg_url().replaceAll("\\\\","")).fitCenter().into(holder.iv);
      if (list.get(position).getIs_user_tz()==1){
         holder.istz.setText("未投资");
         holder.istz.setBackground(context.getResources().getDrawable(R.drawable.tv_tz));
          holder.istz.setTextColor(Color.parseColor("#7f7f7f"));

      }else {
          holder.istz.setText("已投资");
          holder.istz.setBackgroundColor(Color.parseColor("#F6931E"));
          holder.istz.setTextColor(Color.parseColor("#ffffff"));
      }
        double s;
      if (list.get(position).getTz_money()!=null) {

           s = 100 *  Double.valueOf(list.get(position).getTz_money()) /  Double.valueOf(list.get(position).getMoney_pond());

      }else {
         s=0;
      }

      holder.pb.setProgress((int)s);
        return convertView;
    }
    class ViewHolder {
        TextView title;
        ImageView iv;
        TextView  ll;
        TextView  sy;
        TextView  istz;
        ProgressBar pb;








    }

}
