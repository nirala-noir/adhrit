package com.meta.verse.redisProcess;

import redis.clients.jedis.Jedis;

public class ListenRedis {
    public static void main(String[] args) {
        try {
            Jedis jedis = new Jedis("redis://127.0.0.1:6379");
            System.out.println("connection successful");
            System.out.println("the server is pinging :"+ jedis.ping());
            //jedis.set("company-name", "500Rockets.io");
            System.out.println(jedis.get("simpleObject"));
            System.out.println(jedis.hgetAll("simpleMap"));

        }catch (Exception e){

        }
    }
}
//cmd  => redis-cli
