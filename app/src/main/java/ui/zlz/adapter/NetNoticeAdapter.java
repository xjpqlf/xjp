package ui.zlz.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ui.zlz.R;
import ui.zlz.bean.NoticeNetBean;

public class NetNoticeAdapter extends BaseAdapter {
    private List<NoticeNetBean.DataBeanX.DataBean> list;
    private Context context;

    public NetNoticeAdapter(Context context, List<NoticeNetBean.DataBeanX.DataBean> list) {
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
                    R.layout.notice_item, null);
        holder.title=    convertView.findViewById(R.id.noticeTitle11);

        holder.time=  convertView.findViewById(R.id.noticeTime1);




            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
       holder.title.setText(list.get(position).getTitle());

       holder.time.setText(list.get(position).getCreate_time());






        return convertView;
    }
    class ViewHolder {
        TextView title;

        TextView  time;






    }

}
