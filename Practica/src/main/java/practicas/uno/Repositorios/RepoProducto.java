package practicas.uno.Repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.stereotype.Repository;

import practicas.uno.Entidades.Producto;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

@EnableRedisHttpSession 
@CacheConfig(cacheNames = {"productos"})
@Repository
public interface RepoProducto extends JpaRepository<Producto, Long> {
	

	List<Producto> findByNombre(String nombre);
	
	List<Producto> findByPrecio(double precio);
	
	@CachePut(value="productos")
	List<Producto> findAll(); 

}
