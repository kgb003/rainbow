package com.rainbow.conf;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
	ApplicationContext context;
	
	@Override
	public void configure(WebSecurity web) throws Exception
	{
		// 메인페이지 : css나 js 같은것들도 여기에 포함시켜준다.
		// web.ignoring().antMatchers("/**");
	}
 
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http
			// 전부 허용
			.authorizeRequests()
				.antMatchers("/**")
				.permitAll();
//		.and()
//			.logout()
//				.logoutUrl("/sign-out")
//				.logoutSuccessUrl("/")
//				.invalidateHttpSession(true)
//		.and()
//			.formLogin()
//				.loginPage("/sign-in")
//				.loginProcessingUrl("/sign-in/auth")
//				.failureUrl("/sign-in?error=exception")
//				.defaultSuccessUrl("/")
//		
	}
}