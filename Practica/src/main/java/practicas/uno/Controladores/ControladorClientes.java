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
public class ControladorClientes {

	@Autowired
	private RepoProducto repositorioProducto; 
	
	@Autowired
	private RepoCliente repositorioCliente;
	
	@Autowired
	private RepoPedido repositorioPedido;
	
	
	
		
	@PostMapping("/signin")
	public String registrarCliente(Model m, @RequestParam String nombre, @RequestParam String email, @RequestParam String password) {
		Cliente micliente= new Cliente(nombre,password, email);
		micliente.setCarro(new Carro());
		repositorioCliente.save(micliente);
		m.addAttribute("producto",repositorioProducto.findAll());
		return "tienda";
	}
	
	@GetMapping("/producto/addtocarro/{miproducto}")
	public String addtoCarro(Model m, @PathVariable Long miproducto) {
		Cliente cliente= repositorioCliente.findById((long)1).get();
		cliente.getCarro().addProducto(repositorioProducto.findById(miproducto).get());
		repositorioCliente.save(cliente);
		m.addAttribute("micarro",cliente.getCarro().getProductos());
		return "carro";
	}
	
	
	@GetMapping("/carro")
	public String goCarro(Model m) {
		Cliente cliente= repositorioCliente.findById((long)1).get();
		m.addAttribute("micarro",cliente.getCarro().getProductos());
		return "carro";
	}
}
