package com.southgis.component;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.weixin4j.Weixin;
import org.weixin4j.loader.ITokenLoader;
import org.weixin4j.model.base.Token;

import java.util.concurrent.TimeUnit;

/**
 * @Author: WenxiangChen
 * @Description:
 * @Date: Create in 10:57 2019/1/8
 * @Modified By:
 */
@Component
public class RedisTokenLoader implements ITokenLoader {

    private static Logger logger = LoggerFactory.getLogger(RedisTokenLoader.class);

    private final String ACCESS_TOKEN_KEY = "ats_wx100000000001";// 保存到Redis的key

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Token get() {
        String accessToken = null;
        try {
            accessToken = stringRedisTemplate.opsForValue().get(ACCESS_TOKEN_KEY);
            if(StringUtils.isEmpty(accessToken)) {
                Weixin weixin = new Weixin();
                Token token = weixin.getToken();
                accessToken = JSON.toJSONString(token);
                this.refresh(token);
            }
        }catch (Exception e) {
            logger.error("获取access_token出现异常!",e);
        }
        logger.info("wechat access_token:{}", accessToken);
        return JSON.parseObject(accessToken, Token.class);
    }

    @Override
    public void refresh(Token token) {
        logger.info("refresh wechat access_token:{}", token.toString());
        String accessToken = JSON.toJSONString(token);
        stringRedisTemplate.opsForValue().set(ACCESS_TOKEN_KEY, accessToken, token.getExpires_in() - 600L, TimeUnit.SECONDS);// 将access_token保存到Redis并且,设置过期时间长为(7200-600)秒后过期
    }
}
