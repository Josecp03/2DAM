package datos;

public class Articulos {
    private int codarti;
    private String denom; 
    private int stock; 
    private float pvp;
    private double descuento; 
    
    // Constructor vacío
    public Articulos() {}
    
    // Constructor sin descuento
    public Articulos(int codarti, String denom, int stock, float pvp) {
        super();
        this.codarti = codarti;
        this.denom = denom;
        this.stock = stock;
        this.pvp = pvp;
    }
    
    // Constructor con descuento
    public Articulos(int codarti, String denom, int stock, float pvp, double descuento) {
        super();
        this.codarti = codarti;
        this.denom = denom;
        this.stock = stock;
        this.pvp = pvp;
        this.descuento = Math.random() * 101;
    }

    public int getCodarti() {
        return codarti;
    }

    public void setCodarti(int codarti) {
        this.codarti = codarti;
    }

    public String getDenom() {
        return denom;
    }

    public void setDenom(String denom) {
        this.denom = denom;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public float getPvp() {
        return pvp;
    }

    public void setPvp(float pvp) {
        this.pvp = pvp;
    }
    
    // Getter y Setter para 'descuento'
    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }
}
