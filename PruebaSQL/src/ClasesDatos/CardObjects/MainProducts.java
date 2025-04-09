package ClasesDatos.CardObjects;

import ClasesDatos.ProductoInventario;
import SQL_Clases.SQLConsultas;
import prueba.cosas.ModernFrame;
import prueba.cosas.RoundRedButton;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainProducts extends ModernFrame {
    private List<ProductCard> productos = new ArrayList<>();
    private List<ProductCard> productosFiltrados = new ArrayList<>(); // Lista para los productos filtrados
    private JPanel cardsPanel;
    private int currentPage = 1;
    private static final int MIN_CARDS_PER_ROW = 5; // Minimum cards per row
    private int numero_pagina = 0;
    private JTextField filtroNombreField; // Campo de texto para el filtro

    public MainProducts() {
        super();
        setTitle("Gestión de Productos - Tecnishop");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Pantalla completa

        // Customize header
        JPanel headerPanel = (JPanel) getContentPane().getComponent(0);
        
        // Customize content panel
        JPanel contentPanel = (JPanel) getContentPane().getComponent(1);
        contentPanel.setLayout(new BorderLayout());

        // Panel superior con botones y filtro
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Botón Anterior
        JButton btnAnterior = new RoundRedButton("Anterior");
        btnAnterior.addActionListener(e -> {
            if (currentPage > 1) {
                currentPage--;
                cargarProductos(currentPage);
            }
        });

        // Botón Siguiente
        JButton btnSiguiente = new RoundRedButton("Siguiente");
        btnSiguiente.addActionListener(e -> {
            currentPage++;
            cargarProductos(currentPage);
        });

        // Botón Agregar
        JButton btnAgregar = new RoundRedButton("Agregar Producto");
        btnAgregar.addActionListener(new AgregarProductoListener());

        // Campo de texto para el filtro por nombre
        filtroNombreField = new JTextField("Filtrar por nombre...", 20);
        JButton btnFiltrar = new RoundRedButton("Filtrar");
        btnFiltrar.addActionListener(e -> aplicarFiltro());

        // Añadir componentes al topPanel
        topPanel.add(btnAnterior);
        topPanel.add(Box.createHorizontalStrut(10));
        topPanel.add(btnSiguiente);
        topPanel.add(Box.createHorizontalStrut(10));
        topPanel.add(filtroNombreField);
        topPanel.add(Box.createHorizontalStrut(10));
        topPanel.add(btnFiltrar);
        topPanel.add(Box.createHorizontalGlue());
        topPanel.add(btnAgregar);

        contentPanel.add(topPanel, BorderLayout.NORTH);

        // Panel principal para las cards
        cardsPanel = new JPanel();
        cardsPanel.setLayout(new GridLayout(0, MIN_CARDS_PER_ROW, 15, 15)); // Default to min cards per row

        JScrollPane scrollPane = new JScrollPane(cardsPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        // Panel contenedor para centrar
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        centerPanel.add(scrollPane, gbc);

        contentPanel.add(centerPanel, BorderLayout.CENTER);

        cargarProductos(currentPage);
    }

    private void cargarProductos(int pagina) {
        productos = SQLConsultas.cargarArrayProductos(pagina);
        productosFiltrados = new ArrayList<>(productos); // Inicialmente, la lista filtrada es igual a la original
        actualizarCards();
        for (ProductCard card : productos) {
            if (card != null) {
                System.out.println(card.getProducto());
            }
        }
    }

    private void aplicarFiltro() {
        String filtroNombre = filtroNombreField.getText().trim().toLowerCase();
        if (filtroNombre.isEmpty() || filtroNombre.equals("filtrar por nombre...")) {
            productosFiltrados = new ArrayList<>(productos); // Restaurar la lista original
        } else {
            productosFiltrados = productos.stream()
                .filter(card -> card != null && card.getProducto().getNombre().toLowerCase().contains(filtroNombre))
                .collect(Collectors.toList());
        }
        actualizarCards();
    }

    private void actualizarCards() {
        cardsPanel.removeAll();

        if (productosFiltrados.isEmpty()) {
            cardsPanel.revalidate();
            cardsPanel.repaint();
            return;
        }

        // Calculate the number of cards per row (between 4 and 10)
        int totalProducts = (int) productosFiltrados.stream().filter(p -> p != null).count();
        int cardsPerRow = Math.min(Math.max(totalProducts / 2, MIN_CARDS_PER_ROW), MIN_CARDS_PER_ROW);
        cardsPerRow = Math.max(cardsPerRow, MIN_CARDS_PER_ROW);

        // Update the layout of cardsPanel with the new number of columns (cards per row)
        cardsPanel.setLayout(new GridLayout(0, cardsPerRow, 15, 15)); // 0 rows (dynamic), cardsPerRow columns, 15px gaps

        // Add the product cards
        for (ProductCard card : productosFiltrados) {
            if (card != null) {
                // Configuración de tamaño y alineación
                card.setMaximumSize(new Dimension(200, 300)); // Reduced size to fit more cards
                card.setPreferredSize(new Dimension(200, 300));
                card.setAlignmentX(Component.CENTER_ALIGNMENT);
                
                // Estilos
                card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(200, 0, 0), 1),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)
                ));
                card.setBackground(Color.WHITE);
                
                cardsPanel.add(card);
            }
        }

        cardsPanel.revalidate();
        cardsPanel.repaint();
    }

    private class AgregarProductoListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            
            JTextField txtCodigo = new JTextField(15);
            JTextField txtNombre = new JTextField(15);
            JTextField txtPrecio = new JTextField(15);
            JButton btnImagen = new RoundRedButton("Seleccionar Imagen");
            JLabel lblImagen = new JLabel("No hay imagen seleccionada");
            lblImagen.setForeground(new Color(40, 40, 40));
            
            btnImagen.addActionListener(ev -> {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setAcceptAllFileFilterUsed(false);
                fileChooser.addChoosableFileFilter(
                    new javax.swing.filechooser.FileNameExtensionFilter("Imágenes", "jpg", "jpeg", "png"));
                
                if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    lblImagen.setText(file.getName());
                    lblImagen.putClientProperty("file", file);
                }
            });
            
            panel.add(createLabel("Código:"));
            panel.add(txtCodigo);
            panel.add(createLabel("Nombre:"));
            panel.add(txtNombre);
            panel.add(createLabel("Precio:"));
            panel.add(txtPrecio);
            panel.add(createLabel("Imagen:"));
            panel.add(btnImagen);
            panel.add(new JLabel(""));
            panel.add(lblImagen);
            
            int result = JOptionPane.showConfirmDialog(
                MainProducts.this, panel, "Nuevo Producto", 
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            
            if (result == JOptionPane.OK_OPTION) {
                try {
                    ProductoInventario nuevo = new ProductoInventario(
                        productos.size() + 1,
                        txtCodigo.getText(),
                        txtNombre.getText(),
                        0,
                        0,
                        Double.parseDouble(txtPrecio.getText()),
                        Double.parseDouble(txtPrecio.getText()),
                        0.16,
                        0.20,
                        "Disponible");
                    
                    if (lblImagen.getClientProperty("file") != null) {
                        File imagenFile = (File) lblImagen.getClientProperty("file");
                        BufferedImage imagen = ImageIO.read(imagenFile);
                        String base64 = ProductoInventario.imageToBase64(new ImageIcon(imagen));
                        nuevo.setImagenBase64(base64);
                    }
                    
                    ProductCard nuevaCard = new ProductCard(nuevo);
                    productos.add(nuevaCard);
                    productosFiltrados = new ArrayList<>(productos); // Actualizar la lista filtrada
                    actualizarCards();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(MainProducts.this, 
                        "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        private JLabel createLabel(String text) {
            JLabel label = new JLabel(text);
            label.setFont(new Font("Segoe UI", Font.BOLD, 14));
            label.setForeground(new Color(200, 0, 0));
            return label;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainProducts frame = new MainProducts();
            frame.setVisible(true);
        });
    }
}