package practicas.uno.Componentes;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import practicas.uno.Entidades.Cliente;
import practicas.uno.Repositorios.RepoCliente;

@Component
public class DatabaseUsersLoader {
	
	@Autowired
	private RepoCliente repositorioCliente;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostConstruct
	private void initDatabase() {
		repositorioCliente.save(new Cliente("user", passwordEncoder.encode("pass"), "user@mail.net", "USER"));
		repositorioCliente.save(new Cliente("admin", passwordEncoder.encode("adminpass"), "admin@mail.net", "USER", "ADMIN"));
	}
	
}