package th.co.collector.configs;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dataSource;

	@SuppressWarnings("deprecation")
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService);
		auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery("select user_name,password, enabled from user where user_name=?")
		.authoritiesByUsernameQuery("select u.user_name, ul.role from user_role ul inner join user u on ul.user_id = u.user_id where u.user_name=?")
		.passwordEncoder(NoOpPasswordEncoder.getInstance());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
			.antMatchers("/fonts/**").permitAll()
			.antMatchers("/images/**").permitAll()
			.antMatchers("/scripts/**").permitAll()
			.antMatchers("/styles/**").permitAll()
			.antMatchers("/bower_components/**").permitAll()
			.antMatchers("/views/**").permitAll()
			.antMatchers("/webjars/**").permitAll()
			.antMatchers("/syscomponent/**").permitAll()
			.antMatchers("/api/**").authenticated()
			.anyRequest().authenticated()
		
			;
		super.configure(http);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
	}
}
