package practicas.uno.Controladores;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import practicas.uno.Entidades.Carro;
import practicas.uno.Entidades.Cliente;
import practicas.uno.Entidades.Pedido;
import practicas.uno.Entidades.Producto;
import practicas.uno.Entidades.Stock;
import practicas.uno.Repositorios.RepoCliente;
import practicas.uno.Repositorios.RepoPedido;
import practicas.uno.Repositorios.RepoProducto;



@Controller
public class ControladorPedidos {

	@Autowired
	private RepoProducto repositorioProducto; 
	
	@Autowired
	private RepoCliente repositorioCliente;
	
	@Autowired
	private RepoPedido repositorioPedido;
	
	@PostMapping("/producto/buy")
	@ResponseStatus(HttpStatus.CREATED)
	public String realizarPedido(Model m) {
		
		Cliente cliente= repositorioCliente.findById((long)1).get();
		Carro c= cliente.getCarro();
		Pedido mipedido= new Pedido(c.getNumProductos(),c.getPrecio(), c.getProductos(), c.getCliente());
		List<Producto> misproductos= new ArrayList<>(c.getProductos());
		for (Producto producto : misproductos) {
			producto.decrementarStock(1);
		}
		mipedido.setProductos(misproductos);
		repositorioPedido.save(mipedido);
		cliente.getCarro().reiniciar();
		repositorioCliente.save(cliente);
		m.addAttribute("mipedido",mipedido);
		return "pedido_realizado";
	}
	
	@GetMapping("/pedidos")
	public String verPedidos(Model m) {
		List<Pedido> mispedidos= repositorioPedido.findByCliente_IdCliente((long)1);
		m.addAttribute("mispedidos", mispedidos);
		
		return "pedidos";
	}
	
	@GetMapping("/pedido/{idPedido}")
    public ResponseEntity<Pedido> verPedido(@PathVariable long idPedido) {
        //m.addAttribute("mipedido", repositorioPedido.findById(idPedido).get());
        //return "pedido";
		Optional <Pedido> p=repositorioPedido.findById(idPedido);
		if (p.isPresent()) {
			Pedido mipedido= p.get();
			return new ResponseEntity<>(mipedido,HttpStatus.OK);
		}
		else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
}
