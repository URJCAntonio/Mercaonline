package practicas.uno.Controladores;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import practicas.uno.Entidades.Producto;



@Controller
public class ControladorProducto {

	
	private List<Producto> producto= new ArrayList<>();

	public ControladorProducto() {
		producto.add(new Producto(1,25.50,"Estuche"));
		producto.add(new Producto(2,2.25,"Tippex"));
		producto.add(new Producto(3,10.80,"Calculadora"));
	}
	
	@GetMapping("/")
	public String obtenerProductos(Model m) {
		m.addAttribute("producto", this.producto);
		
		return "index";
	}
	
	@GetMapping("/post/{numPost}")
	public String enseñarProducto(Model model, @PathVariable int idProducto) {
		Producto producto= this.producto.get(idProducto-1);
		
		model.addAttribute("post", producto);
		model.addAttribute("numPost", idProducto);
		
		return "enseñar_producto";
	}
	
}
