/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba.ClasesTablas;

/**
 *
 * @author Ernesto
 */
public class Cliente {
    private int id; 
    private String nombreCompleto;
    private String telefono;
    private String correo;
    private String cedula;

    public Cliente(int id, String nombreCompleto, String telefono, String correo, String cedula) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.correo = correo;
        this.cedula = cedula;
    }

    public int getId() {
        return id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public String getCedula() {
        return cedula;
    }
    
    
}
