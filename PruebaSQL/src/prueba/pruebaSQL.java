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
    
    private static Connection con;
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

    public static int preguntar(Scanner e) {
        System.out.println("Agregar cliente");
        System.out.println("Desea Continuar? si(0)/no(1)");
        System.out.println("Ingrese su respuesta: ");
        return e.nextInt();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        do {
            // Pedir los datos del cliente
            System.out.println("Ingrese Nombre:");
            String nombre = scanner.nextLine();
            System.out.println("Ingrese Apellido:");
            String apellido = scanner.nextLine();
            System.out.println("Ingrese Teléfono:");
            String telefono = scanner.nextLine();
            System.out.println("Ingrese Correo:");
            String correo = scanner.nextLine();
            System.out.println("Ingrese CI:");
            String ci = scanner.nextLine();
            
            // Insertar cliente
            insertCliente(nombre, apellido, telefono, correo, ci);

            // Preguntar si desea continuar
        } while (preguntar(scanner) != 1);

        getLastCliente();

        scanner.close();
    }
}
