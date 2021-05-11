package practicas.uno.Controladores;



import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import practicas.uno.Entidades.Producto;
import practicas.uno.Entidades.Stock;
import practicas.uno.Repositorios.RepoProducto;



@Controller
public class ControladorProductos {

	@Autowired
	private RepoProducto repositorioProducto; 
	
	
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
	

	@GetMapping("/tienda")
	public String obtenerProductos(Model m) {
		List<Producto> l2 = repositorioProducto.findAll();
		m.addAttribute("producto",l2);
		return "tienda";
	}

	@GetMapping("/producto{idProducto}")
	public String obtenerProducto(Model m, @PathVariable("idProducto") Long idProducto) {
		Producto miproducto= repositorioProducto.findById(idProducto).get();

		m.addAttribute("miproducto",miproducto);
		m.addAttribute("unidades",miproducto.getStock().getUnidadesPorProducto());
		return "products/producto";
	}
	
	@PostMapping("/producto/add")
	public String addProducto(Model m, @RequestParam String nombre, @RequestParam String descripcion, @RequestParam String url,
			@RequestParam Double precio, @RequestParam Long unidades) {
		Producto miproducto = new Producto(precio,nombre,descripcion,url);
		miproducto.setStock(new Stock(unidades));
		repositorioProducto.save(miproducto);
		return "products/producto_guardado";
	}
	

	/*@GetMapping("/producto/{idProducto}/delete")
	public String deleteProducto(Model m, @PathVariable ("idProducto") Long idProducto) {
		repositorioProducto.deleteById(idProducto);
		return "products/producto_eliminado";
	}*/
}
