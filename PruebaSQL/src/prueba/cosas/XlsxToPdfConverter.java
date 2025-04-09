package prueba.cosas;

import java.io.*;
import java.nio.file.*;
import java.awt.Desktop;

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
        String libreOfficePath = "C:\\Program Files\\LibreOffice\\program\\soffice.exe";

        // Construir el comando
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
            
            // Leer la salida del proceso
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("✅ Conversión a PDF exitosa. Archivo guardado en: " + outputDir);
                
                // =============================================
                // NUEVO CÓDIGO PARA ABRIR EL DIRECTORIO DE SALIDA
                // =============================================
                try {
                    if (Desktop.isDesktopSupported()) {
                        Desktop.getDesktop().open(outputDirectory);
                    } else {
                        // Métodos alternativos por sistema operativo
                        String os = System.getProperty("os.name").toLowerCase();
                        
                        if (os.contains("win")) {
                            // Windows
                            new ProcessBuilder("explorer.exe", outputDir).start();
                        } else if (os.contains("mac")) {
                            // macOS
                            new ProcessBuilder("open", outputDir).start();
                        } else if (os.contains("nix") || os.contains("nux")) {
                            // Linux
                            new ProcessBuilder("xdg-open", outputDir).start();
                        }
                    }
                } catch (Exception e) {
                    System.err.println("⚠️ No se pudo abrir el directorio automáticamente: " + e.getMessage());
                    System.out.println("Puedes acceder manualmente a: " + outputDir);
                }
                
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