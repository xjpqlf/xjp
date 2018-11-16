package ui.zlz.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import ui.zlz.R;
import ui.zlz.bean.UnCardBean;

public class MyUnCardAdapter extends BaseAdapter {
    private List<UnCardBean.DataBeanX.DataBean> list;
    private Context context;

    public MyUnCardAdapter(Context context, List<UnCardBean.DataBeanX.DataBean> list) {
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
                    R.layout.uncard_item, null);


            holder.card = (ImageView) convertView.findViewById(R.id.iv_card);
            holder.card_num = (TextView) convertView.findViewById(R.id.tv_card_num);


            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
String num=list.get(position).getCard_num();
        Glide.with(context).load(list.get(position).getCard_img().replaceAll("\\\\","")).fitCenter().into(holder.card);
        holder.card_num.setText("卡号 :"+num.substring(0,3)+"* * * * * *"+num.substring(num.length()-5,num.length()-1));





        return convertView;
    }
    class ViewHolder {
        ImageView card;
        TextView  card_num;



    }

}
