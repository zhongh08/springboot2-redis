package com.syoki.controller;

import com.syoki.utils.DistributedRedisLock;
import com.syoki.utils.RedissonManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class RedisLockController {

    @RequestMapping("/redder")
    @ResponseBody
    public String redder() throws IOException {
        String key = "test123";

        DistributedRedisLock.acquire(key);

        Long result =  RedissonManager.nextID();

        DistributedRedisLock.release(key);

        return ""+result;
    }

}
