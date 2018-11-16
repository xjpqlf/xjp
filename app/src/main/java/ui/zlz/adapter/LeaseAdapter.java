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
import ui.zlz.bean.Lease;

public class LeaseAdapter extends BaseAdapter {
    private List<Lease> list;
    private Context context;

    public LeaseAdapter(Context context, List<Lease> list) {
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
                    R.layout.lease_item, null);


            holder.rate = (TextView) convertView.findViewById(R.id.tv_lease_rate);
            holder.receive = (TextView) convertView.findViewById(R.id.tv_receivable);
            holder.qishu = (TextView) convertView.findViewById(R.id.tv_qishu);
            holder.amount = (TextView) convertView.findViewById(R.id.tv_amount_lease);
            holder.islease = (TextView) convertView.findViewById(R.id.tv_islease);


            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.rate.setText(list.get(position).getRate());
        holder.receive.setText(list.get(position).getReceivable());
        holder.qishu.setText(list.get(position).getNum());
        holder.amount.setText(list.get(position).getSum());
        holder.receive.setText(list.get(position).getReceivable());
        if (list.get(position).isIslease()){
            holder.islease.setText("已退租");
            holder.islease.setTextColor(Color.parseColor("#ffffff"));
            holder.islease.setBackgroundColor(Color.parseColor("#808080"));
        }else {
            holder.islease.setText("退租");
            holder.islease.setTextColor(Color.parseColor("#ffffff"));
            holder.islease.setBackgroundColor(Color.parseColor("#F6931E"));
        }




        return convertView;
    }
    class ViewHolder {
        TextView rate;
        TextView  receive;
        TextView  qishu;
        TextView  islease;
        TextView  amount;



    }

}
