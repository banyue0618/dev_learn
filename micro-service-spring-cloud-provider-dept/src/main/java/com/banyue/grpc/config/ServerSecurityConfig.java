package com.banyue.grpc.config;

import net.devh.boot.grpc.server.security.authentication.BasicGrpcAuthenticationReader;
import net.devh.boot.grpc.server.security.authentication.GrpcAuthenticationReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


// proxyTargetClass is required, if you use annotation driven security!
// However, you will receive a warning that GrpcServerService#bindService() method is final.
// You cannot avoid that warning (without massive amount of work), but it is safe to ignore it.
// The #bindService() method uses a reference to 'this', which will be used to invoke the methods.
// If the method is not final it will delegate to the original instance and thus it will bypass any security layer that
// you intend to add, unless you re-implement the #bindService() method on the outermost layer (which Spring does not).
/**
 * @author: zhangsp
 * @date: 2023/4/24 14:52
 * @description: The security configuration. If you use spring security for web applications most of the stuff is already configured.
 */
@Configuration(proxyBeanMethods = false)
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
public class ServerSecurityConfig {

    private static final Logger log = LoggerFactory.getLogger(ServerSecurityConfig.class);

    @Bean
    AuthenticationManager authenticationManager(final DaoAuthenticationProvider daoAuthenticationProvider) {
        final List<AuthenticationProvider> providers = new ArrayList<>();
        // Possibly JwtAuthenticationProvider
        providers.add(daoAuthenticationProvider);
        return new ProviderManager(providers);
    }

    @Bean
    GrpcAuthenticationReader authenticationReader() {
        return new BasicGrpcAuthenticationReader();
        // final List<GrpcAuthenticationReader> readers = new ArrayList<>();
        // readers.add(new BasicGrpcAuthenticationReader());
        // readers.add(new SSLContextGrpcAuthenticationReader());
        // return new CompositeGrpcAuthenticationReader(readers);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    /**
     * This could be your database lookup. There are some complete implementations in spring-security-web.
     * @param passwordEncoder
     * @return
     */
    @Bean
    UserDetailsService userDetailsService(final PasswordEncoder passwordEncoder) {
        return username -> {
            log.debug("Searching user: {}", username);
            switch (username) {
                case "guest": {
                    return new User(username, passwordEncoder.encode("123456"), Collections.emptyList());
                }
                case "user": {
                    final List<SimpleGrantedAuthority> authorities =
                            Arrays.asList(new SimpleGrantedAuthority("ROLE_GREET"));
                    return new User(username, passwordEncoder.encode("123456"), authorities);
                }
                default: {
                    throw new UsernameNotFoundException("Could not find user!");
                }
            }
        };
    }

    // One of your authentication providers.
    // They ensure that the credentials are valid and populate the user's authorities.
    /**
     *
     * @param userDetailsService
     * @param passwordEncoder
     * @return
     */
    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider(
            final UserDetailsService userDetailsService,
            final PasswordEncoder passwordEncoder) {

        final DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

}
