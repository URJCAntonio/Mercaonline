package practicas.uno.Entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Stock {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long idProducto;
	
	private long unidades;

	public Stock(long idPedido, long unidades) {
		super();
		this.idProducto = idPedido;
		this.unidades = unidades;
	}

	public long getIdPedido() {
		return idProducto;
	}

	public void setIdPedido(long idPedido) {
		this.idProducto = idPedido;
	}

	public long getUnidades() {
		return unidades;
	}

	public void setUnidades(long unidades) {
		this.unidades = unidades;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idProducto ^ (idProducto >>> 32));
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
		if (idProducto != other.idProducto)
			return false;
		return true;
	}
	
	
	
}
