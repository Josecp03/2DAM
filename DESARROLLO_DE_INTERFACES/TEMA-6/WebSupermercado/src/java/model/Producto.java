package model;

public class Producto {

    private int codigo;
    private String nombre;
    private String descripcion;
    private int cantidad_inventario;
    private float precio;
    private int codigo_categoria;
    private String id_poveedor;
    private String nombreCategoria; // Nuevo campo
    private String nombreProveedor; // Nuevo campo

    public Producto() {
        this.codigo = 0;
        this.nombre = "";
        this.descripcion = "";
        this.cantidad_inventario = 0;
        this.precio = 0;
        this.codigo_categoria = 0;
        this.id_poveedor = "";
        this.nombreCategoria = "";
        this.nombreProveedor = "";
    }

    public Producto(int codigo, String nombre, String descripcion, int cantidad_inventario, float precio, int codigo_categoria, String id_poveedor, String nombreCategoria, String nombreProveedor) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad_inventario = cantidad_inventario;
        this.precio = precio;
        this.codigo_categoria = codigo_categoria;
        this.id_poveedor = id_poveedor;
        this.nombreCategoria = nombreCategoria;
        this.nombreProveedor = nombreProveedor;
    }

    // Getters y Setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad_inventario() {
        return cantidad_inventario;
    }

    public void setCantidad_inventario(int cantidad_inventario) {
        this.cantidad_inventario = cantidad_inventario;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getCodigo_categoria() {
        return codigo_categoria;
    }

    public void setCodigo_categoria(int codigo_categoria) {
        this.codigo_categoria = codigo_categoria;
    }

    public String getId_poveedor() {
        return id_poveedor;
    }

    public void setId_poveedor(String id_poveedor) {
        this.id_poveedor = id_poveedor;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }
}
