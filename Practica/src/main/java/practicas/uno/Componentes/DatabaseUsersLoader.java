package practicas.uno.Componentes;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import practicas.uno.Entidades.Cliente;
import practicas.uno.Repositorios.RepoCliente;

@Component
public class DatabaseUsersLoader {
	
	@Autowired
	private RepoCliente repositorioCliente;
	@PostConstruct
	private void initDatabase() {

	repositorioCliente.save(new Cliente("cliente","pass","user@email.net","CLIENTE"));
	repositorioCliente.save(new Cliente("admin","adminpass","admin@email.net","CLIENTE","ADMIN"));
 }
}