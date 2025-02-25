/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package prueba;

import prueba.cosas.RoundRedButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

/**
 *
 * @author Ernesto
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JFrame;
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
        nombreProducto = new javax.swing.JTextField();
        descripcionProducto = new javax.swing.JTextField();
        costoCompraProducto = new javax.swing.JTextField();
        porcentajeGananciaProducto = new javax.swing.JTextField();
        impuestoProducto = new javax.swing.JTextField();
        cantidadProducto = new javax.swing.JTextField();
        codigo_producto = new javax.swing.JTextField(); // Nuevo JTextField para Código Producto
        jLabel1 = new javax.swing.JLabel("Nombre:");
        jLabel2 = new javax.swing.JLabel("Descripción:");
        jLabel3 = new javax.swing.JLabel("Costo Compra:");
        jLabel4 = new javax.swing.JLabel("Porcentaje Ganancia:");
        jLabel5 = new javax.swing.JLabel("Impuesto:");
        jLabel6 = new javax.swing.JLabel("Cantidad:");
        jLabel7 = new javax.swing.JLabel("Código Producto:"); // Nueva etiqueta
        btnRegresarMenu = new RoundRedButton("Regresar al Menú"); 

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registro Producto");

        jPanel1.setBackground(Color.WHITE);
        jPanel1.setBorder(new LineBorder(new Color(200, 0, 0), 2, true));

        // Configurar campos de texto
        configurarCampoTexto(nombreProducto, "Ingrese nombre");
        configurarCampoTexto(descripcionProducto, "Ingrese descripción");
        configurarCampoTexto(costoCompraProducto, "Ingrese costo ");
        configurarCampoTexto(porcentajeGananciaProducto, "Ingrese % ganancia");
        configurarCampoTexto(impuestoProducto, "Ingrese IVA");
        configurarCampoTexto(cantidadProducto, "Ingrese cantidad");
        configurarCampoTexto(codigo_producto, "Ingrese código"); // Nuevo campo

        // Agregar eventos de focus
        agregarEventoFocus(nombreProducto, "Ingrese nombre");
        agregarEventoFocus(descripcionProducto, "Ingrese descripción");
        agregarEventoFocus(costoCompraProducto, "Ingrese costo ");
        agregarEventoFocus(porcentajeGananciaProducto, "Ingrese % ganancia");
        agregarEventoFocus(impuestoProducto, "Ingrese IVA");
        agregarEventoFocus(cantidadProducto, "Ingrese cantidad");
        agregarEventoFocus(codigo_producto, "Ingrese código"); // Nuevo campo

        // Configurar etiquetas
        configurarEtiqueta(jLabel1);
        configurarEtiqueta(jLabel2);
        configurarEtiqueta(jLabel3);
        configurarEtiqueta(jLabel4);
        configurarEtiqueta(jLabel5);
        configurarEtiqueta(jLabel6);
        configurarEtiqueta(jLabel7); // Nueva etiqueta

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
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Agregar componentes al panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        jPanel1.add(jLabel1, gbc);

        gbc.gridx = 1;
        jPanel1.add(nombreProducto, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        jPanel1.add(jLabel2, gbc);

        gbc.gridx = 1;
        jPanel1.add(descripcionProducto, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        jPanel1.add(jLabel3, gbc);

        gbc.gridx = 1;
        jPanel1.add(costoCompraProducto, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        jPanel1.add(jLabel4, gbc);

        gbc.gridx = 1;
        jPanel1.add(porcentajeGananciaProducto, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        jPanel1.add(jLabel5, gbc);

        gbc.gridx = 1;
        jPanel1.add(impuestoProducto, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        jPanel1.add(jLabel6, gbc);

        gbc.gridx = 1;
        jPanel1.add(cantidadProducto, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6; // Nueva fila para Código Producto
        jPanel1.add(jLabel7, gbc);

        gbc.gridx = 1;
        jPanel1.add(codigo_producto, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        jPanel1.add(InsertButton, gbc);

        gbc.gridy = 8;
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
                    campo.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (campo.getText().isEmpty()) {
                    campo.setText(placeholder);
                    campo.setForeground(Color.GRAY);
                }
            }
        });
    }

    private void configurarEtiqueta(JLabel etiqueta) {
        etiqueta.setForeground(new Color(200, 0, 0));
        etiqueta.setFont(new Font("Arial", Font.BOLD, 12));
    }
    
    private void configurarCampoTexto(JTextField campo, String placeholder) {
        campo.setBackground(Color.WHITE);
        campo.setBorder(new LineBorder(new Color(200, 0, 0), 1));
        campo.setForeground(Color.GRAY);
        campo.setText(placeholder);
        campo.setPreferredSize(new Dimension(200, 30));
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
            "\nImpuesto: " + impuesto,
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
    private javax.swing.JButton btnRegresarMenu; // Botón para regresar al menú
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

