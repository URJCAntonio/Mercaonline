package practicas.uno.Servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import practicas.uno.Entidades.Cliente;
import practicas.uno.Repositorios.RepoCliente;

@Service
public class RepositoryUserDetailsService implements UserDetailsService {

	@Autowired
	private RepoCliente repositorioCliente;

	@Override
	public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {

		Cliente cliente = repositorioCliente.findByNombre(nombre).orElseThrow(() -> new UsernameNotFoundException("User not found"));
		System.err.println("a");
		List<GrantedAuthority> roles = new ArrayList<>();
		for (String role : cliente.getRoles()) {
			roles.add(new SimpleGrantedAuthority("ROLE_" + role));
			System.err.println(role);
		}

		return new org.springframework.security.core.userdetails.User(cliente.getNombre(), cliente.getPassword(), roles);

	}
}