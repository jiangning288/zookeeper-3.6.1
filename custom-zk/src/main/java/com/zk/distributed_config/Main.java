package com.zk.distributed_config;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/*
* 模拟配置中心
* */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        Config config = null;
        try {
            config = new Config("localhost:2181");
        } catch (IOException e) {
            e.printStackTrace();
        }

        config.save("timeout", "1");
        for (int i=0; i<100; i++) {
            System.out.println("====="+config.get("timeout"));
            System.out.println("====="+config.get("grade"));
            TimeUnit.SECONDS.sleep(5);
        }
    }
}
