/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba.cosas;

/**
 *
 * @author Ernesto
 */
import SQL_Clases.pruebaSQL;
import prueba.ClasesTablas.Cliente;
import prueba.ClasesTablas.Equipo;
import prueba.ClasesTablas.Observacion;
import prueba.ClasesTablas.Orden;
import prueba.ClasesTablas.ProblemaEquipo;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.sql.Date;

public class ExcelDataProcessor {

    public static String correctNumber(String number) {
        if (number == null || number.trim().isEmpty()) {
            return null;
        }
        String cleanedNumber = number.replaceAll("\\s+", "").toUpperCase();
        if (cleanedNumber.length() > 10) {
            cleanedNumber = cleanedNumber.substring(0, 10);
        }
        if (cleanedNumber.startsWith("9")) {
            cleanedNumber = "0" + cleanedNumber;
            if (cleanedNumber.length() > 10) {
                cleanedNumber = cleanedNumber.substring(0, 10);
            }
        }
        return cleanedNumber;
    }

    // Método para parsear fechas en formato AAAAMMDD
    public static Date parseExcelDate(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            return null;
        }
        String trimmedDate = dateStr.trim();
        try {
            // Verificar si tiene formato AAAAMMDD (8 dígitos)
            if (trimmedDate.length() == 8 && trimmedDate.matches("\\d{8}")) {
                String year = trimmedDate.substring(0, 4); // Primeros 4 dígitos: año
                String month = trimmedDate.substring(4, 6); // Siguientes 2: mes
                String day = trimmedDate.substring(6, 8);  // Últimos 2: día
                String formattedDate = year + "-" + month + "-" + day; // yyyy-MM-dd
                return Date.valueOf(formattedDate); // Convierte directamente a java.sql.Date
            } else {
                // Intentar parsear como yyyy-MM-dd por si acaso
                return Date.valueOf(trimmedDate);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error parsing date '" + dateStr + "': " + e.getMessage());
            return null;
        }
    }

    public static void processTxtData(String resourcePath) {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        ExcelDataProcessor.class.getResourceAsStream(resourcePath)))) {
            if (br == null) {
                throw new IOException("Resource not found: " + resourcePath);
            }

            String line;
            boolean isHeader = true;

            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                String[] row = line.split(";", -1);

                String fechaStr = row[0] != null && !row[0].isEmpty() ? row[0].toUpperCase() : null;
                String clienteNombre = row[2] != null && !row[2].isEmpty() ? row[2].toUpperCase() : null;
                String cedulaRaw = row[3] != null && !row[3].isEmpty() ? row[3].toUpperCase() : null;
                String telefonoRaw = row[4] != null && !row[4].isEmpty() ? row[4].toUpperCase() : null;
                String correo = row[5] != null && !row[5].isEmpty() ? row[5].toUpperCase() : null;
                String articulo = row[6] != null && !row[6].isEmpty() ? row[6].toUpperCase() : null;
                String marca = row[7] != null && !row[7].isEmpty() ? row[7].toUpperCase() : null;
                String modelo = row[8] != null && !row[8].isEmpty() ? row[8].toUpperCase() : null;
                String numeroSerie = row[9] != null && !row[9].isEmpty() ? row[9].toUpperCase() : null;
                String problemaReportado = row[10] != null && !row[10].isEmpty() ? row[10].toUpperCase() : null;
                String problema2 = row[11] != null && !row[11].isEmpty() ? row[11].toUpperCase() : null;
                String problema3 = row[12] != null && !row[12].isEmpty() ? row[12].toUpperCase() : null;
                String problema4 = row[13] != null && !row[13].isEmpty() ? row[13].toUpperCase() : null;
                String problema5 = row[14] != null && !row[14].isEmpty() ? row[14].toUpperCase() : null;
                String problema6 = row[15] != null && !row[15].isEmpty() ? row[15].toUpperCase() : null;
                String cargadorStr = row[16] != null && !row[16].isEmpty() ? row[16].toUpperCase() : null;
                String bateriaStr = row[17] != null && !row[17].isEmpty() ? row[17].toUpperCase() : null;
                String cablePoderStr = row[18] != null && !row[18].isEmpty() ? row[18].toUpperCase() : null;
                String cableDatosStr = row[19] != null && !row[19].isEmpty() ? row[19].toUpperCase() : null;
                String otros = row[20] != null && !row[20].isEmpty() ? row[20].toUpperCase() : null;
                String quienRealiza = row[21] != null && !row[21].isEmpty() ? row[21].toUpperCase() : null;

                String cedula = correctNumber(cedulaRaw);
                String telefono = correctNumber(telefonoRaw);
                Date fecha = parseExcelDate(fechaStr);
                if (fecha == null) {
                    System.out.println("Skipping row due to invalid date: " + fechaStr);
                    continue;
                }

                int clienteId;
                Cliente cliente;
                if (cedula != null && pruebaSQL.idClienteExiste(cedula)) {
                    clienteId = pruebaSQL.getIdClienteXCedula(cedula);
                    cliente = new Cliente(clienteId, clienteNombre, telefono, correo, cedula);
                } else {
                    if (cedula != null) {
                        pruebaSQL.insertCliente(clienteNombre, "", telefono, correo, cedula);
                        clienteId = pruebaSQL.getIdClienteXCedula(cedula);
                        cliente = new Cliente(clienteId, clienteNombre, telefono, correo, cedula);
                    } else {
                        System.out.println("Skipping row due to missing cedula: " + clienteNombre);
                        continue;
                    }
                }

                Equipo equipo = new Equipo(articulo != null ? articulo : "UNKNOWN", marca, modelo, numeroSerie, clienteId);
                int equipoId = equipo.getId_equipo();

                Boolean cargador = "SI".equals(cargadorStr);
                Boolean bateria = "SI".equals(bateriaStr);
                Boolean cablePoder = "SI".equals(cablePoderStr);
                Boolean cableDatos = "SI".equals(cableDatosStr);
                Observacion observacion = new Observacion(equipo, cargador, bateria, cablePoder, cableDatos, otros);

                StringBuilder problemasStr = new StringBuilder();
                if (problemaReportado != null) problemasStr.append(problemaReportado).append(";");
                if (problema2 != null) problemasStr.append(problema2).append(";");
                if (problema3 != null) problemasStr.append(problema3).append(";");
                if (problema4 != null) problemasStr.append(problema4).append(";");
                if (problema5 != null) problemasStr.append(problema5).append(";");
                if (problema6 != null) problemasStr.append(problema6).append(";");

                ProblemaEquipo problemaEquipo = null;
                if (problemasStr.length() > 0) {
                    String problemasConcat = problemasStr.substring(0, problemasStr.length() - 1);
                    problemaEquipo = new ProblemaEquipo(equipo, problemasConcat);
                }

                String realiza = quienRealiza;
                String ordenId = pruebaSQL.insertOrden(equipoId, fecha, realiza);
                Orden orden = new Orden(ordenId, cliente, equipo, observacion, fecha, realiza, problemaEquipo);
                System.out.println("Orden creada con ID: " + orden.getIdOrden());
            }
        } catch (IOException e) {
            System.out.println("Error reading TXT file: " + e.getMessage());
        }
    }
    
    
    public static void processTxtProductos(String resourcePath) {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        ExcelDataProcessor.class.getResourceAsStream(resourcePath)))) {
            if (br == null) {
                throw new IOException("Resource not found: " + resourcePath);
            }

            String line;
            boolean isHeader = true;

            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                String[] row = line.split(";", -1);

                String codigo = row[0] != null && !row[0].isEmpty() ? row[0].trim() : null;
                String nombre = row[1] != null && !row[1].isEmpty() ? row[1].trim() : null;
                String informacionAdicional = row[2] != null && !row[2].isEmpty() ? row[2].trim() : null;
                String valorStr = row[3] != null && !row[3].isEmpty() ? row[3].trim().replace(",", ".") : null;

                Double valor = null;
                if (valorStr != null) {
                    try {
                        valor = Double.parseDouble(valorStr);
                    } catch (NumberFormatException e) {
                        System.out.println("Error parsing value for product: " + nombre);
                        continue;
                    }
                }

                // Insertar el producto en la base de datos usando pruebaSQL.insertProducto
                pruebaSQL.insertProducto(nombre, informacionAdicional, valor, null, null, 0, codigo);
            }
        } catch (IOException e) {
            System.out.println("Error reading TXT file: " + e.getMessage());
        }
    }

    /*public static void main(String[] args) {
        processTxtProductos("/prueba/cosas/imagenes/productos.txt");
    }*/
}

    /*public static void main(String[] args) {
        String resourcePath = "/prueba/cosas/imagenes/historialtxt.txt";
        processTxtData(resourcePath);
    }*/
