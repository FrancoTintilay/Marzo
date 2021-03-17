package ar.edu.unju.edm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import ar.edu.unju.edm.service.LoginUsuarioServiceImp;
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private AuthenticationSuccessHandler autenticacion;
	String[] resources = new String[] {
			"/static/**","/css/**","/icons/**","/img/**","/js/**","/layer/**"};
			@Override
			protected void configure(HttpSecurity http) throws Exception {
				http
					.authorizeRequests()
						.antMatchers(resources).permitAll()
						.antMatchers("/index").permitAll()
						.anyRequest().authenticated()
						.and()
					.formLogin()
						.loginPage("/index")
						.permitAll()
						.successHandler(autenticacion)
						.failureUrl("/login?error=true")
						.usernameParameter("nombreusuario")
						.passwordParameter("password")
						.and()
					.logout()
						.permitAll()
						.logoutSuccessUrl("/login");
			}
			
			BCryptPasswordEncoder bCryptPasswordEncoder;
			
			@Bean
			public BCryptPasswordEncoder passwordEncoder() {
				bCryptPasswordEncoder =  new BCryptPasswordEncoder(4);
				return bCryptPasswordEncoder;
			}
			
			@Autowired
			LoginUsuarioServiceImp userDetailsService;
			
			@Autowired
			public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
				auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
			}
		}


