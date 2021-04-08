package practicas.uno.Controladores;



import java.security.Principal;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import practicas.uno.Entidades.Carro;
import practicas.uno.Entidades.Cliente;
import practicas.uno.Repositorios.RepoCliente;
import practicas.uno.Repositorios.RepoPedido;
import practicas.uno.Repositorios.RepoProducto;



//@Controller
@RestController
public class ControladorClientes {

	@Autowired
	private RepoProducto repositorioProducto; 
	
	@Autowired
	private RepoCliente repositorioCliente;
	
	@Autowired
	private RepoPedido repositorioPedido;
	
	@ModelAttribute
	public void addAttributes(Model model, HttpServletRequest request) {

		Principal principal = request.getUserPrincipal();

		if (principal != null) {

			model.addAttribute("logged", true);
			model.addAttribute("userName", principal.getName());
			model.addAttribute("admin", request.isUserInRole("ADMIN"));

		} else {
			model.addAttribute("logged", false);
		}
	}
		
	@PostMapping("/registrarse")
	public String registrarCliente(Model m, @RequestBody String nombre, @RequestBody String email, @RequestBody String password) {
		Cliente micliente= new Cliente(nombre,password, email);
		micliente.setCarro(new Carro());
		repositorioCliente.save(micliente);
		m.addAttribute("producto",repositorioProducto.findAll());
		return "tienda";
	}
	
	/*@RequestMapping("/login")
	public String login() {
		return "login";
	}*/
	
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
		if(cliente.getCarro() != null) {
			m.addAttribute("micarro",cliente.getCarro().getProductos());
		}
		return "carro";
	}
}
