/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba;

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

}
