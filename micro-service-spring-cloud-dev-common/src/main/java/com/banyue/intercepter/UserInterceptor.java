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
            k1.setAge(Math.max(k1.getAge(), k2.getAge()));
            return k1;
        })).values().stream().collect(Collectors.toList());

        userList.forEach(System.out::println);

    }
}
