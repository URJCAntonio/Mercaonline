package practicas.uno.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import practicas.uno.Entidades.Cliente;

public interface RepoCliente extends JpaRepository<Cliente, Long>{

	Cliente findByNombre(String nombre);

	
	
}
