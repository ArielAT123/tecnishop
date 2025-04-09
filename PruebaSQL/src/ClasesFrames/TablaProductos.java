package ClasesFrames;

import ClasesDatos.ProductoInventario;
import SQL_Clases.SQLConsultas;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import prueba.cosas.CheckBoxPersonalizado;
import prueba.cosas.ModernFrame;
import prueba.cosas.RoundRedButton;
import prueba.cosas.TablaNegocio;

public class TablaProductos extends ModernFrame {
    
    private TablaNegocio table;
    private DefaultTableModel tableModel;
    private final RoundRedButton updateAllButton;
    private final JFrame previus;
    private final RoundRedButton regresar;
    private JLabel cantidad;
    private JLabel ordenAscendente;
    //private Cicle listaEstados= new Cicle();

    private int selectedRow;
    private JTextField filterField;
    private RoundRedButton filterButton;
    private RoundRedButton allcheckButton;
    private RoundRedButton visualizarEstadoButton;
    private String columnName;
    private List<Integer> selectedRowIndices = new ArrayList<>();
    private Set<ProductoInventario> productosSeleccionados = new HashSet<>();
    private boolean mostrarColoresEstado = false;
    private CheckBoxPersonalizado checkbox;

    public TablaProductos(JFrame previus) {
        super();
        setTitle("Product Table");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.previus = previus;

        // Panel principal para el contenido del body
        JPanel bodyPanel = new JPanel(new BorderLayout(10, 10));
        bodyPanel.setBackground(Color.BLACK);
        bodyPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Configurar el modelo de la tabla
        tableModel = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) return Boolean.class;
                return super.getColumnClass(columnIndex);
            }
        };
        tableModel.addColumn("Check");
        tableModel.addColumn("Código");
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Cantidad");
        tableModel.addColumn("Costo de Compra");
        tableModel.addColumn("Precio Sugerido");
        tableModel.addColumn("Precio Recomendado");
        tableModel.addColumn("IVA");
        tableModel.addColumn("% ganancia");
        tableModel.addColumn("Estado");

        // Crear la tabla personalizada
        table = new TablaNegocio(tableModel, selectedRowIndices);

        // Actualizar índices al cambiar los checkboxes
        table.getModel().addTableModelListener(e -> {
            int row = e.getFirstRow();
            int column = e.getColumn();
            if (column == 0 && row >= 0) {
                Boolean isSelected = (Boolean) tableModel.getValueAt(row, 0);
                if (isSelected != null) {
                    if (isSelected) {
                        if (!selectedRowIndices.contains(row)) {
                            selectedRowIndices.add(row);
                            System.out.println("Fila " + row + " añadida a los índices seleccionados.");
                        }
                    } else {
                        selectedRowIndices.remove(Integer.valueOf(row));
                        System.out.println("Fila " + row + " removida de los índices seleccionados.");
                    }
                    System.out.println("Índices de filas seleccionadas: " + selectedRowIndices);
                    tableModel.fireTableDataChanged();
                }
            }
        });

        // Renderizador personalizado para colores
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
                ((JLabel) c).setHorizontalAlignment(SwingConstants.CENTER);

                if (column == 0) {
                    c.setBackground(table.getBackground());
                    return c;
                }

                if (selectedRowIndices.contains(row)) {
                    c.setBackground(table.getSelectionBackground());
                    c.setForeground(table.getSelectionForeground());
                } else {
                    if (mostrarColoresEstado) {
                        String estado = (String) tableModel.getValueAt(row, 9);
                        System.out.println("Fila " + row + ", Estado: " + estado);
                        if (estado != null) {
                            switch (estado.toLowerCase().trim()) {
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
                        } else {
                            c.setBackground(table.getBackground());
                        }
                    } else {
                        c.setBackground(table.getBackground());
                    }
                    c.setForeground(table.getForeground());
                }

                if (isSelected) {
                    c.setBackground(table.getSelectionBackground());
                    c.setForeground(table.getSelectionForeground());
                }

                return c;
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        bodyPanel.add(scrollPane, BorderLayout.CENTER);

        // Inicializar selectedRow
        selectedRow = -1;

        // Actualizar selectedRow al hacer clic en la tabla
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectedRow = table.rowAtPoint(e.getPoint());
                cantidad.setText((selectedRow != -1) ? "Fila seleccionada: " + selectedRow : "Ninguna fila seleccionada");
            }
        });

        // Botones
        updateAllButton = new RoundRedButton("Actualizar las Filas seleccionadas");
        regresar = new RoundRedButton("Regresar");
        allcheckButton = new RoundRedButton("Check All");
        filterButton = new RoundRedButton("Aplicar Filtro");
        visualizarEstadoButton = new RoundRedButton("Visualización de Estado");

        // Acción para el botón "Visualización de Estado"
        visualizarEstadoButton.addActionListener(e -> {
            System.out.println("Botón Visualización de Estado clicado");
            mostrarColoresEstado = !mostrarColoresEstado;
            visualizarEstadoButton.setText(mostrarColoresEstado ? "Ocultar Estado" : "Visualización de Estado");
            tableModel.fireTableDataChanged();
            table.revalidate();
            table.repaint();
        });

        // Acción para regresar
        regresar.addActionListener(e -> {
            previus.setVisible(true);
            this.dispose();
        });

        // Inicializar la etiqueta cantidad
        cantidad = new JLabel("Ninguna fila seleccionada");
        cantidad.setForeground(Color.WHITE);
        cantidad.setFont(new Font("Arial", Font.PLAIN, 14));
        
        // Crear el checkbox y configurar su listener
        checkbox = new CheckBoxPersonalizado();
        checkbox.addItemListener(e -> {
            actualizarTextoOrden();
        });
        
        // Inicializar el label de orden
        ordenAscendente = new JLabel("           ORDEN " + (checkbox.isSelected() ? "ASCENDENTE" : "DESCENDENTE"));
        ordenAscendente.setForeground(Color.WHITE);
        ordenAscendente.setFont(new Font("Arial", Font.PLAIN, 14));

        // Acción para actualizar todas las filas
        updateAllButton.addActionListener((var e) -> {
            if (selectedRowIndices.isEmpty()) {
                JOptionPane.showMessageDialog(TablaProductos.this, "Por favor, seleccione al menos una fila con checkbox para actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            System.out.println("Índices de filas seleccionadas antes de actualizar: " + selectedRowIndices);

            productosSeleccionados.clear();

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
                    return;
                } catch (Exception ex) {
                    System.out.println("Error inesperado en fila " + rowIndex + ": " + ex.getMessage());
                    JOptionPane.showMessageDialog(TablaProductos.this, "Error inesperado en la fila " + (rowIndex + 1), "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            System.out.println("Productos seleccionados para actualizar: " + productosSeleccionados);
            boolean allSuccess = true;
            for (ProductoInventario producto : productosSeleccionados) {
                try {
                    System.out.println("Intentando actualizar producto: " + producto);
                    actualizarProducto(producto, false);
                } catch (Exception ex) {
                    allSuccess = false;
                    System.out.println("Error al actualizar producto " + producto.getCodigo() + ": " + ex.getMessage());
                }
            }

            System.out.println("Recargando tabla después de todas las actualizaciones");
            tableModel.setRowCount(0);
            SQLConsultas.loadProductsFromDatabaseByColumn(tableModel, "", "", checkbox.isSelected());
            JOptionPane.showMessageDialog(TablaProductos.this, 
                allSuccess ? "Todas las filas han sido actualizadas." : "Algunas filas no se actualizaron correctamente.",
                allSuccess ? "Éxito" : "Advertencia", 
                allSuccess ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.WARNING_MESSAGE);
            selectedRowIndices.clear();
            productosSeleccionados.clear();
        });

        // Acción para el botón "check all"
        allcheckButton.addActionListener(e -> {
            int selectedCount = selectedRowIndices.size();
            int totalRows = tableModel.getRowCount();

            if (selectedCount == totalRows && totalRows > 0) {
                for (int i = 0; i < totalRows; i++) {
                    tableModel.setValueAt(false, i, 0);
                }
                selectedRowIndices.clear();
                productosSeleccionados.clear();
                System.out.println("Todas las filas desmarcadas.");
            } else {
                for (int i = 0; i < totalRows; i++) {
                    tableModel.setValueAt(true, i, 0);
                    if (!selectedRowIndices.contains(i)) {
                        selectedRowIndices.add(i);
                    }
                }
                System.out.println("Todas las filas marcadas. Índices: " + selectedRowIndices);
            }
            tableModel.fireTableDataChanged();
        });

        // Panel para los botones (centrado)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.add(allcheckButton);
        buttonPanel.add(updateAllButton);
        buttonPanel.add(visualizarEstadoButton);
        buttonPanel.add(regresar);
        buttonPanel.add(cantidad);

        // Sección de filtro por nombre
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        filterPanel.setBackground(Color.BLACK);
        JLabel filterLabel = new JLabel("Seleccione la columna a Filtrar:");
        filterLabel.setForeground(Color.WHITE);
        filterLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        filterField = new JTextField(20);
        filterField.setFont(new Font("Arial", Font.PLAIN, 14));

        filterButton.addActionListener(e -> {
            String filterText = filterField.getText().trim();
            tableModel.setRowCount(0);
            SQLConsultas.loadProductsFromDatabaseByColumn(tableModel, columnName, filterText, checkbox.isSelected());
            selectedRowIndices.clear();
            productosSeleccionados.clear();
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
        filterPanel.add(ordenAscendente);
        filterPanel.add(checkbox);

        // Añadir el panel de filtro al NORTH del bodyPanel
        bodyPanel.add(filterPanel, BorderLayout.NORTH);
        // Añadir el panel de botones al SOUTH del bodyPanel
        bodyPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Añadir el bodyPanel al CENTER del ModernFrame
        add(bodyPanel, BorderLayout.CENTER);

        // Cargar los datos de la base de datos (sin filtro inicial)
        SQLConsultas.loadProductsFromDatabaseByColumn(tableModel, "", "", checkbox.isSelected());

        // Hacer visible la ventana
        setVisible(true);
    }

    // Método para actualizar el texto del label de orden
    private void actualizarTextoOrden() {
        ordenAscendente.setText("           ORDEN " + (checkbox.isSelected() ? "ASCENDENTE   " : "DESCENDENTE"));
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
        } catch (HeadlessException ex) {
            System.out.println("Error inesperado al actualizar " + producto.getCodigo() + ": " + ex.getMessage());
            JOptionPane.showMessageDialog(TablaProductos.this, "Error inesperado al actualizar el producto " + producto.getCodigo() + ": " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            throw ex;
        }
    }
}