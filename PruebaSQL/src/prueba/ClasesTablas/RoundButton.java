/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba.ClasesTablas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.JButton;

/**
 *
 * @author Ernesto
 */
public class RoundButton extends JButton {
        public RoundButton(String label) {
            super(label);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setOpaque(false);
            setBackground(new Color(100, 150, 200)); // Color de fondo
            setForeground(Color.WHITE); // Color del texto
            setFont(new Font("Arial", Font.BOLD, 14));
        }
        public RoundButton(){}

        @Override
        protected void paintComponent(Graphics g) {
            if (getModel().isArmed()) {
                g.setColor(new Color(80, 120, 180)); // Color cuando el botón es presionado
            } else {
                g.setColor(getBackground());
            }
            g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20); // Bordes redondeados
            super.paintComponent(g);
        }

        @Override
        protected void paintBorder(Graphics g) {
            g.setColor(getBackground());
            g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20); // Bordes redondeados
        }
    }


