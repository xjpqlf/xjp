package ui.zlz.activity.account;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.util.Log;

import com.qiyukf.unicorn.api.ImageLoaderListener;
import com.qiyukf.unicorn.api.StatusBarNotificationConfig;
import com.qiyukf.unicorn.api.Unicorn;
import com.qiyukf.unicorn.api.UnicornImageLoader;
import com.qiyukf.unicorn.api.YSFOptions;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.https.HttpsUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;
import ui.zlz.R;
import ui.zlz.activity.Constants;


public class Zlzapplication extends Application {

        private static Zlzapplication app = null;
        public static IWXAPI mWxApi;

    public static Zlzapplication getInstance() {
            return app;
        }


        public static Context context = null;
    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.white, android.R.color.darker_gray);//全局设置主题颜色

                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器

        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }

        @Override
        public void onCreate() {
            super.onCreate();
            //初始化网易七鱼客服
            initQy();




            registToWX();
            if (isInit()) {
                super.onCreate();
                app = this;
            }
            init();
        }

    private void initQy() {

        // appKey 可以在七鱼管理系统->设置->APP接入 页面找到
        Unicorn.init(this, "91acd64f562cca9af20131778b4fed9c", options(), new UnicornImageLoader() {
            @Nullable
            @Override
            public Bitmap loadImageSync(String uri, int width, int height) {
                return null;
            }

            @Override
            public void loadImage(String uri, int width, int height, ImageLoaderListener listener) {

            }
        });

    }

    //z注册微信登录
    private void registToWX() {
        //AppConst.WEIXIN.APP_ID是指你应用在微信开放平台上的AppID，记得替换。
        mWxApi = WXAPIFactory.createWXAPI(this, Constants.WEIXIN_APP_ID, false);
        // 将该app注册到微信
        mWxApi.registerApp(Constants.WEIXIN_APP_ID);
    }

        private static List<Activity> lists = new ArrayList<>();

        public static void addActivity(Activity activity) {
            lists.add(activity);
        }

        public static void clearActivity() {
            if (lists != null) {
                for (Activity activity : lists) {
                    activity.finish();
                }

                lists.clear();
            }
        }

        public static Context getAppContext() {
            return context;
        }
    private YSFOptions options() {
        YSFOptions options = new YSFOptions();
        options.statusBarNotificationConfig = new StatusBarNotificationConfig();
        return options;
    }






        private void init(){
//        initLocationService();



            HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);//设置可访问所有的https网站

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggerInterceptor("TAG"))
                    //配置Https
                  //  .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                    .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                    .readTimeout(10000L, TimeUnit.MILLISECONDS)
                    .addInterceptor(new LoggerInterceptor("租来赚"))
                    .hostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                    })

                    .build();

            OkHttpUtils.initClient(okHttpClient);





        }
        private boolean isInit() {
            int pid = android.os.Process.myPid();
            String processAppName = getAppProcessName(pid, this.getApplicationContext());
            Log.i("", "process app name : " + processAppName);

            // 如果app启用了远程的service，此application:onCreate会被调用2次
            // 为了防止环信SDK被初始化2次，加此判断会保证SDK被初始化1次
            // 默认的app会在以包名为默认的process name下运行，如果查到的process name不是app的process name就立即返回
            if (processAppName == null || !processAppName.equalsIgnoreCase(this.getPackageName())) {
                Log.i("", "enter the service process!");

                // 则此application::onCreate 是被service 调用的，直接返回
                return false;
            }
            return true;
        }

        /**
         * check the application process name if process name is not qualified, then we think it is a service process and we will not init SDK
         *
         * @param pID
         * @return
         */
        public String getAppProcessName(int pID, Context context) {
            String processName = null;
            ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            List l = am.getRunningAppProcesses();
            Iterator i = l.iterator();
            PackageManager pm = context.getPackageManager();
            while (i.hasNext()) {
                ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
                try {
                    if (info.pid == pID) {
                        CharSequence c = pm.getApplicationLabel(pm.getApplicationInfo(info.processName, PackageManager.GET_META_DATA));
                        // Log.d("Process", "Id: "+ info.pid +" ProcessName: "+
                        // info.processName +"  Label: "+c.toString());
                        // processName = c.toString();
                        processName = info.processName;
                        return processName;
                    }
                } catch (Exception e) {
                    // Log.d("Process", "Error>> :"+ e.toString());
                }
            }
            return processName;
        }

    }




