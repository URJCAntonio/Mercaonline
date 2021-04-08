package practicas.uno.Componentes;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import practicas.uno.Entidades.Cliente;
import practicas.uno.Repositorios.RepoCliente;
/*
@Component
public class UserRepositoryAuthenticationProvider implements AuthenticationProvider{
	@Autowired
	private RepoCliente RepositorioCliente;
	
	 @Override
	 public Authentication authenticate(Authentication auth) throws AuthenticationException {
	 Cliente cliente = RepositorioCliente.findByNombre(auth.getName());
	 if (cliente == null) {
	 throw new BadCredentialsException("User not found");
	 }
	 String password = (String) auth.getCredentials();
	 if (!new BCryptPasswordEncoder().matches(password, cliente.getPassword())) {
	 throw new BadCredentialsException("Wrong password");
	 }

	 List<GrantedAuthority> roles = new ArrayList<>();
	 for (String role : cliente.getRoles()) {
	 roles.add(new SimpleGrantedAuthority(role));
	 }
	 return new UsernamePasswordAuthenticationToken(cliente.getName(), password, roles);
	 }

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return false;
	}

}
*/
