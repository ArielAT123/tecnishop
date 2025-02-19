/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba.cosas;

/**
 *
 * @author Ernesto
 */
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import prueba.ClasesTablas.Equipo;
import prueba.ClasesTablas.Observacion;

public class ExcelGenerator {

    public void generarExcel(Equipo equipo, Observacion observacion, String problemasReportados, Date fecha) {
        // Crear un nuevo libro de Excel
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Orden");

        // Crear el estilo para las celdas
        CellStyle headerStyle = workbook.createCellStyle();
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerStyle.setFont(headerFont);

        // Llenar el archivo Excel con los datos
        int rowNum = 0;
        Row row = sheet.createRow(rowNum++);
        row.createCell(3).setCellValue("www.intecnic.com");

        row = sheet.createRow(rowNum++);
        row.createCell(3).setCellValue("CDLA. CONDOR MZ. G VILLA 13");

        row = sheet.createRow(rowNum++);
        row.createCell(3).setCellValue("Teléfonos: 0990545798 (WhatsApp)");

        row = sheet.createRow(rowNum++);
        row.createCell(0).setCellValue("R.U.C. 0917526758001");
        row.createCell(3).setCellValue("email: tecnishop.imp@gmail.com");

        row = sheet.createRow(rowNum++);
        row.createCell(0).setCellValue("CLIENTE:");
        row.createCell(5).setCellValue("C.I./R.U.C.:");
        row.createCell(8).setCellValue("FECHA:");
        row.createCell(9).setCellValue(fecha.toString());

        row = sheet.createRow(rowNum++);
        row.createCell(0).setCellValue("TELEFONO:");
        row.createCell(5).setCellValue("E-MAIL:");

        row = sheet.createRow(rowNum++);
        row.createCell(0).setCellValue("ARTICULO");
        row.createCell(2).setCellValue("MARCA");
        row.createCell(4).setCellValue("MODELO");
        row.createCell(6).setCellValue("No. SERIE");
        row.createCell(8).setCellValue("PROBLEMAS REPORTADOS");

        row = sheet.createRow(rowNum++);
        row.createCell(0).setCellValue(equipo.getArticulo());
        row.createCell(2).setCellValue(equipo.getMarca());
        row.createCell(4).setCellValue(equipo.getModelo());
        row.createCell(6).setCellValue(equipo.getNumero_serie());
        row.createCell(8).setCellValue(problemasReportados);

        row = sheet.createRow(rowNum++);
        row.createCell(0).setCellValue("OBSERVACIONES");

        row = sheet.createRow(rowNum++);
        row.createCell(2).setCellValue("CARGADOR");
        row.createCell(4).setCellValue("BATERIA");
        row.createCell(6).setCellValue("CABLE PODER");
        row.createCell(8).setCellValue("CABLE DATOS");

        row = sheet.createRow(rowNum++);
        row.createCell(2).setCellValue(observacion.getCargador() ? "Sí" : "No");
        row.createCell(4).setCellValue(observacion.getBateria() ? "Sí" : "No");
        row.createCell(6).setCellValue(observacion.getCable_poder() ? "Sí" : "No");
        row.createCell(8).setCellValue(observacion.getCable_datos() ? "Sí" : "No");

        row = sheet.createRow(rowNum++);
        row.createCell(2).setCellValue("OTROS:");
        row.createCell(4).setCellValue(observacion.getOtros());

        // Escribir el archivo Excel
        try (FileOutputStream fileOut = new FileOutputStream("Orden.xlsx")) {
            workbook.write(fileOut);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al generar el archivo Excel.", e);
        }
    }
        
        public static void main(String[] args) {
        // Crear un equipo
        Equipo equipo = new Equipo("Laptop", "Dell", "Inspiron 15", "12345XYZ", 1);

        // Crear una observación asociada al equipo
        Observacion observacion = new Observacion(equipo, true, false, true, false, "Tiene rayones en la carcasa");

        // Problemas reportados
        String problemasReportados = "No enciende;Pantalla con líneas";

        // Fecha actual
        Date fecha = new Date(System.currentTimeMillis());

        // Generar el archivo Excel
        ExcelGenerator generator = new ExcelGenerator();
        generator.generarExcel(equipo, observacion, problemasReportados, fecha);

        System.out.println("Archivo Excel generado correctamente.");
    }

}
