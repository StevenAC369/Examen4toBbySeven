/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import datos.VehiculoDAO;
import java.sql.SQLException;
import java.util.List;
import modelo.Vehiculo;

/**
 *
 * @author Steven
 */
public class VehiculosServicio {
     private final VehiculoDAO vehiculoDAO;

    public VehiculosServicio() throws SQLException {
        this.vehiculoDAO = new VehiculoDAO();
    }

    /**
     *
     * @return
     * @throws SQLException
     */
    public List<Vehiculo> listar() throws SQLException {
        return vehiculoDAO.listar();
    }
    
    public void Guardar(String placa) throws SQLException 
    {
        vehiculoDAO.AgregarVehiculo(placa);
    }
}
