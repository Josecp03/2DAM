package datos;

public class Ventas {

	private int codventa;
	private Articulos codarti;
    private Clientes numcli ;
	private int univen; 
	private String fecha;
	private double precioFinal;
	
	public Ventas(){}
	
	public Ventas(int codventa, Articulos codarti, Clientes numcli, int univen, String fecha, double precioFinal) {
		super();
		this.codventa = codventa;
		this.codarti = codarti;
		this.numcli = numcli;
		this.univen = univen;
		this.fecha = fecha;
		this.precioFinal = precioFinal;
	}
	public int getCodventa() {
		return codventa;
	}
	public void setCodventa(int codventa) {
		this.codventa = codventa;
	}
	public Articulos getCodarti() {
		return codarti;
	}
	public void setCodarti(Articulos codarti) {
		this.codarti = codarti;
	}
	public Clientes getNumcli() {
		return numcli;
	}
	public void setNumcli(Clientes numcli) {
		this.numcli = numcli;
	}
	public int getUniven() {
		return univen;
	}
	public void setUniven(int univen) {
		this.univen = univen;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public double getPrecioFinal() {
		return precioFinal;
	}

	public void setPrecioFinal(double precioFinal) {
		this.precioFinal = precioFinal;
	} 
	
	
	
	

}
