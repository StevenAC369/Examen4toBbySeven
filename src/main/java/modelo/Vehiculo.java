/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Steven
 */
public class Vehiculo {

    private int idvehiculo;
    private String placa;

    public int getIdvehiculo() {
        return idvehiculo;
    }

    public void setIdvehiculo(int idvehiculo) {
        this.idvehiculo = idvehiculo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Vehiculo(int idvehiculo, String placa) {
        this.idvehiculo = idvehiculo;
        this.placa = placa;
    }

    @Override
    public String toString() {
        return "Vehiculo{" + "idvehiculo=" + idvehiculo + ", placa=" + placa + '}';
    }
    
}
