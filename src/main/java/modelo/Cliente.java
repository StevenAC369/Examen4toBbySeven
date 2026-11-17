/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Steven
 */
public class Cliente {
    private int idCliente;
    private String Cedula;
    private String Nombre;
    private String Correo;
    private String Telefono;
    
    private String Ciudad;
    private String TipoCliente;
    private String Estado;
    
    
    private Vehiculo vehiculo;
    

    public Cliente(String Cedula, String Nombre, String Correo, String Telefono, Vehiculo vehiculo, String Ciudad, String TipoCliente, String Estado) {
        this.Cedula = Cedula;
        this.Nombre = Nombre;
        this.Correo = Correo;
        this.Telefono = Telefono;
        this.vehiculo = vehiculo;
        this.Ciudad = Ciudad;
        this.TipoCliente = TipoCliente;
        this.Estado = Estado;
   
    }

    public Cliente(int idCliente, String Cedula, String Nombre, String Correo, String Telefono, String Ciudad, String TipoCliente, String Estado) {
        this.idCliente = idCliente;
        this.Cedula = Cedula;
        this.Nombre = Nombre;
        this.Correo = Correo;
        this.Telefono = Telefono;
        this.Ciudad = Ciudad;
        this.TipoCliente = TipoCliente;
        this.Estado = Estado;
    }
    public Cliente()
    {
        
    }
    
    

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String Cedula) {
        this.Cedula = Cedula;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getCiudad() {
        return Ciudad;
    }

    public String getTipoCliente() {
        return TipoCliente;
    }

    public String getEstado() {
        return Estado;
    }

    public void setCiudad(String Ciudad) {
        this.Ciudad = Ciudad;
    }

    public void setTipoCliente(String TipoCliente) {
        this.TipoCliente = TipoCliente;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
    

    @Override
    public String toString() {
        return "Cliente{" + "idCliente=" + idCliente + ", Cedula=" + Cedula + ", Nombre=" + Nombre + ", Correo=" + Correo + ", Telefono=" + Telefono + ", vehiculo=" + vehiculo.getPlaca() + '}';
    }

}
