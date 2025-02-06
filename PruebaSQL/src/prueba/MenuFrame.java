/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba;
import javax.swing.*;
import java.awt.*;

public class MenuFrame extends JFrame {
    private final VentanaAgregarProducto aggProducto = new VentanaAgregarProducto(this);
    private final VentanaAgregarCLienteJFrame aggCliente = new VentanaAgregarCLienteJFrame(this);

    public MenuFrame() {
        setTitle("Menú Principal");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel imagenPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        imagenPanel.setBackground(Color.WHITE);

        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/prueba/imagenes/logo_empresa.jpeg"));
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(200, 100, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel imagenLabel = new JLabel(scaledIcon);
        imagenPanel.add(imagenLabel);

        add(imagenPanel, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 15, 15));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JButton btnAgregarProducto = createStyledButton("Agregar Producto");
        JButton btnAgregarCliente = createStyledButton("Agregar Cliente");
        JButton btnGenerarOrden = createStyledButton("Generar Orden de Trabajo");
        JButton btnGestionarProducto = createStyledButton("Gestionar Producto");

        panel.add(btnAgregarProducto);
        panel.add(btnAgregarCliente);
        panel.add(btnGenerarOrden);
        panel.add(btnGestionarProducto);

        add(panel, BorderLayout.CENTER);

        btnAgregarProducto.addActionListener(e -> {
            aggProducto.setVisible(true);
            this.setVisible(false); 
        });

        btnAgregarCliente.addActionListener(e -> {
                aggCliente.setVisible(true);
                this.setVisible(false);
        }
        );
        btnGenerarOrden.addActionListener(e -> showMessage("Generar Orden de Trabajo seleccionado"));
        btnGestionarProducto.addActionListener(e -> showMessage("Gestionar Producto seleccionado"));
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                if (!isOpaque()) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(getBackground());
                    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                    g2.dispose();
                }
                super.paintComponent(g);
            }
        };
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(200, 0, 0));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        return button;
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuFrame().setVisible(true));
    }
}