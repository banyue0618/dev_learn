package com.banyue.demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description 正则表达式相关的测试
 * @Author zhangsip
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2024/10/16
 */
public class RegexDemo {

    public static void main(String[] args) {

        String numRegex = "^\\d+(,\\d+)*$";

        System.out.println(Pattern.matches(numRegex, "12,30") & Pattern.matches(numRegex, "0234"));


        String pattern = "\\$\\{([a-zA-Z#,-]+)}";  // 匹配以 "${" 开头，以 "}" 结尾的模式
        String string = "${datefrom,dwdw}";  // 在这里替换为你要判断的字符串
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(string);

        if (matcher.matches()) {
            String group = matcher.group();
            System.out.println(group);
            System.out.println(matcher.groupCount());
            System.out.println("匹配成功");
        } else {
            System.out.println("匹配失败");
        }

    }


}
