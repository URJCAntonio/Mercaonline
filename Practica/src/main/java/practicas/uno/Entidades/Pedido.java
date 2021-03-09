package practicas.uno.Entidades;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long idPedido;
	
	@ManyToOne
	private Cliente cliente;
	
	@Column
	private long numProductos;
	
	@Column
	private double precio;
	
	@OneToMany
	private List<Producto> productos;
	
	//@Column
	//private String fecha;
	
	public Pedido() {
		
	}

	public Pedido(long numProductos, double precio, List<Producto> productos ) {

		this.numProductos = numProductos;
		this.precio=precio;
		this.productos=productos;
	}
	
	public long getIdPedido() {
		return idPedido;
	}
	

	public long getNumProductos() {
		return numProductos;
	}
	public void setNumProductos(long numProductos) {
		this.numProductos = numProductos;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	

	
	
	
}