package practicas.uno.Repositorios;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.stereotype.Repository;

import practicas.uno.Entidades.Pedido;

@EnableRedisHttpSession //opcional
@CacheConfig(cacheNames = {"pedidos"})
@Repository
public interface RepoPedido extends JpaRepository<Pedido, Long>{

	@CachePut(value="pedidos")
	List<Pedido> findByCliente_IdCliente(long idCliente); //funciona

	
	
	
}
