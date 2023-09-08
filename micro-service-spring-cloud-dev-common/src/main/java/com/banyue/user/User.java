package com.banyue.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: zhangsp
 * @date: 2023/6/12 10:12
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String userName;

    private int age;

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}
