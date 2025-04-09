package ClasesDatos;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ProductoInventario implements Comparable<ProductoInventario> {
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
    private String imagenBase64;
    private Object userData;

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
    public ProductoInventario(String codigo, String nombre, double precioVentaRecomendado, String estado,String imagenBase64){
        this.codigo = codigo;
        this.nombre = nombre;
        this.precioVentaRecomendado = precioVentaRecomendado;
        this.estado = estado;
        this.imagenBase64 = imagenBase64;
        
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getCostoCompra() {
        return costoCompra;
    }

    public void setCostoCompra(double costoCompra) {
        this.costoCompra = costoCompra;
    }

    public double getPrecioVentaSugerido() {
        return precioVentaSugerido;
    }

    public void setPrecioVentaSugerido(double precioVentaSugerido) {
        this.precioVentaSugerido = precioVentaSugerido;
    }

    public double getPrecioVentaRecomendado() {
        return precioVentaRecomendado;
    }

    public void setPrecioVentaRecomendado(double precioVentaRecomendado) {
        this.precioVentaRecomendado = precioVentaRecomendado;
    }

    public double getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(double impuesto) {
        this.impuesto = impuesto;
    }

    public double getPorcentajeGanancia() {
        return porcentajeGanancia;
    }

    public void setPorcentajeGanancia(double porcentajeGanancia) {
        this.porcentajeGanancia = porcentajeGanancia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    

    public void setImagenBase64(String imagenBase64) {
        this.imagenBase64 = imagenBase64;
    }
    public String getImagenBase64() {
        return imagenBase64;
    }
    public Object getUserData() {
        return userData;
    }
    public void setUserData(Object userData) {
        this.userData = userData;
    }
    

    public static String imageToBase64(ImageIcon icon) {
        if (icon == null) return null;
        
        BufferedImage bi = new BufferedImage(
            icon.getIconWidth(),
            icon.getIconHeight(),
            BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.createGraphics();
        icon.paintIcon(null, g, 0, 0);
        g.dispose();
        
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(bi, "png", baos);
            return Base64.getEncoder().encodeToString(baos.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    

    public ImageIcon getImageIcon(int width, int height) {
        if (imagenBase64 == null || imagenBase64.isEmpty()) {
            return null;
        }
        try {
            byte[] imageData = Base64.getDecoder().decode(imagenBase64);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
            BufferedImage originalImage = ImageIO.read(bis);
            Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int compareTo(ProductoInventario o) {
        int comparacion = Integer.compare(this.cantidad, o.cantidad);
        return comparacion == 0 ? 
            Double.compare(this.precioVentaRecomendado, o.precioVentaRecomendado) : comparacion;
    }

    @Override
    public String toString() {
        return "ProductoInventario{" + "codigo=" + codigo + ", nombre=" + nombre + ", cantidad=" + cantidad + ", precioVentaRecomendado=" + precioVentaRecomendado + '}';
    }
    
    
}