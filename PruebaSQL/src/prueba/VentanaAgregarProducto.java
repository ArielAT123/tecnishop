/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package prueba;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

/**
 *
 * @author Ernesto
 */
public class VentanaAgregarProducto extends javax.swing.JFrame {

    String nombre;
    String descripcion;
    double costoCompra;
    double porcentajeGanancia;
    double impuesto;
    int cantidad;

    private MenuFrame menuFrame; // Referencia al menú principal

    // Constructor que recibe la referencia al menú principal
    public VentanaAgregarProducto(MenuFrame menuFrame) {
        this.menuFrame = menuFrame; // Asignar la referencia
        initComponents();
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text) {
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
        };
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(200, 0, 0));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        return button;
    }

    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        InsertButton = createStyledButton("Insertar");
        nombreProducto = new javax.swing.JTextField();
        descripcionProducto = new javax.swing.JTextField();
        costoCompraProducto = new javax.swing.JTextField();
        porcentajeGananciaProducto = new javax.swing.JTextField();
        impuestoProducto = new javax.swing.JTextField();
        cantidadProducto = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel("Nombre:");
        jLabel2 = new javax.swing.JLabel("Descripción:");
        jLabel3 = new javax.swing.JLabel("Costo Compra:");
        jLabel4 = new javax.swing.JLabel("Porcentaje Ganancia:");
        jLabel5 = new javax.swing.JLabel("Impuesto:");
        jLabel6 = new javax.swing.JLabel("Cantidad:");
        btnRegresarMenu = createStyledButton("back"); // Botón para regresar al menú

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registro Producto");

        jPanel1.setBackground(Color.WHITE);
        jPanel1.setBorder(new LineBorder(new Color(200, 0, 0), 2, true));

        configurarCampoTexto(nombreProducto, "Ingrese nombre");
        configurarCampoTexto(descripcionProducto, "Ingrese descripción");
        configurarCampoTexto(costoCompraProducto, "Ingrese costo ");
        configurarCampoTexto(porcentajeGananciaProducto, "Ingrese % ganancia");
        configurarCampoTexto(impuestoProducto, "Ingrese IVA");
        configurarCampoTexto(cantidadProducto, "Ingrese cantidad");

        agregarEventoFocus(nombreProducto, "Ingrese nombre");
        agregarEventoFocus(descripcionProducto, "Ingrese descripción");
        agregarEventoFocus(costoCompraProducto, "Ingrese costo ");
        agregarEventoFocus(porcentajeGananciaProducto, "Ingrese % ganancia");
        agregarEventoFocus(impuestoProducto, "Ingrese IVA");
        agregarEventoFocus(cantidadProducto, "Ingrese cantidad");

        configurarEtiqueta(jLabel1);
        configurarEtiqueta(jLabel2);
        configurarEtiqueta(jLabel3);
        configurarEtiqueta(jLabel4);
        configurarEtiqueta(jLabel5);
        configurarEtiqueta(jLabel6);

        InsertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarDatos();
                JOptionPane.showMessageDialog(null, "Producto guardado correctamente.");
                resetearCampos();
            }
        });

        // Acción para el botón "Regresar al Menú"
        btnRegresarMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false); // Oculta la ventana actual
                menuFrame.setVisible(true); // Muestra el menú principal
            }
        });

        GroupLayout layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(nombreProducto, 150, 150, 150))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addComponent(descripcionProducto, 150, 150, 150))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3)
                        .addComponent(costoCompraProducto, 150, 150, 150))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel4)
                        .addComponent(porcentajeGananciaProducto, 150, 150, 150))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5)
                        .addComponent(impuestoProducto, 150, 150, 150))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel6)
                        .addComponent(cantidadProducto, 150, 150, 150)))
                .addComponent(InsertButton, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                .addComponent(btnRegresarMenu, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(nombreProducto, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                    .addComponent(descripcionProducto, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                    .addComponent(costoCompraProducto, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                    .addComponent(porcentajeGananciaProducto, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                    .addComponent(impuestoProducto, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                    .addComponent(cantidadProducto, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                .addGap(20)
                .addComponent(InsertButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                .addGap(10)
                .addComponent(btnRegresarMenu, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
        );

        getContentPane().add(jPanel1);
        pack();
        setLocationRelativeTo(null);
    }

    private void configurarCampoTexto(JTextField campo, String placeholder) {
        campo.setBackground(Color.WHITE);
        campo.setBorder(new LineBorder(new Color(200, 0, 0), 1));
        campo.setForeground(Color.GRAY);
        campo.setText(placeholder);
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

    private void guardarDatos() {
        nombre = nombreProducto.getText();
        descripcion = descripcionProducto.getText();
        costoCompra = Double.parseDouble(costoCompraProducto.getText());
        cantidad = Integer.parseInt(cantidadProducto.getText());
        porcentajeGanancia = Double.parseDouble(porcentajeGananciaProducto.getText());
        impuesto = Double.parseDouble(impuestoProducto.getText());
        JOptionPane.showMessageDialog(this,
                "Datos Guardados:\nNombre: " + nombre + "\nDescripción: " + descripcion +
                        "\nPrecio: " + costoCompra + "\nStock: " + cantidad,
                "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    private void resetearCampos() {
        nombreProducto.setText("Ingrese nombre");
        nombreProducto.setForeground(Color.GRAY);
        descripcionProducto.setText("Ingrese descripción");
        descripcionProducto.setForeground(Color.GRAY);
        costoCompraProducto.setText("Ingrese costo de compra");
        costoCompraProducto.setForeground(Color.GRAY);
        porcentajeGananciaProducto.setText("Ingrese porcentaje de ganancia");
        porcentajeGananciaProducto.setForeground(Color.GRAY);
        impuestoProducto.setText("Ingrese impuesto");
        impuestoProducto.setForeground(Color.GRAY);
        cantidadProducto.setText("Ingrese cantidad");
        cantidadProducto.setForeground(Color.GRAY);
    }

    private javax.swing.JButton InsertButton;
    private javax.swing.JButton btnRegresarMenu; // Botón para regresar al menú
    private javax.swing.JTextField nombreProducto;
    private javax.swing.JTextField descripcionProducto;
    private javax.swing.JTextField costoCompraProducto;
    private javax.swing.JTextField porcentajeGananciaProducto;
    private javax.swing.JTextField impuestoProducto;
    private javax.swing.JTextField cantidadProducto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
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

