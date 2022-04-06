package com.sandip.config;

import com.sandip.handler.SimpleAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private SimpleAuthenticationSuccessHandler successHandler;

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/").permitAll()
				.antMatchers("/welcome").hasAnyRole("USER", "ADMIN")
				.antMatchers("/getUserStatements").hasAnyRole("USER")
				.antMatchers("/getAdminStatements").hasAnyRole("ADMIN")
				.anyRequest().authenticated().and().formLogin().successHandler(successHandler)
				.loginPage("/login").permitAll().and().logout().permitAll()
				.and()
				.sessionManagement()
				//.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.maximumSessions(1);
				//.maxSessionsPreventsLogin(true);

		http.csrf().disable();
	}

	@Autowired
	 public void configureGlobal(AuthenticationManagerBuilder authenticationMgr)throws Exception {
	 authenticationMgr.inMemoryAuthentication()
			 .passwordEncoder(NoOpPasswordEncoder.getInstance())
			 .withUser("user").password("user").authorities("ROLE_USER").and()
			 .withUser("admin").password("admin").authorities("ROLE_USER","ROLE_ADMIN");
	 }

}