package com.team.project.member;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // post 방식으로 값을 전송할 때 token을 사용해야하는 보안 설정을 해제
                .authorizeRequests()
                .antMatchers("/css/**", "/js/**", "/*.ico", "/error", "/",
                        "/api/**").permitAll()
                .anyRequest().authenticated()
                .and() // 로그아웃 설정
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // 로그아웃 시 URL 재정의
                .logoutSuccessUrl("/") // 로그아웃 성공 시 redirect 이동
                .invalidateHttpSession(true) // HTTP Session 초기화
                .deleteCookies("JSESSIONID"); // 특정 쿠키 제거
    }
}
