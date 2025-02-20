/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba.cosas;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.nio.file.Files;
import prueba.ClasesTablas.Cliente;
import prueba.ClasesTablas.Equipo;
import prueba.ClasesTablas.Observacion;
import prueba.ClasesTablas.ProblemaEquipo;

public class ExcelGenerator {
    public static void generarOrdenExcel(String quienRealiza, Observacion observacion, Equipo equipo, ProblemaEquipo problems, Cliente cliente, int id_Orden){
        try {
            // Construir ruta del escritorio correctamente
            String escritorio = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "Reportes_Generados";
            Files.createDirectories(Paths.get(escritorio));
            
            String nombreCliente = cliente.getNombreCompleto(); // Reemplazar con el nombre real del cliente
            String telefono = cliente.getTelefono();  
            String cedula = cliente.getCedula();
            String mail = cliente.getCorreo();
            String fechaActual = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            String idOrden = "ORD"+id_Orden;
            String cargador = observacion.getCargador() ? "Sí" : "No";
            String bateria = observacion.getBateria() ? "Sí" : "No";
            String cablePoder = observacion.getCable_poder() ? "Sí" : "No";
            String cableDatos = observacion.getCable_datos() ? "Sí" : "No";
            String otros = observacion.getOtros();
            
            
       
            
            String nuevoNombreArchivo = "REPORTE_" + nombreCliente + "_" + fechaActual + ".xlsx";
            
            Path archivoOrigen = Paths.get("reporte.xlsx");
            Path archivoDestino = Paths.get(escritorio, nuevoNombreArchivo);
            
            // Copiar el archivo original antes de modificarlo
            Files.copy(archivoOrigen, archivoDestino, StandardCopyOption.REPLACE_EXISTING);	
            FileInputStream fileInputStream = new FileInputStream(archivoDestino.toFile());
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            fileInputStream.close();
            
            Sheet sheet = workbook.getSheetAt(0); // Asegúrate de que la hoja es la correcta
            //EQUIPO
            escribirCelda(sheet,10,0,equipo.getArticulo());
            escribirCelda(sheet,10,2,equipo.getMarca());
            escribirCelda(sheet,10,4,equipo.getModelo());
            escribirCelda(sheet,10,6,equipo.getNumero_serie());
            
            // Escribir datos en las celdas correspondientes
            escribirCelda(sheet, 6, 1, nombreCliente); // B7
            escribirCelda(sheet, 33, 9, nombreCliente); // J34
            escribirCelda(sheet, 7, 1, telefono); // B8
            escribirCelda(sheet, 6, 6, cedula); // G7
            escribirCelda(sheet, 7, 6, mail); // G8
            escribirCelda(sheet, 6, 9, fechaActual); // J7
            escribirCelda(sheet, 2, 10, idOrden); // K3
            escribirCelda(sheet, 18, 4, cargador); // E19
            escribirCelda(sheet, 18, 6, bateria); // G19
            escribirCelda(sheet, 18, 8, cablePoder); // I19
            escribirCelda(sheet, 18, 10, cableDatos); // K19
            escribirCelda(sheet, 20, 4, otros); // E21
            escribirCelda(sheet, 33, 1, quienRealiza); // B34
            for(int i=0; i<problems.getProblemas().size(); i++) {
                escribirCelda(sheet, 10+i, 8, problems.getProblemas().get(i)); 
            }
            
            FileOutputStream fileOutputStream = new FileOutputStream(archivoDestino.toFile());
            workbook.write(fileOutputStream);
            workbook.close();
            fileOutputStream.close();
            
            System.out.println("✅ Archivo generado correctamente en: " + archivoDestino);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void escribirCelda(Sheet sheet, int rowNum, int colNum, String valor) {
        Row row = sheet.getRow(rowNum);
        if (row == null) row = sheet.createRow(rowNum);
        
        Cell cell = row.getCell(colNum);
        if (cell == null) cell = row.createCell(colNum);
        
        cell.setCellValue(valor);
    }
}