package ui.zlz.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ui.zlz.R;
import ui.zlz.bean.LzRecordBean;

public class RecordAdapter extends BaseAdapter {
    private List<LzRecordBean.DataBeanX.DataBean> list;
    private Context context;

    public RecordAdapter(Context context, List<LzRecordBean.DataBeanX.DataBean> list) {
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
                    R.layout.lz_item, null);
        holder.name=    convertView.findViewById(R.id.tv_recordname);
        holder.money=    convertView.findViewById(R.id.tv_recordmoney);
        holder.time=    convertView.findViewById(R.id.tv_recordtime);




            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText(list.get(position).getNickname());
        holder.money.setText(list.get(position).getTz_money());
        holder.time.setText(list.get(position).getTz_time());





        return convertView;
    }
    class ViewHolder {
        TextView name;
        TextView  money;
        TextView  time;






    }

}
