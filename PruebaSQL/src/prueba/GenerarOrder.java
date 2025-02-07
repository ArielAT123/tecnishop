package prueba;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import prueba.MenuFrame;
import prueba.MenuFrame;
import prueba.RoundButton;
import prueba.RoundButton;
import prueba.pruebaSQL;
import prueba.pruebaSQL;

public class GenerarOrder extends JFrame {
    private JTextField articuloField, marcaField, modeloField, serieField, otrosCablesField;
    private JTextArea problemasArea;
    private JCheckBox cargadorCheckBox, bateriaCheckBox, cablePoderCheckBox, cableDatosCheckBox;
    private JButton ordenButton, menuButton;
    private pruebaSQL prueba;
    private MenuFrame menuFrame;

    public GenerarOrder(MenuFrame menuFrame) {
        this.menuFrame = menuFrame;
        setTitle("Formulario de Equipo");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10)); // Añadir márgenes entre componentes

        // Panel para los campos de entrada
        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 10, 10)); // Añadir espacios entre celdas
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Añadir márgenes
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

        inputPanel.add(new JLabel("Otros cables:"));
        otrosCablesField = new JTextField();
        inputPanel.add(otrosCablesField);

        add(inputPanel, BorderLayout.NORTH);

        // Panel para los problemas reportados
        JPanel problemasPanel = new JPanel(new BorderLayout());
        problemasPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Añadir márgenes
        problemasPanel.add(new JLabel("Problemas reportados:"), BorderLayout.NORTH);
        problemasArea = new JTextArea();
        problemasPanel.add(new JScrollPane(problemasArea), BorderLayout.CENTER);
        add(problemasPanel, BorderLayout.CENTER);

        // Panel para las observaciones
        JPanel observacionesPanel = new JPanel(new GridLayout(1, 4, 10, 10)); // Añadir espacios entre celdas
        observacionesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Añadir márgenes
        cargadorCheckBox = new JCheckBox("Cargador");
        observacionesPanel.add(cargadorCheckBox);

        bateriaCheckBox = new JCheckBox("Batería");
        observacionesPanel.add(bateriaCheckBox);

        cablePoderCheckBox = new JCheckBox("Cable poder");
        observacionesPanel.add(cablePoderCheckBox);

        cableDatosCheckBox = new JCheckBox("Cable datos");
        observacionesPanel.add(cableDatosCheckBox);

        add(observacionesPanel, BorderLayout.SOUTH);

        // Panel para los botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10)); // Centrar botones y añadir espacios
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Añadir márgenes

        ordenButton = new RoundButton("Realizar orden");
        menuButton = new RoundButton("Regresar al menú");

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
        add(buttonPanel, BorderLayout.SOUTH);

        // Centrar la ventana en la pantalla
        setLocationRelativeTo(null);
    }

    // Clase para crear botones redondeados
   
}