/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package prueba;

import prueba.cosas.RoundRedButton;
import prueba.ClasesTablas.LimitFilter;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.text.AbstractDocument;
import prueba.cosas.ModernFrame;

public class VentanaAgregarCLienteJFrame extends ModernFrame {
    protected String nombre, apellido, correo, telefono, ci;
    protected JFrame previus;
    protected javax.swing.JButton btnRegresarMenu;
    protected javax.swing.JButton InsertButton;
    protected javax.swing.JTextField nombreCliente;
    protected javax.swing.JTextField apellidoCliente;
    protected javax.swing.JTextField correoCliente;
    protected javax.swing.JTextField telefonoCliente;
    protected javax.swing.JTextField CI;
    protected javax.swing.JLabel jLabel1;
    protected javax.swing.JLabel jLabel2;
    protected javax.swing.JLabel jLabel3;
    protected javax.swing.JLabel jLabel4;
    protected javax.swing.JLabel jLabel5;
    protected javax.swing.JPanel jPanel1;

    public VentanaAgregarCLienteJFrame(JFrame menuFrame) {
        super();
        this.previus = menuFrame;
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Tamaño de la ventana
        setLocationRelativeTo(null); // Centrar la ventana
    }

    protected boolean isDefault() {
        return (
                nombre.equals("Ingrese nombre")
                        || apellido.equals("Ingrese apellido")
                        || correo.equals("Ingrese correo")
                        || telefono.equals("Ingrese tel")
                        || ci.equals("Ingrese CI")
        );
    }

    protected void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        InsertButton = new RoundRedButton("Insertar");
        nombreCliente = new javax.swing.JTextField();
        apellidoCliente = new javax.swing.JTextField();
        correoCliente = new javax.swing.JTextField();
        telefonoCliente = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel("Nombre:");
        jLabel2 = new javax.swing.JLabel("Apellido:");
        jLabel3 = new javax.swing.JLabel("Correo:");
        jLabel4 = new javax.swing.JLabel("Teléfono:");
        jLabel5 = new javax.swing.JLabel("CI:");
        CI = new javax.swing.JTextField();
        btnRegresarMenu = new RoundRedButton("Regresar al Menú");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registro Cliente");

        // Estilo del Panel
        jPanel1.setBackground(Color.WHITE);
        jPanel1.setBorder(new LineBorder(new Color(200, 0, 0)));

        // Configuración de los JTextField
        configurarCampoTexto(nombreCliente, "Ingrese nombre");
        configurarCampoTexto(apellidoCliente, "Ingrese apellido");
        configurarCampoTexto(correoCliente, "Ingrese correo");
        configurarCampoTexto(telefonoCliente, "Ingrese tl");
        configurarCampoTexto(CI, "Ingrese CI");

        // Agregar el evento de focus a los JTextField
        agregarEventoFocus(nombreCliente, "Ingrese nombre");
        agregarEventoFocus(apellidoCliente, "Ingrese apellido");
        agregarEventoFocus(correoCliente, "Ingrese correo");
        agregarEventoFocus(telefonoCliente, "Ingrese tl");
        agregarEventoFocus(CI, "Ingrese CI");

        // Configuración de los JLabel
        configurarEtiqueta(jLabel1);
        configurarEtiqueta(jLabel2);
        configurarEtiqueta(jLabel3);
        configurarEtiqueta(jLabel4);
        configurarEtiqueta(jLabel5);

        // Limitar el número de caracteres en los campos de texto
        ((AbstractDocument) telefonoCliente.getDocument()).setDocumentFilter(new LimitFilter(10));
        ((AbstractDocument) CI.getDocument()).setDocumentFilter(new LimitFilter(10));

        // Evento para capturar los datos al presionar el botón
        InsertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarDatos();
                if (!isDefault()) {
                    pruebaSQL.insertCliente(nombre, apellido, telefono, correo, ci);
                }
                resetearCampos();
            }
        });

        // Acción para el botón "Regresar al Menú"
        btnRegresarMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false); // Oculta la ventana actual
                previus.setVisible(true); // Muestra el menú principal
            }
        });

        // Usar GridBagLayout para un mejor control de la disposición
        jPanel1.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaciado entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL; // Ajustar componentes horizontalmente

        // Agregar componentes al panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        jPanel1.add(jLabel1, gbc);

        gbc.gridx = 1;
        jPanel1.add(nombreCliente, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        jPanel1.add(jLabel2, gbc);

        gbc.gridx = 1;
        jPanel1.add(apellidoCliente, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        jPanel1.add(jLabel3, gbc);

        gbc.gridx = 1;
        jPanel1.add(correoCliente, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        jPanel1.add(jLabel4, gbc);

        gbc.gridx = 1;
        jPanel1.add(telefonoCliente, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        jPanel1.add(jLabel5, gbc);

        gbc.gridx = 1;
        jPanel1.add(CI, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2; // Ocupar dos columnas
        gbc.fill = GridBagConstraints.NONE; // No ajustar el tamaño del botón
        gbc.anchor = GridBagConstraints.CENTER; // Centrar el botón
        jPanel1.add(InsertButton, gbc);

        gbc.gridy = 6;
        jPanel1.add(btnRegresarMenu, gbc);

        getContentPane().add(jPanel1);
        pack();
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
    protected void guardarDatos() {
        nombre = nombreCliente.getText();
        apellido = apellidoCliente.getText();
        correo = correoCliente.getText();
        telefono = telefonoCliente.getText();
        ci = CI.getText();

        JOptionPane.showMessageDialog(this,
                "Datos Guardados:\nNombre: " + nombre + "\nApellido: " + apellido +
                        "\nCorreo: " + correo + "\nTeléfono: " + telefono + "\nCI: " + ci,
                "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    // Métodos para configurar los JTextField y JLabel
    protected void configurarCampoTexto(JTextField campo, String placeholder) {
        campo.setText(placeholder);
        campo.setFont(new Font("Arial", Font.PLAIN, 14));
        campo.setForeground(Color.GRAY);
        campo.setBorder(new LineBorder(new Color(100, 149, 237), 2, true));
        campo.setPreferredSize(new Dimension(200, 30)); // Tamaño preferido
    }

    protected void configurarEtiqueta(JLabel etiqueta) {
        etiqueta.setFont(new Font("Arial", Font.BOLD, 14));
        etiqueta.setForeground(new Color(25, 25, 112));
    }

    // Método para restablecer los campos de texto a sus valores iniciales
    protected void resetearCampos() {
        nombreCliente.setText("Ingrese nombre");
        nombreCliente.setForeground(Color.GRAY);

        apellidoCliente.setText("Ingrese apellido");
        apellidoCliente.setForeground(Color.GRAY);

        correoCliente.setText("Ingrese correo");
        correoCliente.setForeground(Color.GRAY);

        telefonoCliente.setText("Ingrese tel");
        telefonoCliente.setForeground(Color.GRAY);

        CI.setText("Ingrese CI");
        CI.setForeground(Color.GRAY);
    }
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
*/

    /**
     * @param args the command line arguments
     
    
    

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
}*/
