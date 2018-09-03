package com.syoki.utils;

import org.redisson.Redisson;
import org.redisson.api.RAtomicLong;
import org.redisson.config.Config;

public class RedissonManager {

    private static final String RAtomicName = "genId_";

    private static Config config = new Config();
    private static Redisson redisson = null;

    public static void init(String key,String value){
        try {
/*            config.useClusterServers() //这是用的集群server
                    .setScanInterval(2000) //设置集群状态扫描时间
                    .setMasterConnectionPoolSize(10000) //设置连接数
                    .setSlaveConnectionPoolSize(10000)
                    .addNodeAddress("127.0.0.1:6379");*/
            if(key==null || "".equals(key)){
                key=RAtomicName;
            }
            config.useSingleServer().setAddress("http://192.168.1.236:6379").setPassword("123456");
            redisson = (Redisson) Redisson.create(config);
            //清空自增的ID数字
            RAtomicLong atomicLong = redisson.getAtomicLong(key);
            long pValue=1;
            if(value!=null && !"".equals(value)){
                pValue = Long.parseLong(value);
            }
            atomicLong.set(pValue);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    static {
        init("test","1");
    }

    public static Redisson getRedisson(){
        return redisson;
    }

    /** 获取redis中的原子ID */
    public static Long nextID(){
        RAtomicLong atomicLong = getRedisson().getAtomicLong(RAtomicName);
        //原子性的获取下一个ID，递增1
        atomicLong.incrementAndGet();
        return atomicLong.get();
    }

}
