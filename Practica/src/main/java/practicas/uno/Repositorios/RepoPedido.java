package practicas.uno.Repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import practicas.uno.Entidades.Pedido;


@Repository
public interface RepoPedido extends JpaRepository<Pedido, Long>{

	List<Pedido> findByCliente_IdCliente(long idCliente);
}
