package ui.zlz.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ui.zlz.R;
import ui.zlz.bean.Order;

public class OrderAdapter extends BaseAdapter {
    private List<Order.DataBeanX.DataBean> list;
    private Context context;

    public OrderAdapter(Context context, List<Order.DataBeanX.DataBean> list) {
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
                    R.layout.order_item, null);


            holder.num = (TextView) convertView.findViewById(R.id.tv_ordernum);
            holder.name = (TextView) convertView.findViewById(R.id.tv_ordername);
            holder.time = (TextView) convertView.findViewById(R.id.tv_ordertime);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.num.setText(list.get(position).getSn());
        holder.name.setText(list.get(position).getGoods_name());
        holder.time.setText(list.get(position).getPtime());




        return convertView;
    }
    class ViewHolder {
        TextView num;
        TextView  name;
        TextView  time;



    }

}
