/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba.cosas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;

/**
 *
 * @author Ernesto
 */


public class RoundRedButton extends JButton {
    public RoundRedButton(String text) {
        super(text);
        setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        setBackground(new Color(200, 0, 0));
        setForeground(Color.WHITE);
        setFocusPainted(false);
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        setContentAreaFilled(false);
        setOpaque(false);

        // Agregar el MouseListener para cambiar el color cuando el mouse entra y sale
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(new Color(220, 20, 20));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(new Color(200, 0, 0));
            }
        });
    }

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
}