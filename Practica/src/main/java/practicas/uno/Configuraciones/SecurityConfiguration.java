package practicas.uno.Configuraciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//import practicas.uno.Componentes.UserRepositoryAuthenticationProvider;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	//public UserRepositoryAuthenticationProvider authenticationProvider;
	
	@Override
	 protected void configure(HttpSecurity http) throws Exception {

		// Public pages
		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers("/tienda").permitAll();
		http.authorizeRequests().antMatchers("/login").permitAll();
		http.authorizeRequests().antMatchers("/producto").permitAll();
		http.authorizeRequests().antMatchers("/carro").permitAll();
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
		 http.formLogin().defaultSuccessUrl("/home");
		 http.formLogin().failureUrl("/loginerror");
	}
	
	@Override
	 protected void configure(AuthenticationManagerBuilder auth) throws Exception {

	 // User
	 auth.inMemoryAuthentication()
	 .withUser("user").password("pass").roles("USER");
	 }

}
