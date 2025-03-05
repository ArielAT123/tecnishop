package ClasesFrames;

import SQL_Clases.pruebaSQL;
import prueba.cosas.RoundRedButton;
import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.sql.Date;
import prueba.ClasesTablas.*;
import prueba.cosas.ExcelGenerator;
import prueba.cosas.ModernFrame;
import SQL_Clases.SQLConsultas;


public class GenerarOrder extends ModernFrame {
    private JTextField articuloField, marcaField, modeloField, serieField, cedula_Cliente, realizaOrden;
    private JTextArea problemasArea, otrosCablesField;
    private JCheckBox cargadorCheckBox, bateriaCheckBox, cablePoderCheckBox, cableDatosCheckBox;
    private RoundRedButton ordenButton, menuButton, refreshButton;
    private JFrame previus;
    private VentanaAgregarCLienteJFrame aggCliente = new VentanaAgregarCLienteJFrame(this);
    private Orden orden;
    private ExcelGenerator excelGenerator = new ExcelGenerator();

    public GenerarOrder(JFrame previus) {
        super();
        this.previus = previus;
        setTitle("Formulario de Equipo");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel principal para todo el contenido del formulario (body)
        JPanel bodyPanel = new JPanel(new BorderLayout(10, 10));
        bodyPanel.setBackground(new Color(245, 245, 245)); // Fondo gris claro
        bodyPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

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

        bodyPanel.add(inputPanel, BorderLayout.NORTH);

        // Panel para los problemas reportados
        JPanel problemasPanel = new JPanel(new BorderLayout());
        problemasPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        problemasPanel.add(new JLabel("Problemas reportados:"), BorderLayout.NORTH);
        problemasArea = new JTextArea(5, 20); // Asegurar un tamaño mínimo visible
        problemasArea.setLineWrap(true); // Permitir ajuste de línea
        problemasArea.setWrapStyleWord(true); // Ajuste por palabras
        JScrollPane scrollPane = new JScrollPane(problemasArea); // Añadir scroll
        scrollPane.setPreferredSize(new Dimension(700, 150)); // Tamaño preferido para visibilidad
        problemasPanel.add(scrollPane, BorderLayout.CENTER);
        bodyPanel.add(problemasPanel, BorderLayout.CENTER);

        // Panel para las observaciones y botones
        JPanel southPanel = new JPanel(new BorderLayout());

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
        otrosCablesField = new JTextArea(2, 10); // Tamaño pequeño pero visible
        observacionesPanel.add(new JScrollPane(otrosCablesField));

        southPanel.add(observacionesPanel, BorderLayout.CENTER);

        // Panel para los botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        ordenButton = new RoundRedButton("Generar orden");
        menuButton = new RoundRedButton("Regresar al menú");
        refreshButton = new RoundRedButton("Nueva Orden");

        ordenButton.addActionListener(e -> {
            String cedula = cedula_Cliente.getText();
            if (pruebaSQL.idClienteExiste(cedula)) {
                orden = crearOrden();
                ExcelGenerator.generarOrdenExcel(orden.getQuienRealiza(), orden.getObservacion(), 
                        orden.getEquipo(), orden.getProblemas(), orden.getCliente(), orden.getIdOrden());
            } else {
                aggCliente.btnRegresarMenu.setText("Regresar");
                aggCliente.CI.setText(cedula);
                aggCliente.setVisible(true);
                this.setVisible(false);
            }
        });

        menuButton.addActionListener(e -> {
            setVisible(false);
            previus.setVisible(true);
        });

        refreshButton.addActionListener(e -> {
            resetearCampos();
        });

        buttonPanel.add(ordenButton);
        buttonPanel.add(menuButton);
        buttonPanel.add(refreshButton);

        southPanel.add(buttonPanel, BorderLayout.SOUTH);

        bodyPanel.add(southPanel, BorderLayout.SOUTH);

        // Agregar el bodyPanel al CENTER del ModernFrame
        add(bodyPanel, BorderLayout.CENTER);

        // Centrar la ventana
        setLocationRelativeTo(null);

        // Filtro para limitar la cédula a 10 caracteres
        ((AbstractDocument) cedula_Cliente.getDocument()).setDocumentFilter(new LimitFilter(10));
    }

    public Orden crearOrden() {
        int idCliente = pruebaSQL.getIdClienteXCedula(cedula_Cliente.getText());
        Equipo equipo = new Equipo(articuloField.getText(), marcaField.getText(), modeloField.getText(), serieField.getText(), idCliente);
        Observacion observacion = new Observacion(equipo, cargadorCheckBox.isSelected(), bateriaCheckBox.isSelected(), 
                cablePoderCheckBox.isSelected(), cableDatosCheckBox.isSelected(), otrosCablesField.getText());
        ProblemaEquipo problemas = new ProblemaEquipo(equipo, problemasArea.getText());
        String idOrden = pruebaSQL.insertOrden(equipo.getId_equipo(), Date.valueOf(java.time.LocalDate.now()),realizaOrden.getText());

        return new Orden(idOrden, SQLConsultas.getClienteFromDatabase(idCliente), equipo, observacion, 
                Date.valueOf(java.time.LocalDate.now()), realizaOrden.getText(), problemas);
    }

    public void resetearCampos() {
        articuloField.setText("");
        marcaField.setText("");
        modeloField.setText("");
        serieField.setText("");
        cedula_Cliente.setText("");
        realizaOrden.setText("");
        problemasArea.setText("");
        otrosCablesField.setText("");

        cargadorCheckBox.setSelected(false);
        bateriaCheckBox.setSelected(false);
        cablePoderCheckBox.setSelected(false);
        cableDatosCheckBox.setSelected(false);
    }
}