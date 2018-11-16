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
import ui.zlz.bean.NewComer;

public class NewComerAdapter extends BaseAdapter {
    private List<NewComer> list;
    private Context context;

    public NewComerAdapter(Context context, List<NewComer> list) {
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
                    R.layout.newcomer_item, null);


            holder.rate = (TextView) convertView.findViewById(R.id.tv_rate);
            holder.time = (TextView) convertView.findViewById(R.id.tv_times);
            holder.isinveas= (TextView) convertView.findViewById(R.id.tv_isinvest);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.rate.setText(list.get(position).getRate());
        holder.time.setText(list.get(position).getTime());
        if (list.get(position).isInvest()){
            holder.isinveas.setText("已投资");
            holder.isinveas.setBackgroundColor(Color.parseColor("#bebebe"));
        }else {
            holder.isinveas.setText("投资");
            holder.isinveas.setBackgroundColor(Color.parseColor("#F6931E"));
        }




        return convertView;
    }
    class ViewHolder {
        TextView time;
        TextView  rate;
        TextView  isinveas;



    }

}
