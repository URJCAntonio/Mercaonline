package practicas.uno.Entidades;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Carro {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long idCarro;
	
	@Column
	private long numProductos;
	
	@Column
	private double precio;
	
	//@OneToOne(mappedBy="carro")
	//private Cliente cliente;
	
	/*@OneToMany
	private List<Producto> productos;
	*/
	
	
	public Carro(long id, long numProductos, long precio) {

		this.idCarro = id;
		this.numProductos = numProductos;
		this.precio = precio;

	}
	
	public long getId() {
		return idCarro;
	}
	public void setId(long id) {
		this.idCarro = id;
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
	
	@Override
	public String toString() {
		return "Carro [idCarro=" + idCarro + ", numProductos=" + numProductos + ", precio=" + precio + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idCarro ^ (idCarro >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carro other = (Carro) obj;
		if (idCarro != other.idCarro)
			return false;
		return true;
	}
	
	
	
	
}
