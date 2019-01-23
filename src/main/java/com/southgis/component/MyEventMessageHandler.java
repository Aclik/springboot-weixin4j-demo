package com.southgis.component;

import org.springframework.stereotype.Component;
import org.weixin4j.model.message.OutputMessage;
import org.weixin4j.model.message.event.*;
import org.weixin4j.model.message.output.TextOutputMessage;
import org.weixin4j.spi.IEventMessageHandler;

/**
 * @Author: WenxiangChen
 * @Description:
 * @Date: Create in 11:46 2019/1/8
 * @Modified By:
 */
@Component
public class MyEventMessageHandler implements IEventMessageHandler {

    /**
     * 关注事件
     * @param subscribeEventMessage
     * @return
     */
    @Override
    public OutputMessage subscribe(SubscribeEventMessage subscribeEventMessage) {
        System.out.println("欢迎关注~~");
        return new TextOutputMessage("欢迎关注~~");
    }

    /**
     * 取消关注事件
     *
     * @param unSubscribeEventMessage
     * @return
     */
    @Override
    public OutputMessage unSubscribe(UnSubscribeEventMessage unSubscribeEventMessage) {
        return null;
    }


    @Override
    public OutputMessage qrsceneSubscribe(QrsceneSubscribeEventMessage qrsceneSubscribeEventMessage) {
        return null;
    }

    @Override
    public OutputMessage qrsceneScan(QrsceneScanEventMessage qrsceneScanEventMessage) {
        return null;
    }

    @Override
    public OutputMessage location(LocationEventMessage locationEventMessage) {
        return null;
    }

    @Override
    public OutputMessage click(ClickEventMessage clickEventMessage) {
        return null;
    }

    @Override
    public OutputMessage view(ViewEventMessage viewEventMessage) {
        return null;
    }

    @Override
    public OutputMessage scanCodePush(ScanCodePushEventMessage scanCodePushEventMessage) {
        return null;
    }

    @Override
    public OutputMessage scanCodeWaitMsg(ScanCodeWaitMsgEventMessage scanCodeWaitMsgEventMessage) {
        return null;
    }

    @Override
    public OutputMessage picSysPhoto(PicSysPhotoEventMessage picSysPhotoEventMessage) {
        return null;
    }

    @Override
    public OutputMessage picPhotoOrAlbum(PicPhotoOrAlbumEventMessage picPhotoOrAlbumEventMessage) {
        return null;
    }

    @Override
    public OutputMessage picWeixin(PicWeixinEventMessage picWeixinEventMessage) {
        return null;
    }

    @Override
    public OutputMessage locationSelect(LocationSelectEventMessage locationSelectEventMessage) {
        return null;
    }
}
