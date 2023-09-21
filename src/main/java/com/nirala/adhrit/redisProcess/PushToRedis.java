package com.meta.verse.redisProcess;

import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class PushToRedis {
    public static void main(String[] args) {
        try {
            Config config = new Config();
            config.useSingleServer().setAddress("redis://127.0.0.1:6379");
            RedissonClient redissonClient = Redisson.create(config);

            RBucket<String> bucket = redissonClient.getBucket("simpleObject");
            bucket.set("this is object value");

            RMap<String, String> map = redissonClient.getMap("simpleMap");
            map.put("mapKey", "this is map value");

            String objectvalue = bucket.get();
            System.out.println("stored object value: " + objectvalue);

            String mapValue = map.get("mapKey");
            System.out.println("Stored value: " + mapValue);
            redissonClient.shutdown();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
