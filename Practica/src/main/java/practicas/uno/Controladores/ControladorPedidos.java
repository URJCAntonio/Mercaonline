package practicas.uno.Controladores;



import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping("/producto/buy")
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
    public String verPedido(Model m, @PathVariable long idPedido) {
        m.addAttribute("mipedido", repositorioPedido.findById(idPedido).get());
        return "pedido";
    }
    
}