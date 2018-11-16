package ui.zlz.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import ui.zlz.R;
import ui.zlz.activity.Constants;
import ui.zlz.bean.PaetnerBean;

public class PartnerAdapter extends BaseAdapter {
    private List<PaetnerBean.DataBeanX.DataBean> list;
    private Context context;

    public PartnerAdapter(Context context, List<PaetnerBean.DataBeanX.DataBean> list) {
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
                    R.layout.partner_item, null);
        holder.iv=    convertView.findViewById(R.id.image);




            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Glide.with(context).load(Constants.BASE_URL+list.get(position).getImg_url().replaceAll("\\\\","")).fitCenter().into(holder.iv);




        return convertView;
    }
    class ViewHolder {
        ImageView iv;






    }

}
