package ui.zlz.activity.account;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.TResult;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import ui.zlz.R;
import ui.zlz.activity.Constants;
import ui.zlz.activity.account.updateinfo.SecurityInfoActivity;
import ui.zlz.utility.DebugFlags;
import ui.zlz.utility.GetSign;
import ui.zlz.utility.SharedPrefUtil;
import ui.zlz.utility.ToastUtil;
import ui.zlz.widget.ActionSheetDialog;

public class AccountInfoActivity extends TakePhotoActivity implements View.OnClickListener {
    //TakePhoto
    private TakePhoto takePhoto;
    private CropOptions cropOptions;  //裁剪参数
    private CompressConfig compressConfig;  //压缩参数
    private Uri imageUri;  //图片保存路径
    private CircleImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_info);
        Zlzapplication.addActivity(this);
        //申请相关权限

        initData();
        init();
    }

    private void init() {
       ImageView ivback= findViewById(R.id.iv_back_info);

        imageView = findViewById(R.id.iv_infotx);
        RelativeLayout rltx= findViewById(R.id.rl_tx);
        RelativeLayout info= findViewById(R.id.rl_info);
        RelativeLayout safe= findViewById(R.id.rl_accsafe);
        RelativeLayout card= findViewById(R.id.rl_bankcard);
        Button btout=findViewById(R.id.bt_outlogin);
        ivback.setOnClickListener(this);
        rltx.setOnClickListener(this);
        info.setOnClickListener(this);
        safe.setOnClickListener(this);
        card.setOnClickListener(this);
        btout.setOnClickListener(this);
        if (!SharedPrefUtil.getString(AccountInfoActivity.this,"headerImg","").startsWith("http")) {
            Glide.with(AccountInfoActivity.this).load(Constants.BASE_URL + SharedPrefUtil.getString(AccountInfoActivity.this,"headerImg","").replaceAll("\\\\", "")).error(R.mipmap.touxiang).fitCenter().into(imageView);
        }else {
            Glide.with(AccountInfoActivity.this).load( SharedPrefUtil.getString(AccountInfoActivity.this,"headerImg","").replaceAll("\\\\", "")).error(R.mipmap.touxiang).fitCenter().into(imageView);

        }



    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back_info:
                finish();
                break;
            case R.id.rl_tx:
                AndPermission.with(this)
                        .runtime()
                        .permission(Permission.Group.CAMERA)

                        .onGranted(new Action<List<String>>() {
                            @Override
                            public void onAction(List<String> data) {
                                // data.get(0);
                                showAlerDailog();
                            }
                        })
                        .onDenied(new Action<List<String>>() {
                            @Override
                            public void onAction(List<String> data) {
                                /**
                                 * 当用户没有允许该权限时，回调该方法
                                 */
                                Toast.makeText(AccountInfoActivity.this, "没有获取相机权限，该功能无法使用", Toast.LENGTH_SHORT).show();
                                /**
                                 * 判断用户是否点击了禁止后不再询问，AndPermission.hasAlwaysDeniedPermission(MainActivity.this, data)
                                 */
                                if (AndPermission.hasAlwaysDeniedPermission(AccountInfoActivity.this, data)) {
                                    //true，弹窗再次向用户索取权限

                                }
                            }
                        }).start();









                break;
            case R.id.rl_info:
startActivity(new Intent(AccountInfoActivity.this,PersonalBasicInfoActivity.class));
                break;
            case R.id.rl_accsafe:
startActivity(new Intent(AccountInfoActivity.this,SecurityInfoActivity.class));

                break;
            case R.id.rl_bankcard:
                //跳转我的银行卡信息
                startActivity(new Intent(AccountInfoActivity.this,MyUnionPayingCardActivity.class));

                break;
            case R.id.bt_outlogin:
                Zlzapplication.clearActivity();
                startActivity(new Intent(AccountInfoActivity.this,LoginActivity.class));


                break;


        }


    }

    private void showAlerDailog() {

        new ActionSheetDialog(AccountInfoActivity.this)
                .builder()

                .setCancelable(false)
                .setCanceledOnTouchOutside(true)

                .addSheetItem("拍照",
                        ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                    @Override public void onClick(int which) {
                        imageUri = getImageCropUri();
                        //拍照并裁剪
                        takePhoto.onPickFromCaptureWithCrop(imageUri, cropOptions);

                    } })
                .addSheetItem("选择相册图片",
                        ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                    @Override public void onClick(int which) {
                        imageUri = getImageCropUri();
                        //从相册中选取图片并裁剪
                        takePhoto.onPickFromGalleryWithCrop(imageUri, cropOptions);

                    } })
                .show();


    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        String iconPath = result.getImage().getOriginalPath();
        //上传头像
        UpLoadHeadImg(iconPath);


        //Toast显示图片路径
        Toast.makeText(this, "imagePath:" + iconPath, Toast.LENGTH_SHORT).show();
        //Google Glide库 用于加载图片资源
        DebugFlags.logD(iconPath+"图片地址");

    }
    //上传图像
    private void UpLoadHeadImg(final String iconPath) {
        String str=GetSign.getSign();
        String[] strs=str.split(",");
        DebugFlags.logD("签名"+str);
        String[] names = iconPath.split("/");

        OkHttpUtils.post()

                .url(Constants.BASE_URL +"Api/User/upload_header")
                .addParams("t",strs[0])
                .addParams("r",strs[1])
                .addParams("e",strs[2])
                //   .addParams("mobile","13071266270")
                .addParams("token",SharedPrefUtil.getString(this,"token",""))
                .addFile("img",names[names.length-1],new File(iconPath))






                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        DebugFlags.logD("保存图像"+response);
                        try {
                            JSONObject js=new JSONObject(response);
                            if ("0".equals(js.getString("code"))){
                                ToastUtil.show(js.getString("message"));

                            }else {

                                ToastUtil.show(js.getString("message"));
                                String data=js.getString("data");
                                JSONObject datas =new JSONObject(data);

                                String headerImg=datas.getString("avatar_url");

                                SharedPrefUtil.saveString(AccountInfoActivity.this,"headerImg",headerImg);
                                Glide.with(AccountInfoActivity.this).load(Constants.BASE_URL+headerImg).fitCenter().into(imageView);

                               // Glide.with(AccountInfoActivity.this).load(iconPath).into(imageView);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });


    }


    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
        Toast.makeText(AccountInfoActivity.this, "Error:" + msg, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void takeCancel() {
        super.takeCancel();
    }














    private void initData() {
        ////获取TakePhoto实例
        takePhoto = getTakePhoto();
        //设置裁剪参数
        cropOptions = new CropOptions.Builder().setAspectX(1).setAspectY(1).setWithOwnCrop(false).create();
        //设置压缩参数
        compressConfig=new CompressConfig.Builder().setMaxSize(50*1024).setMaxPixel(800).create();
        takePhoto.onEnableCompress(compressConfig,true);  //设置为需要压缩
    }


    //获得照片的输出保存Uri
    private Uri
    getImageCropUri() {
        File file=new File(Environment.getExternalStorageDirectory(), "/temp/"+System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        return Uri.fromFile(file);
    }


}
