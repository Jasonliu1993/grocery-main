package com.jwebcoder.grocerymain.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class GroceryResourceConfig extends ResourceServerConfigurerAdapter {


    @Autowired
    private RedisConnectionFactory connectionFactory;

    @Bean
    public MyRedisTokenStore tokenStore() {
        return new MyRedisTokenStore(connectionFactory);
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //默认配置(所有认证请求都需要授权，要求用户在进入你的应用的任何URL之前都进行验证)
        /*
        http.authorizeRequests().anyRequest().authenticated().and()
                .formLogin().and()
                .httpBasic();
         */
        //配置进入以下URL之前不需要验证
        http.csrf().disable()
                .antMatcher("/**").authorizeRequests()
                .antMatchers("/common/**").permitAll()
                .anyRequest().authenticated();
        //通过 formLogin() 定义当需要用户登录时候，转到的登录页面
        // http.formLogin().loginPage("/login").permitAll().and().authorizeRequests().anyRequest().authenticated();
    }
}
