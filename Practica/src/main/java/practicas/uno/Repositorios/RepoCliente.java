package practicas.uno.Repositorios;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import practicas.uno.Entidades.Cliente;

public interface RepoCliente extends JpaRepository<Cliente, Long>{

	Optional<Cliente> findByNombre(String nombre);

	
	
}
