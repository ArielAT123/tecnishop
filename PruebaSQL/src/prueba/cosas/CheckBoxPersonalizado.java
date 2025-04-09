/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba.cosas;

import javax.swing.JCheckBox;

/**
 *
 * @author Ernesto
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

public class CheckBoxPersonalizado extends JCheckBox {
    
    // Constructor
    public CheckBoxPersonalizado() {
        super();
        configurarApariencia();
        configurarEventos();
    }
    
    public CheckBoxPersonalizado(String text) {
        super(text);
        configurarApariencia();
        configurarEventos();
    }
    
    public CheckBoxPersonalizado(String text, boolean selected) {
        super(text, selected);
        configurarApariencia();
        configurarEventos();
    }
    
    private void configurarApariencia() {
        // Estilo minimalista como en la tabla TECNISHOP
        this.setOpaque(false);
        this.setBackground(new Color(240, 240, 240)); // Fondo claro
        this.setForeground(new Color(50, 50, 50)); // Texto oscuro
        this.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        this.setFocusPainted(false); // Quitar el borde de enfoque
        this.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        
        // Cambiar los iconos del checkbox (opcional)
        this.setIcon(crearIconoCheck(false));
        this.setSelectedIcon(crearIconoCheck(true));
        this.setDisabledIcon(crearIconoCheck(false));
    }
    
    private Icon crearIconoCheck(boolean seleccionado) {
        // Crear un icono personalizado simple
        return new Icon() {
            @Override
            public void paintIcon(Component c, Graphics g, int x, int y) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Dibujar el cuadro
                g2.setColor(seleccionado ? new Color(0, 120, 215) : Color.GRAY);
                g2.drawRect(x, y, 13, 13);
                
                if (seleccionado) {
                    // Dibujar la palomita
                    g2.setStroke(new BasicStroke(2));
                    g2.drawLine(x + 3, y + 7, x + 6, y + 10);
                    g2.drawLine(x + 6, y + 10, x + 11, y + 3);
                }
                
                g2.dispose();
            }
            
            @Override
            public int getIconWidth() {
                return 15;
            }
            
            @Override
            public int getIconHeight() {
                return 15;
            }
        };
    }
    
    private void configurarEventos() {
        // Cambiar el color al pasar el mouse
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setBackground(new Color(220, 220, 220));
            }
            
            public void mouseExited(java.awt.event.MouseEvent evt) {
                setBackground(new Color(240, 240, 240));
            }
        });
        
        // Efecto visual al cambiar estado
        this.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                setForeground(new Color(0, 80, 160));
            } else {
                setForeground(new Color(50, 50, 50));
            }
        });
    }
    
    // Método para resaltar checkboxes de productos escasos
    public void marcarProductoEscaso(boolean esEscaso) {
        if (esEscaso) {
            this.setForeground(Color.RED);
            this.setFont(new Font("Segoe UI", Font.BOLD, 12));
        } else {
            this.setForeground(new Color(50, 50, 50));
            this.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        }
    }
}