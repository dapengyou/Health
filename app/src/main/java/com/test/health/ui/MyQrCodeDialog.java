package com.test.health.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.UserManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.baselibrary.Utils.QRCodeUtil;
import com.test.baselibrary.Utils.Utils;
import com.test.health.R;

import java.util.Random;


/**
 * Created by renzhiqiang on 16/8/19.
 */
public class MyQrCodeDialog extends Dialog {

    private Context mContext;

    /**
     * UI
     */
    private ImageView mQrCodeView;
    private TextView mTickView;
    private TextView mCloseView;

    private String[] names = {"小美女，，，你这么美，这么瘦，，你自己知道么，哈哈哈哈哈哈",
            "山有木兮木有枝，心悦君兮君不知",
            "还扫！没啦"};

    public MyQrCodeDialog(Context context) {
        super(context, 0);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_mycode_layout);
        initView();
    }

    private void initView() {

        mQrCodeView = (ImageView) findViewById(R.id.qrcode_view);
        mCloseView = (TextView) findViewById(R.id.close_view);
        mCloseView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        Random random = new Random();
        String name = names[random.nextInt(names.length)];//需要生成的内容

        mQrCodeView.setImageBitmap(QRCodeUtil.createQRCode(
                Utils.dip2px(mContext, 200),
                Utils.dip2px(mContext, 200),
                name));
    }
}
