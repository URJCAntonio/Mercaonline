package practicas.uno.Controladores;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import practicas.uno.Entidades.Cliente;
import practicas.uno.Repositorios.RepoCliente;

@Controller
public class ControladorCliente {

	@Autowired
	private RepoCliente repositorioCliente;
	
	
	@PostConstruct
	public void init() {
		//repositorioCliente.save(new Cliente("Jaimito", "jaimito@gmail.com"));
	}
	
	/*@GetMapping("/prueba")
	public String getCliente(Model m) {
		List<Cliente> l= repositorioCliente.findAll();
		m.addAttribute("cliente",l);
		return "prueba";
	}*/
	
}
