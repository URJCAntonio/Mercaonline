package practicas.uno.Repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import practicas.uno.Entidades.Producto;

public interface RepoProducto extends JpaRepository<Producto, Long> {
	
	List<Producto> findByNombre(String nombre);
	
	List<Producto> findByPrecio(double precio);
	
	
}
