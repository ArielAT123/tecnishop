/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba.ClasesTablas;

import prueba.pruebaSQL;

/**
 *
 * @author Ernesto
 */
public class Equipo {
    private pruebaSQL prueba; 
    private String articulo,marca, modelo,numero_serie;
    private int id_Cliente;
    private int id_equipo;
    public Equipo(String articulo, String marca, String modelo, String numero_serie, int id_cliente) {
        this.articulo = articulo;
        this.marca = marca;
        this.modelo = modelo;
        this.numero_serie = numero_serie;
        this.id_Cliente = id_cliente;
        //id_equipo= prueba.insertEquipo(this.articulo, this.id_Cliente, this.marca,  this.modelo, this.numero_serie);
    }

    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNumero_serie() {
        return numero_serie;
    }

    public void setNumero_serie(String numero_serie) {
        this.numero_serie = numero_serie;
    }

    public int getId_cliente() {
        return id_Cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_Cliente = id_cliente;
    }

    public int getId_equipo() {
        return id_equipo;
    }
    
}
