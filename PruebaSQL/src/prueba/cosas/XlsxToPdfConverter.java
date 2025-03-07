/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba.cosas;

/**
 *
 * @author Ernesto
 */
import java.io.*;
import java.nio.file.*;

public class XlsxToPdfConverter {

    public static boolean convertXlsxToPdf(String xlsxPath, String outputDir) {
        File xlsxFile = new File(xlsxPath);
        if (!xlsxFile.exists() || !xlsxFile.isFile()) {
            System.err.println("❌ El archivo .xlsx no existe o no es válido: " + xlsxPath);
            return false;
        }

        File outputDirectory = new File(outputDir);
        try {
            Files.createDirectories(outputDirectory.toPath());
        } catch (IOException e) {
            System.err.println("❌ Error al crear el directorio de salida: " + outputDir);
            e.printStackTrace();
            return false;
        }

        // Ruta de LibreOffice
        String libreOfficePath = "C:\\Program Files\\LibreOffice\\program\\soffice.exe"; // Sin comillas extra aquí

        // Construir el comando como una lista de argumentos
        ProcessBuilder pb = new ProcessBuilder(
            libreOfficePath,
            "--headless",
            "--convert-to", "pdf",
            xlsxPath,
            "--outdir", outputDir
        );

        try {
            pb.redirectErrorStream(true);
            Process process = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("✅ Conversión a PDF exitosa. Archivo guardado en: " + outputDir);
                return true;
            } else {
                System.err.println("❌ Error al convertir a PDF. Código de salida: " + exitCode);
                return false;
            }
        } catch (IOException e) {
            System.err.println("❌ Error de entrada/salida al ejecutar el comando: " + e.getMessage());
            e.printStackTrace();
            return false;
        } catch (InterruptedException e) {
            System.err.println("❌ El proceso fue interrumpido: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /*public static void main(String[] args) {
        String xlsxPath = "C:\\Users\\ernes\\OneDrive\\Escritorio\\orden.xlsx";
        String outputDir = "C:\\Users\\ernes\\OneDrive\\Escritorio";
        boolean success = XlsxToPdfConverter.convertXlsxToPdf(xlsxPath, outputDir);
        if (success) {
            System.out.println("Conversión completada con éxito.");
        } else {
            System.out.println("Falló la conversión.");
        }
    }*/
}