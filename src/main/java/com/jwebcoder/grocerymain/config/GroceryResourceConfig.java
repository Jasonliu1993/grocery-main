package com.jwebcoder.grocerymain.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
public class GroceryResourceConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .antMatcher("/**").authorizeRequests()
                .antMatchers("/common/**")
                .permitAll()
                .anyRequest().authenticated();
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
