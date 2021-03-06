package ui.zlz.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ui.zlz.R;
import ui.zlz.bean.AwardAndFriend;

public class AwardAdapter extends BaseAdapter {
    private List<AwardAndFriend> list;
    private Context context;

    public AwardAdapter(Context context, List<AwardAndFriend> list) {
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
                    R.layout.award_item, null);


            holder.name = (TextView) convertView.findViewById(R.id.tv_contens);
            holder.jf = (TextView) convertView.findViewById(R.id.tv_jf);


            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText(list.get(position).getNickname());
        holder.jf.setText(list.get(position).getIntegral()+"积分");





        return convertView;
    }
    class ViewHolder {
        TextView name;
        TextView  jf;



    }

}
