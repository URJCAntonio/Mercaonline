package practicas.uno.Repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.stereotype.Repository;

import practicas.uno.Entidades.Producto;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

//@EnableRedisHttpSession //opcional
@CacheConfig(cacheNames = "productos")
@Repository
public interface RepoProducto extends JpaRepository<Producto, Long> {
	
	@Cacheable
	List<Producto> findByNombre(String nombre);
	
	@Cacheable
	List<Producto> findByPrecio(double precio);

	@CacheEvict(allEntries = true)
	Producto save(Producto producto);
	
}
