package prueba.cosas;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.List;

public class TablaNegocio extends JTable {

    private List<Integer> selectedRowIndices;

    public TablaNegocio(DefaultTableModel model, List<Integer> selectedRowIndices) {
        super(model);
        this.selectedRowIndices = selectedRowIndices;

        // Configuraciones básicas de decoración
        getTableHeader().setReorderingAllowed(false);

        // Estilo del encabezado (negro con texto blanco)
        getTableHeader().setBackground(Color.BLACK);
        getTableHeader().setForeground(Color.WHITE);
        getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));

        // Estilo de las celdas
        setFont(new Font("Arial", Font.PLAIN, 14));
        setRowHeight(25);
        setGridColor(Color.LIGHT_GRAY);
        setShowGrid(true);

        // Ajustar tamaños de columnas
        getColumnModel().getColumn(0).setPreferredWidth(30);

        // Renderizador y editor para la columna de checkboxes
        TableCellRenderer checkboxRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JCheckBox checkbox = new JCheckBox();
                checkbox.setSelected(value != null && (Boolean) value);
                checkbox.setHorizontalAlignment(JCheckBox.CENTER);
                checkbox.setBackground(table.getBackground());
                return checkbox;
            }
        };
        getColumnModel().getColumn(0).setCellRenderer(checkboxRenderer);

        setDefaultEditor(Boolean.class, new DefaultCellEditor(new JCheckBox()) {
            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                JCheckBox checkbox = (JCheckBox) super.getTableCellEditorComponent(table, value, isSelected, row, column);
                checkbox.setHorizontalAlignment(JCheckBox.CENTER);
                return checkbox;
            }
        });
    }

    // Getter para selectedRowIndices (si es necesario)
    public List<Integer> getSelectedRowIndices() {
        return selectedRowIndices;
    }
}