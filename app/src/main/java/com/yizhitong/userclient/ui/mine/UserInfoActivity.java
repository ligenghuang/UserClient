package com.yizhitong.userclient.ui.mine;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lgh.huanglib.util.CheckNetwork;
import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.base.ActivityStack;
import com.lgh.huanglib.util.config.GlideUtil;
import com.lgh.huanglib.util.data.MySharedPreferencesUtil;
import com.lgh.huanglib.util.data.ResUtil;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.yizhitong.userclient.BuildConfig;
import com.yizhitong.userclient.R;
import com.yizhitong.userclient.actions.BaseAction;
import com.yizhitong.userclient.actions.UserInfoAction;
import com.yizhitong.userclient.event.PersonalDataDto;
import com.yizhitong.userclient.event.UpdataInfoDto;
import com.yizhitong.userclient.event.UserInfoDto;
import com.yizhitong.userclient.net.WebUrlUtil;
import com.yizhitong.userclient.ui.impl.UserInfoView;
import com.yizhitong.userclient.ui.login.LoginActivity;
import com.yizhitong.userclient.utils.base.UserBaseActivity;
import com.yizhitong.userclient.utils.data.MySp;
import com.yizhitong.userclient.utils.dialog.LogoutDialog;
import com.yizhitong.userclient.utils.dialog.ModifyDialog;
import com.yizhitong.userclient.utils.dialog.ModifyIdNumberDialog;
import com.yizhitong.userclient.utils.dialog.PicturesDialog;
import com.yizhitong.userclient.utils.imageloader.GlideImageLoader;
import com.yizhitong.userclient.utils.photo.PicUtils;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
/**
* description ： 个人信息
* author : lgh
* email : 1045105946@qq.com
* date : 2019/6/25
*/
public class UserInfoActivity extends UserBaseActivity<UserInfoAction> implements UserInfoView {

    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.f_title_tv)
    TextView titleTv;

    @BindView(R.id.iv_user_portrait)
    ImageView userPortaitIv;
    @BindView(R.id.tv_user_name)
    TextView userNameTv;
    @BindView(R.id.tv_user_phone)
    TextView userPhoneTv;
    @BindView(R.id.tv_user_id_number)
    TextView userIdNumberTv;


    int updata = 0;

    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;

    public static final int REQUEST_CODE_TAKE = 102;
    public static final int REQUEST_CODE_ALBUM = 103;
    public static int REQUEST_SELECT_TYPE = -1;//选择的类型
    private ArrayList<ImageItem> selImageList = new ArrayList<>(); //当前选择的所有图片
    ArrayList<ImageItem> images = null;

    @Override
    public int intiLayout() {
        return R.layout.activity_user_info;
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
                .addTag("UserInfoActivity")  //给上面参数打标记，以后可以通过标记恢复
                .navigationBarWithKitkatEnable(false)
                .init();
        toolbar.setNavigationOnClickListener(view -> finish());
        titleTv.setText(ResUtil.getString(R.string.user_info_tip_title));
    }

    @Override
    protected void init() {
        super.init();
        mContext = this;
        mActicity = this;
        initImagePicker();
        getUserInfo();
    }

    @Override
    protected UserInfoAction initAction() {
        return new UserInfoAction(this, this);
    }


    @OnClick({R.id.rl_user_portrait,R.id.rl_user_name,R.id.rl_user_id_number,R.id.tv_logout})
    void OnClick(View view){
        switch (view.getId()){
            case R.id.rl_user_portrait:
                //todo 修改头像
                showSelectDiaLog();
                break;
            case R.id.rl_user_name:
                //todo 修改昵称
                ModifyDialog modifyDialog = new ModifyDialog(mContext,R.style.MY_AlertDialog,ResUtil.getString(R.string.user_info_tip_6));
                modifyDialog.setOnClickListener(new ModifyDialog.OnClickListener() {
                    @Override
                    public void confirm(String txet) {
                        if (TextUtils.isEmpty(txet)){
                            showNormalToast(ResUtil.getString(R.string.user_info_tip_6));
                        }else {
                           updataNicename(txet);
                            hideInput();
                            modifyDialog.dismiss();
                        }
                    }
                });
                modifyDialog.show();
                break;
            case R.id.rl_user_id_number:
                //todo 修改身份证号码
                ModifyIdNumberDialog modifyDialog2 = new ModifyIdNumberDialog(mContext,R.style.MY_AlertDialog,ResUtil.getString(R.string.user_info_tip_7));
                modifyDialog2.setOnClickListener(new ModifyIdNumberDialog.OnClickListener() {
                    @Override
                    public void confirm(String txet) {
                        if (TextUtils.isEmpty(txet)){
                            showNormalToast(ResUtil.getString(R.string.user_info_tip_7));
                        }else {
                            updataIdNumber(txet);
                            hideInput();
                            modifyDialog2.dismiss();
                        }
                    }
                });
                modifyDialog2.show();
                break;
            case R.id.tv_logout:
                //todo 退出登录
                LogoutDialog logoutDialog = new LogoutDialog(mContext,R.style.MY_AlertDialog);
                logoutDialog.setOnClickListener(new LogoutDialog.OnClickListener() {
                    @Override
                    public void confirm() {
                        logout();
                    }
                });
                logoutDialog.show();
                break;
        }
    }

    /**
     * 获取个人资料
     */
    @Override
    public void getUserInfo() {
        if (CheckNetwork.checkNetwork2(mContext)){
            loadDialog();
            baseAction.getUserInfo();
        }
    }

    /**
     * 获取个人资料成功
     * @param personalDataDto
     */
    @Override
    public void getUserInfoSuccessful(PersonalDataDto personalDataDto) {
        loadDiss();
        PersonalDataDto.DataBean dataBean = personalDataDto.getData();
        userIdNumberTv.setText(dataBean.getIdnumber());
        userNameTv.setText(dataBean.getNicename());
        userPhoneTv.setText(dataBean.getPhome());
        String portrait = dataBean.getNiceImg();
        if (portrait.indexOf("H5/Uimg") != -1) {
            GlideUtil.setImage(mContext, WebUrlUtil.IMG_URL + portrait, userPortaitIv, R.drawable.icon_placeholder);
            L.e("lgh", WebUrlUtil.IMG_URL + portrait);
        } else {
            GlideUtil.setImage(mContext, WebUrlUtil.IMG_URL + "H5/Uimg" + portrait, userPortaitIv, R.drawable.icon_placeholder);
            L.e("lgh", WebUrlUtil.IMG_URL + "H5/Uimg" + portrait);
        }
    }

    /**
     * 修改头像
     *
     * @param filePath
     */
    @Override
    public void updataFile(String filePath) {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            updata = 1;
            baseAction.updataFile(filePath);
        }
    }

    /**
     * 修改昵称
     *
     * @param nicename
     */
    @Override
    public void updataNicename(String nicename) {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            updata = 2;
            baseAction.updataNicename(nicename);
        }
    }

    /**
     * 修改身份证号码
     *
     * @param idNumber
     */
    @Override
    public void updataIdNumber(String idNumber) {
        if (CheckNetwork.checkNetwork2(mContext)) {
            loadDialog();
            updata = 3;
            baseAction.updataIdNumber(idNumber);
        }
    }

    /**
     * 修改成功
     *
     * @param updataInfoDto
     */
    @Override
    public void updataSuccessful(UpdataInfoDto updataInfoDto) {
        loadDiss();
        if (updataInfoDto.getCode() == 1) {
            switch (updata) {
                case 1:
                    //todo 头像
                    GlideUtil.setImageCircle(mContext, images.get(0).path, userPortaitIv, R.drawable.icon_placeholder);
                    break;
                case 2:
                    //todo 昵称
                    userNameTv.setText(updataInfoDto.getData().getNicename());
                    break;
                case 3:
                    //TODO 身份证号码
                    userIdNumberTv.setText(updataInfoDto.getData().getIdnumber());
                    break;
            }
        }
    }

    /**
     * 退出登录
     */
    @Override
    public void logout() {
        if (CheckNetwork.checkNetwork2(mContext)){
            loadDialog();
            baseAction.logout();
        }
    }

    /**
     * 退出登录成功
     */
    @Override
    public void logoutSuccessful() {
        loadDiss();
        finish();
        MySp.clearAllSP(mContext);
        MySharedPreferencesUtil.setSessionId(mContext,null);
    }

    /**
     * 失败
     *
     * @param message
     * @param code
     */
    @Override
    public void onError(String message, int code) {
        loadDiss();
        if (code == -2) {
            showNormalToast(message);
            jumpActivity(mContext, LoginActivity.class);
        } else {
            showNormalToast(message);
        }
    }

    @Override
    public void onLigonError() {
        jumpActivity(mContext, LoginActivity.class);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (baseAction != null) {
            baseAction.toRegister();
        }
    }

    @Override
    public void onPause() {
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
        Intent intent = new Intent(UserInfoActivity.this, ImageGridActivity.class);
        intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
        startActivityForResult(intent, REQUEST_CODE_SELECT);
    }

    private void takeUserGally() {
        //打开选择,本次允许选择的数量

        REQUEST_SELECT_TYPE = REQUEST_CODE_ALBUM;
        ImagePicker.getInstance().setSelectLimit(1);
        Intent intent1 = new Intent(UserInfoActivity.this, ImageGridActivity.class);
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
                L.e("xx", REQUEST_SELECT_TYPE + "返回的图片 数量 " + images.size());
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
                                    L.e("lgh", "images.get(0).path  = " + images.get(0).path);
                                    updataFile(images.get(0).path);
                                } catch (Exception e) {
                                    loadError(ResUtil.getString(R.string.main_select_phone_error), mContext);
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
                            L.e("lgh", "images.get(0).path  = " + images.get(0).path);
//                            GlideUtil.setImageCircle(mContext,images.get(0).path,userPortaitIv,R.drawable.icon_placeholder);
                            updataFile(images.get(0).path);
                        } catch (Exception e) {
                            loadError(ResUtil.getString(R.string.main_select_phone_error), mContext);

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

    }

    /**********************************修改头像 end*********************************************/


}
