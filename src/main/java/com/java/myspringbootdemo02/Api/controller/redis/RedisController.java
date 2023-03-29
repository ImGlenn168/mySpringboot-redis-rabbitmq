package com.java.myspringbootdemo02.Api.controller.redis;

import com.java.myspringbootdemo02.Api.result.Result;
import org.springframework.web.bind.annotation.RequestMapping;

public interface RedisController {

    @RequestMapping("/redis/setStr")
    Result setStr();

    @RequestMapping("/redis/getStr")
    Result getStr();

    @RequestMapping("/redis/del")
    Result del();

    @RequestMapping("/redis/expire")
    Result expire();

    @RequestMapping("/redis/lock")
    Result lock();

    @RequestMapping("/redis/lockV2")
    Result lockV2();

    @RequestMapping("/redis/unLock")
    Result unLock();
}
