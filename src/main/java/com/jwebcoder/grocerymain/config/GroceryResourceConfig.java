package com.jwebcoder.grocerymain.config;

import com.jwebcoder.grocerymain.common.utils.EncryptionUtility;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration

public class GroceryResourceConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/common/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .and()
                .logout()
                .and()
                .httpBasic();
    }

    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(urlUserService).passwordEncoder(new PasswordEncoder() {//此处为密码使用md5 进行加密

            @Override
            public String encode(CharSequence rawPassword) {
                return EncryptionUtility.encrypt4MD5((String) rawPassword);
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return encodedPassword.equals(EncryptionUtility.encrypt4MD5(((String) rawPassword)));
            }
        });
    }*/
}
