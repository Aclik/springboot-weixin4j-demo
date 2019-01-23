package com.southgis.component;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.weixin4j.loader.ITicketLoader;
import org.weixin4j.model.js.Ticket;
import org.weixin4j.model.js.TicketType;

import java.util.concurrent.TimeUnit;

/**
 * @Author: WenxiangChen
 * @Description:
 * @Date: Create in 11:37 2019/1/8
 * @Modified By:
 */
@Component
public class RedisTicketLoader implements ITicketLoader {

    private Logger logger = LoggerFactory.getLogger(RedisTicketLoader.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private String appid = "";

    @Override
    public Ticket get(TicketType ticketType) {
        String key = "";
        if (null != ticketType) {
            switch (ticketType) {
                case JSAPI:
                    key = "wechat_ticket_jsapi";
                    break;
                case WX_CARD:
                    key = "wechat_ticket_wxcard";
                    break;
                default:
                    key = "wechat_ticket";
                    break;
            }
        }
        String ticket = stringRedisTemplate.opsForValue().get(key);
        logger.info("wechat ticket:{}", ticket);
        return JSON.parseObject(ticket, Ticket.class);
    }

    @Override
    public void refresh(Ticket ticket) {
        String key = "";
        if (null != ticket.getTicketType()) {
            switch (ticket.getTicketType()) {
                case JSAPI:
                    key = "wechat_ticket_jsapi_" + appid;
                    break;
                case WX_CARD:
                    key = "wechat_ticket_wxcard_" + appid;
                    break;
                default:
                    key = "wechat_ticket_" + appid;
                    break;
            }
        }
        logger.info("refresh wechat ticket:{}", ticket.toString());
        String ticketValue = JSON.toJSONString(ticket);
        //ticket.getExpires_in() - 600L，是为了提前10分钟过期
        stringRedisTemplate.opsForValue().set(key, ticketValue, ticket.getExpires_in() - 600L, TimeUnit.SECONDS);

    }
}
