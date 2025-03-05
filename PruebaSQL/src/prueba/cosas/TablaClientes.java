/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba.cosas;

/**
 *
 * @author Ernesto
 */
import SQL_Clases.SQLConsultas;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TablaClientes extends JFrame {

    private final JTable tablaClientes;
    private final DefaultTableModel modeloTabla;

    public TablaClientes() {
        setTitle("Listado de Clientes");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Configurar la tabla y el modelo
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Apellido");
        modeloTabla.addColumn("Correo");
        modeloTabla.addColumn("Teléfono");

        tablaClientes = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaClientes);
        add(scrollPane);

        // Cargar datos
        SQLConsultas.cargarDatos(modeloTabla);
    }


    
}
