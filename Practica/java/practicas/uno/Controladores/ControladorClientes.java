package practicas.uno.Controladores;



import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import practicas.uno.Entidades.Carro;
import practicas.uno.Entidades.Cliente;
import practicas.uno.Entidades.Producto;
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
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@ModelAttribute
	public void addAttributes(Model model, HttpServletRequest request) {

		Principal principal = request.getUserPrincipal();

		if (principal != null) {
			model.addAttribute("logged", true);
			model.addAttribute("admin", request.isUserInRole("ADMIN"));
			model.addAttribute("name", request.getRemoteUser());

		} else {
			model.addAttribute("logged", false);
		}
	}
		
	@PostMapping("/registrarse")
	public String registrarCliente(Model m, @RequestParam String nombre, @RequestParam String email, @RequestParam String password, HttpServletRequest request) {
		
		Cliente micliente;
		
		if(request.isUserInRole("ADMIN")) {
			micliente = new Cliente(nombre, passwordEncoder.encode(password), email, "USER", "ADMIN");
		}else {
			micliente= new Cliente(nombre, passwordEncoder.encode(password), email, "USER");
		}
		
		micliente.setCarro(new Carro());
		repositorioCliente.save(micliente);
		try {
			request.login(nombre, password);
		} catch (ServletException e) {
			e.printStackTrace();
		}
		return "logs/registrado";
	}
	
	@GetMapping("/producto/addtocarro/{miproducto}")
	public String addtoCarro(Model m, @PathVariable Long miproducto, HttpServletRequest request) {
		//Se almacena el cliente correspondiente a la sesión en la variable cliente
		Cliente cliente= repositorioCliente.findByNombre(request.getRemoteUser()).get();
		// Se añade al carro del cliente el producto
		Producto prod=repositorioProducto.findById(miproducto).get();
		if (prod.getStock().getUnidadesPorProducto()<=0) {
			return "products/prod_no_añadido";
		}
		else {
			cliente.getCarro().addProducto(prod);
			// Se almacena en la base de datos el cliente con el carro actualizado
			repositorioCliente.save(cliente);
			m.addAttribute("micarro",cliente.getCarro().getProductos());
			return "carro";
		}
	}
	
	@RequestMapping("/producto/deleteofcarro/{miproducto}")
	public String deleteofCarro(Model m, @PathVariable ("miproducto") long idProducto, HttpServletRequest request) {
		Cliente cliente= repositorioCliente.findByNombre(request.getRemoteUser()).get();
		Producto miproducto= repositorioProducto.findById(idProducto).get();
		if(cliente.getCarro().findProducto(miproducto)) {
			cliente.getCarro().deleteProducto(miproducto);
			repositorioCliente.save(cliente);
			return "products/prodcarro_eliminado";
		}
		else return "products/producto_no_encontrado_en_carro";
		
	}
	
	@GetMapping("/carro")
	public String goCarro(Model m, HttpServletRequest request) {
		Cliente cliente= repositorioCliente.findByNombre(request.getRemoteUser()).get();
		if(cliente.getCarro() != null) {
			m.addAttribute("micarro",cliente.getCarro().getProductos());
		}
		return "carro";
	}
}
