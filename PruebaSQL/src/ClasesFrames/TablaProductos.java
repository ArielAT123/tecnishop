package ClasesFrames;

import SQL_Clases.SQLConsultas;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;
import prueba.cosas.ModernFrame;
import prueba.cosas.RoundRedButton;

public class TablaProductos extends ModernFrame {
    
    private JTable table;
    private DefaultTableModel tableModel;
    private final RoundRedButton updateButton;
    private final RoundRedButton updateAllButton;
    private final JFrame previus;
    private final RoundRedButton regresar;
    private JLabel cantidad;
    private int selectedRow;
    private JTextField filterField; // Campo de texto para el filtro por nombre
    private RoundRedButton filterButton; // Botón para aplicar el filtro por nombre
    private String columnName;
    private Set<Integer> filasSeleccionadas= new HashSet<>();

    public TablaProductos(JFrame previus) {
        super();
        setTitle("Product Table");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Pantalla completa
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.previus = previus;

        // Panel principal para el contenido del body
        JPanel bodyPanel = new JPanel(new BorderLayout(10, 10));
        bodyPanel.setBackground(new Color(245, 245, 245)); // Fondo gris claro, igual al body de ModernFrame
        bodyPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Configurar la tabla
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Codigo");
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Cantidad");
        tableModel.addColumn("Costo de Compra");
        tableModel.addColumn("Precio Sugerido");
        tableModel.addColumn("Precio Recomendado");
        tableModel.addColumn("IVA");
        tableModel.addColumn("% ganancia");

        table = new JTable(tableModel);
        table.getTableHeader().setReorderingAllowed(false); // Columnas no movibles

     
        

        JScrollPane scrollPane = new JScrollPane(table);
        bodyPanel.add(scrollPane, BorderLayout.CENTER); // Tabla en el centro del body

        // Inicializar selectedRow
        selectedRow = -1;

        // Agregar un ListSelectionListener a la tabla
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    selectedRow = table.getSelectedRow();
                    cantidad.setText((selectedRow != -1) ? "Fila seleccionada: " + (selectedRow) : "Ninguna fila seleccionada");
                    if(selectedRow != -1){filasSeleccionadas.add(selectedRow);
                        System.out.println("fila añadida: "+selectedRow);
                        System.out.println(filasSeleccionadas.toString());}
                }
            }
        });                                                                                                                                                     

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
                actualizarFila(selectedRow, true);
                
            } else {
                JOptionPane.showMessageDialog(TablaProductos.this, "Por favor, seleccione una fila para actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Inicializar la etiqueta cantidad
        cantidad = new JLabel("Ninguna fila seleccionada");

        // Acción para actualizar todas las filas
        updateAllButton.addActionListener(e -> {
            for(Integer row: filasSeleccionadas){
                System.out.println(row);
                actualizarFila(row, false);
            }
            JOptionPane.showMessageDialog(TablaProductos.this, "Todas las filas han sido actualizadas.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        });

        // Panel para los botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.add(updateButton);
        buttonPanel.add(updateAllButton);
        buttonPanel.add(regresar);
        buttonPanel.add(cantidad);

        // Sección de filtro por nombre
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        filterPanel.setBackground(new Color(245, 245, 245));
        JLabel filterLabel = new JLabel("Seleccione la columna a Filtrar:");
        filterField = new JTextField(20); // Campo de texto para el filtro
        filterButton = new RoundRedButton("Aplicar Filtro");

        filterButton.addActionListener(e -> {
            String filterText = filterField.getText().trim();
            tableModel.setRowCount(0); // Limpiar la tabla
            SQLConsultas.loadProductsFromDatabaseByColumn(tableModel, columnName ,filterText); // Cargar datos filtrados por nombre
        });
        
        table.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int columnIndex = table.getTableHeader().columnAtPoint(e.getPoint());
                if (columnIndex >= 0) {
                    columnName=tableModel.getColumnName(columnIndex);
                    filterLabel.setText("Filtrar por "+ columnName);
                                        
                }
            }
        });

        filterPanel.add(filterLabel);
        filterPanel.add(filterField);
        filterPanel.add(filterButton);

        // Añadir el panel de filtro al NORTH del bodyPanel
        bodyPanel.add(filterPanel, BorderLayout.NORTH);
        // Añadir el panel de botones al SOUTH del bodyPanel
        bodyPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Añadir el bodyPanel al CENTER del ModernFrame
        add(bodyPanel, BorderLayout.CENTER);

        // Cargar los datos de la base de datos (sin filtro inicial)
        SQLConsultas.loadProductsFromDatabaseByColumn(tableModel, "" ,"");

        // Hacer visible la ventana
        setVisible(true);
    }

    // Método general para actualizar una fila específica
    private void actualizarFila(int row, boolean mostrar) {
        try {
            String codigo = (String) tableModel.getValueAt(row, 0);
            String nombre = (String) tableModel.getValueAt(row, 1);
            Integer cantidad = Integer.parseInt(tableModel.getValueAt(row, 2).toString());
            Double costo_compra = Double.parseDouble(tableModel.getValueAt(row, 3).toString());
            Double precio_venta_sugerido = Double.parseDouble(tableModel.getValueAt(row, 4).toString());
            Double precio_venta_recomendado = Double.parseDouble(tableModel.getValueAt(row, 5).toString());
            Double impuesto = Double.parseDouble(tableModel.getValueAt(row, 6).toString());
            Double porcentaje_ganancia = Double.parseDouble(tableModel.getValueAt(row, 7).toString());
            
            SQLConsultas.updateProductInDatabase(this, codigo, nombre, cantidad, costo_compra, precio_venta_sugerido, precio_venta_recomendado, impuesto, porcentaje_ganancia);
            if(mostrar){
                JOptionPane.showMessageDialog(TablaProductos.this,
                "Datos Actualizados:\nCódigo: " + codigo +
                "\nNombre: " + nombre +
                "\nCantidad: " + cantidad +
                "\nCosto de Compra: " + costo_compra +
                "\nPrecio Venta Sugerido: " + precio_venta_sugerido +
                "\nPrecio Venta Recomendado: " + precio_venta_recomendado +
                "\nImpuesto: " + impuesto +
                "\nPorcentaje de Ganancia: " + porcentaje_ganancia,
                "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(TablaProductos.this, "Error: Los valores numéricos no son válidos en la fila " + (row + 1), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}