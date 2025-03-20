/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba.cosas;
import javax.swing.*;
import java.awt.*;

public class ModernFrame extends JFrame {

    public ModernFrame() {
        Image icono = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/prueba/imagenes/logo_empresa.jpeg"));
        setIconImage(icono);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Crear el panel del encabezado
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(200, 0, 0));
        headerPanel.setPreferredSize(new Dimension(800, 100));
        headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));

        // Agregar la imagen con glow
        try {
            ImageProcessors glowImage = new ImageProcessors("/prueba/cosas/imagenes/logo.png");
            headerPanel.add(glowImage);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Agregar el título
        JLabel titleLabel = new JLabel("TECNISHOP");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        headerPanel.add(titleLabel);

        // Crear el panel de contenido
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(new Color(245, 245, 245));
        contentPanel.setLayout(new GridBagLayout());

        // Crear el panel del pie de página
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(40, 40, 40));
        footerPanel.setPreferredSize(new Dimension(800, 40));
        JLabel footerLabel = new JLabel("© 2025 - Tecnishop APP");
        footerLabel.setForeground(Color.WHITE);
        footerLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        footerPanel.add(footerLabel);

        // Añadir los paneles a la ventana
        add(headerPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);

        // Configurar la ventana
        getContentPane().setBackground(new Color(245, 245, 245));
        setResizable(true);
        pack();
        
    }
}