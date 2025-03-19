/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClasesDatos;

/**
 *
 * @author Ernesto
 */
public class ProductoInventario implements Comparable<ProductoInventario>{
    private int id;
    private String codigo;
    private String nombre;
    private int cantidad;
    private double costoCompra;
    private double precioVentaSugerido;
    private double precioVentaRecomendado;
    private double impuesto;
    private double porcentajeGanancia;
    private String estado;

    // Constructor
    public ProductoInventario(int id, String codigo, String nombre, int cantidad, double costoCompra, 
                             double precioVentaSugerido, double precioVentaRecomendado, double impuesto, 
                             double porcentajeGanancia, String estado) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.costoCompra = costoCompra;
        this.precioVentaSugerido = precioVentaSugerido;
        this.precioVentaRecomendado = precioVentaRecomendado;
        this.impuesto = impuesto;
        this.porcentajeGanancia = porcentajeGanancia;
        this.estado = estado;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public double getCostoCompra() { return costoCompra; }
    public void setCostoCompra(double costoCompra) { this.costoCompra = costoCompra; }
    public double getPrecioVentaSugerido() { return precioVentaSugerido; }
    public void setPrecioVentaSugerido(double precioVentaSugerido) { this.precioVentaSugerido = precioVentaSugerido; }
    public double getPrecioVentaRecomendado() { return precioVentaRecomendado; }
    public void setPrecioVentaRecomendado(double precioVentaRecomendado) { this.precioVentaRecomendado = precioVentaRecomendado; }
    public double getImpuesto() { return impuesto; }
    public void setImpuesto(double impuesto) { this.impuesto = impuesto; }
    public double getPorcentajeGanancia() { return porcentajeGanancia; }
    public void setPorcentajeGanancia(double porcentajeGanancia) { this.porcentajeGanancia = porcentajeGanancia; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    @Override
    public String toString() {
        return "ProductoInventario{codigo=" + codigo + ", nombre=" + nombre + ", cantidad=" + cantidad + ", estado=" + estado + "}";
    }

   
    @Override
    public int hashCode() {
        return codigo.hashCode();
    }

    

    @Override
    public int compareTo(ProductoInventario o) {
        int comparacionCantidad = Integer.compare(this.cantidad, o.cantidad);

        // Si las cantidades son iguales, comparar por precioVentaRecomendado
        if (comparacionCantidad == 0) {
            return Double.compare(this.precioVentaRecomendado, o.precioVentaRecomendado);
        }

        return comparacionCantidad;
    }
}
