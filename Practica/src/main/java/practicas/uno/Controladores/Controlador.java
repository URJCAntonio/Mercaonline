package practicas.uno.Controladores;



import java.security.Principal;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;

import practicas.uno.Entidades.Producto;
import practicas.uno.Entidades.Stock;
import practicas.uno.Repositorios.RepoProducto;



@Controller
public class Controlador {

	@Autowired
	private RepoProducto repositorioProducto; 
	
	
	/*@PostConstruct
	public void init() {
		
		Producto inicial= new Producto(25.50,"Estuche", "Guarda objetos cualquiera en su interior.",
				"https://cdn.discordapp.com/attachments/752885933401047142/816303859562840114/EK717_95Z_AUTH_UC128126_mMid-scaled.png");
		inicial.setStock(new Stock(69));
		repositorioProducto.save(inicial);
		
	}*/
	
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
	

	@RequestMapping("/login")
	public String login() {
		return "logs/login";
	}
	
	@CacheEvict(value={"pedidos","clientes"},allEntries=true)
	@RequestMapping("/logmeout")
	public String logout() {
		return "index";
	}
	
	@RequestMapping("/failUrl")
	public String registrarCliente() {
		return "logs/failUrl";
	}
	@GetMapping("/registro")
	public String pagRegistro() {
		return "logs/registro";
	}
	@GetMapping("/addProducto")
	public String addProducto() {
		return "products/añadir_producto";
	}
	
	@GetMapping("/")
	public String inicio() {
		return "index";
	}
}
