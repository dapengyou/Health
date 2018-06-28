package com.test.health.im.common.runbable;


import com.test.health.im.client.intel.AimMessageListener;
import com.test.health.im.common.base.AimConfig;
import com.test.health.im.common.intel.ImHandler;

import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class NioSocketRunnable implements Runnable {
    private Selector selector;
    private SocketChannel socketChannel;
    private volatile boolean started;
    private final AimConfig config;
    private final AimMessageListener listener;
    private ImHandler handler;
    private static final String tag="SocketRunnable";

    public NioSocketRunnable(AimConfig config, AimMessageListener listener){
        this.config=config;
        this.handler=config.handler;
        this.listener=listener;
    }
    /**
     * 上一次解码剩下的数据
     */
    private ByteBuffer lastByteBuffer = null;

    /**
     * 新收到的数据
     */
    private ByteBuffer newByteBuffer = null;
    private Socket socket;
    private Integer READ_BUFFER_SIZE=2048;
    @Override
    public void run() {

    }
}
