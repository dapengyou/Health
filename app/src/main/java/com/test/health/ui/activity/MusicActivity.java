package com.test.health.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.LoginFilter;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.test.baselibrary.CustomView.LricView;
import com.test.baselibrary.base.BaseActivity;
import com.test.health.MyService;
import com.test.health.R;
import com.test.health.bean.ChatBean;
import com.test.health.bean.DummyContent;
import com.test.health.im.client.AimClient;
import com.test.health.im.client.intel.AimMessageListener;
import com.test.health.im.common.base.AimConfig;
import com.test.health.im.common.base.ImPacket;
import com.test.health.im.common.packets.ChatBody;
import com.test.health.im.common.packets.Command;
import com.test.health.im.common.packets.LoginReqBody;
import com.test.health.im.common.tcp.DefaultHandler;
import com.test.health.im.common.tcp.TcpPacket;
import com.test.health.ui.adapter.ChatAdapter;
import com.test.health.ui.fragment.TopGradualFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 音频歌词同步
 */
public class MusicActivity extends BaseActivity implements TopGradualFragment.OnListFragmentInteractionListener {
    private MediaPlayer mp;
    private Fragment mFragment;
    private boolean isShowWord = false;//是否展示歌词

    @BindView(R.id.framelayout)
    FrameLayout mFrameLayout;

    @BindView(R.id.tv_name)
    TextView mTvName;

    @BindView(R.id.lricView)
    LricView mLricView;

    @BindView(R.id.et_msg)
    EditText mEtMsg;

    @BindView(R.id.bt_send)
    Button mBtSend;

    @BindView(R.id.rl_layout)
    RecyclerView mRecyclerView;

    private ChatAdapter mChatAdapter;

    private List<ChatBean> mChatBeanList = new ArrayList<>();

    //im类初始化
    static AimClient client = AimClient.getInstance();
    AimConfig config = new AimConfig();

    private String mId;
    private String mContent;
    private String mData;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_music;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        //开始播放音乐
        playMusic();
//        mp = MediaPlayer.create(this, R.raw.test);
//        mp.start();


        //设置Fragment
//        mFragment = TopGradualFragment.newInstance(1);
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.rl_layout, mFragment)
//                .commit();

        //注册butterKnife
        ButterKnife.bind(this);

    }

    /**
     * 开始音乐
     */
    public void playMusic() {
        Intent intent = new Intent(this, MyService.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Key", "play");
        intent.putExtras(bundle);
        startService(intent);
    }

    /**
     * 停止music
     */
    public void stopMusic() {
        Intent intent = new Intent(this, MyService.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Key", "stop");
        intent.putExtras(bundle);
//        stopService(intent);
        startService(intent);
    }

    @Override
    protected void initData(Intent intent, Bundle savedInstanceState) {
        initIm();
        initRecycleView();
    }

    private void initRecycleView() {
        mChatAdapter = new ChatAdapter(this, new ArrayList());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mChatAdapter);

        mChatAdapter.setNewData(mChatBeanList);
        mRecyclerView.smoothScrollToPosition(mChatBeanList.size());

    }

    /**
     * 按返回键弹出弹框
     */
    @Override
    public void onBackPressed() {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //处理确认按钮的点击事件
                        stopMusic();
                        finish();
                    }
                }).setMessage("停止音乐？").create();

        dialog.show();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            printJson(msg);
            Log.d("msg", msg.getData().getString("msg"));
            if (!TextUtils.isEmpty(mContent)) {
                mChatBeanList.add(new ChatBean(ChatBean.NORMAL, mContent, "xxx", ""));

                mChatAdapter.setNewData(mChatBeanList);
                mRecyclerView.smoothScrollToPosition(mChatBeanList.size());
            }
        }
    };

    /**
     * {"command":11,"data":{"chatType":1,"content":"还喝酒","createTime":1530087316637,"from":"hello_client","group_id":"100","id":"3794814104274ef18bb997e7beb5e99e","msgType":0,"to":"1526529298615"}}
     * <p>
     * {"code":10000,"command":12,"msg":"发送成功"}
     * <p>
     * 解析的是第一条，是自己发送  会有第二条， 接收别人发的显示第一条json
     *
     * @param msg
     */
    private void printJson(Message msg) {
        JSONObject json = null;
        try {
            json = new JSONObject(msg.getData().getString("msg"));

            Iterator<String> it = json.keys();
            while (it.hasNext()) {
                String myKey = it.next().toString();
                if ("data".equals(myKey)) {
                    mData = json.optString(myKey);

                    JSONObject jsonObject = new JSONObject(mData);
                    Iterator<String> iterator = jsonObject.keys();
                    while (iterator.hasNext()) {
                        String key = iterator.next().toString();
                        if ("content".equals(key)) {
                            mContent = jsonObject.optString(key);
                        }
                        if ("id".equals(key)) {
                            mId = jsonObject.optString(key);
                        }
                    }
                }
                if ("msg".equals(myKey)) {
                    mContent = null;
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    /**
     * 初始化设置im
     */
    private void initIm() {
        config.ip = "192.168.132.233";     //设置连接的ip地址
        config.port = 8888;     //设置接口
        config.handler = new DefaultHandler();
        client.init(config);
        client.setListener(new AimMessageListener() {
            @Override
            public void onConnected() {
                byte[] loginBody = new LoginReqBody("hello_client", "123").toByte();
                TcpPacket loginPacket = new TcpPacket(Command.COMMAND_LOGIN_REQ, loginBody);
                client.send(loginPacket);
            }

            @Override
            public void onRecieve(ImPacket packet) {
                Message msg = new Message();
                Bundle bund = new Bundle();
                try {
                    String str = new String(packet.getBody(), "utf-8");
                    System.out.println(str);
                    bund.putCharSequence("msg", str);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                msg.setData(bund);
                handler.sendMessage(msg);
            }
        });
        client.connect();
    }

    @Override
    protected void initListener() {
    }

    @Override
    protected void onViewClick(View v) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        mp.stop();
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }


    /**
     * 点击歌词后view翻转
     *
     * @param view
     */
    @OnClick({R.id.framelayout, R.id.tv_name, R.id.lricView, R.id.bt_send})
    public void onClicked(View view) {
        switch (view.getId()) {
            case R.id.framelayout:
            case R.id.tv_name:
            case R.id.lricView:
                if (isShowWord) {
                    isShowWord = false;
                    mTvName.setVisibility(View.VISIBLE);
                    mLricView.setVisibility(View.GONE);
                } else {
                    isShowWord = true;
                    mTvName.setVisibility(View.GONE);
                    mLricView.setVisibility(View.VISIBLE);
                }
                break;

            case R.id.bt_send:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String msg = mEtMsg.getText().toString().trim();

                        ChatBody chatBody = new ChatBody()
                                .setFrom("hello_client")
                                .setTo("1526529298615")
                                .setMsgType(0)
                                .setChatType(1)
                                .setGroup_id("100")
                                .setContent(msg);

                        client.sendMsg(chatBody);
                    }
                }).start();
                mEtMsg.setText("");
                break;
        }
    }
}
