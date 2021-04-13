package practicas.uno.Controladores;



import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import practicas.uno.Comunicacion.Comunicacion;


@Controller
public class ControladorPedidos {

	@Autowired
	private RepoProducto repositorioProducto; 
	
	@Autowired
	private RepoCliente repositorioCliente;
	
	@Autowired
	private RepoPedido repositorioPedido;
	
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
	
	@PostMapping("/producto/buy")
	public String realizarPedido(Model m, HttpServletRequest request, @RequestParam int cupon) {
		Cliente cliente= repositorioCliente.findByNombre(request.getRemoteUser()).get();
		Carro c= cliente.getCarro();
		Pedido mipedido= new Pedido(c.getNumProductos(),c.getPrecio(), c.getProductos(), c.getCliente());
		List<Producto> misproductos= new ArrayList<>(c.getProductos());
		
		
		
		if(misproductos.isEmpty()) {
			return "pedidos/pedido_vacio";
		}
		boolean suficienteStock = true;
		
		for (Producto producto : misproductos) {
			producto.decrementarStock(1);
			if (producto.getStock().getUnidadesPorProducto()<=0) {
				suficienteStock = false;
			}
		}
		if(!suficienteStock) {
			for (Producto producto : misproductos) {
				producto.decrementarStock(-1);
			}
			return "pedidos/stock_insuficiente";
		}else {
			
			//********************************************************************************************
			//************************************ APLICAR DESCUENTOS ************************************
			//********************************************************************************************
			int descuento=0;
			if(cupon==0) {
				System.err.println("cero");
			}else{
				descuento = Comunicacion.enviar("usar", cupon);
				
				if(descuento==-1) {
					m.addAttribute("descuento", "Se ha producido un error al tramitar su código de descuento");
				}else {
					m.addAttribute("descuento", "Se ha aplicado y consumido su vale descuento por valor de "+descuento+"€ a su compra.");
				}
			}
			//********************************************************************************************
			//************************************ APLICAR DESCUENTOS ************************************
			//********************************************************************************************
			
			mipedido.setProductos(misproductos);
			mipedido.setDescuento(descuento);
			repositorioPedido.save(mipedido);
			cliente.getCarro().reiniciar();
			repositorioCliente.save(cliente);
			m.addAttribute("mipedido",mipedido);
			
			//********************************************************************************************
			//*************************************** GENERAR CUPÓN **************************************
			//********************************************************************************************
			double precioFinal = mipedido.getPrecioFinal();
			int codigo=0;
			int valor=0; 
			if(precioFinal>100) {
				valor = 35;
			}else if(precioFinal>50) {
				valor = 15;
			}else if(precioFinal>20) {
				valor = 5;
			}else if(precioFinal>5) {
				valor = 1;
			}
			
			if(valor<=0) {
				codigo = Comunicacion.enviar("generar", valor);
				m.addAttribute("codigo", "¡Muchas gracias por su compra! Como agradecimiento, le regalamos este vale descuento por valor de "+valor+"€: "+codigo);
			}
			
			//********************************************************************************************
			//*************************************** GENERAR CUPÓN **************************************
			//********************************************************************************************
			return "pedidos/pedido_realizado";
		}
		
	}
	
	@GetMapping("/pedidos")
	public String verPedidos(Model m, HttpServletRequest request) {
		Cliente cliente= repositorioCliente.findByNombre(request.getRemoteUser()).get();
		List<Pedido> mispedidos =repositorioPedido.findByCliente_IdCliente(cliente.getId());
		m.addAttribute("mispedidos", mispedidos);
		
		return "pedidos/pedidos";
	
	}
	
	@GetMapping("/pedido/{idPedido}")
    public String verPedido(Model m, @PathVariable("idPedido") long idPedido) {
        m.addAttribute("mipedido", repositorioPedido.findById(idPedido).get());
        return "pedidos/pedido";
    }
    
}
