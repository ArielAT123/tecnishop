/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba.cosas;

/**
 *
 * @author Ernesto
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import prueba.ClasesTablas.RoundRedButton;

public class TablaProductos extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private RoundRedButton updateButton;
    private JFrame previus;
    private RoundRedButton regresar;

    public TablaProductos(JFrame previus) {
        setTitle("Product Table");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.previus=previus;

       
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Codigo");
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Cantidad");
        tableModel.addColumn("Costo de Compra");
        tableModel.addColumn("Precio Sugerido");
        tableModel.addColumn("Precio Recomendado");
        tableModel.addColumn("IVA");
        tableModel.addColumn("% ganancia");
        

        // Crear la tabla con el modelo
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // codigo, nombre, cantidad, costo_compra, precio_venta_sugerido, precio_venta_recomendado, impuesto, porcentaje_ganancia
        updateButton = new RoundRedButton("Actualizar");
        regresar=new RoundRedButton("Regresar");
        
        regresar.addActionListener(e -> {
                previus.setVisible(true);
                this.setVisible(false);
        });
                
                
        updateButton.addActionListener(e -> {
         actualizar();
        }
        ); 
//
        // Panel para el botón
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(updateButton);
        buttonPanel.add(regresar);

        // Añadir componentes al JFrame
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Cargar los datos de la base de datos
        SQLConsultas.loadProductsFromDatabase(tableModel);
    }
    
    private void actualizar(){
    int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            try {
                // Obtener los valores de la fila seleccionada
                String codigo = (String) tableModel.getValueAt(selectedRow, 0);
                String nombre = (String) tableModel.getValueAt(selectedRow, 1);
                int cantidad = Integer.parseInt(tableModel.getValueAt(selectedRow, 2).toString()); // Convertir a int
                double costo_compra = Double.parseDouble(tableModel.getValueAt(selectedRow, 3).toString()); // Convertir a double
                double precio_venta_sugerido = Double.parseDouble(tableModel.getValueAt(selectedRow, 4).toString()); // Convertir a double
                double precio_venta_recomendado = Double.parseDouble(tableModel.getValueAt(selectedRow, 5).toString()); // Convertir a double
                double impuesto = Double.parseDouble(tableModel.getValueAt(selectedRow, 6).toString()); // Convertir a double
                double porcentaje_ganancia = Double.parseDouble(tableModel.getValueAt(selectedRow, 7).toString()); // Convertir a double

                // Llamar al método para actualizar la base de datos
                SQLConsultas.updateProductInDatabase(codigo, nombre, cantidad, costo_compra, precio_venta_sugerido, precio_venta_recomendado, impuesto, porcentaje_ganancia);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(TablaProductos.this, "Error: Los valores numéricos no son válidos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(TablaProductos.this, "Por favor, seleccione una fila para actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
        }}

}