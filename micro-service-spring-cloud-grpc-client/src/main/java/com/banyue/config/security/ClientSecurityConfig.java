package com.banyue.config.security;

import io.grpc.CallCredentials;
import net.devh.boot.grpc.client.inject.StubTransformer;
import net.devh.boot.grpc.client.security.CallCredentialsHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

/*
 * Copyright (c) 2016-2021 Michael Zhang <yidongnan@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 * Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */


/**
 * @author: zhangsp
 * @date: 2023/4/24 14:48
 * @description:
 *  The security configuration for the client. In this case we assume that we use the same passwords for all stubs. If
 * you need per stub credentials you can delete the grpcCredentials and define a {@link StubTransformer} yourself.
 */
@Configuration(proxyBeanMethods = false)
public class ClientSecurityConfig {

    @Value("${auth.username}")
    private String username;

    @Value("${auth.password}")
    private String password;



//    @Bean(name = "defaultAuthCredentials")
//    CallCredentials defaultAuthCredentials() {
//        return CallCredentialsHelper.basicAuth("youke", "123456");
//    }

    @Bean(name = "basicAuthCredentials")
    CallCredentials basicAuthCredentials() {
        return CallCredentialsHelper.basicAuth(this.username, this.password);
    }

}
