package ClasesDatos.CardObjects;

import ClasesDatos.ProductoInventario;
import prueba.cosas.RoundRedButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Image;
import java.io.IOException;

public class ProductCard extends Card<ProductoInventario> {
    private boolean tieneImagen;
    private JButton editButton;
    private JButton insertarImagenButton;
    private JLabel imageLabel;

    public ProductCard(ProductoInventario producto) {
        super(producto, null);
        
        // Obtener imagen redimensionada (200x150 px)
        ImageIcon productImage = producto.getImageIcon(200, 150);
        if (productImage == null) {
            tieneImagen = false;
            productImage = createDefaultImageIcon();
        } else {
            tieneImagen = true;
        }
        
        this.imageLabel = new JLabel(productImage);
        this.nameLabel = new JLabel(producto.getNombre(), SwingConstants.CENTER);
        this.anotherLable = new JLabel(String.format("$%.2f", producto.getPrecioVentaRecomendado()), 
                                     SwingConstants.CENTER);

        // Botón de Editar con el ícono editIcon.png
        editButton = new JButton(createEditIcon());
        editButton.setToolTipText("Editar Imagen");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showInsertImageButton(); // Mostrar el botón "Insertar Imagen" al hacer clic en Editar
            }
        });
        add(editButton, BorderLayout.PAGE_START);

        // Botón "Insertar Imagen" (inicialmente no se agrega al panel)
        insertarImagenButton = new RoundRedButton("Insertar Imagen");
        insertarImagenButton.setVisible(false);
        insertarImagenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertImage(); // Insertar la imagen y ocultar el botón
            }
        });

        add(this.imageLabel, BorderLayout.CENTER);
        add(nameLabel, BorderLayout.NORTH);
        add(anotherLable, BorderLayout.SOUTH);
    }

    // Crear el ícono para el botón de Editar
    private ImageIcon createEditIcon() {
        try {
            File iconFile = new File("src/resources/editIcon.png");
            BufferedImage iconImage = ImageIO.read(iconFile);
            Image scaledImage = iconImage.getScaledInstance(24, 24, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        } catch (Exception e) {
            return new ImageIcon(); // Retorna un ícono vacío si falla
        }
    }


    private ImageIcon createDefaultImageIcon() {
        try {
            File imageFile = new File("src/resources/defaultProduct.png");
            BufferedImage originalImage = ImageIO.read(imageFile);
            Image scaledImage = originalImage.getScaledInstance(200, 150, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        } catch (IOException e) {
            BufferedImage image = new BufferedImage(200, 150, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = image.createGraphics();
            g2d.setColor(Color.LIGHT_GRAY);
            g2d.fillRect(0, 0, 200, 150);
            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("Arial", Font.BOLD, 14));
            String text = "Sin imagen";
            FontMetrics fm = g2d.getFontMetrics();
            int x = (200 - fm.stringWidth(text)) / 2;
            int y = (150 - fm.getHeight()) / 2 + fm.getAscent();
            g2d.drawString(text, x, y);
            g2d.dispose();
            return new ImageIcon(image);
        }
    }

    // Mostrar el botón "Insertar Imagen"
    private void showInsertImageButton() {
        if (!insertarImagenButton.isVisible()) {
            add(insertarImagenButton, BorderLayout.PAGE_END); // Agregar el botón al panel
            insertarImagenButton.setVisible(true); // Hacerlo visible
            revalidate(); // Actualizar el layout
            repaint(); // Redibujar el panel
        }
    }

    // Insertar una nueva imagen
    private void insertImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Imágenes", "jpg", "jpeg", "png"));

        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            try {
                File file = fileChooser.getSelectedFile();
                BufferedImage imagen = ImageIO.read(file);
                String base64 = ProductoInventario.imageToBase64(new ImageIcon(imagen));
                elemento.setImagenBase64(base64);

                // Actualizar la etiqueta de imagen con la nueva imagen
                ImageIcon newImage = elemento.getImageIcon(200, 150);
                if (newImage != null) {
                    imageLabel.setIcon(newImage);
                    tieneImagen = true;
                }

                // Ocultar y remover el botón "Insertar Imagen"
                insertarImagenButton.setVisible(false);
                remove(insertarImagenButton);
                revalidate();
                repaint();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    // Getter para acceder al ProductoInventario subyacente
    public ProductoInventario getProducto() {
        return elemento;
    }
}