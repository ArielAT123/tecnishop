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
        bodyPanel.setBackground(Color.BLACK); // Fondo negro, como en TablaNegocio
        bodyPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel para los campos de entrada
        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        inputPanel.setBackground(Color.BLACK); // Fondo negro
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Estilo para las etiquetas (letras blancas, fuente Arial en negrita)
        Font labelFont = new Font("Comic Sans MS", Font.BOLD, 14);
        Color labelColor = Color.WHITE;

        // Estilo para los campos de texto (fondo gris oscuro, letras blancas, fuente Arial)
        Font textFont = new Font("Comic Sans MS", Font.PLAIN, 14);
        Color textBackground = new Color(50, 50, 50); // Gris oscuro
        Color textForeground = Color.WHITE;

        JLabel cedulaLabel = new JLabel("CEDULA:");
        cedulaLabel.setForeground(labelColor);
        cedulaLabel.setFont(labelFont);
        inputPanel.add(cedulaLabel);
        cedula_Cliente = new JTextField();
        cedula_Cliente.setBackground(textBackground);
        cedula_Cliente.setForeground(textForeground);
        cedula_Cliente.setFont(textFont);
        inputPanel.add(cedula_Cliente);

        JLabel articuloLabel = new JLabel("ARTICULO:");
        articuloLabel.setForeground(labelColor);
        articuloLabel.setFont(labelFont);
        inputPanel.add(articuloLabel);
        articuloField = new JTextField();
        articuloField.setBackground(textBackground);
        articuloField.setForeground(textForeground);
        articuloField.setFont(textFont);
        inputPanel.add(articuloField);

        JLabel marcaLabel = new JLabel("MARCA:");
        marcaLabel.setForeground(labelColor);
        marcaLabel.setFont(labelFont);
        inputPanel.add(marcaLabel);
        marcaField = new JTextField();
        marcaField.setBackground(textBackground);
        marcaField.setForeground(textForeground);
        marcaField.setFont(textFont);
        inputPanel.add(marcaField);

        JLabel modeloLabel = new JLabel("MODELO:");
        modeloLabel.setForeground(labelColor);
        modeloLabel.setFont(labelFont);
        inputPanel.add(modeloLabel);
        modeloField = new JTextField();
        modeloField.setBackground(textBackground);
        modeloField.setForeground(textForeground);
        modeloField.setFont(textFont);
        inputPanel.add(modeloField);

        JLabel serieLabel = new JLabel("N° SERIE:");
        serieLabel.setForeground(labelColor);
        serieLabel.setFont(labelFont);
        inputPanel.add(serieLabel);
        serieField = new JTextField();
        serieField.setBackground(textBackground);
        serieField.setForeground(textForeground);
        serieField.setFont(textFont);
        inputPanel.add(serieField);

        JLabel realizaOrdenLabel = new JLabel("ORDEN ELABORADA POR:");
        realizaOrdenLabel.setForeground(labelColor);
        realizaOrdenLabel.setFont(labelFont);
        inputPanel.add(realizaOrdenLabel);
        realizaOrden = new JTextField();
        realizaOrden.setBackground(textBackground);
        realizaOrden.setForeground(textForeground);
        realizaOrden.setFont(textFont);
        inputPanel.add(realizaOrden);

        bodyPanel.add(inputPanel, BorderLayout.NORTH);

        // Panel para los problemas reportados
        JPanel problemasPanel = new JPanel(new BorderLayout());
        problemasPanel.setBackground(Color.BLACK); // Fondo negro
        problemasPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel problemasLabel = new JLabel("Problemas reportados:");
        problemasLabel.setForeground(labelColor);
        problemasLabel.setFont(labelFont);
        problemasPanel.add(problemasLabel, BorderLayout.NORTH);
        problemasArea = new JTextArea(5, 20);
        problemasArea.setLineWrap(true);
        problemasArea.setWrapStyleWord(true);
        problemasArea.setBackground(textBackground); // Fondo gris oscuro
        problemasArea.setForeground(textForeground); // Texto blanco
        problemasArea.setFont(textFont); // Fuente Arial
        JScrollPane scrollPane = new JScrollPane(problemasArea);
        scrollPane.setPreferredSize(new Dimension(700, 150));
        scrollPane.setBackground(Color.BLACK); // Fondo del JScrollPane
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY)); // Borde gris claro
        problemasPanel.add(scrollPane, BorderLayout.CENTER);
        bodyPanel.add(problemasPanel, BorderLayout.CENTER);

        // Panel para las observaciones y botones
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.setBackground(Color.BLACK); // Fondo negro

        // Panel para las observaciones (CheckBoxes)
        JPanel observacionesPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        observacionesPanel.setBackground(Color.BLACK); // Fondo negro
        observacionesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        cargadorCheckBox = new JCheckBox("Cargador");
        cargadorCheckBox.setForeground(labelColor); // Texto blanco
        cargadorCheckBox.setBackground(Color.BLACK); // Fondo negro
        cargadorCheckBox.setFont(labelFont); // Fuente Arial en negrita
        observacionesPanel.add(cargadorCheckBox);

        bateriaCheckBox = new JCheckBox("Batería");
        bateriaCheckBox.setForeground(labelColor);
        bateriaCheckBox.setBackground(Color.BLACK);
        bateriaCheckBox.setFont(labelFont);
        observacionesPanel.add(bateriaCheckBox);

        cablePoderCheckBox = new JCheckBox("Cable poder");
        cablePoderCheckBox.setForeground(labelColor);
        cablePoderCheckBox.setBackground(Color.BLACK);
        cablePoderCheckBox.setFont(labelFont);
        observacionesPanel.add(cablePoderCheckBox);

        cableDatosCheckBox = new JCheckBox("Cable datos");
        cableDatosCheckBox.setForeground(labelColor);
        cableDatosCheckBox.setBackground(Color.BLACK);
        cableDatosCheckBox.setFont(labelFont);
        observacionesPanel.add(cableDatosCheckBox);

        JLabel otrosLabel = new JLabel("Otros:");
        otrosLabel.setForeground(labelColor);
        otrosLabel.setFont(labelFont);
        observacionesPanel.add(otrosLabel);
        otrosCablesField = new JTextArea(2, 10);
        otrosCablesField.setBackground(textBackground); // Fondo gris oscuro
        otrosCablesField.setForeground(textForeground); // Texto blanco
        otrosCablesField.setFont(textFont); // Fuente Arial
        JScrollPane otrosScrollPane = new JScrollPane(otrosCablesField);
        otrosScrollPane.setBackground(Color.BLACK);
        otrosScrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        observacionesPanel.add(otrosScrollPane);

        southPanel.add(observacionesPanel, BorderLayout.CENTER);

        // Panel para los botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(Color.BLACK); // Fondo negro
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
        String idOrden = pruebaSQL.insertOrden(equipo.getId_equipo(), Date.valueOf(java.time.LocalDate.now()), realizaOrden.getText());

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