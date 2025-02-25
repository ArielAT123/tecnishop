/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba.cosas;

/**
 *
 * @author Ernesto
 */



import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.ui.FlatRoundBorder;
import javax.swing.*;
import java.awt.*;

public class ModernJFrame extends JFrame {

    public ModernJFrame() {
        // Configurar el tema FlatLaf
        FlatLightLaf.setup();

        // Configurar el JFrame
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar en la pantalla

        // Crear un panel principal con BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Margen
        mainPanel.setBackground(new Color(240, 240, 240)); // Fondo claro

        // Título de la interfaz
        JLabel titleLabel = new JLabel("Gestión de Órdenes");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(200, 50, 50)); // Texto en rojo oscuro
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER); // Centrar el texto
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Panel central (puedes agregar componentes aquí)
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(255, 255, 255)); // Fondo blanco
        centerPanel.setBorder(BorderFactory.createCompoundBorder(
                new FlatRoundBorder(), // Borde redondeado
                BorderFactory.createEmptyBorder(10, 10, 10, 10) // Margen interno
        ));
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Agregar el panel principal al JFrame
        add(mainPanel);
    }
    
    
}