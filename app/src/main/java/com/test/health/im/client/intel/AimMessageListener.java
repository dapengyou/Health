package com.test.health.im.client.intel;


import com.test.health.im.common.base.ImPacket;

public interface AimMessageListener {
    void onConnected();
    void onRecieve(ImPacket packet);
}
