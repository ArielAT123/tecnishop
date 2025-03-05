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
    public static void generarOrdenExcel(String quienRealiza, Observacion observacion, Equipo equipo, ProblemaEquipo problems, Cliente cliente, String id_Orden){
        try {
            // Construir ruta del escritorio correctamente
            String escritorio = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "Reportes_Generados";
            Files.createDirectories(Paths.get(escritorio));
            
            String nombreCliente = cliente.getNombreCompleto(); // Reemplazar con el nombre real del cliente
            String telefono = cliente.getTelefono();  
            String cedula = cliente.getCedula();
            String mail = cliente.getCorreo();
            String idOrden = "ORD-"+id_Orden;
            String cargador = observacion.getCargador() ? "Sí" : "No";
            String bateria = observacion.getBateria() ? "Sí" : "No";
            String cablePoder = observacion.getCable_poder() ? "Sí" : "No";
            String cableDatos = observacion.getCable_datos() ? "Sí" : "No";
            String otros = observacion.getOtros();
            String fecha = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            
            
       
            
            String nuevoNombreArchivo = "REPORTE_" + nombreCliente + "_" + id_Orden + ".xlsx";
            
            Path archivoOrigen = Paths.get("reporte.xlsx");
            Path archivoDestino = Paths.get(escritorio, nuevoNombreArchivo);
            
            // Copiar el archivo original antes de modificarlo
            Files.copy(archivoOrigen, archivoDestino, StandardCopyOption.REPLACE_EXISTING);	
            FileInputStream fileInputStream = new FileInputStream(archivoDestino.toFile());
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            fileInputStream.close();

            Sheet sheet = workbook.getSheetAt(0); // Asegúrate de que la hoja es la correcta
            int cambio = 0;          

            for(int cont=0; cont<2; cont++){
            //EQUIPO
            escribirCelda(sheet,10+cambio,0,equipo.getArticulo());
            escribirCelda(sheet,10+cambio,2,equipo.getMarca());
            escribirCelda(sheet,10+cambio,4,equipo.getModelo());
            escribirCelda(sheet,10+cambio,6,equipo.getNumero_serie());
            
            // Escribir datos en las celdas correspondientes
            escribirCelda(sheet, 6+cambio, 1, nombreCliente); // B7
            escribirCelda(sheet, 33+cambio, 9, nombreCliente); // J34
            escribirCelda(sheet, 7+cambio, 1, telefono); // B8
            escribirCelda(sheet, 6+cambio, 6, cedula); // G7
            escribirCelda(sheet, 7+cambio, 6, mail); // G8
            escribirCelda(sheet, 6+cambio, 9, fecha); // J7
            escribirCelda(sheet, 2+cambio, 10, idOrden); // K3
            escribirCelda(sheet, 18+cambio, 4, cargador); // E19
            escribirCelda(sheet, 18+cambio, 6, bateria); // G19
            escribirCelda(sheet, 18+cambio, 8, cablePoder); // I19
            escribirCelda(sheet, 18+cambio, 10, cableDatos); // K19
            escribirCelda(sheet, 20+cambio, 4, otros); // E21
            escribirCelda(sheet, 33+cambio, 1, quienRealiza); // B34
            for(int i=0; i<problems.getProblemas().size(); i++) {
                escribirCelda(sheet, 10+i+cambio, 8, problems.getProblemas().get(i)); 
            }
            cambio+=35;
            }
            
            FileOutputStream fileOutputStream = new FileOutputStream(archivoDestino.toFile());
            workbook.write(fileOutputStream);
            workbook.close();
            fileOutputStream.close();
            
            System.out.println("✅ Archivo generado correctamente en: " + archivoDestino);
        } catch (IOException e) {
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