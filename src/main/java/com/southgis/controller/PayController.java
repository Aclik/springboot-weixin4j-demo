package com.southgis.controller;

import com.southgis.component.RedisTokenLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: WenxiangChen
 * @Description:
 * @Date: Create in 16:25 2019/1/8
 * @Modified By:
 */
@Controller
public class PayController {

    //@Autowired
   // private IWeChatPayService weChatPayService;

    @Autowired
    private RedisTokenLoader redisTokenLoader;

    @RequestMapping(value = "/helloWeChat")
    @ResponseBody
    public Object helloWeChat() {
        return redisTokenLoader.get();
    }
}
