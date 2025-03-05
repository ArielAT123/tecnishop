/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClasesFrames;
import prueba.cosas.RoundRedButton;
import javax.swing.*;
import java.awt.*;
import prueba.cosas.ModernFrame;

public class MenuFrame extends ModernFrame {
    private final VentanaAgregarProducto aggProducto = new VentanaAgregarProducto(this);
    private final VentanaAgregarCLienteJFrame aggCliente = new VentanaAgregarCLienteJFrame(this);
    private final GenerarOrder generarOrden = new GenerarOrder(this);
    private TablaProductos gestionProductos;

    public MenuFrame() {
        super(); // Llama al constructor de ModernFrame
        setTitle("Menú Principal");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        // Obtener el contentPanel heredado de ModernFrame
        Container contentPane = getContentPane();
        JPanel contentPanel = (JPanel) contentPane.getComponent(1); // El segundo componente es el contentPanel

        // Panel para la imagen (se añade al NORTH del contentPanel)
        JPanel imagenPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        imagenPanel.setBackground(new Color(245, 245, 245)); // Mismo fondo que contentPanel

        // Panel para los botones (se añade al CENTER del contentPanel)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1, 15, 15));
        buttonPanel.setBackground(new Color(245, 245, 245)); // Coherente con el fondo
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        RoundRedButton btnAgregarProducto = new RoundRedButton("Agregar Producto");
        RoundRedButton btnAgregarCliente = new RoundRedButton("Agregar Cliente");
        RoundRedButton btnGenerarOrden = new RoundRedButton("Generar Orden de Trabajo");
        RoundRedButton btnGestionarProducto = new RoundRedButton("Gestionar Producto");

        buttonPanel.add(btnAgregarProducto);
        buttonPanel.add(btnAgregarCliente);
        buttonPanel.add(btnGenerarOrden);
        buttonPanel.add(btnGestionarProducto);

        // Configurar el layout del contentPanel heredado
        contentPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3; // 30% del ancho
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.anchor = GridBagConstraints.WEST; // Justificar a la izquierda

        contentPanel.add(buttonPanel, gbc);

        // Listeners de los botones
        btnAgregarProducto.addActionListener(e -> {
            aggProducto.setVisible(true);
            this.setVisible(false);
        });

        btnAgregarCliente.addActionListener(e -> {
            aggCliente.setVisible(true);
            this.setVisible(false);
        });

        btnGenerarOrden.addActionListener(e -> {
            generarOrden.setVisible(true);
            this.setVisible(false);
        });

        btnGestionarProducto.addActionListener(e -> {
            gestionProductos = new TablaProductos(this);
            gestionProductos.setVisible(true);
            this.setVisible(false);
        });
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuFrame().setVisible(true));
    }
}