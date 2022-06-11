package com.demo.config;



import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.context.WebApplicationContext;

import com.demo.service.MyUserPrinciple;
import com.demo.service.StudentService;
import com.demo.service.StudentServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//			.authorizeRequests()
//				.antMatchers("/StuApp/home").permitAll()
//				.anyRequest().authenticated()
//				.and()
//			.formLogin()
//		//		.loginPage()
//				.permitAll()
//				.and()
//			.logout()
//				.permitAll();
//		System.out.println("Configure Method");
//	}
//
//	@Bean
//	@Override
//	public UserDetailsService userDetailsService() {
//		@SuppressWarnings("deprecation")
//		UserDetails user =
//			 User.withDefaultPasswordEncoder()
//				.username("cjc")
//				.password("cjc")
//				.roles("USER")
//				.build();
//		System.out.println("UserDetailsService Method");
//
//
//		return new InMemoryUserDetailsManager(user);
	

    @Autowired
    private WebApplicationContext applicationContext;

//    @Autowired
//    private AuthenticationSuccessHandlerImpl successHandler;

    @Autowired
    private DataSource dataSource;

    private UserDetailsService userDetailsService;

    @PostConstruct
    public void completeSetup() {
        userDetailsService = applicationContext.getBean(StudentServiceImpl.class);
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
            .passwordEncoder(encoder())
            .and()
            .authenticationProvider(authenticationProvider())
            .jdbcAuthentication()
            .dataSource(dataSource);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
            .antMatchers("/resources/**");
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/StuApp/login")
            .permitAll()
            .and()
            .formLogin()
            .permitAll()
            //.successHandler(successHandler)
            .and()
            .csrf()
            .disable();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }

//    @Bean
//    public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
//        return new SecurityEvaluationContextExtension();
//    }
	}
	

