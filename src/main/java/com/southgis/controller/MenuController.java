package com.southgis.controller;

import com.alibaba.fastjson.JSONObject;
import com.southgis.component.RedisTokenLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.weixin4j.Weixin;
import org.weixin4j.component.MenuComponent;
import org.weixin4j.model.menu.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: WenxiangChen
 * @Description:
 * @Date: Create in 14:51 2019/1/22
 * @Modified By:
 */
@Controller
public class MenuController {

    private Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private RedisTokenLoader redisTokenLoader;

    @RequestMapping(value = "/createMenu")
    @ResponseBody
    public Object createMenu() {
        try {
            List<SingleButton> parentButtonList = new ArrayList<>();
            // "百度查询"一级按钮
            ViewButton viewButton = new ViewButton("百度一下");
            viewButton.setUrl("https://www.baidu.com");
            parentButtonList.add(viewButton);
            // "进度查询"一级按钮
            ViewButton viewButton2 = new ViewButton("进度查询");
            viewButton2.setUrl("https://www.baidu.com");
            parentButtonList.add(viewButton2);
            // "问题咨询"一级按钮，"业务咨询"和"疑问解答"二级按钮
            SingleButton problemButton = new SingleButton("问题咨询");
            List<SingleButton> childrenButton = new ArrayList<>();
            ClickButton clickButton1 = new ClickButton("业务咨询","6007");
            ClickButton clickButton2 = new ClickButton("疑问解答","6008");
            childrenButton.add(clickButton1);
            childrenButton.add(clickButton2);
            problemButton.setSubButton(childrenButton);
            parentButtonList.add(problemButton);
            // 将按钮添加到菜单实例
            Menu menu = new Menu();
            menu.setButton(parentButtonList);

            Weixin weixin = new Weixin();
            System.out.println("access_token:" + weixin.getToken());

            MenuComponent menuComponent = new MenuComponent(weixin);
            menuComponent.delete();// 创建前，要先把之前菜单进行删除
            menuComponent.create(menu);
            return menu;
        }catch (Exception e){
            logger.error("微信公众号菜单创建失败!",e);
        }
        return "";
    }
}
