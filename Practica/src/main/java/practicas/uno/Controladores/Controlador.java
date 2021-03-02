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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import practicas.uno.Entidades.Cliente;
import practicas.uno.Entidades.Producto;
import practicas.uno.Repositorios.RepoCliente;
import practicas.uno.Repositorios.RepoProducto;



@Controller
//@RestController
//@RequestMapping("/")
public class Controlador {

	@Autowired
	private RepoProducto repositorioProducto; 
	
	@Autowired
	private RepoCliente repositorioCliente;
	
	@PostConstruct
	public void init() {
		repositorioCliente.save(new Cliente("admin","1234", "admin@gmail.com"));

		repositorioProducto.save(new Producto(25.50,"Estuche", "Guarda objetos cualquiera en su interior.",
				"https://cdn.discordapp.com/attachments/752885933401047142/816303859562840114/EK717_95Z_AUTH_UC128126_mMid-scaled.png"));
	}
	
	
	@GetMapping("/tienda")
	public String obtenerProductos(Model m) {
		List<Producto> l2 = repositorioProducto.findAll();
		m.addAttribute("producto",l2);
		return "tienda";
	}
	
	
	@GetMapping("/producto{idProducto}")
	public String obtenerProducto(Model m, @PathVariable Long idProducto) {
		Producto miproducto= repositorioProducto.findById(idProducto).get();
		
		m.addAttribute("miproducto",miproducto);
		return "producto";
	}
	
	@PostMapping("/producto/add")
	public String addProducto(Model m, @RequestParam String nombre, @RequestParam String descripcion, @RequestParam String url,@RequestParam Double precio) {
		repositorioProducto.save(new Producto(precio,nombre,descripcion,url));
		System.out.println("Producto guardado");
		return "producto_guardado";
	}
	
	
	@GetMapping("/producto/{idProducto}/delete")
	public String deleteProducto(Model m, @PathVariable Long idProducto) {
		repositorioProducto.deleteById(idProducto);
		return "producto_eliminado";
	}
	
	@PostMapping("/signin")
	public String registrarCliente(Model m, @RequestParam String nombre, @RequestParam String email, @RequestParam String password) {
		repositorioCliente.save(new Cliente(nombre,password, email));
		m.addAttribute("producto",repositorioProducto.findAll());
		return "tienda";
	}
	
	
}
