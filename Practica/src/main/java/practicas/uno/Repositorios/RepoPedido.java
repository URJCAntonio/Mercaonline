package practicas.uno.Repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import practicas.uno.Entidades.Pedido;

public interface RepoPedido extends JpaRepository<Pedido, Long>{

	List<Pedido> findByCliente_IdCliente(long idCliente);
}
