package com.app.minhaescolaapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf()
			.disable()
			.authorizeRequests()
			.antMatchers(HttpMethod.GET, "/").permitAll()
			.anyRequest().authenticated()
			.and().formLogin().permitAll()
			.loginPage("/login")
			.defaultSuccessUrl("/index")
			.failureUrl("/login?error=true")
			.and().logout().logoutSuccessUrl("/login")
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsServiceImpl)
		.passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Bean 
	public  HttpFirewall allowUrlEncodedSlashHttpFirewall() {
		StrictHttpFirewall fireWall =  new  StrictHttpFirewall ();
	    fireWall.setAllowUrlEncodedSlash( true );    
	    return fireWall;
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.httpFirewall(allowUrlEncodedSlashHttpFirewall());
	}
	
	@Bean
	public HttpFirewall defaultHttpFirewall() {
	    return new StrictHttpFirewall();
	}
	
}
