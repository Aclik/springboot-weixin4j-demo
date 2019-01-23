package com.southgis.service.impl;

import com.southgis.service.IWeChatPayService;
import org.springframework.stereotype.Service;
import org.weixin4j.Configuration;
import org.weixin4j.Weixin;
import org.weixin4j.WeixinPayConfig;
import org.weixin4j.component.PayComponent;
import org.weixin4j.model.pay.UnifiedOrder;
import org.weixin4j.util.SignUtil;

/**
 * @Author: WenxiangChen
 * @Description:
 * @Date: Create in 16:30 2019/1/8
 * @Modified By:
 */
@Service
public class WeChatServiceImpl implements IWeChatPayService {

    @Override
    public void weChatPay() {
        // 统一下单
        UnifiedOrder unifiedOrder = new UnifiedOrder();
        String appid = Configuration.getOAuthAppId();     // appid
        unifiedOrder.setAppid(appid);
        String mch_id = Configuration.getProperty("weixin4j.payConfig.partnerId");//商户id
        unifiedOrder.setMch_id(mch_id);
        Weixin weixin = new Weixin();
        PayComponent payComponent = weixin.pay();
        try {
            payComponent.payUnifiedOrder(unifiedOrder);
        }catch (Exception e) {

        }

    }
}
