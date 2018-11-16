package ui.zlz.utility;

import android.view.Gravity;
import android.widget.Toast;

import ui.zlz.activity.account.Zlzapplication;

public class ToastUtil {
    public static void showInCenter(int strId) {

        Zlzapplication context = Zlzapplication.getInstance();

        Toast toast = Toast.makeText(context,strId, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

    public static void showInCenter(String str) {
        Zlzapplication context =  Zlzapplication.getInstance();
        Toast toast = Toast.makeText(context,str, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

    public static void show(int strId) {
      Zlzapplication context = Zlzapplication.getInstance();
        Toast toast = Toast.makeText(context,strId, Toast.LENGTH_SHORT);
        toast.show();
    }

    public static void show(String str) {
       Zlzapplication context =  Zlzapplication.getInstance();
        Toast toast = Toast.makeText(context,str, Toast.LENGTH_SHORT);
        toast.show();
    }


    /**
     * 获取版本号
     * @return 当前应用的版本号
     */
  /*  public static void showAlerDialog() {
        final Zlzapplication context =  Zlzapplication.getInstance();
        new AlertDialog(context)
                .builder()
                .setTitle("重新登录提示")
                .setMsg("你的账号已在其他设备登录,请重新登录登录")
                .setPositiveButton("登录", new View.OnClickListener() {
                    @Override public void onClick(View v) {
                       context. startActivity(new Intent(context,LoginActivity.class));

                    } })
                .setNegativeButton("取消", new View.OnClickListener() {
                    @Override public void onClick(View v) {

                    } })
                .show();


    }*/
}
