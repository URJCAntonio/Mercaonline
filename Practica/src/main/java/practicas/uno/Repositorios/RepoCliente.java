package practicas.uno.Repositorios;

import java.util.Optional;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import practicas.uno.Entidades.Cliente;

@CacheConfig(cacheNames = {"clientes"})
@Repository
public interface RepoCliente extends JpaRepository<Cliente, Long>{

	@CachePut(value="clientes")
	Optional<Cliente> findByNombre(String nombre);

	
}
