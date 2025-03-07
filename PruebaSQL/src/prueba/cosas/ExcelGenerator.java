/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba.cosas;


import java.awt.Desktop;
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
    public static void generarOrdenExcel(String quienRealiza, Observacion observacion, Equipo equipo, ProblemaEquipo problems, Cliente cliente, String id_Orden) {
try {
            // Rutas de salida
            String escritorioExcel = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "Reportes_Generados";
            String outputDirPdf = "C:\\Users\\ernes\\OneDrive\\Escritorio\\Reportes_PDF";
            
            // Crear directorios si no existen
            Files.createDirectories(Paths.get(escritorioExcel));
            Files.createDirectories(Paths.get(outputDirPdf));
            
            // Generar datos del reporte
            String nombreCliente = cliente.getNombreCompleto();
            String telefono = cliente.getTelefono();  
            String cedula = cliente.getCedula();
            String mail = cliente.getCorreo();
            String idOrden = "ORD-" + id_Orden;
            String cargador = observacion.getCargador() ? "Sí" : "No";
            String bateria = observacion.getBateria() ? "Sí" : "No";
            String cablePoder = observacion.getCable_poder() ? "Sí" : "No";
            String cableDatos = observacion.getCable_datos() ? "Sí" : "No";
            String otros = observacion.getOtros();
            String fecha = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            
            String nuevoNombreArchivo = "REPORTE_" + nombreCliente + "_" + id_Orden + ".xlsx";
            Path archivoOrigen = Paths.get("reporte.xlsx");
            Path archivoDestino = Paths.get(escritorioExcel, nuevoNombreArchivo);
            
            // Copiar y modificar el archivo Excel
            Files.copy(archivoOrigen, archivoDestino, StandardCopyOption.REPLACE_EXISTING);	
            FileInputStream fileInputStream = new FileInputStream(archivoDestino.toFile());
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            fileInputStream.close();

            Sheet sheet = workbook.getSheetAt(0);
            int cambio = 0;          

            for (int cont = 0; cont < 2; cont++) {
                escribirCelda(sheet, 10 + cambio, 0, equipo.getArticulo());
                escribirCelda(sheet, 10 + cambio, 2, equipo.getMarca());
                escribirCelda(sheet, 10 + cambio, 4, equipo.getModelo());
                escribirCelda(sheet, 10 + cambio, 6, equipo.getNumero_serie());
                escribirCelda(sheet, 6 + cambio, 1, nombreCliente);
                escribirCelda(sheet, 33 + cambio, 9, nombreCliente);
                escribirCelda(sheet, 7 + cambio, 1, telefono);
                escribirCelda(sheet, 6 + cambio, 6, cedula);
                escribirCelda(sheet, 7 + cambio, 6, mail);
                escribirCelda(sheet, 6 + cambio, 9, fecha);
                escribirCelda(sheet, 2 + cambio, 10, idOrden);
                escribirCelda(sheet, 18 + cambio, 4, cargador);
                escribirCelda(sheet, 18 + cambio, 6, bateria);
                escribirCelda(sheet, 18 + cambio, 8, cablePoder);
                escribirCelda(sheet, 18 + cambio, 10, cableDatos);
                escribirCelda(sheet, 20 + cambio, 4, otros);
                escribirCelda(sheet, 33 + cambio, 1, quienRealiza);
                for (int i = 0; i < problems.getProblemas().size(); i++) {
                    escribirCelda(sheet, 10 + i + cambio, 8, problems.getProblemas().get(i)); 
                }
                cambio += 39;
            }
            
            FileOutputStream fileOutputStream = new FileOutputStream(archivoDestino.toFile());
            workbook.write(fileOutputStream);
            workbook.close();
            fileOutputStream.close();
            
            System.out.println("✅ Archivo Excel generado en: " + archivoDestino);

            // Convertir a PDF directamente usando el método estático
            boolean conversionExitosa = XlsxToPdfConverter.convertXlsxToPdf(archivoDestino.toString(), outputDirPdf);
            
            if (conversionExitosa) {
                // Abrir la carpeta de PDFs
                if (Desktop.isDesktopSupported()) {
                    Desktop desktop = Desktop.getDesktop();
                    File carpetaPdf = new File(outputDirPdf);
                    if (carpetaPdf.exists()) {
                        desktop.open(carpetaPdf);
                    } else {
                        System.out.println("❌ La carpeta no existe: " + outputDirPdf);
                    }
                } else {
                    System.out.println("❌ Desktop no es soportado en este sistema.");
                }
            } else {
                System.out.println("❌ Falló la conversión a PDF.");
            }

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