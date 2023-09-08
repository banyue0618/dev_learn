package com.banyue.intercepter;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.banyue.user.User;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.slf4j.MDC;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author: zhangsp
 * @date: 2023/5/21 9:04
 * @description:
 */
public class UserInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取到用户标识
        String userNo = getUserNo(request);
        //把用户 ID 放到 MDC 上下文中
        MDC.put("userId", userNo);

        return super.preHandle(request, response, handler);
    }

    private String getUserNo(HttpServletRequest request) {
        // 通过 SSO 或者Cookie 或者 Auth信息获取到 当前登陆的用户信息
        return "ABC123456789";
    }

    public static void main(String[] args) {
        List<User> userList = new ArrayList<>();
        SpelExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("#root.userName");
        User user = new User();
        user.setUserName("张三");
        user.setAge(15);
        userList.add(user);
        System.out.println(expression.getValue(user));

        userList.add(new User("张三", 16));
        userList.add(new User("李四", 17));
        userList.add(new User("李四", 14));

        userList = userList.stream().collect(Collectors.toMap(User::getUserName, k -> k, (k1, k2)->{
            k1.setAge(k1.getAge()  + k2.getAge());
            return k1;
        })).values().stream().collect(Collectors.toList());

        userList.forEach(System.out::println);

//        String reg = "\\s*(?:\"(?=[^>]*?(?:(?=\\\\)|>|<(?:(?!script\\b)[\\s\\S])|\\bi[^\\s>]+\\b\\s*=|\\bon[a-z]+\\s*=\\s*))[^>]*+\")|(?:'(?=[^>]*?(?:(?=\\\\)|>|<(?:(?!script\\b)[\\s\\S])|\\bi[^\\s>]+\\b\\s*=|\\bon[a-z]+\\s*=\\s*))[^>]*+')/igm;";
//        String reg = "^background-(?:color|image|position|repeat|size):\\s*([^;\\{\\}]+);*$";
//        String reg = "data-background-(style|size|position|image):\\s*[^;\\{\\}]+";
//        String reg = "/data-background(?:-(style|size|position|image))+:\\s*([^;\\{\\}]+(?:\\([^;]*\\))?)[;\\}]";
        String reg = "^(?!//)(?![\\p{L}\\p{N}\\\\\\.\\#@\\$%\\+&amp;;\\-_~,\\?=/!]*(&amp;colon))[\\p{L}\\p{N}\\\\\\.\\#@\\$%\\+&amp;;\\-_~,\\?=/!]*";

//        boolean b = Pattern.matches(reg, "background-repeat:repeat; background-position:center center; background-image:url(http://pic1.win4000.com/wallpaper/d/55138396e9183.jpg);");
        boolean c = Pattern.matches(reg, "https://tse1-mm.cn.bing.net/th/id/OIP-C.kb8uYgKRGCDfjRgcLs1PrgHaEt?w=279&amp;h=180&amp;c=7&amp;r=0&amp;o=5&amp;dpr=1.3&amp;pid=1.7");
//        boolean b = Pattern.matches("^(?=/fbacs/fs/00!tmpfiledata/).*(?<=你好)$", "/fbacs/fs/00!tmpfiledata/0000000000131364/R-C.jpg?_downloadmode=2");

        System.out.println(c);


        HashMap<String,String> map = new HashMap<>();
        map.put("1","10");
        map.put("1","100");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "-" + entry.getValue());
        }

    }
}
