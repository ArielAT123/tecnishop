/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba.cosas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import prueba.pruebaSQL;
import static prueba.pruebaSQL.connect;

/**
 *
 * @author Ernesto
 */
public class SQLConsultas extends pruebaSQL{
    public static void cargarDatos(DefaultTableModel modeloTabla) {
        String query = "SELECT id, Nombre, Apellido, correo, CI FROM cliente;";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Object[] fila = new Object[5];
                fila[0] = rs.getInt("id");
                fila[1] = rs.getString("Nombre");
                fila[2] = rs.getString("Apellido");
                fila[3] = rs.getString("correo");
                fila[4] = rs.getString("CI");
                
                modeloTabla.addRow(fila);
            }
        } catch (Exception e) {
            System.out.println("No se pudo conectar a la base");
        }
    }
    public static void loadProductsFromDatabase(DefaultTableModel tableModel) {
    String query = "SELECT codigo, nombre, cantidad, costo_compra, precio_venta_sugerido, precio_venta_recomendado, impuesto, porcentaje_ganancia FROM producto";
    try (Connection conn = connect()) {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            Vector<Object> row = new Vector<>();
            row.add(rs.getString("codigo")); // String
            row.add(rs.getString("nombre")); // String
            row.add(rs.getInt("cantidad")); // Integer
            row.add(rs.getDouble("costo_compra")); // Double
            row.add(rs.getDouble("precio_venta_sugerido")); // Double
            row.add(rs.getDouble("precio_venta_recomendado")); // Double
            row.add(rs.getDouble("impuesto")); // Double
            row.add(rs.getDouble("porcentaje_ganancia")); // Double
            tableModel.addRow(row);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

}
    
    public static void updateProductInDatabase(String codigo, String nombre, int cantidad, double costo_compra, double precio_venta_sugerido, double precio_venta_recomendado, double impuesto, double porcentaje_ganancia) {
    String query = "UPDATE producto SET nombre = ?, cantidad = ?, costo_compra = ?, precio_venta_sugerido = ?, precio_venta_recomendado = ?, impuesto = ?, porcentaje_ganancia = ? WHERE codigo = ?";

    try (Connection conn = connect(); // Asegúrate de que el método connect() devuelva una conexión válida
         PreparedStatement pstmt = conn.prepareStatement(query)) {

        // Establecer los parámetros en la consulta
        pstmt.setString(1, nombre);
        pstmt.setInt(2, cantidad);
        pstmt.setDouble(3, costo_compra);
        pstmt.setDouble(4, precio_venta_sugerido);
        pstmt.setDouble(5, precio_venta_recomendado);
        pstmt.setDouble(6, impuesto);
        pstmt.setDouble(7, porcentaje_ganancia);
        pstmt.setString(8, codigo); // Usar el código como identificador único

        // Ejecutar la actualización
        int rowsAffected = pstmt.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Producto actualizado correctamente.");
        } else {
            System.out.println("No se encontró el producto con código: " + codigo);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}
