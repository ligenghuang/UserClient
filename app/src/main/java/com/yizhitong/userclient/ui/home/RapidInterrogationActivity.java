package com.yizhitong.userclient.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.data.PriceUtils;
import com.lgh.huanglib.util.data.ResUtil;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.yizhitong.userclient.BuildConfig;
import com.yizhitong.userclient.R;
import com.yizhitong.userclient.actions.RapidInterrogationAction;
import com.yizhitong.userclient.adapters.ImageItemAdapter;
import com.yizhitong.userclient.event.GeneralDto;
import com.yizhitong.userclient.event.post.AddAskHeadPost;
import com.yizhitong.userclient.ui.impl.RapidInterrogationView;
import com.yizhitong.userclient.ui.login.LoginActivity;
import com.yizhitong.userclient.ui.mine.HealthRecordsActivity;
import com.yizhitong.userclient.utils.base.UserBaseActivity;
import com.yizhitong.userclient.utils.data.MySp;
import com.yizhitong.userclient.utils.dialog.PicturesDialog;
import com.yizhitong.userclient.utils.imageloader.GlideImageLoader;
import com.yizhitong.userclient.utils.photo.PicUtils;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * description ： 快速问诊
 * author : lgh
 * email : 1045105946@qq.com
 * date : 2019/6/19
 */
public class RapidInterrogationActivity extends UserBaseActivity<RapidInterrogationAction> implements RapidInterrogationView {
    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.f_title_tv)
    TextView titleTv;

    @BindView(R.id.tv_patient)
    TextView mTvPatient;
    @BindView(R.id.ll_patient)
    LinearLayout mLlPatient;
    @BindView(R.id.tv_money)
    TextView mTvMoney;
    @BindView(R.id.tv_pay)
    TextView mTvPay;

    @BindView(R.id.rv_img)
    RecyclerView mRvImg;
    @BindView(R.id.et_describe)
    EditText mEtDescribe;
    @BindView(R.id.tv_describe_num)
    TextView mTvDescribeNum;


    String patientid = "";

    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;

    public static final int REQUEST_CODE_TAKE = 102;
    public static final int REQUEST_CODE_ALBUM = 103;
    public static int REQUEST_SELECT_TYPE = -1;//选择的类型

    private ArrayList<ImageItem> selImageList = new ArrayList<>(); //当前选择的所有图片
    ArrayList<ImageItem> images = null;
    List<String> imageList = new ArrayList<>();
    int maxImgCount = 8;

    ImageItemAdapter imageItemAdapter;
    double amount = 0;

    @Override
    public int intiLayout() {
        return R.layout.activity_rapid_interrogation;
    }

    @Override
    protected RapidInterrogationAction initAction() {
        return new RapidInterrogationAction(this, this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().addActivity(new WeakReference<>(this));
        binding();
    }

    @Override
    protected void initTitlebar() {
        super.initTitlebar();
        mImmersionBar
                .statusBarView(R.id.top_view)
                .keyboardEnable(true)
                .statusBarDarkFont(true, 0.2f)
                .addTag("RapidInterrogationActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
        titleTv.setText(ResUtil.getString(R.string.rapid_interrogation_tip_title));
    }

    @Override
    protected void init() {
        super.init();
        mContext = this;
        mActicity = this;

        initImagePicker();
        imageItemAdapter = new ImageItemAdapter(mContext,true);
        mRvImg.setLayoutManager(new GridLayoutManager(mContext, 4));
        mRvImg.setAdapter(imageItemAdapter);

        getRegisteredAmount();
        loadView();
    }


    @Override
    protected void loadView() {
        super.loadView();
        mEtDescribe.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(mEtDescribe.getText().toString())) {
                    mTvDescribeNum.setText("0/200");
                } else {
                    mTvDescribeNum.setText(mEtDescribe.getText().length() + "/200");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        imageItemAdapter.setOnClickListener(new ImageItemAdapter.OnClickListener() {
            @Override
            public void OnClick(int position) {
                imageList.remove(position);
                imageItemAdapter.refresh(imageList);
                maxImgCount++;
                showNormalToast(ResUtil.getString(R.string.rapid_interrogation_tip_7));
            }
        });
    }

    @OnClick({R.id.ll_patient, R.id.iv_add_img, R.id.tv_pay})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.ll_patient:
                //todo 健康档案
                if (!MySp.iSLoginLive(mContext)) {
                    //todo 判断是否登录
                    jumpActivityNotFinish(mContext, LoginActivity.class);
                    return;
                }
                Intent intent = new Intent(mContext, HealthRecordsActivity.class);
                intent.putExtra("isSelect", true);
                startActivityForResult(intent, 200);
                break;
            case R.id.iv_add_img:
                //todo 选择图片
                showSelectDiaLog();
                break;
            case R.id.tv_pay:
                //todo 提交问诊单
                submit();
                break;
        }
    }

    /**
     * 提交问诊单
     */
    private void submit() {
        AddAskHeadPost post = new AddAskHeadPost();
        if (TextUtils.isEmpty(patientid)){
            showNormalToast(ResUtil.getString(R.string.rapid_interrogation_tip_6));
            return;
        }
        post.setPatientid(patientid);

        if (TextUtils.isEmpty(mEtDescribe.getText().toString())){
            showNormalToast(ResUtil.getString(R.string.rapid_interrogation_tip_8));
            return;
        }
        post.setIll_note(mEtDescribe.getText().toString());
        post.setAll_money(amount);
        post.setDoctor_money(amount);
        addAskHead(imageList,post);
    }

    /**
     *获取快速问诊费用
     */
    @Override
    public void getRegisteredAmount() {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            baseAction.getRegisteredAmount();
        }
    }

    /**
     * 获取快速问诊费用 成功
     * @param amiunt
     */
    @Override
    public void getRegisteredAmountSuccessful(double amiunt) {
        loadDiss();
        amount = amiunt;
        mTvMoney.setText("￥" + PriceUtils.formatPrice(amiunt));
    }

    /**
     * 上传图片
     * @param path
     */
    @Override
    public void fileName(String path) {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            baseAction.fileName(path);
        }
    }

    /**
     * 上传图片 成功
     * @param path
     */
    @Override
    public void fileNameSuccessful(String path) {
        loadDiss();
        imageList.add(path);
        imageItemAdapter.refresh(imageList);
    }

    /**
     * 保存问诊单
     * @param imgs
     * @param post
     */
    @Override
    public void addAskHead(List<String> imgs, AddAskHeadPost post) {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            baseAction.addAskHead(imgs, post);
        }
    }

    /**
     * 保存问诊单成功
     * @param generalDto
     */
    @Override
    public void addAskHeadSuccessful(String generalDto) {
        loadDiss();
        Intent intent = new Intent(mContext,RapidInterrogationPayActivity.class);
        intent.putExtra("id",generalDto);
        startActivity(intent);
    }

    @Override
    public void onError(String message, int code) {
        loadDiss();
        showNormalToast(message);
    }

    @Override
    public void onLigonError() {
        loadDiss();
        jumpActivity(mContext, LoginActivity.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (baseAction != null) {
            baseAction.toRegister();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (baseAction != null) {
            baseAction.toUnregister();
        }
    }

    /*********************************选择图片*****************************************/

    /**
     * 选择图片
     */
    private void showSelectDiaLog() {
        PicturesDialog dialog = new PicturesDialog(this, R.style.MY_AlertDialog);
        dialog.setOnClickListener(new PicturesDialog.OnClickListener() {
            @Override
            public void onCamera() {
                takePhoto();
            }

            @Override
            public void onPhoto() {
                takeUserGally();
            }
        });
        dialog.show();
    }

    private void takePhoto() {
        /**
         * 0.4.7 目前直接调起相机不支持裁剪，如果开启裁剪后不会返回图片，请注意，后续版本会解决
         *
         * 但是当前直接依赖的版本已经解决，考虑到版本改动很少，所以这次没有上传到远程仓库
         *
         * 如果实在有所需要，请直接下载源码引用。
         */
        REQUEST_SELECT_TYPE = REQUEST_CODE_TAKE;
        //打开选择,本次允许选择的数量
        ImagePicker.getInstance().setSelectLimit(1);
        Intent intent = new Intent(RapidInterrogationActivity.this, ImageGridActivity.class);
        intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
        startActivityForResult(intent, REQUEST_CODE_SELECT);
    }

    private void takeUserGally() {

        REQUEST_SELECT_TYPE = REQUEST_CODE_ALBUM;
        //打开选择,本次允许选择的数量
        ImagePicker.getInstance().setSelectLimit(1);
        Intent intent1 = new Intent(RapidInterrogationActivity.this, ImageGridActivity.class);
        /* 如果需要进入选择的时候显示已经选中的图片，
         * 详情请查看ImagePickerActivity
         * */
//                                intent1.putExtra(ImageGridActivity.EXTRAS_IMAGES,images);
        startActivityForResult(intent1, REQUEST_CODE_SELECT);
    }

    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(true);                           //允许裁剪（单选才有效）
        imagePicker.setMultiMode(false);
        imagePicker.setSaveRectangle(true);
        imagePicker.setSelectLimit(1);              //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(400);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(400);                         //保存文件的高度。单位像素
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            L.e("xx", "添加图片返回....");
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                L.e("lgh_images", REQUEST_SELECT_TYPE + "返回的图片 数量 " + images.size());
                switch (REQUEST_SELECT_TYPE) {
                    case REQUEST_CODE_ALBUM:
                        if (images != null) {
                            selImageList.addAll(images);

                            if (CheckNetwork.checkNetwork2(mContext)) {
                                File imgUri = new File(images.get(0).path);
                                Uri dataUri = FileProvider.getUriForFile
                                        (this, BuildConfig.APPLICATION_ID + ".android7.fileprovider", imgUri);
                                int zoomSacle = 3;
                                try {
                                    // 当图片大小大于512kb至少缩小两倍
                                    if (imgUri.length() / 1024 > 512) {
                                        zoomSacle = zoomSacle * 10;
                                    }
//                                    PicUtils.showCutPhoto(data, zoomSacle, imgUri.getPath());
////                                    PicUtils.getCompressedImgPath(images.get(0).path, photoOption);
//                                    //todo  请求接口 修改头像
//                                    uploadAvatar(images.get(0).path);
                                    fileName(images.get(0).path);
                                } catch (Exception e) {
                                    loadError(ResUtil.getString(R.string.main_select_phone_error), mContext);
                                }
                                for (int i = 0; i < images.size(); i++) {
                                    L.e("lgh_images", "images.get(" + i + ").path  = " + images.get(i).path);
                                }
                            }
                        }
                        break;
                    case REQUEST_CODE_TAKE:
//                        PicUtils.compressBmpToFile(images.get(0).path, photoOption);
                        File imgUri = new File(images.get(0).path);
                        Uri dataUri = FileProvider.getUriForFile
                                (this, BuildConfig.APPLICATION_ID + ".android7.fileprovider", imgUri);
                        int zoomSacle = 3;
                        try {
                            // 当图片大小大于512kb至少缩小两倍
                            if (imgUri.length() / 1024 > 512) {
                                zoomSacle = zoomSacle * 10;
                            }
                            PicUtils.showCutPhoto(data, zoomSacle, imgUri.getPath());
//                                    PicUtils.getCompressedImgPath(images.get(0).path, photoOption);
//                                    baseAction.uploadImage(images.get(0).path);
                        } catch (Exception e) {
                            loadError(ResUtil.getString(R.string.main_select_phone_error), mContext);
                        }

                        try {
                            //todo  请求接口 修改头像
//                            uploadAvatar(images.get(0).path);
                            fileName(images.get(0).path);
//                            GlideUtil.setImageCircle(mContext,images.get(0).path,userPortaitIv,R.drawable.icon_placeholder);
//                            updataFile(images.get(0).path);
                        } catch (Exception e) {
                            loadError(ResUtil.getString(R.string.main_select_phone_error), mContext);

                        }

                        for (int i = 0; i < images.size(); i++) {
                            L.e("lgh_images", "images.get(" + i + ").path  = " + images.get(i).path);
                        }

                        break;
                }
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            L.e("xx", "预览图片返回....");
            //预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {

            }
        }
        if (resultCode == 200) {
            if (data != null) {
                mTvPatient.setText(data.getStringExtra("name"));
                patientid = data.getStringExtra("id");
            }
        }

    }

    /**********************************修改头像 end*********************************************/


}
