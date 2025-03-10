/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SQL_Clases;

import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.function.Consumer;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import prueba.ClasesTablas.Cliente;
import static SQL_Clases.pruebaSQL.connect;

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


    // Nuevo método para filtrar por cualquier columna
    public static void loadProductsFromDatabaseByColumn(DefaultTableModel tableModel, String columnName, String filterValue) {
        String dbColumnName;
        switch (columnName) {
            case "Codigo": dbColumnName = "codigo"; break;
            case "Nombre": dbColumnName = "nombre"; break;
            case "Cantidad": dbColumnName = "cantidad"; break;
            case "Costo de Compra": dbColumnName = "costo_compra"; break;
            case "Precio Sugerido": dbColumnName = "precio_venta_sugerido"; break;
            case "Precio Recomendado": dbColumnName = "precio_venta_recomendado"; break;
            case "IVA": dbColumnName = "impuesto"; break;
            case "% ganancia": dbColumnName = "porcentaje_ganancia"; break;
            default: dbColumnName = "";
        }

        String query = "SELECT id, codigo, nombre, cantidad, costo_compra, precio_venta_sugerido, precio_venta_recomendado, impuesto, porcentaje_ganancia, estado FROM producto";
        if (!filterValue.isEmpty() && !dbColumnName.isEmpty()) {
            query += " WHERE " + dbColumnName + " LIKE '%" + filterValue + "%'";
        }

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(false); // Columna "Seleccionar" inicializada como false
                row.add(rs.getString("codigo"));
                row.add(rs.getString("nombre"));
                row.add(rs.getInt("cantidad"));
                row.add(rs.getDouble("costo_compra"));
                row.add(rs.getDouble("precio_venta_sugerido"));
                row.add(rs.getDouble("precio_venta_recomendado"));
                row.add(rs.getDouble("impuesto"));
                row.add(rs.getDouble("porcentaje_ganancia"));
                row.add(rs.getString("estado"));
                tableModel.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void updateProductInDatabase(Component parentComponent, String codigo, String nombre, Integer cantidad, Double costoCompra, Double precioVentaSugerido, Double precioVentaRecomendado, Double impuesto, Double porcentajeGanancia) {
        String query = "UPDATE producto SET nombre = ?, cantidad = ?, costo_compra = ?, precio_venta_sugerido = ?, precio_venta_recomendado = ?, impuesto = ?, porcentaje_ganancia = ? WHERE codigo = ?";

        try (Connection conn = connect()) {
            conn.setAutoCommit(true); // Usar autocommit para cada actualización
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                System.out.println("Ejecutando actualización para código: " + codigo + " con cantidad: " + cantidad);
                pstmt.setString(1, nombre);
                pstmt.setInt(2, cantidad);
                pstmt.setDouble(3, costoCompra);
                pstmt.setDouble(4, precioVentaSugerido);
                pstmt.setDouble(5, precioVentaRecomendado);
                pstmt.setDouble(6, impuesto);
                pstmt.setDouble(7, porcentajeGanancia);
                pstmt.setString(8, codigo);

                int rowsAffected = pstmt.executeUpdate();
                System.out.println("Consulta ejecutada. Filas afectadas: " + rowsAffected + " para código: " + codigo);

                if (rowsAffected > 0) {
                    System.out.println("Producto actualizado correctamente. Rows affected: " + rowsAffected + " (Código: " + codigo + ")");
                } else {
                    System.out.println("No se encontró el producto con código: " + codigo);
                    JOptionPane.showMessageDialog(parentComponent,
                        "No se encontró el producto con código: " + codigo,
                        "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error SQL al actualizar código " + codigo + ": " + e.getMessage());
            JOptionPane.showMessageDialog(parentComponent, "Error al actualizar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
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
