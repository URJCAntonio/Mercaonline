package practicas.uno.Entidades;

import java.io.Serializable;
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
public class Stock implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long idStock;
	
	@Column
	private long unidadesPorProducto;
	
	@OneToOne(mappedBy="stock")
	private Producto productos;
	

	public Stock() {
		
	}

	public Stock(long unidades) {
	
		this.unidadesPorProducto = unidades;
	}
	
	public void sacarUnidades(long l) {
		unidadesPorProducto-=l;
	}

	public long getIdStock() {
		return idStock;
	}

	public long getUnidadesPorProducto() {
		return unidadesPorProducto;
	}

	public void setUnidadesPorProducto(long unidadesPorProducto) {
		this.unidadesPorProducto = unidadesPorProducto;
	}


	public Producto getProductos() {
		return productos;
	}

	public void setProductos(Producto productos) {
		this.productos = productos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idStock ^ (idStock >>> 32));
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
		Stock other = (Stock) obj;
		if (idStock != other.idStock)
			return false;
		return true;
	}
	
	
	
}
