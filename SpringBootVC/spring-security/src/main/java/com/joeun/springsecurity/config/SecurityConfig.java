package com.joeun.springsecurity.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.joeun.springsecurity.security.CustomAccessDeniedHandler;
import com.joeun.springsecurity.security.CustomUserDetailsService;
import com.joeun.springsecurity.security.LoginSuccessHandler;

import lombok.extern.slf4j.Slf4j;

/*
 * 스프링 시큐리티 설정 클래스
 */

@Slf4j
@Configuration
@EnableWebSecurity              // 해당 클래스를 스프링 시큐리티 설정 빈으로 등록
@EnableGlobalMethodSecurity(prePostEnabled = true)
/*
 * @EnableGlobalMethodSecurity
 *  - prePostEnabled = true : @preAuthorize, @PostAuthorize 어노테이션 사용 활성화
 *  - securedEnabled = true : @Secured 어노테이션 사용 활성화
 *controller : @preAuthorize  메소드 실행 전에 인가 설정
  controller : @PostAuthorize : 메소드 실행 후 인가 설정
  controller : @Secured : 메소드 실행에 대한 인가(권한) 설정

 */

public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private DataSource dataSource;      //application.properties 에 정의한 데이터 소스를 가져오는 객체

    @Autowired
    private PasswordEncoder passwordEncoder;        // 비밀번호 암호화 객체 
    
    /*
     * 인가 처리
     * 로그인 설정
     * 로그아웃 설정
     * 자동 로그인 설정
     * 예외 처리
     * CSRF 방지 기능 설정
     */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 스프링 시큐리티 설정
        log.info("스프링 시큐리티 설정...");

        // 인증 & 인가
        // ✅ 인증 (authentication)
        // : 등록된 사용자인지 확인하여 입증

        // ✅ 인가 (authorization)
        // : 사용자의 권한을 확인하여 권한에 따라 자원의 사용범위를 구분하여 허락하는 것
        // 인가 처리

        //람다식 방식
        http.authorizeRequests((auth)->auth
            .antMatchers("/").permitAll()
            .antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
            .antMatchers("/admin/**").hasRole("ADMIN")
            .anyRequest().permitAll())      //위에서 람다식으로 정의된 경로 인가 외의 경우에 인증된 사용자만을 허가하도록 합니다.
            ;

        // 빌더 패턴
        // http.authorizeRequests()                   // 인가 설정
        //     //  antMatchers("자원 경로")            - 인가에 대한 URL 경로를 설정
        //     //  permitAll()                        - 모든 사용자 허용
        //     //  hasAnyRole()                       - 여러 권한에 대한 허용 
        //     //  hasRole()                          - 단일 권한에 대한 허용
        //     .antMatchers("/").permitAll()
        //     .antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
        //     .antMatchers("/admin/**").hasRole("ADMIN")
            ;

        // 로그인 설정
        http.formLogin((formLogin)->formLogin
            .defaultSuccessUrl("/")         // 로그인 성공 시, URL : "/"(기본값)
            .usernameParameter("username")
            .passwordParameter("password")
            .loginPage("/login")        //기본 제공되는 로그인 폼 외에 커스터마이징 페이지를 쓸 경로를 지정
            .loginProcessingUrl("/loginPro")        //커스텀 로그인 요청 처리 경로 지정
            .successHandler(authenticationSuccessHandler())//로그인 성공 처리 빈을 지정
            .permitAll()                // 로그인 폼은 모든 사용자에게 허용
            );
            

        // 로그아웃 설정
        http.logout((logout)->logout
            .logoutUrl("/logout") //로그아웃 요청 처리 경로 
            .logoutSuccessUrl("/login")     // 로그아웃 성공 시, URL : "/login?logout" (기본값)
            .permitAll());
        
            

        // http.csrf().disable();

        //자동로그인 설정

        http.rememberMe((rememberMe)->rememberMe
            .key("joeun")
        //DataSource 가 등록된 PersistenRepository : 토큰 정보 객체
            .tokenRepository(tokenRepository())
            .tokenValiditySeconds(60*60*24*7))    //토큰 유효기간)
        ;
        
        


        //인증 예외 처리
        // http.exceptionHandling()
        // .accessDeniedPage("/exception") //접근 거부 시, 이동 경로 지정
        // .accessDeniedHandler(accessDeniedHandler())
        ;
        
        
    }

    /*
     * 인메모리 방식
     * JDBC 방식
     * 사용자 정의 방식(UserDetailsService)
     */

    // 인증 관리 메소드
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // AuthenticationManagerBuilder : 인증 관리 객체
        // auth.inMemoryAuthentication()               // 인증 방식 : 인메모리 방식
        //     // .withUser("아이디").password("비밀번호").roles("권한")
        //     // passwordEncoder.encode("비밀번호")     :   비밀번호 암화화
        //     .withUser("user").password(passwordEncoder.encode("123456")).roles("USER")
        //     .and()
        //     .withUser("admin").password(passwordEncoder.encode("123456")).roles("ADMIN")
        //     ;

        //인증 방식 : JDBC 인증

        // String sql1 = "SELECT user_id AS username, user_pw as password, enabled FROM `user` WHERE user_id = ?";
        // String sql2 = "SELECT user_id AS username, auth FROM `user_auth` WHERE user_id = ?";

        

        //     //데이터 소스
        //     //인증 쿼리 ( 아이디/비밀번호/휴면여부)
        //     //인가 쿼리 ( 아이디/권한)
        //     //비밀번호 암호화 방식
        // auth.jdbcAuthentication()
        // .dataSource(dataSource)
        // //인증쿼리
        // .usersByUsernameQuery(sql1)
        // //인가쿼리 : username 과 auth속성의 ROLE_권한명을 읽어 권한으로 
        // .authoritiesByUsernameQuery(sql2)
        // //비밀번호 암호화 방식
        // .passwordEncoder(passwordEncoder);


        //사용자 정의 인증
        auth.userDetailsService(customUserDetailsService())
        .passwordEncoder(passwordEncoder);

        
        
        
    }
    //인증 성공 처리 클래스 - 빈 등록
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(){

        return new LoginSuccessHandler();

    }

    //PersistentRepository 토큰 정보 객체 - 빈 등록
    @Bean
    public PersistentTokenRepository tokenRepository(){
        //토큰 저장 데이터 베이스를 등록하는 객체입니다.
        JdbcTokenRepositoryImpl repositoryImpl = new JdbcTokenRepositoryImpl();
        repositoryImpl.setDataSource(dataSource);

        return repositoryImpl;

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        
        return super.authenticationManagerBean();
    }


    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new CustomAccessDeniedHandler();
    }


    @Bean
    public UserDetailsService customUserDetailsService(){

        return new CustomUserDetailsService();
    }


    
}
