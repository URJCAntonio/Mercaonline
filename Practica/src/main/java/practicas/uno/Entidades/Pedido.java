package practicas.uno.Entidades;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long idPedido;
	private long idCliente;
	
	@Column
	private long numProductos;
	
	
	public Pedido(long idPedido, long idCliente, long numProductos) {
	
		this.idPedido = idPedido;
		this.idCliente = idCliente;
		this.numProductos = numProductos;

	}
	
	public long getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(long idPedido) {
		this.idPedido = idPedido;
	}
	public long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}
	public long getNumProductos() {
		return numProductos;
	}
	public void setNumProductos(long numProductos) {
		this.numProductos = numProductos;
	}
	

	@Override
	public String toString() {
		return "Pedido [idPedido=" + idPedido + ", idCliente=" + idCliente + ", numProductos=" + numProductos + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idCliente ^ (idCliente >>> 32));
		result = prime * result + (int) (idPedido ^ (idPedido >>> 32));
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
		Pedido other = (Pedido) obj;
		if (idCliente != other.idCliente)
			return false;
		if (idPedido != other.idPedido)
			return false;
		return true;
	}

	
	
	
}
