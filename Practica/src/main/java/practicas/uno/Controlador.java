package practicas.uno;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class Controlador {

	
	private List<Productos> productos= new ArrayList<>();

	public Controlador() {
		productos.add(new Productos(1,25.50,"Estuche"));
		productos.add(new Productos(2,2.25,"Tippex"));
		productos.add(new Productos(3,10.80,"Calculadora"));
	}
	
	@GetMapping("/")
	public String obtenerProductos(Model m) {
		m.addAttribute("productos", this.productos);
		
		return "index";
	}
	@GetMapping("/post/{numPost}")
	public String enseñarProducto(Model model, @PathVariable int idProducto) {
		Productos producto= productos.get(idProducto-1);
		
		model.addAttribute("post", producto);
		model.addAttribute("numPost", idProducto);
		
		return "enseñar_producto";
	}
	
}
