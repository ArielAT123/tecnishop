package prueba;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class GenerarOrder extends JFrame {
    private JTextField articuloField, marcaField, modeloField, serieField, cedula_Cliente;
    private JTextArea problemasArea, otrosCablesField;
    private JCheckBox cargadorCheckBox, bateriaCheckBox, cablePoderCheckBox, cableDatosCheckBox;
    private RoundRedButton ordenButton, menuButton;
    private MenuFrame menuFrame;
    //datos para la orden
    private Equipo equipo;
    private Date fecha; 
    
    private pruebaSQL prueba;
    

    public GenerarOrder(MenuFrame menuFrame) {
        this.menuFrame = menuFrame;
        setTitle("Formulario de Equipo");
        setSize(800, 600); // Aumentar el tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10)); // Añadir márgenes entre componentes

        // Panel para los campos de entrada
        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 10, 10)); // Añadir espacios entre celdas
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Añadir márgenes
        
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
        problemasPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Añadir márgenes
        problemasPanel.add(new JLabel("Problemas reportados:"), BorderLayout.NORTH);
        problemasArea = new JTextArea();
        problemasPanel.add(new JScrollPane(problemasArea), BorderLayout.CENTER);
        add(problemasPanel, BorderLayout.CENTER);

        // Panel para las observaciones (CheckBoxes)
        JPanel observacionesPanel = new JPanel(new GridLayout(2, 2, 10, 10)); // Cambiar a 2 filas y 2 columnas
        observacionesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Añadir márgenes
        cargadorCheckBox = new JCheckBox("Cargador");
        observacionesPanel.add(cargadorCheckBox);

        bateriaCheckBox = new JCheckBox("Batería");
        observacionesPanel.add(bateriaCheckBox);

        cablePoderCheckBox = new JCheckBox("Cable poder");
        observacionesPanel.add(cablePoderCheckBox);

        cableDatosCheckBox = new JCheckBox("Cable datos");
        observacionesPanel.add(cableDatosCheckBox);
        
        observacionesPanel.add(new JLabel("Otros cables:"));
        otrosCablesField = new JTextArea();
        observacionesPanel.add(otrosCablesField);

        // Panel para los botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10)); // Centrar botones y añadir espacios
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Añadir márgenes

        ordenButton = new RoundRedButton("Realizar orden");
        menuButton = new RoundRedButton("Regresar al menú");

        ordenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí puedes agregar la lógica para realizar la orden
                System.out.println("Orden realizada");
            }
        });

        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false); // Oculta la ventana actual
                menuFrame.setVisible(true); // Muestra el menú principal
            }
        });

        buttonPanel.add(ordenButton);
        buttonPanel.add(menuButton);

        // Panel contenedor para observaciones y botones
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(observacionesPanel, BorderLayout.CENTER); // Agregar observaciones al centro
        southPanel.add(buttonPanel, BorderLayout.SOUTH); // Agregar botones al sur

        // Añadir el panel contenedor a la región Sur
        add(southPanel, BorderLayout.SOUTH);

        // Centrar la ventana en la pantalla
        setLocationRelativeTo(null);
    }
    //usar despues de validar que existe un nuevo cliente
    public void obtenerDatos(){
        String articulo=articuloField.getText(),
                marca=marcaField.getText(),
                modelo=modeloField.getText(),
                serie=serieField.getText();
        int idCliente=prueba.getIdClienteXCedula(cedula_Cliente.getText());      
        equipo=new Equipo(articulo,marca,modelo,serie,idCliente);
    }}