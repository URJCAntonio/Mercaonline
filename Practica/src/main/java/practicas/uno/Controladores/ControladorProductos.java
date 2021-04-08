package practicas.uno.Controladores;



import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
public class ControladorProductos {

	@Autowired
	private RepoProducto repositorioProducto; 
	
	@Autowired
	private RepoCliente repositorioCliente;
	
	@Autowired
	private RepoPedido repositorioPedido;
	
	@ModelAttribute
	public void addAttributes(Model model) {
	    //model.addAttribute("msg", "Welcome to the Netherlands!");
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
		m.addAttribute("unidades",miproducto.getStock().getUnidadesPorProducto());
		return "producto";
	}
	
	@PostMapping("/producto/add")
	public String addProducto(Model m, @RequestParam String nombre, @RequestParam String descripcion, @RequestParam String url,
			@RequestParam Double precio, @RequestParam Long unidades) {
		Producto miproducto = new Producto(precio,nombre,descripcion,url);
		miproducto.setStock(new Stock(unidades));
		repositorioProducto.save(miproducto);
		return "producto_guardado";
	}
	
	/*
	@GetMapping("/producto/{idProducto}/delete")
	public String deleteProducto(Model m, @PathVariable Long idProducto) {
		repositorioProducto.deleteById(idProducto);
		return "producto_eliminado";
	}
	*/
}
