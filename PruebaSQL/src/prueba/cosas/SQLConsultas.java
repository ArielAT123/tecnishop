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
import java.util.function.Consumer;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import prueba.ClasesTablas.Cliente;
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
    
    public static void executeTransaction(Consumer<Connection> operation) {
        Connection conn = null;
        try {
            conn = connect(); // Obtener la conexión
            conn.setAutoCommit(false); // Desactivar autocommit

            operation.accept(conn); // Ejecutar la operación

            conn.commit(); // Confirmar la transacción
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback(); // Revertir la transacción en caso de error
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true); // Restaurar autocommit
                    conn.close(); // Cerrar la conexión
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void loadProductsFromDatabase(DefaultTableModel tableModel) {
        String query = "SELECT codigo, nombre, cantidad, costo_compra, precio_venta_sugerido, precio_venta_recomendado, impuesto, porcentaje_ganancia FROM producto";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(rs.getString("codigo"));
                row.add(rs.getString("nombre"));
                row.add(rs.getInt("cantidad"));
                row.add(rs.getDouble("costo_compra"));
                row.add(rs.getDouble("precio_venta_sugerido"));
                row.add(rs.getDouble("precio_venta_recomendado"));
                row.add(rs.getDouble("impuesto"));
                row.add(rs.getDouble("porcentaje_ganancia"));
                tableModel.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void updateProductInDatabase(String codigo, String nombre, Integer cantidad, Double costo_compra, Double precio_venta_sugerido, Double precio_venta_recomendado, Double impuesto, Double porcentaje_ganancia) {
        String query = "UPDATE producto SET nombre = ?, cantidad = ?, costo_compra = ?, precio_venta_sugerido = ?, precio_venta_recomendado = ?, impuesto = ?, porcentaje_ganancia = ? WHERE codigo = ?";

        executeTransaction(conn -> {
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                // Establecer los parámetros en la consulta
                pstmt.setString(1, nombre);
                pstmt.setInt(2, cantidad);
                pstmt.setDouble(3, costo_compra);
                pstmt.setDouble(4, precio_venta_sugerido);
                pstmt.setDouble(5, precio_venta_recomendado);
                pstmt.setDouble(6, impuesto);
                pstmt.setDouble(7, porcentaje_ganancia);
                pstmt.setString(8, codigo);

                // Ejecutar la actualización
                int rowsAffected = pstmt.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Producto actualizado correctamente. Rows afected: "+rowsAffected);
                } else {
                    System.out.println("No se encontró el producto con código: " + codigo);
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error al ejecutar la consulta", e);
            }
        });
    }
    
   public static Cliente getClienteFromDatabase(int clienteId) {
    String query = "SELECT id, CONCAT(Nombre, ' ', Apellido) AS nombre_completo, telefono, correo, CI FROM cliente WHERE id = ?";
    
    try (Connection conn = connect();
         PreparedStatement stmt = conn.prepareStatement(query)) {
        
        stmt.setInt(1, clienteId);
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            return new Cliente(
                rs.getInt("id"),
                rs.getString("nombre_completo"), // Ya concatenado en la consulta SQL
                rs.getString("telefono"),
                rs.getString("correo"),
                rs.getString("CI")
            );
        }
        
    } catch (Exception e) {
        System.out.println("Error al obtener el cliente: " + e.getMessage());
    }
    
    return null; // Retorna null si no encuentra el cliente o hay un error
}


}
