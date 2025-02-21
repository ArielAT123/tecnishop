/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba.cosas;

/**
 *
 * @author Ernesto
 */
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class RoundRefreshButton extends JButton {
    private Color backgroundColor = new Color(200, 0, 0); // Color de fondo del botón
    private BufferedImage iconImage; // Imagen del ícono de "reloading"

    public RoundRefreshButton() {
        super();
        setPreferredSize(new Dimension(50, 50)); // Tamaño preferido del botón
        setContentAreaFilled(false); // Hace que el área de contenido no se rellene
        setFocusPainted(false); // Elimina el borde de enfoque
        setBorderPainted(false); // Elimina el borde pintado
        setOpaque(false); // Hace que el botón sea transparente

        // Cargar la imagen del ícono de "reloading"
        try {
            iconImage = ImageIO.read(getClass().getResource("/prueba/imagenes/reload.png"));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("No se pudo cargar la imagen del ícono.");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        // Activar antialiasing para bordes suaves
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Dibujar el fondo circular
        g2.setColor(backgroundColor);
        g2.fill(new Ellipse2D.Double(0, 0, getWidth(), getHeight()));

        // Dibujar el ícono de "reloading" si la imagen está cargada
        if (iconImage != null) {
            // Escalar la imagen al tamaño del botón
            int buttonWidth = getWidth();
            int buttonHeight = getHeight();
            Image scaledImage = iconImage.getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);

            // Dibujar la imagen escalada en el centro del botón
            int x = (buttonWidth - scaledImage.getWidth(null)) / 2;
            int y = (buttonHeight - scaledImage.getHeight(null)) / 2;
            g2.drawImage(scaledImage, x, y, null);
        }

        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        // No pintar ningún borde
    }

    @Override
    public boolean contains(int x, int y) {
        // Hacer que el botón sea circular en términos de interacción
        Shape shape = new Ellipse2D.Double(0, 0, getWidth(), getHeight());
        return shape.contains(x, y);
    }
}