package practicas.uno;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Controlador {

	
	private List<Productos> productos= new ArrayList<>();

	public Controlador() {
		productos.add(new Productos(1,25.50,"Estuche","https://cdn.discordapp.com/attachments/752885933401047142/813481418431791159/latest.png"));
		productos.add(new Productos(2,2.25,"Tippex", "https://cdn.discordapp.com/attachments/752885933401047142/813481418431791159/latest.png"));
		productos.add(new Productos(3,10.80,"Calculadora", "https://cdn.discordapp.com/attachments/752885933401047142/813481418431791159/latest.png"));
	}
	
	@GetMapping("/")
	public String principal(Model m) {
		m.addAttribute("productos", this.productos);
		
		return "index";
	}
	
}
