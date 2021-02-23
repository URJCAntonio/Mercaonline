package practicas.uno;

public class Productos {

	private int id;
	private double precio;
	private String nombre;
	private String url= "https://cdn.discordapp.com/attachments/752885933401047142/813481418431791159/latest.png";
	public Productos(int id, double precio, String nombre, String url) {
		super();
		this.id = id;
		this.precio = precio;
		this.nombre = nombre;
		this.url= url;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
		return "Productos [id=" + id + ", precio=" + precio + ", nombre=" + nombre + "]";
	}
	
	
}
