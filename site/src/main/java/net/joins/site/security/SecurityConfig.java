package net.joins.site.security;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Log
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    SiteUsersService siteUsersService;

    protected void configure(HttpSecurity http) throws Exception {
        log.info("security config...");

        http.authorizeRequests().antMatchers("/guest/**").permitAll(); //모든 사용자 접근 가능
        http.authorizeRequests().antMatchers("/manager/**").hasRole("MANAGER"); //특정 권한자 접근 가능
        http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN"); //특정 권한자 접근 가능

        // http.formLogin(); //시큐리티에서 자동으로 만들어주는 로그인 페이지
        http.formLogin().loginPage("/login"); //커스텀 로그인 페이지

        http.exceptionHandling().accessDeniedPage("/accessDenied");

        //logout
        //http.logout().invalidateHttpSession(true);
        http.logout().logoutUrl("/logout").invalidateHttpSession(true);

        //커스텀 인증방식 사용
        http.userDetailsService(siteUsersService);

    }

}
