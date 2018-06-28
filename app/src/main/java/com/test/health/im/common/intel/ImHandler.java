package com.test.health.im.common.intel;



import com.test.health.im.common.base.ImPacket;
import com.test.health.im.common.exception.AioDecodeException;

import java.nio.ByteBuffer;

/**
 * 数据编码，解码的接口
 * @author Administrator
 * @date 2018/5/17/017
 */
public interface ImHandler {
    ImPacket decode(ByteBuffer buffer) throws AioDecodeException;
    byte[] encode(ImPacket packet);
}
