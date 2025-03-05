/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba.cosas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Ernesto
 */
public class Utilitaria {
    public static boolean esNumeroValido(String texto) {
    return texto.matches("-?\\d+(\\.\\d+)?");
}
    
    public static boolean esEnteroValido(String texto) {
    return texto.matches("-?\\d+");
}
    public static ArrayList<String> convertir_a_Lista(String c){
        String[] lineas = c.split("\\r?\\n");
        List<String> lista = new ArrayList<>(Arrays.asList(lineas));
        return (ArrayList<String>) lista;
    }

}
