package comunicador;

public class Cupon {
	public int codigo;
	public int descuento;
	
	public Cupon(int codigo, int descuento) {
		this.codigo = codigo;
		this.descuento = descuento;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getDescuento() {
		return descuento;
	}

	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}
	
}
