package practicas.uno.Controladores;



import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
public class Controlador {

	@Autowired
	private RepoProducto repositorioProducto; 
	
	@Autowired
	private RepoCliente repositorioCliente;
	
	@Autowired
	private RepoPedido repositorioPedido;
	
	@PostConstruct
	public void init() {
		Cliente admin = new Cliente("admin","1234", "admin@gmail.com");
		admin.setCarro(new Carro());
		repositorioCliente.save(admin);
		Producto inicial= new Producto(25.50,"Estuche", "Guarda objetos cualquiera en su interior.",
				"https://cdn.discordapp.com/attachments/752885933401047142/816303859562840114/EK717_95Z_AUTH_UC128126_mMid-scaled.png");
		inicial.setStock(new Stock(69));
		repositorioProducto.save(inicial);
		
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
	
	
	/*@GetMapping("/producto/{idProducto}/delete")
	public String deleteProducto(Model m, @PathVariable Long idProducto) {
		repositorioProducto.deleteById(idProducto);
		return "producto_eliminado";
	}*/
	
	@PostMapping("/signin")
	public String registrarCliente(Model m, @RequestParam String nombre, @RequestParam String email, @RequestParam String password) {
		Cliente micliente= new Cliente(nombre,password, email);
		micliente.setCarro(new Carro());
		repositorioCliente.save(micliente);
		m.addAttribute("producto",repositorioProducto.findAll());
		return "tienda";
	}
	
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
		m.addAttribute("micarro",cliente.getCarro().getProductos());
		return "carro";
	}
	
	@GetMapping("/producto/buy")
	public String realizarPedido(Model m) {
		Carro c= repositorioCliente.findById((long)1).get().getCarro();
		Pedido mipedido= new Pedido(c.getNumProductos(),c.getPrecio(), c.getProductos(), c.getCliente());
		List<Producto> misproductos= new ArrayList<>(c.getProductos());
		mipedido.setProductos(misproductos);
		repositorioPedido.save(mipedido);
		repositorioCliente.save(repositorioCliente.findById((long)1).map(target -> {
			target.setId((long)1);
			target.getCarro().reiniciar();
			return target;
		}).get());
		 
		m.addAttribute("mipedido",mipedido);
		return "pedido_realizado";
	}
	
	@GetMapping("/pedidos")
	public String verPedidos(Model m) {
		List<Pedido> mispedidos= repositorioPedido.findByCliente_IdCliente((long)1);
		m.addAttribute("mispedidos", mispedidos);
		
		return "pedidos";
	}
	
}
