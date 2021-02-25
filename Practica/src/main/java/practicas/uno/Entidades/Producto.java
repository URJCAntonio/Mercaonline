package practicas.uno.Entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Producto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idProducto;
	
	
	@Column
	private double precio;
	
	@Column
	private String nombre;
	 
	
	public Producto() {
	
	}
	public Producto(double precio, String nombre) {
		
		this.precio = precio;
		this.nombre = nombre;
	}
	public long getId() {
		return idProducto;
	}
	public void setId(long id) {
		this.idProducto = id;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public String toString() {
		return "Producto [idProducto=" + idProducto + ", precio=" + precio + ", nombre=" + nombre + "]";
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
		Producto other = (Producto) obj;
		if (idProducto != other.idProducto)
			return false;
		return true;
	}
	
	
}
