package prueba.cosas;

import org.apache.poi.ss.usermodel.*;
import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import prueba.ClasesTablas.*;

public class ExcelGenerator {
    public static void generarOrdenExcel(String quienRealiza, Observacion observacion, 
            Equipo equipo, ProblemaEquipo problems, Cliente cliente, String id_Orden) {
        
        Workbook workbook = null;
        FileOutputStream fileOutputStream = null;
        Path archivoFinal = null;
        
        try {
            // 1. Ruta ABSOLUTA al archivo plantilla
            Path rutaPlantilla = Paths.get("src/resources/reporte.xlsx").toAbsolutePath();
            
            if (!Files.exists(rutaPlantilla)) {
                throw new FileNotFoundException(
                    "ERROR: No se encontró reporte.xlsx en:\n" + rutaPlantilla + "\n\n" +
                    "SOLUCIÓN:\n" +
                    "1. Crear carpeta 'src/resources/' en tu proyecto\n" +
                    "2. Poner el archivo reporte.xlsx dentro\n" +
                    "3. Verificar que el nombre sea EXACTO (sin mayúsculas)"
                );
            }

            // 2. Directorios de salida
            Path salidaExcel = Paths.get(System.getProperty("user.home"), "Reportes_Generados");
            Path salidaPDF = Paths.get(System.getProperty("user.home"), "Reportes_PDF");
            Files.createDirectories(salidaExcel);
            Files.createDirectories(salidaPDF);
            
            // 3. Nombre del archivo final
            String nombreBase = "REPORTE_" + cliente.getNombreCompleto()
                                    .replaceAll("[^a-zA-Z0-9-_]", "") + "_" + id_Orden;
            archivoFinal = salidaExcel.resolve(nombreBase + ".xlsx");
            
            // 4. Copiar plantilla y abrir para modificación
            Files.copy(rutaPlantilla, archivoFinal, StandardCopyOption.REPLACE_EXISTING);
            workbook = new XSSFWorkbook(new FileInputStream(archivoFinal.toFile()));
            Sheet sheet = workbook.getSheetAt(0);
            
            // 5. Escribir datos (2 copias)
            int cambio = 0;
            String fecha = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            
            for (int cont = 0; cont < 2; cont++) {
                escribirCelda(sheet, 10 + cambio, 0, equipo.getArticulo());
                escribirCelda(sheet, 10 + cambio, 2, equipo.getMarca());
                escribirCelda(sheet, 10 + cambio, 4, equipo.getModelo());
                escribirCelda(sheet, 10 + cambio, 6, equipo.getNumero_serie());
                escribirCelda(sheet, 6 + cambio, 1, cliente.getNombreCompleto());
                escribirCelda(sheet, 33 + cambio, 9, cliente.getNombreCompleto());
                escribirCelda(sheet, 7 + cambio, 1, cliente.getTelefono());
                escribirCelda(sheet, 6 + cambio, 6, cliente.getCedula());
                escribirCelda(sheet, 7 + cambio, 6, cliente.getCorreo());
                escribirCelda(sheet, 6 + cambio, 9, fecha);
                escribirCelda(sheet, 2 + cambio, 10, "ORD-" + id_Orden);
                escribirCelda(sheet, 18 + cambio, 4, observacion.getCargador() ? "Sí" : "No");
                escribirCelda(sheet, 18 + cambio, 6, observacion.getBateria() ? "Sí" : "No");
                escribirCelda(sheet, 18 + cambio, 8, observacion.getCable_poder() ? "Sí" : "No");
                escribirCelda(sheet, 18 + cambio, 10, observacion.getCable_datos() ? "Sí" : "No");
                escribirCelda(sheet, 20 + cambio, 4, observacion.getOtros());
                escribirCelda(sheet, 33 + cambio, 1, quienRealiza);
                
                if (problems != null) {
                    for (int i = 0; i < problems.getProblemas().size(); i++) {
                        escribirCelda(sheet, 10 + i + cambio, 8, problems.getProblemas().get(i)); 
                    }
                }
                cambio += 39;
            }
            
            // 6. Guardar cambios en Excel
            fileOutputStream = new FileOutputStream(archivoFinal.toFile());
            workbook.write(fileOutputStream);
            
            // 7. Cerrar recursos del Excel
            workbook.close();
            fileOutputStream.close();
            
            // 8. Convertir a PDF usando XlsxToPdfConverter (que ya incluye la apertura del directorio)
            boolean conversionExitosa = XlsxToPdfConverter.convertXlsxToPdf(
                archivoFinal.toString(), 
                salidaPDF.toString()
            );
            
            // 9. Mostrar mensaje con resultados
            String mensaje = "";
            if (conversionExitosa) {
                mensaje += "\n\n✅ PDF generado en:\n" + salidaPDF.resolve(nombreBase + ".pdf");
            } else {
                mensaje += "\n\n⚠️ No se pudo generar el PDF automáticamente";
            }
            
            JOptionPane.showMessageDialog(null, mensaje, "Éxito", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, 
                "❌ Error crítico:\n" + e.getMessage(),
                "Fallo", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            try {
                if (workbook != null) workbook.close();
                if (fileOutputStream != null) fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    private static void escribirCelda(Sheet sheet, int rowNum, int colNum, String valor) {
        Row row = sheet.getRow(rowNum) != null ? sheet.getRow(rowNum) : sheet.createRow(rowNum);
        Cell cell = row.getCell(colNum) != null ? row.getCell(colNum) : row.createCell(colNum);
        cell.setCellValue(valor != null ? valor : "");
    }
}