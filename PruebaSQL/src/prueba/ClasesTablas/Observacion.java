/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba.ClasesTablas;

import SQL_Clases.pruebaSQL;

/**
 *
 * @author Ernesto
 */
public class Observacion {
    private Integer id;
    private Boolean cargador;
    private Boolean bateria;
    private Boolean cable_poder;
    private Boolean cable_datos;
    private String otros;
    private Integer equipo_id;
    
    public Observacion(Equipo equipo, Boolean cargador, Boolean bateria, Boolean cable_poder, Boolean cable_datos, String otros) {
        this.equipo_id = equipo.getId_equipo();
        this.cargador = cargador;
        this.bateria = bateria;
        this.cable_poder = cable_poder;
        this.cable_datos = cable_datos;
        this.otros = otros;
        this.id = pruebaSQL.insertObservacion(this.equipo_id, this.cargador, this.bateria, this.cable_poder, this.cable_datos, this.otros);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getCargador() {
        return cargador;
    }

    public void setCargador(Boolean cargador) {
        this.cargador = cargador;
    }

    public Boolean getBateria() {
        return bateria;
    }

    public void setBateria(Boolean bateria) {
        this.bateria = bateria;
    }

    public Boolean getCable_poder() {
        return cable_poder;
    }

    public void setCable_poder(Boolean cable_poder) {
        this.cable_poder = cable_poder;
    }

    public Boolean getCable_datos() {
        return cable_datos;
    }

    public void setCable_datos(Boolean cable_datos) {
        this.cable_datos = cable_datos;
    }

    public String getOtros() {
        return otros;
    }

    public void setOtros(String otros) {
        this.otros = otros;
    }

    public Integer getEquipo_id() {
        return equipo_id;
    }

    public void setEquipo_id(Integer equipo_id) {
        this.equipo_id = equipo_id;
    }
    
    
}
