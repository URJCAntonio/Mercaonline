package practicas.uno.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import practicas.uno.Entidades.Stock;


public interface RepoStock extends JpaRepository<Stock, Long>{

	
}
