/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Vehiculo;

/**
 *
 * @author Steven
 */
public class VehiculoDAO {
    public VehiculoDAO() throws SQLException
    {
        
    }
    
    public List<Vehiculo> listar() throws SQLException {
        List<Vehiculo> vehiculos = new ArrayList<>();
        String sql = """
            SELECT idvehiculo, placa
            FROM vehiculos
            ORDER BY placa
            """;
        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Vehiculo vehiculo = new Vehiculo(
                    rs.getInt("idvehiculo"),
                    rs.getString("placa"))
                ;
                vehiculos.add(vehiculo);
            }
        }
        return vehiculos;
    }
    
    
    public void AgregarVehiculo(String placa) throws SQLException
    {
        String sql = "INSERT INTO vehiculos (placa) VALUES (?)";
        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, placa);
            ps.executeUpdate();
        }
        
    }
}
