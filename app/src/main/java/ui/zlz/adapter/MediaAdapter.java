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
import ui.zlz.activity.Constants;
import ui.zlz.bean.AboutMediaBean;

public class MediaAdapter extends BaseAdapter {
    private List<AboutMediaBean.DataBeanX.DataBean> list;
    private Context context;

    public MediaAdapter(Context context, List<AboutMediaBean.DataBeanX.DataBean> list) {
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
                    R.layout.media_item, null);
        holder.title=    convertView.findViewById(R.id.newTitle1);
        holder.iv=    convertView.findViewById(R.id.iv_newsloge1);
        holder.type=    convertView.findViewById(R.id.newDepartment1);
        holder.time=  convertView.findViewById(R.id.newTime1);




            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
       holder.title.setText(list.get(position).getTitle());
       holder.type.setText(list.get(position).getType()+"  |");
       holder.time.setText(list.get(position).getCreate_time());
        Glide.with(context).load(Constants.BASE_URL+list.get(position).getShow_img().replaceAll("\\\\","")).fitCenter().into(holder.iv);





        return convertView;
    }
    class ViewHolder {
        TextView title;
        ImageView iv;
        TextView  type;
        TextView  time;






    }

}
