package practicas.uno.Entidades;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

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
	private double precioInicial;
	
	@Column
	private int descuento;
	
	@Column
	private double precioFinal;
	
	@ManyToMany(cascade=CascadeType.ALL)
	private List<Producto> productos;
	
	//@Column
	//private String fecha;
	
	public Pedido() {
		
	}

	public Pedido(long numProductos, double precioInicial, List<Producto> productos, Cliente cliente ) {

		this.numProductos = numProductos;
		this.precioInicial=precioInicial;
		this.cliente=cliente;
		this.descuento=0;
		this.precioFinal=precioInicial;
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

	public double getPrecioInicial() {
		return precioInicial;
	}

	public void setPrecioInicial(double precioInicial) {
		this.precioInicial = precioInicial;
	}

	public int getDescuento() {
		return descuento;
	}

	public void setDescuento(int descuento) {
		this.descuento = descuento;
		this.precioFinal = this.precioInicial - this.descuento;
		if(this.precioFinal<0) {
			this.precioFinal=0;
		}
	}

	public double getPrecioFinal() {
		return precioFinal;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	
	@Override
	public String toString() {
		return "Pedido [idPedido=" + idPedido + ", cliente=" + cliente + ", numProductos=" + numProductos + ", precio="
				+ precioInicial + ", productos=" + productos + "]";
	}
	
	
	
}
