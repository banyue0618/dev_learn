package com.banyue.demo;

/**
 * @Description 二进制相关进制数据的测试
 * @Author zhangsip
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2024/10/16
 */
public class BinaryDemo {


    public static void main(String[] args) {


        //        // 0101
        int k = 5;
        System.out.println((k & 1) == 1); // true
        System.out.println((k & 2) == 2); // false
        System.out.println((k & 3) == 3); // false
        System.out.println((k & 4));  // 4
        System.out.println((k & 4) == 4); // true

    }


}
