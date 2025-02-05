/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package prueba;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

/**
 *
 * @author Ernesto
 */
public class VentanaAgregarCLienteJFrame extends javax.swing.JFrame {
    private String nombre, apellido, correo, telefono, ci;
    
    /**
     * Creates new form VentanaAgregarCLienteJFrame
     */
    public VentanaAgregarCLienteJFrame() {
        initComponents();
    }
/*
    @SuppressWarnings("checked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        InsertButton = new javax.swing.JButton();
        nombreCliente = new javax.swing.JTextField();
        apellidoCliente = new javax.swing.JTextField();
        correoCliente = new javax.swing.JTextField();
        telefonoCliente = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        InsertButton.setText("Insertar");

        nombreCliente.setText("Nombre Cliente");
        nombreCliente.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                nombreClienteInputMethodTextChanged(evt);
            }
        });
        nombreCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreClienteActionPerformed(evt);
            }
        });

        apellidoCliente.setText("apellido");
        apellidoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apellidoClienteActionPerformed(evt);
            }
        });

        correoCliente.setText("correo");

        telefonoCliente.setText("telefono");

        jLabel1.setText("Nombre");

        jLabel2.setText("Apellido");

        jLabel3.setText("Correo");

        jLabel4.setText("Telefono");

        jLabel5.setText("CI");

        jTextField1.setText("cedula");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(InsertButton))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nombreCliente))
                        .addGap(47, 47, 47)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(apellidoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(correoCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE))
                        .addGap(57, 57, 57)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(telefonoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(63, 63, 63)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(99, 99, 99)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)))))
                .addContainerGap(108, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(apellidoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(correoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(telefonoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 123, Short.MAX_VALUE)
                .addComponent(InsertButton)
                .addGap(93, 93, 93))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void apellidoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apellidoClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_apellidoClienteActionPerformed

    private void nombreClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreClienteActionPerformed

    private void nombreClienteInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_nombreClienteInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreClienteInputMethodTextChanged
*/private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        InsertButton = new javax.swing.JButton("Insertar");
        nombreCliente = new javax.swing.JTextField();
        apellidoCliente = new javax.swing.JTextField();
        correoCliente = new javax.swing.JTextField();
        telefonoCliente = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel("Nombre:");
        jLabel2 = new javax.swing.JLabel("Apellido:");
        jLabel3 = new javax.swing.JLabel("Correo:");
        jLabel4 = new javax.swing.JLabel("Teléfono:");
        jLabel5 = new javax.swing.JLabel("CI:");
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Registro Cliente");

        // Estilo del Panel
        jPanel1.setBackground(new Color(240, 248, 255));  // Azul muy claro
        jPanel1.setBorder(new LineBorder(new Color(100, 149, 237), 2, true));

        // Configuración de los JTextField
        configurarCampoTexto(nombreCliente, "Ingrese nombre");
        configurarCampoTexto(apellidoCliente, "Ingrese apellido");
        configurarCampoTexto(correoCliente, "Ingrese correo");
        configurarCampoTexto(telefonoCliente, "Ingrese teléfono");
        configurarCampoTexto(jTextField1, "Ingrese CI");
        // Agregar el evento de focus a los JTextField
        agregarEventoFocus(nombreCliente, "Ingrese nombre");
        agregarEventoFocus(apellidoCliente, "Ingrese apellido");
        agregarEventoFocus(correoCliente, "Ingrese correo");
        agregarEventoFocus(telefonoCliente, "Ingrese teléfono");
        agregarEventoFocus(jTextField1, "Ingrese CI");


        // Configuración de los JLabel
        configurarEtiqueta(jLabel1);
        configurarEtiqueta(jLabel2);
        configurarEtiqueta(jLabel3);
        configurarEtiqueta(jLabel4);
        configurarEtiqueta(jLabel5);

        // Botón Insertar Estilizado
        InsertButton.setFont(new Font("Arial", Font.BOLD, 14));
        InsertButton.setBackground(new Color(70, 130, 180));  // Azul oscuro
        InsertButton.setForeground(Color.WHITE);
        InsertButton.setBorder(new LineBorder(new Color(25, 25, 112), 2, true));

        // Evento para capturar los datos al presionar el botón
        InsertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarDatos();
                prueba.insertCliente(nombre, apellido,  telefono,  correo,  ci);
                resetearCampos();
            }
        });

        // Diseño del Panel
        GroupLayout layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(nombreCliente, 150, 150, 150))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addComponent(apellidoCliente, 150, 150, 150))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3)
                        .addComponent(correoCliente, 150, 150, 150))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel4)
                        .addComponent(telefonoCliente, 150, 150, 150))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5)
                        .addComponent(jTextField1, 150, 150, 150)))
                .addComponent(InsertButton, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(nombreCliente, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                    .addComponent(apellidoCliente, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                    .addComponent(correoCliente, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                    .addComponent(telefonoCliente, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                .addGap(20)
                .addComponent(InsertButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
        );

        getContentPane().add(jPanel1);
        pack();
        setLocationRelativeTo(null);  // Centrar la ventana
    }

        // Método para agregar evento de FocusListener a los JTextField
    private void agregarEventoFocus(JTextField campo, String placeholder) {
        campo.addFocusListener(new java.awt.event.FocusListener() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (campo.getText().equals(placeholder)) {
                    campo.setText(""); // Borra el texto cuando hace clic
                    campo.setForeground(Color.BLACK); // Cambia el color del texto a negro
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (campo.getText().trim().isEmpty()) {
                    campo.setText(placeholder); // Restaura el texto si está vacío
                    campo.setForeground(Color.GRAY); // Mantiene el color gris
                }
            }
        });
    }


    // Método para capturar los datos cuando se presiona "Insertar"
    private void guardarDatos() {
        nombre = nombreCliente.getText();
        apellido = apellidoCliente.getText();
        correo = correoCliente.getText();
        telefono = telefonoCliente.getText();
        ci = jTextField1.getText();

        JOptionPane.showMessageDialog(this, 
            "Datos Guardados:\nNombre: " + nombre + "\nApellido: " + apellido +
            "\nCorreo: " + correo + "\nTeléfono: " + telefono + "\nCI: " + ci,
            "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    // Métodos para configurar los JTextField y JLabel
    private void configurarCampoTexto(JTextField campo, String placeholder) {
        campo.setText(placeholder);
        campo.setFont(new Font("Arial", Font.PLAIN, 14));
        campo.setForeground(Color.GRAY);
        campo.setBorder(new LineBorder(new Color(100, 149, 237), 2, true));
    }

    private void configurarEtiqueta(JLabel etiqueta) {
        etiqueta.setFont(new Font("Arial", Font.BOLD, 14));
        etiqueta.setForeground(new Color(25, 25, 112));
    }
    // Método para restablecer los campos de texto a sus valores iniciales
    private void resetearCampos() {
        nombreCliente.setText("Ingrese nombre");
        nombreCliente.setForeground(Color.GRAY);

        apellidoCliente.setText("Ingrese apellido");
        apellidoCliente.setForeground(Color.GRAY);

        correoCliente.setText("Ingrese correo");
        correoCliente.setForeground(Color.GRAY);

        telefonoCliente.setText("Ingrese teléfono");
        telefonoCliente.setForeground(Color.GRAY);

        jTextField1.setText("Ingrese CI");
        jTextField1.setForeground(Color.GRAY);
}

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaAgregarCLienteJFrame().setVisible(true);
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton InsertButton;
    private javax.swing.JTextField apellidoCliente;
    private javax.swing.JTextField correoCliente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField nombreCliente;
    private javax.swing.JTextField telefonoCliente;
    // End of variables declaration//GEN-END:variables
    private pruebaSQL prueba;
}
