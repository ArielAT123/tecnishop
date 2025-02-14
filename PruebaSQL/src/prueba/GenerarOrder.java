package prueba;

import prueba.ClasesTablas.RoundRedButton;
import prueba.ClasesTablas.LimitFilter;
import prueba.ClasesTablas.Equipo;
import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import javax.swing.text.AbstractDocument;
import prueba.ClasesTablas.Observacion;

public class GenerarOrder extends JFrame {
    private JTextField articuloField, marcaField, modeloField, serieField, cedula_Cliente;
    private JTextArea problemasArea, otrosCablesField;
    private JCheckBox cargadorCheckBox, bateriaCheckBox, cablePoderCheckBox, cableDatosCheckBox;
    private RoundRedButton ordenButton, menuButton;
    private MenuFrame menuFrame;
    private VentanaAgregarCLienteJFrame aggCliente = new VentanaAgregarCLienteJFrame(this);
    // Datos para la orden
    private Equipo equipo;
    private Observacion observacion;
    private Date fecha = Date.valueOf(java.time.LocalDate.now());
    public GenerarOrder(MenuFrame menuFrame) {
        this.menuFrame = menuFrame;
        setTitle("Formulario de Equipo");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        

        // Panel para los campos de entrada
        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("CEDULA:"));
        cedula_Cliente = new JTextField();
        inputPanel.add(cedula_Cliente);

        inputPanel.add(new JLabel("ARTICULO:"));
        articuloField = new JTextField();
        inputPanel.add(articuloField);

        inputPanel.add(new JLabel("MARCA:"));
        marcaField = new JTextField();
        inputPanel.add(marcaField);

        inputPanel.add(new JLabel("MODELO:"));
        modeloField = new JTextField();
        inputPanel.add(modeloField);

        inputPanel.add(new JLabel("N° SERIE:"));
        serieField = new JTextField();
        inputPanel.add(serieField);

        add(inputPanel, BorderLayout.NORTH);

        // Panel para los problemas reportados
        JPanel problemasPanel = new JPanel(new BorderLayout());
        problemasPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        problemasPanel.add(new JLabel("Problemas reportados:"), BorderLayout.NORTH);
        problemasArea = new JTextArea();
        problemasPanel.add(new JScrollPane(problemasArea), BorderLayout.CENTER);
        add(problemasPanel, BorderLayout.CENTER);

        // Panel para las observaciones (CheckBoxes)
        JPanel observacionesPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        observacionesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        cargadorCheckBox = new JCheckBox("Cargador");
        observacionesPanel.add(cargadorCheckBox);

        bateriaCheckBox = new JCheckBox("Batería");
        observacionesPanel.add(bateriaCheckBox);

        cablePoderCheckBox = new JCheckBox("Cable poder");
        observacionesPanel.add(cablePoderCheckBox);

        cableDatosCheckBox = new JCheckBox("Cable datos");
        observacionesPanel.add(cableDatosCheckBox);

        observacionesPanel.add(new JLabel("Otros:"));
        otrosCablesField = new JTextArea();
        observacionesPanel.add(otrosCablesField);

        // Panel para los botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        ordenButton = new RoundRedButton("Realizar orden");
        menuButton = new RoundRedButton("Regresar al menú");

        ordenButton.addActionListener(e -> {
            String cedula = cedula_Cliente.getText();
            if (pruebaSQL.idClienteExiste(cedula)) {
                obtenerDatos();
                crearOrden();
                JOptionPane.showMessageDialog(this, "Orden guardada con éxito.", "Información", JOptionPane.INFORMATION_MESSAGE);
                resetearCampos();
            } else {
                aggCliente.btnRegresarMenu.setText("Regresar");
                aggCliente.CI.setText(cedula); // Pasar la cédula al JFrame de AgregarCliente
                aggCliente.setVisible(true);
                this.setVisible(false);
            }
        });

        menuButton.addActionListener(e -> {
            setVisible(false); // Oculta la ventana actual
            menuFrame.setVisible(true); // Muestra el menú principal
        });

        buttonPanel.add(ordenButton);
        buttonPanel.add(menuButton);

        // Panel contenedor para observaciones y botones
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(observacionesPanel, BorderLayout.CENTER);
        southPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(southPanel, BorderLayout.SOUTH);

        // Centrar la ventana en la pantalla
        setLocationRelativeTo(null);
        
        ((AbstractDocument) cedula_Cliente.getDocument()).setDocumentFilter(new LimitFilter(10));

    }

    public void crearOrden() {
        pruebaSQL.insertOrden(equipo.getId_equipo(), fecha);
    }

    public void obtenerDatos() {
        String 
                articulo = articuloField.getText(),
                marca = marcaField.getText(),
                modelo = modeloField.getText(),
                serie = serieField.getText(),
                otros = otrosCablesField.getText();
        
        Boolean 
                cargador=cargadorCheckBox.isSelected(),
                bateria= bateriaCheckBox.isSelected(),
                cable_poder=cablePoderCheckBox.isSelected() ,
                cable_datos= cableDatosCheckBox.isSelected();

        int idCliente = pruebaSQL.getIdClienteXCedula(cedula_Cliente.getText());
        equipo = new Equipo(articulo, marca, modelo, serie, idCliente);
        observacion = new Observacion(equipo,cargador,bateria,cable_poder,cable_datos,otros);
    }
    public void resetearCampos() {
    
        articuloField.setText(""); // Limpiar campo de artículo
        marcaField.setText("");     // Limpiar campo de marca
        modeloField.setText("");    // Limpiar campo de modelo
        serieField.setText("");     // Limpiar campo de número de serie
        cedula_Cliente.setText(""); // Limpiar campo de cédula
    
        problemasArea.setText("");  // Limpiar área de problemas reportados
        otrosCablesField.setText(""); // Limpiar área de otros cables

        cargadorCheckBox.setSelected(false);    // Desmarcar cargador
        bateriaCheckBox.setSelected(false);     // Desmarcar batería
        cablePoderCheckBox.setSelected(false);  // Desmarcar cable de poder
        cableDatosCheckBox.setSelected(false);  // Desmarcar cable de datos
    }
}