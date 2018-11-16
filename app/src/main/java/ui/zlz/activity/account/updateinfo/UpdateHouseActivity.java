package ui.zlz.activity.account.updateinfo;

import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bumptech.glide.Glide;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.TResult;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import ui.zlz.R;
import ui.zlz.utility.ToastUtil;
import ui.zlz.widget.ActionSheetDialog;
import ui.zlz.widget.SpinerPopWindow;

public class UpdateHouseActivity extends TakePhotoActivity implements View.OnClickListener {
    private TextView tvValue;
    private RadioGroup rp;
    private SpinerPopWindow<String> mSpinerPopWindow;
    private List<String> list;
    private TimePickerView pvTime;
    //TakePhoto
    private TakePhoto takePhoto;
    private CropOptions cropOptions;  //裁剪参数
    private CompressConfig compressConfig;  //压缩参数
    private Uri imageUri;  //图片保存路径
    private CircleImageView imageView;
    private ImageView iv1;
    private ImageView iv2;
    private ImageView iv3;
    private  int s=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      setContentView(R.layout.updata_house);
      initData();
      init();


    }



    private void init() {
        initTimePicker();
        list=new ArrayList<>();

     list.add("身份证");
     list.add("港澳通行证");


        mSpinerPopWindow = new SpinerPopWindow<String>(this, list,itemClickListener);
        LinearLayout llchosercard=findViewById(R.id.ll_chose_card);
        LinearLayout llf1=findViewById(R.id.ll_fc1);
        tvValue=findViewById(R.id.tv_value);
        iv1 = findViewById(R.id.iv_fan1);
        iv2 = findViewById(R.id.iv_fan2);
        iv3 = findViewById(R.id.iv_fan3);
        LinearLayout llf2=findViewById(R.id.ll_fc2);
        LinearLayout llf3=findViewById(R.id.ll_fc3);
        EditText etname=findViewById(R.id.et_house_name);
        EditText etnum=findViewById(R.id.et_cardnum);
        rp = findViewById(R.id.radioGroupID);
    RadioButton raf= findViewById(R.id.femaleGroupID);
    RadioButton ram=  findViewById(R.id.maleGroupID);
    RelativeLayout rlchosebirth=findViewById(R.id.rl_chosebirth);
    initRp();
    llchosercard.setOnClickListener(this);
        llf1.setOnClickListener(this);
        llf2.setOnClickListener(this);
        llf3.setOnClickListener(this);
        rlchosebirth.setOnClickListener(this);


    }

    private void initRp() {
        int checkedRadioButtonId = rp.getCheckedRadioButtonId();

        int sex = 0;
        //定义一个变量默认值为0 作用就是做一个标识
        switch (checkedRadioButtonId) {
            case R.id.femaleGroupID:
                //选中的是男
                sex = 1;
                break;
            case R.id.maleGroupID:
                //选中的是女
                sex = 2;
                break;


        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_chose_card:

                mSpinerPopWindow.setWidth(tvValue.getWidth());
                mSpinerPopWindow.showAsDropDown(tvValue);



                break;
            case R.id.rl_chosebirth:
                pvTime.show(view);




                break;
            case R.id.ll_fc1:
                s=1;
                showAlerDailog();


                break;
            case R.id.ll_fc2:
                s=2;
                showAlerDailog();
                break;
            case R.id.ll_fc3:
                s=3;
                showAlerDailog();
                break;

        }

    }
    /**
     * popupwindow显示的ListView的item点击事件
     */
    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            mSpinerPopWindow.dismiss();
            tvValue.setText(list.get(position));
          //  Toast.makeText(UpdateHouseActivity.this, "点击了:" + list.get(position),Toast.LENGTH_LONG).show();
        }
    };



    private String getTime(Date date) {//可根据需要自行截取数据显示
        Log.d("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }


    private void initTimePicker() {//Dialog 模式下，在底部弹出

        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                Toast.makeText(UpdateHouseActivity.this, getTime(date), Toast.LENGTH_SHORT).show();
                Log.i("pvTime", "onTimeSelect");

            }
        })
                .setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                    @Override
                    public void onTimeSelectChanged(Date date) {
                        Log.i("pvTime", "onTimeSelectChanged");
                    }
                })
                .setType(new boolean[]{true, true, true,false,false,false})
                .isDialog(true) //默认设置false ，内部实现将DecorView 作为它的父控件。
                .build();

        Dialog mDialog = pvTime.getDialog();
        if (mDialog != null) {

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);

            params.leftMargin = 0;
            params.rightMargin = 0;
            pvTime.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
            }
        }
    }




    private void showAlerDailog() {

        new ActionSheetDialog(UpdateHouseActivity.this)
                .builder()

                .setCancelable(false)
                .setCanceledOnTouchOutside(true)

                .addSheetItem("拍照",
                        ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override public void onClick(int which) {
                                imageUri = getImageCropUri();
                                ToastUtil.show(imageUri+"");
                                //拍照并裁剪
                                takePhoto.onPickFromCaptureWithCrop(imageUri, cropOptions);

                            } })
                .addSheetItem("选择相册图片",
                        ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override public void onClick(int which) {
                                imageUri = getImageCropUri();
                                ToastUtil.show(imageUri+"");
                                //从相册中选取图片并裁剪
                               takePhoto.onPickFromGalleryWithCrop(imageUri, cropOptions);

                            } })
                .show();


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

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
        Toast.makeText(UpdateHouseActivity.this, "Error:" + msg, Toast.LENGTH_SHORT).show();
    }

    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
       // String iconPath = result.getImage().getOriginalPath();

        //上传头像
            if (s==1){
                String iconPath = result.getImage().getOriginalPath();
                Glide.with(this).load(iconPath).into(iv1);
            }else if (s==2){
                String iconPath = result.getImage().getOriginalPath();
                Glide.with(this).load(iconPath).into(iv2);

            }else if (s==3){
                String iconPath = result.getImage().getOriginalPath();
                Glide.with(this).load(iconPath).into(iv3);
            }



    }
}
