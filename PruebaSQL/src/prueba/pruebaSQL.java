/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba;

import java.sql.*;
import prueba.ClasesTablas.ProblemaEquipo;
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
    public static void insertProducto(String nombre, String descripcion, Double costoCompra, Double porcentajeGanancia, Double impuesto, Integer cantidad, String codigo) {
    String query = "INSERT INTO producto (nombre, descripcion, costo_compra, porcentaje_ganancia, impuesto, cantidad, codigo) VALUES (?, ?, ?, ?, ?, ?, ?)";
    try (Connection conn = connect();
         PreparedStatement stmt = conn.prepareStatement(query)) {
        
        stmt.setString(1, nombre);
        stmt.setString(2, descripcion);
        
        // Verificamos si es null para usar setNull()
        if (costoCompra != null) {
            stmt.setDouble(3, costoCompra);
        } else {
            stmt.setNull(3, java.sql.Types.DOUBLE);
        }
        
        if (porcentajeGanancia != null) {
            stmt.setDouble(4, porcentajeGanancia);
        } else {
            stmt.setNull(4, java.sql.Types.DOUBLE);
        }
        
        if (impuesto != null) {
            stmt.setDouble(5, impuesto);
        } else {
            stmt.setNull(5, java.sql.Types.DOUBLE);
        }
        
        if (cantidad != null) {
            stmt.setInt(6, cantidad);
        } else {
            stmt.setNull(6, java.sql.Types.INTEGER);
        }
        stmt.setString(7, codigo);
        
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
    
    public static int getIdClienteXCedula(String ci) {
    int clienteId=0;
    String query = "SELECT id FROM cliente WHERE CI = ? LIMIT 1";

    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
         PreparedStatement ps = connection.prepareStatement(query)) {

        ps.setString(1, ci);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            clienteId = rs.getInt("id");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return clienteId;
}
    public static boolean idClienteExiste(String ci){
        return getIdClienteXCedula(ci)!=0;
    }
    
    
    public static String insertOrden(int id_equipo, Date fecha) {
        String query = "INSERT INTO orden (equipo_id, fecha) VALUES (?, ?)";
        String generatedId = null; // Valor por defecto si no se genera un ID

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Insertar la orden sin especificar el ID (el trigger lo generará)
            stmt.setInt(1, id_equipo);
            stmt.setDate(2, fecha);
            stmt.executeUpdate();

            // Recuperar el ID recién insertado
            generatedId = obtenerUltimoIdInsertado(conn);
            System.out.println("Orden guardada correctamente. ID de la orden: " + generatedId);
        } catch (Exception e) {
            System.out.println("Error al guardar orden: " + e.getMessage());
        }

        return generatedId; // Devuelve el ID generado como String
    }

    // Método para obtener el ID de la última orden insertada
    private static String obtenerUltimoIdInsertado(Connection conn) throws SQLException {
        String query = "SELECT id FROM orden ORDER BY id DESC LIMIT 1"; // Obtener el último ID insertado
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getString("id"); // Devolver el ID como String
            }
        }
        return null; // Si no se encuentra ningún ID
    }
    public static int insertEquipo(String articulo, int id_Cliente, String marca, String modelo, String numero_serie) {
    String query = "INSERT INTO equipo (nombre, cliente_id, marca, modelo, numero_serie) VALUES (?, ?, ?, ?, ?)";
    int generatedId = -1; // Valor por defecto en caso de error

    try (Connection conn = connect();
         PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

        stmt.setString(1, articulo);
        stmt.setInt(2, id_Cliente);
        stmt.setString(3, marca);
        stmt.setString(4, modelo);
        stmt.setString(5, numero_serie);
        stmt.executeUpdate();

        // Obtener el ID generado
        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                generatedId = generatedKeys.getInt(1);
                System.out.println("Equipo guardado correctamente. ID: " + generatedId);
            } else {
                System.out.println("Error al obtener el ID generado.");
            }
        }
    } catch (Exception e) {
        System.out.println("Error al guardar equipo: " + e.getMessage());
    }

    return generatedId;
    }
    
    
    public static int insertObservacion(Integer equipo_id, Boolean cargador, Boolean bateria, Boolean cable_poder, Boolean cable_datos, String otros) {
    String query = "INSERT INTO observaciones (equipo_id, cargador, bateria, cable_poder, cable_datos, otros) VALUES (?, ?, ?, ?, ?, ?)";
    int generatedId = -1; // Valor por defecto en caso de error

    try (Connection conn = connect();
         PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

        stmt.setInt(1, equipo_id);
        stmt.setBoolean(2, cargador);
        stmt.setBoolean(3, bateria);
        stmt.setBoolean(4, cable_poder);
        stmt.setBoolean(5, cable_datos);
        if (otros != null) {
            stmt.setString(6, otros);
        } else {
            stmt.setNull(6, java.sql.Types.INTEGER);
        }
        stmt.executeUpdate();

        // Obtener el ID generado
        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                generatedId = generatedKeys.getInt(1);
                System.out.println("Observación guardada correctamente. ID: " + generatedId);
            } else {
                System.out.println("Error al obtener el ID generado.");
            }
        }
    } catch (Exception e) {
        System.out.println("Error al guardar observación: " + e.getMessage());
    }

    return generatedId;
    }
    

    public static void insertProblema(ProblemaEquipo problema) {
    Integer idEquipo = problema.getEquipo().getId_equipo();
    String query = "INSERT INTO problema_equipo(equipo_id, problema) VALUES (?,?)"; 
    
    try (Connection conn = connect();
         PreparedStatement stmt = conn.prepareStatement(query)) {
        
        for (String problem : problema.getProblemas()) {
            stmt.setInt(1, idEquipo);
            stmt.setString(2, problem);
            stmt.executeUpdate(); // Ejecuta la inserción
        }
        
    } catch (Exception e) {
        System.out.println("Error al guardar: " + e.getMessage());
    }
}

}

