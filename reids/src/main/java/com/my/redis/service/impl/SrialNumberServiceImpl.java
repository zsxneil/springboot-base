package com.my.redis.service.impl;

import com.my.redis.service.ISerialNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class SrialNumberServiceImpl implements ISerialNumberService {

    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 定义流水号工单默认前缀
     */
    private static final String SERIAL_NUMBER = "item:serial:";

    @Override
    public String genrerate(String bizCode) throws Exception {
        if (!StringUtils.hasText(bizCode)) {
            throw new Exception("业务代码不能为空");
        }

        //获取当前时间,返回格式如yyyyMMdd
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        //构造redis过期时间 UnixMillis
        //设置过期时间为当前日的最后一纳秒
        LocalDateTime lastSecondOfThisDay = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        Date dateTime = Date.from(lastSecondOfThisDay.toInstant(ZoneOffset.of("+8")));

        //构造redis的key
        String key = SERIAL_NUMBER + date +":"+ bizCode;

        //判断key是否存在
        boolean exist = redisTemplate.hasKey(key);

        long incr = redisTemplate.opsForValue().increment(key, 1);
        if (!exist) {
            redisTemplate.expireAt(key, dateTime);
        }
        String formatNumber = String.format("%05d", incr);
        StringBuilder sb = new StringBuilder(20);
        //转换成业务需要的格式  bizCode + date + incr
        sb.append(bizCode).append("-").append(date).append("-").append(formatNumber);

        return sb.toString();
    }



}
