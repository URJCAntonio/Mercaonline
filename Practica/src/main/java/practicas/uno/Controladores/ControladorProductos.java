package practicas.uno.Controladores;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
	public ResponseEntity<Producto> obtenerProducto(@PathVariable Long idProducto) {
		/* m.addAttribute("miproducto",miproducto);
		m.addAttribute("unidades",miproducto.getStock().getUnidadesPorProducto());
		
		Producto miproducto= repositorioProducto.findById(idProducto).get();
		*/
		//return "producto";
		Optional<Producto> miproducto= repositorioProducto.findById(idProducto);
		if( miproducto.isPresent()) {
			Producto producto= miproducto.get();
			return new ResponseEntity<>(producto,HttpStatus.OK);
		}
		else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@PostMapping("/producto/add")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto addProducto(Model m, @RequestBody String nombre, @RequestBody String descripcion, @RequestBody String url,
			@RequestParam Double precio, @RequestParam Long unidades) {
		
		Producto miproducto = new Producto(precio,nombre,descripcion,url);
		miproducto.setStock(new Stock(unidades));
		repositorioProducto.save(miproducto);
		//return "producto_guardado";	
		return miproducto;
	}
	
	
	@DeleteMapping("/producto/{idProducto}/delete")
	public ResponseEntity<Producto> deleteProducto( @PathVariable Long idProducto) {
		//repositorioProducto.deleteById(idProducto);
		//return "producto_eliminado";
		try {
			repositorioProducto.deleteById(idProducto);
			return new ResponseEntity<> (null,HttpStatus.OK);
			
		}catch (EmptyResultDataAccessException exc) {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
	}
	
}
