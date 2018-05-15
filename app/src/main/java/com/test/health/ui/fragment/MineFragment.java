package com.test.health.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.test.baselibrary.base.BaseFragment;
import com.test.baselibrary.constant.Constant;
import com.test.baselibrary.zxing.app.CaptureActivity;
import com.test.health.R;
import com.test.health.ui.MyQrCodeDialog;
import com.test.health.ui.activity.AdBrowserActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lady_zhou on 2018/3/28.
 */

public class MineFragment extends BaseFragment {
    public static final String TAG = MineFragment.class.getSimpleName();//得到类名
    private static final int REQUEST_QRCODE = 0x01;


    private TextView mTvZxing;
    private TextView mTvMakeErWeiMa;

    /**
     * 使用单例
     *
     * @return
     */
    public static MineFragment newInstance() {
        MineFragment fragment = new MineFragment();
        return fragment;
    }

    @Override
    protected void initData(Bundle arguments, Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mTvZxing = findViewById(R.id.tv_zxing);
        mTvMakeErWeiMa = findViewById(R.id.tv_make_erweima);
    }

    @Override
    protected void initListener() {
        mTvZxing.setOnClickListener(this);
        mTvMakeErWeiMa.setOnClickListener(this);
    }

    @Override
    protected void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.tv_zxing:
                if (hasPermission(Constant.HARDWEAR_CAMERA_PERMISSION)) {
                    doOpenCamera();
                } else {
                    requestPermission(Constant.HARDWEAR_CAMERA_CODE, Constant.HARDWEAR_CAMERA_PERMISSION);
                }
                break;
            case R.id.tv_make_erweima:
                MyQrCodeDialog dialog = new MyQrCodeDialog(mActivity);
                dialog.show();
                break;
        }
    }

    @Override
    public void doOpenCamera() {
        Intent intent = new Intent(mActivity, CaptureActivity.class);
        startActivityForResult(intent, REQUEST_QRCODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_QRCODE:
                if (resultCode == Activity.RESULT_OK) {
                    //获取到扫码后返回的地址
                    String code = data.getStringExtra("SCAN_RESULT");
                    //判断地址是否是http 或https
                    if (code.contains("http") || code.contains("https")) {
                        Intent intent = new Intent(mActivity, AdBrowserActivity.class);
                        intent.putExtra(AdBrowserActivity.KEY_URL, code);
                        startActivity(intent);
                    } else {
                        Toast.makeText(mActivity, code, Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }
}
