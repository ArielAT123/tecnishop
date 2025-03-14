package ClasesFrames;

import ClasesDatos.ProductoInventario;
import SQL_Clases.SQLConsultas;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    private JTextField filterField;
    private RoundRedButton filterButton;
    private String columnName;
    private List<Integer> selectedRowIndices = new ArrayList<>(); // Lista dinámica de índices de filas seleccionadas
    private Set<ProductoInventario> productosSeleccionados = new HashSet<>(); // Set para los productos a actualizar

    public TablaProductos(JFrame previus) {
        super();
        setTitle("Product Table");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.previus = previus;

        // Panel principal para el contenido del body
        JPanel bodyPanel = new JPanel(new BorderLayout(10, 10));
        bodyPanel.setBackground(new Color(245, 245, 245));
        bodyPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Configurar la tabla con columna de checkboxes
        tableModel = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) return Boolean.class; // Primera columna para checkboxes
                return super.getColumnClass(columnIndex);
            }
        };
        tableModel.addColumn("Check"); // Columna 0: Checkbox
        tableModel.addColumn("Codigo");       // Columna 1
        tableModel.addColumn("Nombre");       // Columna 2
        tableModel.addColumn("Cantidad");     // Columna 3
        tableModel.addColumn("Costo de Compra"); // Columna 4
        tableModel.addColumn("Precio Sugerido"); // Columna 5
        tableModel.addColumn("Precio Recomendado"); // Columna 6
        tableModel.addColumn("IVA");          // Columna 7
        tableModel.addColumn("% ganancia");   // Columna 8
        tableModel.addColumn("Estado");       // Columna 9

        table = new JTable(tableModel);
        table.getTableHeader().setReorderingAllowed(false);

        // Renderizador y editor para la columna de checkboxes
        TableCellRenderer checkboxRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JCheckBox checkbox = new JCheckBox();
                checkbox.setSelected(value != null && (Boolean) value);
                checkbox.setHorizontalAlignment(JCheckBox.CENTER);
                return checkbox;
            }
        };
        table.getColumnModel().getColumn(0).setCellRenderer(checkboxRenderer);

        table.setDefaultEditor(Boolean.class, new DefaultCellEditor(new JCheckBox()) {
            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                JCheckBox checkbox = (JCheckBox) super.getTableCellEditorComponent(table, value, isSelected, row, column);
                checkbox.setHorizontalAlignment(JCheckBox.CENTER);
                return checkbox;
            }
        });

        // Actualizar índices y cambiar color de las filas seleccionadas
        table.getModel().addTableModelListener(e -> {
            int row = e.getFirstRow();
            int column = e.getColumn();
            if (column == 0 && row >= 0) { // Solo para la columna de checkboxes y asegurarse de que la fila es válida
                Boolean isSelected = (Boolean) tableModel.getValueAt(row, 0); // Obtener el valor del checkbox
                if (isSelected != null) {
                    if (isSelected) {
                        if (!selectedRowIndices.contains(row)) {
                            selectedRowIndices.add(row);
                            System.out.println("Fila " + row + " añadida a los índices seleccionados.");
                            table.repaint(); // Forzar repintado para reflejar el color de selección
                        }
                    } else {
                        selectedRowIndices.remove(Integer.valueOf(row));
                        System.out.println("Fila " + row + " removida de los índices seleccionados.");
                        table.repaint(); // Forzar repintado para restaurar el color original
                    }
                    System.out.println("Índices de filas seleccionadas: " + selectedRowIndices);
                }
            }
        });

        // Renderizador personalizado para cambiar el color de las filas según el estado y la selección
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // No aplicar color al checkbox (columna 0)
                if (column == 0) {
                    c.setBackground(table.getBackground());
                    return c;
                }

                // Si la fila está seleccionada dinámicamente (checkbox marcado), usar el color de selección
                if (selectedRowIndices.contains(row)) {
                    c.setBackground(table.getSelectionBackground());
                } else {
                    // Restaurar color basado en el estado
                    String estado = (String) tableModel.getValueAt(row, 9);
                    switch (estado) {
                        case "disponible":
                            c.setBackground(new Color(0, 255, 0, 150));
                            break;
                        case "escaso":
                            c.setBackground(new Color(255, 255, 0, 150));
                            break;
                        case "agotado":
                            c.setBackground(new Color(255, 0, 0, 150));
                            break;
                        default:
                            c.setBackground(table.getBackground());
                            break;
                    }
                }

                // Mantener el color de selección si la fila está seleccionada manualmente (clic)
                if (isSelected) {
                    c.setBackground(table.getSelectionBackground());
                    c.setForeground(table.getSelectionForeground());
                } else {
                    c.setForeground(table.getForeground());
                }

                return c;
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        bodyPanel.add(scrollPane, BorderLayout.CENTER);

        // Inicializar selectedRow
        selectedRow = -1;

        // Actualizar selectedRow al hacer clic en la tabla (para la fila seleccionada)
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectedRow = table.rowAtPoint(e.getPoint());
                cantidad.setText((selectedRow != -1) ? "Fila seleccionada: " + selectedRow : "Ninguna fila seleccionada");
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
                try {
                    String codigo = (String) tableModel.getValueAt(selectedRow, 1);
                    String nombre = (String) tableModel.getValueAt(selectedRow, 2);
                    int cantidad = Integer.parseInt(tableModel.getValueAt(selectedRow, 3).toString());
                    double costoCompra = Double.parseDouble(tableModel.getValueAt(selectedRow, 4).toString());
                    double precioVentaSugerido = Double.parseDouble(tableModel.getValueAt(selectedRow, 5).toString());
                    double precioVentaRecomendado = Double.parseDouble(tableModel.getValueAt(selectedRow, 6).toString());
                    double impuesto = Double.parseDouble(tableModel.getValueAt(selectedRow, 7).toString());
                    double porcentajeGanancia = Double.parseDouble(tableModel.getValueAt(selectedRow, 8).toString());
                    String estado = (String) tableModel.getValueAt(selectedRow, 9);

                    ProductoInventario producto = new ProductoInventario(-1, codigo, nombre, cantidad, costoCompra,
                            precioVentaSugerido, precioVentaRecomendado, impuesto, porcentajeGanancia, estado);
                    actualizarProducto(producto, true);
                } catch (NumberFormatException ex) {
                    System.out.println("Error de formato en fila " + selectedRow + ": " + ex.getMessage());
                    JOptionPane.showMessageDialog(TablaProductos.this, "Error: Datos inválidos en la fila " + (selectedRow + 1), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    System.out.println("Error inesperado en fila " + selectedRow + ": " + ex.getMessage());
                    JOptionPane.showMessageDialog(TablaProductos.this, "Error inesperado en la fila " + (selectedRow + 1), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(TablaProductos.this, "Por favor, seleccione una fila para actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Inicializar la etiqueta cantidad
        cantidad = new JLabel("Ninguna fila seleccionada");

        // Acción para actualizar todas las filas
        updateAllButton.addActionListener(e -> {
            if (selectedRowIndices.isEmpty()) {
                JOptionPane.showMessageDialog(TablaProductos.this, "Por favor, seleccione al menos una fila con checkbox para actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            System.out.println("Índices de filas seleccionadas antes de actualizar: " + selectedRowIndices);

            // Limpiar el set antes de llenarlo
            productosSeleccionados.clear();

            // Crear productos y añadirlos al set basados en los índices seleccionados
            for (Integer rowIndex : selectedRowIndices) {
                try {
                    String codigo = (String) tableModel.getValueAt(rowIndex, 1);
                    String nombre = (String) tableModel.getValueAt(rowIndex, 2);
                    int cantidad = Integer.parseInt(tableModel.getValueAt(rowIndex, 3).toString());
                    double costoCompra = Double.parseDouble(tableModel.getValueAt(rowIndex, 4).toString());
                    double precioVentaSugerido = Double.parseDouble(tableModel.getValueAt(rowIndex, 5).toString());
                    double precioVentaRecomendado = Double.parseDouble(tableModel.getValueAt(rowIndex, 6).toString());
                    double impuesto = Double.parseDouble(tableModel.getValueAt(rowIndex, 7).toString());
                    double porcentajeGanancia = Double.parseDouble(tableModel.getValueAt(rowIndex, 8).toString());
                    String estado = (String) tableModel.getValueAt(rowIndex, 9);

                    ProductoInventario producto = new ProductoInventario(-1, codigo, nombre, cantidad, costoCompra,
                            precioVentaSugerido, precioVentaRecomendado, impuesto, porcentajeGanancia, estado);
                    productosSeleccionados.add(producto);
                    System.out.println("Producto añadido al set desde fila " + rowIndex + ": " + producto);
                } catch (NumberFormatException ex) {
                    System.out.println("Error de formato en fila " + rowIndex + ": " + ex.getMessage());
                    JOptionPane.showMessageDialog(TablaProductos.this, "Error: Datos inválidos en la fila " + (rowIndex + 1), "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Salir si hay un error para evitar actualizaciones parciales
                } catch (Exception ex) {
                    System.out.println("Error inesperado en fila " + rowIndex + ": " + ex.getMessage());
                    JOptionPane.showMessageDialog(TablaProductos.this, "Error inesperado en la fila " + (rowIndex + 1), "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Salir si hay un error para evitar actualizaciones parciales
                }
            }

            // Actualizar todos los productos en el set
            System.out.println("Productos seleccionados para actualizar: " + productosSeleccionados);
            boolean allSuccess = true;
            for (ProductoInventario producto : productosSeleccionados) {
                try {
                    System.out.println("Intentando actualizar producto: " + producto);
                    actualizarProducto(producto, false);
                } catch (Exception ex) {
                    allSuccess = false;
                    System.out.println("Error al actualizar producto " + producto.getCodigo() + ": " + ex.getMessage());
                    ex.printStackTrace();
                }
            }

            // Recargar la tabla una sola vez después de todas las actualizaciones
            System.out.println("Recargando tabla después de todas las actualizaciones");
            tableModel.setRowCount(0);
            SQLConsultas.loadProductsFromDatabaseByColumn(tableModel, "", "");
            JOptionPane.showMessageDialog(TablaProductos.this, 
                allSuccess ? "Todas las filas han sido actualizadas." : "Algunas filas no se actualizaron correctamente.",
                allSuccess ? "Éxito" : "Advertencia", 
                allSuccess ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.WARNING_MESSAGE);
            selectedRowIndices.clear(); // Limpiar los índices después de la actualización
            productosSeleccionados.clear(); // Limpiar el set después de la actualización
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
        filterField = new JTextField(20);
        filterButton = new RoundRedButton("Aplicar Filtro");

        filterButton.addActionListener(e -> {
            String filterText = filterField.getText().trim();
            tableModel.setRowCount(0);
            SQLConsultas.loadProductsFromDatabaseByColumn(tableModel, columnName, filterText);
            selectedRowIndices.clear(); // Limpiar índices al filtrar
            productosSeleccionados.clear(); // Limpiar el set al filtrar
        });

        table.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int columnIndex = table.getTableHeader().columnAtPoint(e.getPoint());
                if (columnIndex >= 0) {
                    columnName = tableModel.getColumnName(columnIndex);
                    filterLabel.setText("Filtrar por " + columnName);
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
        SQLConsultas.loadProductsFromDatabaseByColumn(tableModel, "", "");

        // Hacer visible la ventana
        setVisible(true);
    }

    // Método para actualizar un producto directamente desde el objeto ProductoInventario
    private void actualizarProducto(ProductoInventario producto, boolean mostrar) {
        try {
            System.out.println("Procesando actualización para producto: " + producto.getCodigo());
            SQLConsultas.updateProductInDatabase(this, producto.getCodigo(), producto.getNombre(), producto.getCantidad(),
                    producto.getCostoCompra(), producto.getPrecioVentaSugerido(), producto.getPrecioVentaRecomendado(),
                    producto.getImpuesto(), producto.getPorcentajeGanancia());

            if (mostrar) {
                JOptionPane.showMessageDialog(TablaProductos.this,
                        "Datos Actualizados:\nCódigo: " + producto.getCodigo() +
                                "\nNombre: " + producto.getNombre() +
                                "\nCantidad: " + producto.getCantidad() +
                                "\nCosto de Compra: " + producto.getCostoCompra() +
                                "\nPrecio Venta Sugerido: " + producto.getPrecioVentaSugerido() +
                                "\nPrecio Venta Recomendado: " + producto.getPrecioVentaRecomendado() +
                                "\nImpuesto: " + producto.getImpuesto() +
                                "\nPorcentaje de Ganancia: " + producto.getPorcentajeGanancia(),
                        "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            System.out.println("Error de formato al actualizar " + producto.getCodigo() + ": " + ex.getMessage());
            JOptionPane.showMessageDialog(TablaProductos.this, "Error: Los valores numéricos no son válidos para el producto " + producto.getCodigo(), "Error", JOptionPane.ERROR_MESSAGE);
            throw ex;
        } catch (Exception ex) {
            System.out.println("Error inesperado al actualizar " + producto.getCodigo() + ": " + ex.getMessage());
            JOptionPane.showMessageDialog(TablaProductos.this, "Error inesperado al actualizar el producto " + producto.getCodigo() + ": " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            throw ex;
        }
    }
}