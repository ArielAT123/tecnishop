/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba;
import prueba.ClasesTablas.RoundRedButton;
import javax.swing.*;
import java.awt.*;
import prueba.cosas.TablaProductos;

public class MenuFrame extends JFrame {
    private final VentanaAgregarProducto aggProducto = new VentanaAgregarProducto(this);
    private final VentanaAgregarCLienteJFrame aggCliente = new VentanaAgregarCLienteJFrame(this);
    private final GenerarOrder generarOrden = new GenerarOrder(this);
    private  TablaProductos gestionProductos;

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

        RoundRedButton btnAgregarProducto = new RoundRedButton("Agregar Producto");//createStyledButton("Agregar Producto");
        RoundRedButton btnAgregarCliente = new RoundRedButton("Agregar Cliente");
        RoundRedButton btnGenerarOrden = new RoundRedButton("Generar Orden de Trabajo");
        RoundRedButton btnGestionarProducto = new RoundRedButton("Gestionar Producto");

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
        btnGenerarOrden.addActionListener(e ->{
                generarOrden.setVisible(true);
                this.setVisible(false);
        }
        );
        btnGestionarProducto.addActionListener(e ->{
            gestionProductos = new TablaProductos(this);
            gestionProductos.setVisible(true);
            this.setVisible(false);
        } );
    }

    

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuFrame().setVisible(true));
    }
}