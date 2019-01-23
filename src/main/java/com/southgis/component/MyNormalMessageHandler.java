package com.southgis.component;

import org.springframework.stereotype.Component;
import org.weixin4j.model.message.Image;
import org.weixin4j.model.message.OutputMessage;
import org.weixin4j.model.message.normal.*;
import org.weixin4j.model.message.output.ImageOutputMessage;
import org.weixin4j.model.message.output.TextOutputMessage;
import org.weixin4j.spi.INormalMessageHandler;

/**
 * @Author: WenxiangChen
 * @Description:
 * @Date: Create in 11:44 2019/1/8
 * @Modified By:
 */
@Component
public class MyNormalMessageHandler implements INormalMessageHandler {

    /**
     * 接收到文本消息
     *
     * @param textInputMessage
     * @return
     */
    @Override
    public OutputMessage textTypeMsg(TextInputMessage textInputMessage) {
        return new TextOutputMessage("你的消息已收到");
    }

    /**
     * 接收到图片消息
     *
     * @param imageInputMessage
     * @return
     */
    @Override
    public OutputMessage imageTypeMsg(ImageInputMessage imageInputMessage) {
        Image image = new Image();
        image.setMediaId(imageInputMessage.getMediaId());
        return new ImageOutputMessage(image);
    }

    /**
     * 接收到语音消息
     *
     * @param voiceInputMessage
     * @return
     */
    @Override
    public OutputMessage voiceTypeMsg(VoiceInputMessage voiceInputMessage) {
        return null;
    }

    /**
     * 接收到视频消息
     *
     * @param videoInputMessage
     * @return
     */
    @Override
    public OutputMessage videoTypeMsg(VideoInputMessage videoInputMessage) {
        return null;
    }

    /**
     * 接收到小视频消息
     *
     * @param shortVideoInputMessage
     * @return
     */
    @Override
    public OutputMessage shortvideoTypeMsg(ShortVideoInputMessage shortVideoInputMessage) {
        return null;
    }

    /**
     * 接收到地里位置信息
     *
     * @param locationInputMessage
     * @return
     */
    @Override
    public OutputMessage locationTypeMsg(LocationInputMessage locationInputMessage) {
        return null;
    }

    /**
     * 接收到链接信息
     *
     * @param linkInputMessage
     * @return
     */
    @Override
    public OutputMessage linkTypeMsg(LinkInputMessage linkInputMessage) {
        return null;
    }
}
