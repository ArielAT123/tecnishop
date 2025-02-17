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
import prueba.ClasesTablas.RoundRedButton;

public class TablaProductos extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private RoundRedButton updateButton;
    private RoundRedButton updateAllButton;
    private JFrame previus;
    private RoundRedButton regresar;
    private JLabel cantidad;
    int selectedRow;

    public TablaProductos(JFrame previus) {
        setTitle("Product Table");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.previus = previus;

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
        selectedRow = table.getSelectedRow(); // Obtener la fila seleccionada


        // Botones
        updateButton = new RoundRedButton("Actualizar Fila Seleccionada");
        updateAllButton = new RoundRedButton("Actualizar Todas las Filas");
        regresar = new RoundRedButton("Regresar");
        
        // Acción para regresar
        regresar.addActionListener(e -> {
            previus.setVisible(true);
            this.dispose();
        });

        // Acción para actualizar la fila seleccionada
        updateButton.addActionListener(e -> {
            if (selectedRow != -1) {
                actualizarFila(selectedRow);
            } else {
                JOptionPane.showMessageDialog(TablaProductos.this, "Por favor, seleccione una fila para actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        String texto = (selectedRow != -1) ? String.valueOf(selectedRow) : "Ninguna fila seleccionada";
        cantidad = new JLabel(texto);
        // Acción para actualizar todas las filas
        updateAllButton.addActionListener(e -> {
            for (int row = 0; row < tableModel.getRowCount(); row++) {
                actualizarFila(row);
            }
            JOptionPane.showMessageDialog(TablaProductos.this, "Todas las filas han sido actualizadas.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        });

        // Panel para los botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(updateButton);
        buttonPanel.add(updateAllButton);
        buttonPanel.add(regresar);
        buttonPanel.add(cantidad);

        // Añadir componentes al JFrame
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Cargar los datos de la base de datos
        SQLConsultas.loadProductsFromDatabase(tableModel);
    }

    // Método general para actualizar una fila específica
    private void actualizarFila(int row) {
        try {
            // Obtener los valores de la fila
            String codigo = (String) tableModel.getValueAt(row, 0);
            String nombre = (String) tableModel.getValueAt(row, 1);
            Integer cantidad = Integer.parseInt(tableModel.getValueAt(row, 2).toString());
            Double costo_compra = Double.parseDouble(tableModel.getValueAt(row, 3).toString());
            Double precio_venta_sugerido = Double.parseDouble(tableModel.getValueAt(row, 4).toString());
            Double precio_venta_recomendado = Double.parseDouble(tableModel.getValueAt(row, 5).toString());
            Double impuesto = Double.parseDouble(tableModel.getValueAt(row, 6).toString());
            Double porcentaje_ganancia = Double.parseDouble(tableModel.getValueAt(row, 7).toString());

            // Llamar al método para actualizar la base de datos
            SQLConsultas.updateProductInDatabase(codigo, nombre, cantidad, costo_compra, precio_venta_sugerido, precio_venta_recomendado, impuesto, porcentaje_ganancia);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(TablaProductos.this, "Error: Los valores numéricos no son válidos en la fila " + (row + 1), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}