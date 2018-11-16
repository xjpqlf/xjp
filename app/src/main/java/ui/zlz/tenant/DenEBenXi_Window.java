package ui.zlz.tenant;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.widget.PopupWindowCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import ui.zlz.R;

public class DenEBenXi_Window extends PopupWindow{

    public DenEBenXi_Window(Context context) {
        super(context);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setOutsideTouchable(true);
        setFocusable(true);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View contentView = LayoutInflater.from(context).inflate(R.layout.activity_tenant_jump_hint,null, false);
        setContentView(contentView);
    }

}
