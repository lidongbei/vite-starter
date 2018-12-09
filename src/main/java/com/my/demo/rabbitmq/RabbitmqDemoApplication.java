package com.my.demo.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

@SpringBootApplication
public class RabbitmqDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqDemoApplication.class, args);
//        System.gc();
        getMemoryInfo();

        byte[] b = new byte[1 * 1024 * 1024];
        System.out.println("分配了1M空间给数组");

        getMemoryInfo();
        System.gc();
        getMemoryInfo();
    }


    public static void getMemoryInfo(){
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage memoryUsage = memoryMXBean.getHeapMemoryUsage(); //椎内存使用情况
        long totalMemorySize = memoryUsage.getInit(); //初始的总内存
        long maxMemorySize = memoryUsage.getMax(); //最大可用内存
        long usedMemorySize = memoryUsage.getUsed(); //已使用的内存
        System.out.println("初始的总内存：" +totalMemorySize/(1024*1024)+"M");
        System.out.println("最大可用内存：" +maxMemorySize/(1024*1024)+"M");
        System.out.println("当前可用内存：" +(totalMemorySize-usedMemorySize)/(1024*1024)+"M");
        System.out.println("已使用的内存：" +usedMemorySize/(1024*1024)+"M");

//        System.out.println("Xmx(系统的最大空间)=" + Runtime.getRuntime().maxMemory() / 1024.0 / 1024 + "M");   //系统的最大空间
//
//        System.out.println("free mem(系统的空闲空间)=" + Runtime.getRuntime().freeMemory() / 1024.0 / 1024 + "M");   //系统的空闲空间
//
//        System.out.println("total mem(当前可用的总空间)=" + Runtime.getRuntime().totalMemory() / 1024.0 / 1024 + "M");
    }
}
