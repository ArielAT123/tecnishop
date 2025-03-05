/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba.ClasesTablas;

import java.util.ArrayList;
import prueba.cosas.Utilitaria;
import SQL_Clases.pruebaSQL;

/**
 *
 * @author Ernesto
 */
public class ProblemaEquipo {
    private ArrayList<String> problemas;
    private Equipo equipo;
    
    public ProblemaEquipo(Equipo equipo, String c){
        this.problemas=Utilitaria.convertir_a_Lista(c);
        this.equipo=equipo;
        pruebaSQL.insertProblema(this);
    }

    public ArrayList<String> getProblemas() {
        return problemas;
    }

    public Equipo getEquipo() {
        return equipo;
    }
    
}
