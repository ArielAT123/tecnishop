/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba.ClasesTablas;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Ernesto
 */
public class ProblemasEquipo {
    private ArrayList<String> problemas;
    
   public static ArrayList<String> convertir(String c) {
        String[] problemas = c.split(";");
        return new ArrayList<>(Arrays.asList(problemas)); // Convierte el array a ArrayList
   }
   
   public ProblemasEquipo(String c){
       this.problemas=convertir(c);
   }
}
