package practicas.uno.Controladores;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import practicas.uno.Entidades.Cliente;
import practicas.uno.Entidades.Producto;
import practicas.uno.Repositorios.RepoCliente;
import practicas.uno.Repositorios.RepoProducto;



@Controller
//@RestController
//@RequestMapping("/")
public class ControladorProducto {

	@Autowired
	private RepoProducto repositorioProducto; 
	
	@Autowired
	private RepoCliente repositorioCliente;
	
	@PostConstruct
	public void init() {
		repositorioCliente.save(new Cliente("Jaimito", "jaimito@gmail.com"));
		repositorioCliente.save(new Cliente("anto", "anto@gmail.com"));
		repositorioProducto.save(new Producto(25.50,"Estuche"));
	}
	
	
	@GetMapping("/prueba")
	public String obtenerProductos(Model m) {
		List<Cliente> l1= repositorioCliente.findAll();
		m.addAttribute("cliente",l1);
		List<Producto> l2 = repositorioProducto.findAll();
		m.addAttribute("producto",l2);
		return "prueba";
	}
	
	/*private List<Producto> producto= new ArrayList<>();
	
	

	public ControladorProducto() {
		producto.add(new Producto(25.50,"Estuche"));
		producto.add(new Producto(2.25,"Tippex"));
		producto.add(new Producto(10.80,"Calculadora"));
		//repositorioProducto.save(new Producto(25.50,"Estuche"));
		
	}
	
	@GetMapping("/")
	public String obtenerProductos(Model m) {
		m.addAttribute("producto", this.repository);
		
		return "index";
	}
	
	@GetMapping("/post/{numPost}")
	public String enseñarProducto(Model model, @PathVariable int idProducto) {
		Producto producto= this.producto.get(idProducto-1);
		
		model.addAttribute("post", producto);
		model.addAttribute("numPost", idProducto);
		
		return "enseñar_producto";
	}*/
	
}
