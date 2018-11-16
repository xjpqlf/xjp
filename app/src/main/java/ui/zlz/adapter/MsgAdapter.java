package ui.zlz.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ui.zlz.R;
import ui.zlz.bean.Msg;

public class MsgAdapter extends BaseAdapter {
    private List<Msg.DataBeanX.DataBean> list;
    private Context context;

    public MsgAdapter(Context context, List<Msg.DataBeanX.DataBean> list) {
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
                    R.layout.msg_item, null);


            holder.title = (TextView) convertView.findViewById(R.id.tv_msg_title);
            holder.time = (TextView) convertView.findViewById(R.id.tv_msg_time);
            holder.conten = (TextView) convertView.findViewById(R.id.tv_conten);
            holder.cri =convertView.findViewById(R.id.iv_isread);



            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long lt = new Long(list.get(position).getCtime());
        Date date = new Date(lt);
      String  res = simpleDateFormat.format(date);
         if (0==list.get(position).getRead_status()){
             holder.cri.setVisibility(View.VISIBLE);

         }else {
             holder.cri.setVisibility(View.GONE);
         }
        holder.title.setText(list.get(position).getTitle());
        holder.time.setText(res);
        holder.conten.setText(list.get(position).getContent());





        return convertView;
    }
    class ViewHolder {
        TextView title;
        TextView  time;
        TextView  conten;
        ImageView cri;




    }

}
