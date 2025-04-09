/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ClasesFrames;

import prueba.cosas.Utilitaria;
import SQL_Clases.pruebaSQL;
import prueba.cosas.RoundRedButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import prueba.cosas.ModernFrame;

public class VentanaAgregarProducto extends ModernFrame {

    String nombre;
    String descripcion;
    Double costoCompra;
    Double porcentajeGanancia;
    Double impuesto;
    Integer cantidad;
    String codigo;

    private JFrame previus; // Referencia al menú principal

    // Constructor que recibe la referencia al menú principal
    public VentanaAgregarProducto(MenuFrame menuFrame) {
        super();
        this.previus = menuFrame; // Asignar la referencia
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
    }

    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        InsertButton = new RoundRedButton("Insertar");
        btnRegresarMenu = new RoundRedButton("Regresar al Menú");
        nombreProducto = new javax.swing.JTextField();
        descripcionProducto = new javax.swing.JTextField();
        costoCompraProducto = new javax.swing.JTextField();
        porcentajeGananciaProducto = new javax.swing.JTextField();
        impuestoProducto = new javax.swing.JTextField();
        cantidadProducto = new javax.swing.JTextField();
        codigo_producto = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel("Nombre:");
        jLabel2 = new javax.swing.JLabel("Descripción:");
        jLabel3 = new javax.swing.JLabel("Costo Compra:");
        jLabel4 = new javax.swing.JLabel("Porcentaje Ganancia:");
        jLabel5 = new javax.swing.JLabel("Impuesto:");
        jLabel6 = new javax.swing.JLabel("Cantidad:");
        jLabel7 = new javax.swing.JLabel("Código Producto:");
        titleLabel = new javax.swing.JLabel("Registrar Nuevo Producto");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registro Producto");

        // Estilo del Panel principal
        jPanel1.setBackground(Color.BLACK); // Fondo negro
        jPanel1.setBorder(new LineBorder(Color.LIGHT_GRAY, 2)); // Borde gris claro

        // Estilo para las etiquetas y campos de texto
        Font labelFont = new Font("Arial", Font.BOLD, 14); // Negrita para etiquetas
        Font textFont = new Font("Arial", Font.BOLD, 14); // Negrita para campos de texto
        Font buttonFont = new Font("Arial", Font.BOLD, 12); // Negrita para botones
        Color labelColor = Color.WHITE; // Texto blanco
        Color textBackground = new Color(50, 50, 50); // Fondo gris oscuro para campos de texto
        Color textForeground = Color.WHITE; // Texto blanco para campos de texto

        // Configuración del título
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Título más grande y en negrita
        titleLabel.setForeground(labelColor);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        // Configuración de los JLabel
        configurarEtiqueta(jLabel1, labelFont, labelColor);
        configurarEtiqueta(jLabel2, labelFont, labelColor);
        configurarEtiqueta(jLabel3, labelFont, labelColor);
        configurarEtiqueta(jLabel4, labelFont, labelColor);
        configurarEtiqueta(jLabel5, labelFont, labelColor);
        configurarEtiqueta(jLabel6, labelFont, labelColor);
        configurarEtiqueta(jLabel7, labelFont, labelColor);

        // Configuración de los JTextField
        configurarCampoTexto(nombreProducto, "Ingrese nombre", textFont, textBackground, textForeground);
        configurarCampoTexto(descripcionProducto, "Ingrese descripción", textFont, textBackground, textForeground);
        configurarCampoTexto(costoCompraProducto, "Ingrese costo", textFont, textBackground, textForeground);
        configurarCampoTexto(porcentajeGananciaProducto, "Ingrese % ganancia", textFont, textBackground, textForeground);
        configurarCampoTexto(impuestoProducto, "Ingrese IVA", textFont, textBackground, textForeground);
        configurarCampoTexto(cantidadProducto, "Ingrese cantidad", textFont, textBackground, textForeground);
        configurarCampoTexto(codigo_producto, "Ingrese código", textFont, textBackground, textForeground);

        // Agregar eventos de focus
        agregarEventoFocus(nombreProducto, "Ingrese nombre");
        agregarEventoFocus(descripcionProducto, "Ingrese descripción");
        agregarEventoFocus(costoCompraProducto, "Ingrese costo");
        agregarEventoFocus(porcentajeGananciaProducto, "Ingrese % ganancia");
        agregarEventoFocus(impuestoProducto, "Ingrese IVA");
        agregarEventoFocus(cantidadProducto, "Ingrese cantidad");
        agregarEventoFocus(codigo_producto, "Ingrese código");
        

        // Acción para el botón "Insertar"
        InsertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarDatos();
                pruebaSQL.insertProducto(nombre, descripcion, costoCompra, porcentajeGanancia, impuesto, cantidad, codigo);
                JOptionPane.showMessageDialog(null, "Producto guardado correctamente.");
                resetearCampos();
            }
        });

        // Acción para el botón "Regresar al Menú"
        btnRegresarMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                previus.setVisible(true);
            }
        });

        // Usar GridBagLayout para un mejor control de la disposición
        jPanel1.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaciado entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Título en la parte superior
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Ocupar dos columnas
        gbc.anchor = GridBagConstraints.CENTER; // Centrar el título
        jPanel1.add(titleLabel, gbc);

        // Campos de texto y etiquetas
        gbc.gridwidth = 1; // Restablecer a una columna
        gbc.anchor = GridBagConstraints.EAST; // Alinear etiquetas a la derecha
        gbc.gridx = 0;
        gbc.gridy = 1;
        jPanel1.add(jLabel1, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST; // Alinear campos a la izquierda
        jPanel1.add(nombreProducto, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        jPanel1.add(jLabel2, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        jPanel1.add(descripcionProducto, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        jPanel1.add(jLabel3, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        jPanel1.add(costoCompraProducto, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        jPanel1.add(jLabel4, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        jPanel1.add(porcentajeGananciaProducto, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.EAST;
        jPanel1.add(jLabel5, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        jPanel1.add(impuestoProducto, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.EAST;
        jPanel1.add(jLabel6, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        jPanel1.add(cantidadProducto, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.EAST;
        jPanel1.add(jLabel7, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        jPanel1.add(codigo_producto, gbc);

        // Botones centrados
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2; // Ocupar dos columnas
        gbc.fill = GridBagConstraints.NONE; // No ajustar el tamaño del botón
        gbc.anchor = GridBagConstraints.CENTER; // Centrar el botón
        jPanel1.add(InsertButton, gbc);

        gbc.gridy = 9;
        jPanel1.add(btnRegresarMenu, gbc);

        getContentPane().add(jPanel1);
        pack();
    }

    private void agregarEventoFocus(JTextField campo, String placeholder) {
        campo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (campo.getText().equals(placeholder)) {
                    campo.setText("");
                    campo.setForeground(Color.WHITE); // Texto blanco
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (campo.getText().isEmpty()) {
                    campo.setText(placeholder);
                    campo.setForeground(Color.GRAY); // Placeholder en gris
                }
            }
        });
    }

    private void configurarEtiqueta(JLabel etiqueta, Font font, Color color) {
        etiqueta.setFont(font); // Negrita
        etiqueta.setForeground(color); // Texto blanco
    }

    private void configurarCampoTexto(JTextField campo, String placeholder, Font font, Color background, Color foreground) {
        campo.setText(placeholder);
        campo.setFont(font); // Negrita
        campo.setForeground(Color.GRAY); // Placeholder en gris
        campo.setBackground(background); // Fondo gris oscuro
        campo.setBorder(new LineBorder(Color.LIGHT_GRAY, 2, true)); // Borde gris claro
        campo.setPreferredSize(new Dimension(200, 30)); // Tamaño preferido
    }

    private void guardarDatos() {
        nombre = nombreProducto.getText();
        descripcion = descripcionProducto.getText();
        codigo = codigo_producto.getText();
        costoCompra = validarCostoCompra();
        porcentajeGanancia = validarPorcentajeGanancia();
        impuesto = validarImpuesto();
        cantidad = validarCantidad();

        JOptionPane.showMessageDialog(this,
                "Datos Guardados:\nNombre: " + nombre +
                        "\nDescripción: " + descripcion +
                        "\nCosto de Compra: " + costoCompra +
                        "\nCantidad: " + cantidad +
                        "\nPorcentaje de Ganancia: " + porcentajeGanancia +
                        "\nImpuesto: " + impuesto +
                        "\nCódigo: " + codigo,
                "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    private void resetearCampos() {
        nombreProducto.setText("Ingrese nombre");
        nombreProducto.setForeground(Color.GRAY);

        descripcionProducto.setText("Ingrese descripción");
        descripcionProducto.setForeground(Color.GRAY);

        costoCompraProducto.setText("Ingrese costo");
        costoCompraProducto.setForeground(Color.GRAY);

        porcentajeGananciaProducto.setText("Ingrese % ganancia");
        porcentajeGananciaProducto.setForeground(Color.GRAY);

        impuestoProducto.setText("Ingrese IVA");
        impuestoProducto.setForeground(Color.GRAY);

        cantidadProducto.setText("Ingrese cantidad");
        cantidadProducto.setForeground(Color.GRAY);

        codigo_producto.setText("Ingrese código");
        codigo_producto.setForeground(Color.GRAY);
    }

    private Double validarCostoCompra() {
        String texto = costoCompraProducto.getText();
        if (Utilitaria.esNumeroValido(texto)) {
            double valor = Double.parseDouble(texto);
            return (valor >= 0) ? valor : null;
        }
        return null;
    }

    private Double validarPorcentajeGanancia() {
        String texto = porcentajeGananciaProducto.getText();
        if (Utilitaria.esNumeroValido(texto)) {
            double valor = Double.parseDouble(texto);
            return (valor >= 0 && valor < 1) ? valor : null;
        }
        return null;
    }

    private Double validarImpuesto() {
        String texto = impuestoProducto.getText();
        if (Utilitaria.esNumeroValido(texto)) {
            double valor = Double.parseDouble(texto);
            return (valor >= 0 && valor < 1) ? valor : null;
        }
        return null;
    }

    private Integer validarCantidad() {
        String texto = cantidadProducto.getText();
        if (Utilitaria.esEnteroValido(texto)) {
            int valor = Integer.parseInt(texto);
            return (valor >= 0) ? valor : null;
        }
        return null;
    }

    private javax.swing.JButton InsertButton;
    private javax.swing.JButton btnRegresarMenu;
    private javax.swing.JTextField nombreProducto;
    private javax.swing.JTextField descripcionProducto;
    private javax.swing.JTextField costoCompraProducto;
    private javax.swing.JTextField porcentajeGananciaProducto;
    private javax.swing.JTextField impuestoProducto;
    private javax.swing.JTextField cantidadProducto;
    private javax.swing.JTextField codigo_producto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JPanel jPanel1;
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    /*@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    */
// Método para restablecer los campos de texto a sus valores iniciales
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

