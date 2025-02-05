/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba;

import java.sql.*;
import java.util.Scanner;

/**
 *
 * @author Ernesto
 */
public class pruebaSQL {
    
    //private static Connection con;
    private static final String URL = "jdbc:mysql://localhost:3306/tecnishop";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    public static Connection connect() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            System.out.println("Error de conexión: " + e.getMessage());
            return null;
        }
    }

    // Método para conectar a la base de datos
     public static void insertCliente(String nombre, String apellido, String telefono, String correo, String ci) {
        String query = "INSERT INTO cliente (Nombre, Apellido, telefono, correo, CI) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nombre);
            stmt.setString(2, apellido);
            stmt.setString(3, telefono);
            stmt.setString(4, correo);
            stmt.setString(5, ci);
            stmt.executeUpdate();
            System.out.println("Cliente guardado correctamente.");
        } catch (Exception e) {
            System.out.println("Error al guardar cliente: " + e.getMessage());
        }
    }

    public static void getLastCliente() {
        String query = "SELECT * FROM cliente ORDER BY id DESC LIMIT 1";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                System.out.println("Último cliente guardado:");
                System.out.println("Nombre: " + rs.getString("Nombre"));
                System.out.println("Apellido: " + rs.getString("Apellido"));
                System.out.println("Teléfono: " + rs.getString("telefono"));
                System.out.println("Correo: " + rs.getString("correo"));
                System.out.println("CI: " + rs.getString("CI"));
            } else {
                System.out.println("No hay clientes en la base de datos.");
            }
        } catch (Exception e) {
            System.out.println("Error al obtener cliente: " + e.getMessage());
        }
    }
    public static void insertProducto(String nombre, String descripcion, double costoCompra, double porcentajeGanancia, double impuesto, int cantidad) {
    String query = "INSERT INTO producto (nombre, descripcion, costo_compra, porcentaje_ganancia, impuesto, cantidad) VALUES (?, ?, ?, ?, ?, ?)";
    try (Connection conn = connect();
         PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, nombre);
        stmt.setString(2, descripcion);
        stmt.setDouble(3, costoCompra);
        stmt.setDouble(4, porcentajeGanancia);
        stmt.setDouble(5, impuesto);
        stmt.setInt(6, cantidad);
        stmt.executeUpdate();
        System.out.println("Producto guardado correctamente.");
    } catch (Exception e) {
        System.out.println("Error al guardar producto: " + e.getMessage());
    }
}
    public static void getLastProducto() {
    String query = "SELECT * FROM producto ORDER BY id DESC LIMIT 1";
    try (Connection conn = connect();
         PreparedStatement stmt = conn.prepareStatement(query);
         ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
            System.out.println("Último producto guardado:");
            System.out.println("Nombre: " + rs.getString("nombre"));
            System.out.println("Descripción: " + rs.getString("descripcion"));
            System.out.println("Costo de Compra: " + rs.getDouble("costo_compra"));
            System.out.println("Porcentaje de Ganancia: " + rs.getDouble("porcentaje_ganancia"));
            System.out.println("Impuesto: " + rs.getDouble("impuesto"));
            System.out.println("Cantidad: " + rs.getInt("cantidad"));
        } else {
            System.out.println("No hay productos en la base de datos.");
        }
    } catch (Exception e) {
        System.out.println("Error al obtener producto: " + e.getMessage());
    }
}
}
