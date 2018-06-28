package com.test.health.im.client;


import com.test.health.im.client.intel.AimMessageListener;
import com.test.health.im.common.base.AimConfig;
import com.test.health.im.common.base.ImPacket;
import com.test.health.im.common.packets.ChatBody;
import com.test.health.im.common.packets.Command;
import com.test.health.im.common.runbable.SocketRunnable;
import com.test.health.im.common.tcp.TcpPacket;

public class AimClient {
    private static AimClient instance;
    private AimConfig config;
    private AimMessageListener listener;
    private Thread networkThread;
    private SocketRunnable runnable;
    public static AimClient getInstance() {
        if(instance==null){
            instance=new AimClient();
        }
        return instance;
    }
    private AimClient(){

    }
    public void init(AimConfig config){
        this.config=config;
    }
    public void  setListener(AimMessageListener listener){
        this.listener=listener;
    }
    public void connect(){
        if(config==null){
            new RuntimeException("please init");
        }
        if(networkThread==null || !networkThread.isAlive()){
            runnable=new SocketRunnable(config,listener);
            networkThread=new Thread(runnable);
            networkThread.start();
            //todo 启动心跳
        }
    }
    public boolean sendMsg(ChatBody chat){
        TcpPacket p=new TcpPacket(Command.COMMAND_CHAT_REQ,chat.toByte());
        return send(p);
    }
    public boolean send(ImPacket packet){
        return runnable.send(packet);
    }
    public AimConfig getConfig() {
        return config;
    }
}
