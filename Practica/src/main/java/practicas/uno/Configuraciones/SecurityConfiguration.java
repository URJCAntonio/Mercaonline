package practicas.uno.Configuraciones;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import practicas.uno.Servicios.RepositoryUserDetailsService;

//import practicas.uno.Componentes.UserRepositoryAuthenticationProvider;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	RepositoryUserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10, new SecureRandom());
	}
	
	@Override
	 protected void configure(HttpSecurity http) throws Exception {

		// Public pages
		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers("/login").permitAll();
		http.authorizeRequests().antMatchers("/failUrl").permitAll();
		http.authorizeRequests().antMatchers("/tienda").permitAll();
		http.authorizeRequests().antMatchers("/producto").permitAll();
		http.authorizeRequests().antMatchers("/carro").permitAll();
		http.authorizeRequests().antMatchers("/registro").permitAll();
		http.authorizeRequests().antMatchers("registro.html").permitAll();
		http.authorizeRequests().antMatchers("/loginerror").permitAll();	//Crear loginError
		http.authorizeRequests().antMatchers("/logout").permitAll();		//Crear LogOut
		
		// Private pages (all other pages)
		http.authorizeRequests().antMatchers("/home").hasAnyRole("USER");
		http.authorizeRequests().antMatchers("/admin").hasAnyRole("ADMIN");

		http.authorizeRequests().anyRequest().authenticated();
		
		// Login form
		 http.formLogin().loginPage("/login");
		 http.formLogin().usernameParameter("nombre");
		 http.formLogin().passwordParameter("password");
		 http.formLogin().defaultSuccessUrl("/");
		 http.formLogin().failureUrl("/failUrl");
	}
	
	@Override
	 protected void configure(AuthenticationManagerBuilder auth) throws Exception {

	 // User
	 auth.inMemoryAuthentication()
	 .withUser("user").password("pass").roles("USER");
	 }

}
