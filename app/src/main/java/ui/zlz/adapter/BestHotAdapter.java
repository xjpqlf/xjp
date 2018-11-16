package ui.zlz.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ui.zlz.R;
import ui.zlz.bean.BestHot;

public class BestHotAdapter extends BaseAdapter {
    private List<BestHot> list;
    private Context context;

    public  BestHotAdapter(Context context, List<BestHot> list) {
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
                    R.layout.hot_item, null);


            holder.laese = (TextView) convertView.findViewById(R.id.tv_shouyilv);
            holder.repay = (TextView) convertView.findViewById(R.id.tv_zuqi);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.repay.setText(list.get(position).getRepay());
        holder.laese.setText(list.get(position).getLease());




        return convertView;
    }
    class ViewHolder {
        TextView repay;
        TextView  laese;



    }

}
