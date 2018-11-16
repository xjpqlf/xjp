package ui.zlz.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ui.zlz.R;
import ui.zlz.bean.AllLaeses;

public class AllLeasesAdapter extends BaseAdapter {
    private List<AllLaeses.DataBeanX.DataBean> list;
    private Context context;

    public AllLeasesAdapter(Context context, List<AllLaeses.DataBeanX.DataBean> list) {
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
                    R.layout.all_leases_item, null);
        holder.title=    convertView.findViewById(R.id.tv_title);
        holder.fbsj=    convertView.findViewById(R.id.tv_tzfbsj);
        holder.tzje=    convertView.findViewById(R.id.tv_tzje1);
        holder.syjes=  convertView.findViewById(R.id.tv_syje_all);
        holder.hastz=    convertView.findViewById(R.id.tv_hastz);



            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.title.setText(list.get(position).getName()+list.get(position).getOrder_no());
        holder.fbsj.setText(list.get(position).getCtime());
        holder.syjes.setText(list.get(position).getSurplus_tz()+"");
        holder.tzje.setText(list.get(position).getMoney_pond());
        if (list.get(position).getIs_user_tz()==1){
  holder.hastz.setText("已投资");
  holder.hastz.setBackgroundColor(Color.parseColor("#999999"));
        }else {
            holder.hastz.setText("投资");
            holder.hastz.setBackgroundColor(Color.parseColor("#F6931E"));
        }





        return convertView;
    }
    class ViewHolder {
        TextView title;
        TextView  fbsj;
        TextView  tzje;
        TextView  syjes;
        TextView  hastz;





    }

}
