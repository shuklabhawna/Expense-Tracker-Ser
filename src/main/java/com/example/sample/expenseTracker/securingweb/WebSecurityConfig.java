package com.example.sample.expenseTracker.securingweb;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${app.origin.dev.ip:http://localhost:3000}")
	String localIp;
	
	@Value("${app.origin.prd.ip:http://127.0.0.1:3000}")
	String remoteIp;
	
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
	
	protected void cojnfigure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/settlements").hasRole("ADMIN")
		.antMatchers("/accounts/*","/currencies/*","/expenseDetails/*","/expenseTypes/*","/users/*","/verifyToken**").hasAnyRole("ADMIN","USER")
		.antMatchers("/*","/login","/logout","/").permitAll()
		.and().formLogin();

	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable();
		http.authorizeRequests()
		.antMatchers("/settlements").hasRole("ADMIN")
		.antMatchers("/accounts/**","/currencies/**","/expenseDetails/**","/expenseTypes/**","/users/**").hasAnyRole("ADMIN","USER")
		.antMatchers("/*","/login","/logout","/").permitAll()
		.and().formLogin();

	}
	
	protected void kconfigure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().permitAll();
		http.cors().and().csrf().disable();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(
				"/v2/api-docs",
	            "/swagger-resources",
	            "/swagger-resources/**",
	            "/configuration/ui",
	            "/configuration/security",
	            "/swagger-ui.html",
	            "/webjars/**");
	}
	@Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
	
@Bean
public PasswordEncoder getPasswordEncoder() {return NoOpPasswordEncoder.getInstance();}
	
}
