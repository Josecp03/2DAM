package principal;

public class Totales {

    private Integer numero;
    private Long cuenta;
    private Double media;
    private String nombre;

    public Totales(final Integer numero, final Long cuenta, final Double media, final String nombre) {
        this.cuenta = cuenta;
        this.media = media;
        this.nombre = nombre;
        this.numero = numero;
    }

    public Totales() {
    }

    public Long getCuenta() {
        return this.cuenta;
    }

    public void setCuenta(final Long cuenta) {
        this.cuenta = cuenta;
    }

    public Integer getNumero() { // Cambiado de Object a Integer
        return this.numero;
    }

    public void setNumero(final Integer numero) { // Cambiado de Object a Integer
        this.numero = numero;
    }

    public Double getMedia() {
        return this.media;
    }

    public void setMedia(final Double media) {
        this.media = media;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }
}
