package com.banyue.demo;

/**
 * @Description 字符串及相关方法的测试
 * @Author zhangsip
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2024/10/16
 */
public class StrDemo {


    public static void main(String[] args) {

    }


    public static void internTest(){
        String s = new String("1");
        s.intern();
        String s2 = "1";
        System.out.println(s == s2);

        String s3 = new String("1") + new String("1");
        s3.intern();
        String s4 = "11";
        System.out.println(s3 == s4);
    }
}
