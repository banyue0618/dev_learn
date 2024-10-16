package com.banyue.demo;

import org.lionsoul.ip2region.xdb.Searcher;

import java.util.concurrent.TimeUnit;

/**
 * @Description ip地址转换为位置信息
 * @Author zhangsip
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2024/10/16
 */
public class Ip2RegionDemo {

    public static void main(String[] args) throws Exception{
        String dbPath = "F:\\Downloads\\ip2region-master\\data\\ip2region.xdb";

        Searcher searcher = null;
        try {
            searcher = Searcher.newWithFileOnly(dbPath);
        } catch (Exception e) {
            System.out.printf("failed to create searcher with `%s`: %s\n", dbPath, e);
            return;
        }

        // 2、查询
        // 国内
//        String ip = "110.242.68.66";
//        String ip = "1.202.167.255";
//        String ip = "41.192.63.255";
//        String ip = "14.159.255.255";
//        String ip = "223.255.252.0";
        String ip = "223.252.223.255";

        //国外
//        String ip = "3.5.255.255";
//        String ip = "3.161.212.147";
//        String ip = "3.162.9.232";
//        String ip = "110.242.68.66";

        try {
            long sTime = System.nanoTime();
            String region = searcher.search(ip);
            long cost = TimeUnit.NANOSECONDS.toMicros( System.nanoTime() - sTime);
            System.out.printf("{region: %s, ioCount: %d, took: %d μs}\n", region, searcher.getIOCount(), cost);
        } catch (Exception e) {
            System.out.printf("failed to search(%s): %s\n", ip, e);
        }

        // 3、关闭资源
        searcher.close();
        // 备注：并发使用，每个线程需要创建一个独立的 searcher 对象单独使用。

    }


}
