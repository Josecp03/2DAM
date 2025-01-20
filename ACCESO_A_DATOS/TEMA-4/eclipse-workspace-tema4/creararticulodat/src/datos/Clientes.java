package datos;

public class Clientes {

	 private int numcli ;
	 private String nombre;
     private String pobla;
     private int descuento;
     
     
     public Clientes(){}
     

     public Clientes(int numcli, String nombre, String pobla , int descuento) {
         super();
         this.numcli = numcli;
         this.nombre = nombre;
         this.pobla = pobla;
         this.descuento = (int) (Math.random() * 2);
     }
     
	public int getNumcli() {
		return numcli;
	}
	public void setNumcli(int numcli) {
		this.numcli = numcli;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPobla() {
		return pobla;
	}
	public void setPobla(String pobla) {
		this.pobla = pobla;
	}

    public int getDecuento() {
        return descuento;
    }

    public void setDecuento(int decuento) {
        this.descuento = decuento;
    }
	
        
}
