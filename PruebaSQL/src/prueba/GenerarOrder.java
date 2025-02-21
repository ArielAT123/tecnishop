package prueba;

import prueba.cosas.RoundRedButton;
import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.sql.Date;
import prueba.ClasesTablas.*;
import prueba.cosas.ExcelGenerator;
import prueba.cosas.RoundRefreshButton;
import prueba.cosas.SQLConsultas;

public class GenerarOrder extends JFrame {
    private JTextField articuloField, marcaField, modeloField, serieField, cedula_Cliente, realizaOrden;
    private JTextArea problemasArea, otrosCablesField;
    private JCheckBox cargadorCheckBox, bateriaCheckBox, cablePoderCheckBox, cableDatosCheckBox;
    private RoundRedButton ordenButton, menuButton, refreshbutton;
    private JFrame previus;
    private VentanaAgregarCLienteJFrame aggCliente = new VentanaAgregarCLienteJFrame(this);
    private Orden orden;
    private ExcelGenerator excelGenerator = new ExcelGenerator();

    public GenerarOrder(JFrame previus) {
        this.previus = previus;
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
        
        inputPanel.add(new JLabel("ORDEN ELABORADA POR: "));
        realizaOrden = new JTextField();
        inputPanel.add(realizaOrden);

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
        otrosCablesField = new JTextArea(" ");
        observacionesPanel.add(otrosCablesField);

        // Panel para los botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        ordenButton = new RoundRedButton("Generar orden");
        menuButton = new RoundRedButton("Regresar al menú");
        refreshbutton= new RoundRedButton("Nueva Orden");
        

        ordenButton.addActionListener(e -> {
            String cedula = cedula_Cliente.getText();
            if (pruebaSQL.idClienteExiste(cedula)) {
                orden = crearOrden();
                ExcelGenerator.generarOrdenExcel(orden.getQuienRealiza(), orden.getObservacion(), orden.getEquipo(), orden.getProblemas(), orden.getCliente(), orden.getIdOrden());
                
            } else {
                aggCliente.btnRegresarMenu.setText("Regresar");
                aggCliente.CI.setText(cedula); // Pasar la cédula al JFrame de AgregarCliente
                aggCliente.setVisible(true);
                this.setVisible(false);
            }
        });

        menuButton.addActionListener(e -> {
            setVisible(false); // Oculta la ventana actual
            previus.setVisible(true); // Muestra el menú principal
        });
        
        refreshbutton.addActionListener(e -> {
            resetearCampos();
        });
        

        buttonPanel.add(ordenButton);
        buttonPanel.add(menuButton);
        buttonPanel.add(refreshbutton);        


        // Panel contenedor para observaciones y botones
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(observacionesPanel, BorderLayout.CENTER);
        southPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(southPanel, BorderLayout.SOUTH);

        // Centrar la ventana en la pantalla
        setLocationRelativeTo(null);

        ((AbstractDocument) cedula_Cliente.getDocument()).setDocumentFilter(new LimitFilter(10));
    }

    public Orden crearOrden() {
        int idCliente = pruebaSQL.getIdClienteXCedula(cedula_Cliente.getText());
        Equipo equipo = new Equipo(articuloField.getText(), marcaField.getText(), modeloField.getText(), serieField.getText(), idCliente);
        Observacion observacion = new Observacion(equipo, cargadorCheckBox.isSelected(), bateriaCheckBox.isSelected(), cablePoderCheckBox.isSelected(), cableDatosCheckBox.isSelected(), otrosCablesField.getText());
        ProblemaEquipo problemas = new ProblemaEquipo(equipo, problemasArea.getText());
        String idOrden = pruebaSQL.insertOrden(equipo.getId_equipo(), Date.valueOf(java.time.LocalDate.now()));

        return new Orden(idOrden, SQLConsultas.getClienteFromDatabase(idCliente), equipo, observacion, Date.valueOf(java.time.LocalDate.now()), realizaOrden.getText(), problemas);
    }

    public void resetearCampos() {
        articuloField.setText(""); // Limpiar campo de artículo
        marcaField.setText("");     // Limpiar campo de marca
        modeloField.setText("");    // Limpiar campo de modelo
        serieField.setText("");     // Limpiar campo de número de serie
        cedula_Cliente.setText(""); // Limpiar campo de cédula
        realizaOrden.setText("");
        problemasArea.setText("");  // Limpiar área de problemas reportados
        otrosCablesField.setText(""); // Limpiar área de otros cables

        cargadorCheckBox.setSelected(false);    // Desmarcar cargador
        bateriaCheckBox.setSelected(false);     // Desmarcar batería
        cablePoderCheckBox.setSelected(false);  // Desmarcar cable de poder
        cableDatosCheckBox.setSelected(false);  // Desmarcar cable de datos
    }
}