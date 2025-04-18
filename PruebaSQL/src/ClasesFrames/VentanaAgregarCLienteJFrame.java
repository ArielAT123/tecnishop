package ClasesFrames;

import SQL_Clases.pruebaSQL;
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
import javax.swing.BorderFactory;
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
    protected javax.swing.JLabel titleLabel;
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
                        || telefono.equals("Ingrese tl")
                        || ci.equals("Ingrese CI")
        );
    }

    protected void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        InsertButton = new RoundRedButton("Insertar");
        btnRegresarMenu = new RoundRedButton("Regresar al Menú");
        nombreCliente = new javax.swing.JTextField();
        apellidoCliente = new javax.swing.JTextField();
        correoCliente = new javax.swing.JTextField();
        telefonoCliente = new javax.swing.JTextField();
        CI = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel("Nombre:");
        jLabel2 = new javax.swing.JLabel("Apellido:");
        jLabel3 = new javax.swing.JLabel("Correo:");
        jLabel4 = new javax.swing.JLabel("Teléfono:");
        jLabel5 = new javax.swing.JLabel("CI:");
        titleLabel = new javax.swing.JLabel("Registrar Nuevo Cliente");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registro Cliente");

        // Estilo del Panel principal
        jPanel1.setBackground(Color.BLACK); // Fondo negro
        jPanel1.setBorder(new LineBorder(Color.LIGHT_GRAY, 2)); // Borde gris claro

        // Estilo para las etiquetas y campos de texto
        Font labelFont = new Font("Arial", Font.BOLD, 14); // Negrita para etiquetas
        Font textFont = new Font("Arial", Font.BOLD, 14); // Negrita para campos de texto
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

        // Configuración de los JTextField
        configurarCampoTexto(nombreCliente, "Ingrese nombre", textFont, textBackground, textForeground);
        configurarCampoTexto(apellidoCliente, "Ingrese apellido", textFont, textBackground, textForeground);
        configurarCampoTexto(correoCliente, "Ingrese correo", textFont, textBackground, textForeground);
        configurarCampoTexto(telefonoCliente, "Ingrese tl", textFont, textBackground, textForeground);
        configurarCampoTexto(CI, "Ingrese CI", textFont, textBackground, textForeground);

        // Agregar el evento de focus a los JTextField
        agregarEventoFocus(nombreCliente, "Ingrese nombre");
        agregarEventoFocus(apellidoCliente, "Ingrese apellido");
        agregarEventoFocus(correoCliente, "Ingrese correo");
        agregarEventoFocus(telefonoCliente, "Ingrese tl");
        agregarEventoFocus(CI, "Ingrese CI");

        // Limitar el número de caracteres en los campos de texto
        ((AbstractDocument) telefonoCliente.getDocument()).setDocumentFilter(new LimitFilter(10));
        ((AbstractDocument) CI.getDocument()).setDocumentFilter(new LimitFilter(10));

        
        

        // Evento para capturar los datos al presionar el botón "Insertar"
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
        gbc.insets = new Insets(10, 10, 10, 10); // Espaciado entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL; // Ajustar componentes horizontalmente

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
        jPanel1.add(nombreCliente, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        jPanel1.add(jLabel2, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        jPanel1.add(apellidoCliente, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        jPanel1.add(jLabel3, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        jPanel1.add(correoCliente, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        jPanel1.add(jLabel4, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        jPanel1.add(telefonoCliente, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.EAST;
        jPanel1.add(jLabel5, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        jPanel1.add(CI, gbc);

        // Botones centrados
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2; // Ocupar dos columnas
        gbc.fill = GridBagConstraints.NONE; // No ajustar el tamaño del botón
        gbc.anchor = GridBagConstraints.CENTER; // Centrar el botón
        jPanel1.add(InsertButton, gbc);

        gbc.gridy = 7;
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
                    campo.setForeground(Color.WHITE); // Texto blanco
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (campo.getText().trim().isEmpty()) {
                    campo.setText(placeholder); // Restaura el texto si está vacío
                    campo.setForeground(Color.GRAY); // Placeholder en gris
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
        if ("Ingrese correo".equals(correo)) {
            correo = "NO HAY CORREO";
        }
        if ("Ingrese CI".equals(ci)) {
            ci = "NO CI";
        }

        JOptionPane.showMessageDialog(this,
                "Datos Guardados:\nNombre: " + nombre + "\nApellido: " + apellido +
                        "\nCorreo: " + correo + "\nTeléfono: " + telefono + "\nCI: " + ci,
                "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    // Métodos para configurar los JTextField y JLabel
    protected void configurarCampoTexto(JTextField campo, String placeholder, Font font, Color background, Color foreground) {
        campo.setText(placeholder);
        campo.setFont(font); // Negrita
        campo.setForeground(Color.GRAY); // Placeholder en gris
        campo.setBackground(background); // Fondo gris oscuro
        campo.setBorder(new LineBorder(Color.LIGHT_GRAY, 2, true)); // Borde gris claro
        campo.setPreferredSize(new Dimension(200, 30)); // Tamaño preferido
    }

    protected void configurarEtiqueta(JLabel etiqueta, Font font, Color color) {
        etiqueta.setFont(font); // Negrita
        etiqueta.setForeground(color); // Texto blanco
    }

    // Método para restablecer los campos de texto a sus valores iniciales
    protected void resetearCampos() {
        nombreCliente.setText("Ingrese nombre");
        nombreCliente.setForeground(Color.GRAY);

        apellidoCliente.setText("Ingrese apellido");
        apellidoCliente.setForeground(Color.GRAY);

        correoCliente.setText("Ingrese correo");
        correoCliente.setForeground(Color.GRAY);

        telefonoCliente.setText("Ingrese tl");
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
