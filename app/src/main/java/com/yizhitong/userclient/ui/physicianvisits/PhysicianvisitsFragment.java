package com.yizhitong.userclient.ui.physicianvisits;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lgh.huanglib.util.L;
import com.lgh.huanglib.util.base.MyFragmentPagerAdapter;
import com.lgh.huanglib.util.cusview.CustomViewPager;
import com.lgh.huanglib.util.data.ResUtil;
import com.qmuiteam.qmui.widget.QMUITabSegment;
import com.yizhitong.userclient.R;
import com.yizhitong.userclient.actions.BaseAction;
import com.yizhitong.userclient.ui.MainActivity;
import com.yizhitong.userclient.utils.base.UserBaseFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhysicianvisitsFragment extends UserBaseFragment {
    View view;
    @BindView(R.id.top_view)
    View topView;
    @BindView(R.id.f_title_tv)
    TextView titleTv;

    @BindView(R.id.tabSegment)
    QMUITabSegment tabSegment;
    @BindView(R.id.viewpager)
    CustomViewPager viewPager;

    List<String> list = new ArrayList<>();
    ArrayList<Fragment> fragments;
    MyFragmentPagerAdapter fragmentPagerAdapter;

    public static int Position = -1;

    @Override
    protected BaseAction initAction() {
        return null;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = getContext();
        mActivity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_physicianvisits, container, false);
        ButterKnife.bind(this, view);
        binding();
        mImmersionBar.setStatusBarView(getActivity(), topView);
        return view;
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        super.onFragmentVisibleChange(isVisible);
        L.e("lgh_myInquiry","isVisible   = "+isVisible);
        if (isVisible){
            L.e("lgh_myInquiry","isVisible11   = "+isVisible);
            ((MainActivity) getActivity()).changeStatusBar(true, R.color.white);
            viewPager.setCurrentItem(Position, false);
            MyInquiryFragment myInquiryFragment = (MyInquiryFragment) fragments.get(Position);
            myInquiryFragment.getAskHead();
        }
//        else {
//            L.e("lgh_myInquiry","isVisible22   = "+isVisible);
//            Position =-1;
//        }

    }

    @Override
    protected void initialize() {
        titleTv.setText(ResUtil.getString(R.string.inquity_tip_title));
        initTabAndPager();
        tabSegment.addOnTabSelectedListener(new QMUITabSegment.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int index) {
                //todo 获取tab位置
                Position = index;
            }

            @Override
            public void onTabUnselected(int index) {

            }

            @Override
            public void onTabReselected(int index) {

            }

            @Override
            public void onDoubleTap(int index) {

            }
        });
    }

    //todo 初始化 tab和pager
    private void initTabAndPager() {
        list = Arrays.asList(getResources().getStringArray(R.array.inquiry_type_list));
        tabSegment.setDefaultNormalColor(ResUtil.getColor(R.color.color_9));
        tabSegment.setDefaultSelectedColor(ResUtil.getColor(R.color.color_38a234));
        tabSegment.setHasIndicator(true);
        tabSegment.setIndicatorPosition(false);
        tabSegment.setIndicatorWidthAdjustContent(true);

        fragments = new ArrayList<Fragment>();

        for (int i = 0; i < list.size(); i++) {
            fragments.add(MyInquiryFragment.newInstance(i));

            tabSegment.addTab(new QMUITabSegment.Tab(list.get(i)));
        }
        fragmentPagerAdapter = new MyFragmentPagerAdapter(
                getChildFragmentManager(), fragments);

        viewPager.setPagingEnabled(false);
        viewPager.setAdapter(fragmentPagerAdapter);
        viewPager.setCurrentItem(Position, false);
        viewPager.setOffscreenPageLimit(list.size());

        tabSegment.setMode(QMUITabSegment.MODE_FIXED);
        tabSegment.setupWithViewPager(viewPager, false);
    }

}
