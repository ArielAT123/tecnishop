/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba.ClasesTablas;

/**
 *
 * @author Ernesto
 */


import java.sql.Date;

public class Orden {
    private String idOrden;
    private Cliente cliente;
    private Equipo equipo;
    private Observacion observacion;
    private Date fecha;
    private String quienRealiza;
    private ProblemaEquipo problemas;

    public Orden(String idOrden, Cliente cliente, Equipo equipo, Observacion observacion, Date fecha, String quienRealiza, ProblemaEquipo problemas) {
        this.idOrden = idOrden;
        this.cliente = cliente;
        this.equipo = equipo;
        this.observacion = observacion;
        this.fecha = fecha;
        this.quienRealiza = quienRealiza;
        this.problemas = problemas;
    }

    // Getters y Setters
    public String getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(String idOrden) {
        this.idOrden = idOrden;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Observacion getObservacion() {
        return observacion;
    }

    public void setObservacion(Observacion observacion) {
        this.observacion = observacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getQuienRealiza() {
        return quienRealiza;
    }

    public void setQuienRealiza(String quienRealiza) {
        this.quienRealiza = quienRealiza;
    }

    public ProblemaEquipo getProblemas() {
        return problemas;
    }

    public void setProblemas(ProblemaEquipo problemas) {
        this.problemas = problemas;
    }
}
